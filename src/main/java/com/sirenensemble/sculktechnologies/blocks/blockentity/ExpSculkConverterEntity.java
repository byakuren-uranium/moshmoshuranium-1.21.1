package com.sirenensemble.sculktechnologies.blocks.blockentity;

import com.sirenensemble.sculktechnologies.items.ModItems;
import com.sirenensemble.sculktechnologies.recipe.ConverterRecipes;
import com.sirenensemble.sculktechnologies.recipe.ConverterRecipesInput;
import com.sirenensemble.sculktechnologies.recipe.ModRecipes;
import com.sirenensemble.sculktechnologies.screen.realvamp.ConverterMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.ItemContainerContents;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.items.ItemStackHandler;

import javax.annotation.Nullable;
import java.util.Optional;

public class ExpSculkConverterEntity extends BlockEntity implements MenuProvider {


    public final ItemStackHandler INVENTORY = new ItemStackHandler(3){
        @Override
        protected int getStackLimit(int slot, ItemStack stack) {
            return 64;
        }

        @Override
        protected void onContentsChanged(int slot) {
setChanged();
        if(!level.isClientSide){
            level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
        }
        }
    };

private static final int INPUT_SLOT = 0;
private static final int OUTPUT_SLOT = 1;
private static final int CHARGE_SLOT = 2;

protected final ContainerData data;
private int progress = 0;
private int maxProgress = 80;
private int chargeBus;
private int maxCharge = 50000;

    public ExpSculkConverterEntity(BlockPos pos, BlockState blockState) {
        super(ModBlockEntity.EXPSCULKCONVERTER_BE.get(), pos, blockState);
data = new ContainerData() {
    @Override
    public int get(int i) {
        return switch (i){
            case 0 -> ExpSculkConverterEntity.this.progress;
            case 1 -> ExpSculkConverterEntity.this.maxProgress;
            case 2 -> ExpSculkConverterEntity.this.chargeBus;
            case 3 -> ExpSculkConverterEntity.this.maxCharge;
            default -> 0;
        };
    }
    @Override
    public void set(int i, int i1) {
        switch (i){
            case 0: ExpSculkConverterEntity.this.progress = i1;
            case 1: ExpSculkConverterEntity.this.maxProgress = i1;
            case 2: ExpSculkConverterEntity.this.chargeBus = i1;
            case 3: ExpSculkConverterEntity.this.maxCharge = i1;
        };
    }
    @Override
    public int getCount() {
        return 4;
    }
};
    }

private float rotation;

    public float getRenderingRotation() {
        rotation += 1.0f;
        if(rotation >= 360) {
            rotation = 0;
        }
        return rotation;
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
        tag.putInt("converter_charge", chargeBus);
        tag.putInt("converter_max_charge", maxCharge);
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);
        INVENTORY.deserializeNBT(registries, tag.getCompound("inventory"));
        progress = tag.getInt("converter_progress");
        maxProgress = tag.getInt("converter_max_progress");
        chargeBus = tag.getInt("converter_charge");
        maxCharge = tag.getInt("converter_max_charge");
    }
    @Nullable
    @Override
    public  AbstractContainerMenu createMenu(int i, Inventory inventory, Player player) {
        return new ConverterMenu(i, inventory, this, this.data);
    }

    @Nullable
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    public void tick(Level uwu, BlockPos blockPos, BlockState blockState) {
        if(hasRecipe()) {
            increaseCraftingProgress();
            setChanged(uwu, blockPos, blockState);

            if(hasCraftingFinished()) {
                craftItem();
                resetProgress();
                chargeBus = chargeBus + 1000;
            }
        } else {
            resetProgress();
        }
        if(chargeBus >= maxCharge){
            chargeBus = maxCharge;
        }
        if (INVENTORY.getStackInSlot(INPUT_SLOT).is(ModItems.EXP_BATTERY) && chargeBus != 0 && INVENTORY.getStackInSlot(INPUT_SLOT).getDamageValue() <= INVENTORY.getStackInSlot(INPUT_SLOT).getMaxDamage()) {
            INVENTORY.getStackInSlot(INPUT_SLOT).getItem().setDamage(INVENTORY.getStackInSlot(INPUT_SLOT), INVENTORY.getStackInSlot(INPUT_SLOT).getItem().getDamage(INVENTORY.getStackInSlot(INPUT_SLOT)) - 100);
            if (INVENTORY.getStackInSlot(0).getDamageValue() > 1) {
                chargeBus = chargeBus - 100;
            }
        }
    }


    private void resetProgress() {
        progress = 0;
        maxProgress = 80;
    }

    private boolean hasCraftingFinished() {
        return this.progress >= this.maxProgress;
    }

    private void increaseCraftingProgress() {
        progress++;
    }


    private void craftItem() {
        Optional<RecipeHolder<ConverterRecipes>> recipe = getCurrentRecipe();
        ItemStack output = recipe.get().value().output();

        INVENTORY.extractItem(INPUT_SLOT, 1, false);
        INVENTORY.setStackInSlot(OUTPUT_SLOT, new ItemStack(output.getItem(),
                INVENTORY.getStackInSlot(OUTPUT_SLOT).getCount() + output.getCount()));
    }



    private boolean hasRecipe() {
        Optional<RecipeHolder<ConverterRecipes>> recipe = getCurrentRecipe();
        if(recipe.isEmpty()) {
            return false;
        }

        ItemStack output = recipe.get().value().output();
        return canInsertAmountIntoOutputSlot(output.getCount()) && canInsertItemIntoOutputSlot(output);
    }

    private Optional<RecipeHolder<ConverterRecipes>> getCurrentRecipe() {

        return this.level.getRecipeManager().getRecipeFor(ModRecipes.CONVERTER_TYPE.get(), new ConverterRecipesInput(INVENTORY.getStackInSlot(INPUT_SLOT)), level);
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




    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider pRegistries) {
        return saveWithoutMetadata(pRegistries);
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("Converter");
    }



}
