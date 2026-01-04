package com.sirenensemble.sculktechnologies.blocks.blockentity;

import com.sirenensemble.sculktechnologies.items.ModItems;
import com.sirenensemble.sculktechnologies.recipe.*;
import com.sirenensemble.sculktechnologies.screen.realvamp.InjectorMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.items.ItemStackHandler;
import org.checkerframework.checker.units.qual.C;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class SculkInjectorEntity extends BlockEntity implements MenuProvider {


    public final ItemStackHandler INVENTORY = new ItemStackHandler(4) {
        @Override
        protected int getStackLimit(int slot, ItemStack stack) {
            return 64;
        }

        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
            if (!level.isClientSide) {
                level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
            }
        }
    };
    private int chargeBus;
    private int maxCharge = 25000;
    private static final int INPUT_SLOT = 0;
    private static final int OUTPUT_SLOT = 1;
    private static final int SCULK_SLOT = 2;
    private static final int CHARGE_SLOT = 3;

    protected final ContainerData data;
    private int progress = 0;
    private int maxProgress = 80;


    public SculkInjectorEntity(BlockPos pos, BlockState blockState) {
        super(ModBlockEntity.SCULK_INJECTOR_BE.get(), pos, blockState);
        data = new ContainerData() {
            @Override
            public int get(int i) {
                return switch (i) {
                    case 0 -> SculkInjectorEntity.this.progress;
                    case 1 -> SculkInjectorEntity.this.maxProgress;
                    case 2 -> SculkInjectorEntity.this.chargeBus;
                    case 3 -> SculkInjectorEntity.this.maxCharge;
                    default -> 0;
                };
            }

            @Override
            public void set(int i, int i1) {
                switch (i){
                    case 0: SculkInjectorEntity.this.progress = i1;
                    case 1: SculkInjectorEntity.this.maxProgress = i1;
                    case 2: SculkInjectorEntity.this.chargeBus = i1;
                    case 3: SculkInjectorEntity.this.maxCharge = i1;
                };
            }
            @Override
            public int getCount() {
                return 4;
            }
        };
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("Injector");
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider pRegistries) {
        return saveWithoutMetadata(pRegistries);
    }

    @Override
    public @Nullable AbstractContainerMenu createMenu(int i, Inventory inventory, Player player) {
        return new InjectorMenu(i, inventory, this, this.data);
    }

    public void cleanContent(){
        INVENTORY.setStackInSlot(0, ItemStack.EMPTY);
    }
    public void drops() {
        SimpleContainer inv = new SimpleContainer(INVENTORY.getSlots());
        for(int i = 0; i < INVENTORY.getSlots(); i++) {
            inv.setItem(i, INVENTORY.getStackInSlot(i));
        }

        Containers.dropContents(this.level, this.worldPosition, inv);
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);
        tag.put("inventory", INVENTORY.serializeNBT(registries));
        tag.putInt("converter_progress", progress);
        tag.putInt("converter_max_progress", maxProgress);
        tag.putInt("charge", chargeBus);
        tag.putInt("max_charge", maxCharge);
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);
        INVENTORY.deserializeNBT(registries, tag.getCompound("inventory"));
        progress = tag.getInt("converter_progress");
        maxProgress = tag.getInt("converter_max_progress");
        chargeBus = tag.getInt("charge");
        maxCharge = tag.getInt("max_charge");
    }
    @Nullable
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    public void tick(Level uwu, BlockPos blockPos, BlockState blockState) {
        if(hasRecipe() && hasSculk()) {
            increaseCraftingProgress();
            setChanged(uwu, blockPos, blockState);

            if(hasCraftingFinished()) {
                craftItem();
                chargeBus = chargeBus - 400;
                resetProgress();
            }
        } else {
            resetProgress();
        }
        if(chargeBus >= maxCharge){
            chargeBus = maxCharge;
        }
        if (INVENTORY.getStackInSlot(CHARGE_SLOT).is(Items.EXPERIENCE_BOTTLE) && chargeBus != 2500){
            chargeBus = chargeBus +100;
        }
        if (INVENTORY.getStackInSlot(CHARGE_SLOT).is(ModItems.EXP_BATTERY) && chargeBus != maxCharge && INVENTORY.getStackInSlot(CHARGE_SLOT).getDamageValue() < INVENTORY.getStackInSlot(CHARGE_SLOT).getMaxDamage() - 1){
            INVENTORY.getStackInSlot(CHARGE_SLOT).getItem().setDamage(INVENTORY.getStackInSlot(CHARGE_SLOT), INVENTORY.getStackInSlot(CHARGE_SLOT).getItem().getDamage(INVENTORY.getStackInSlot(CHARGE_SLOT)) + 100);
            chargeBus = chargeBus + 100;
        }

    }

    private boolean hasSculk() {
        return INVENTORY.getStackInSlot(SCULK_SLOT).is(Blocks.SCULK.asItem());
    }

    @Deprecated
    private void getRecipeChargeDecrease() {
            chargeBus--;
            chargeBus--;
            chargeBus--;
            chargeBus--;
            chargeBus--;
    }

    private void increaseCraftingProgress() {
        progress++;
    }

    private void resetProgress() {
        progress = 0;
        maxProgress = 80;
    }
    private boolean hasCraftingFinished() {
        return this.progress >= this.maxProgress;
    }

    private boolean hasRecipe() {
        Optional<RecipeHolder<SculkInjectorRecipes>> recipe = getCurrentRecipe();
        if (recipe.isEmpty()) {
            return false;
        }
        ItemStack output = recipe.get().value().output();
        return canInsertAmountIntoOutputSlot(output.getCount()) && canInsertItemIntoOutputSlot(output);
    }

        private Optional<RecipeHolder<SculkInjectorRecipes>> getCurrentRecipe() {
            return this.level.getRecipeManager().getRecipeFor(ModRecipes.INJECTOR_TYPE.get(), new SculkInjectorRecipesInput(INVENTORY.getStackInSlot(INPUT_SLOT)), level);
        }


    private boolean canInsertItemIntoOutputSlot(ItemStack output) {
        return INVENTORY.getStackInSlot(OUTPUT_SLOT).isEmpty() ||
                INVENTORY.getStackInSlot(OUTPUT_SLOT).getItem() == output.getItem();
    }

    private boolean canInsertAmountIntoOutputSlot(int count) {
        int maxCount = INVENTORY.getStackInSlot(OUTPUT_SLOT).isEmpty() ? 64 : INVENTORY.getStackInSlot(OUTPUT_SLOT).getMaxStackSize();
        int currentCount = INVENTORY.getStackInSlot(OUTPUT_SLOT).getCount();

        return maxCount >= currentCount + count;
    }

    private void craftItem() {
        Optional<RecipeHolder<SculkInjectorRecipes>> recipe = getCurrentRecipe();
        ItemStack output = recipe.get().value().output();

        INVENTORY.extractItem(INPUT_SLOT, 1, false);
        INVENTORY.setStackInSlot(OUTPUT_SLOT, new ItemStack(output.getItem(),
                INVENTORY.getStackInSlot(OUTPUT_SLOT).getCount() + output.getCount()));
    }

}
