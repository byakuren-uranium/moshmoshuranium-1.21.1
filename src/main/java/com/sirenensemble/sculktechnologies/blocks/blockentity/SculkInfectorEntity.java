package com.sirenensemble.sculktechnologies.blocks.blockentity;

import com.sirenensemble.sculktechnologies.blocks.blockstates.SculkInfectorBlock;
import com.sirenensemble.sculktechnologies.items.ModItems;
import com.sirenensemble.sculktechnologies.screen.realvamp.InfectorMenu;
import com.sirenensemble.sculktechnologies.screen.realvamp.InjectorMenu;
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
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.items.ItemStackHandler;
import org.jetbrains.annotations.Nullable;

public class SculkInfectorEntity extends BlockEntity implements MenuProvider {
    public final ItemStackHandler INVENTORY = new ItemStackHandler(1) {
        @Override
        protected int getStackLimit(int slot, ItemStack stack) { return 1;
        }

        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
            if (!level.isClientSide) {
                level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
            }
        }
    };


    private int INPUT = 0;
    private int chargeBus;
    private int maxCharge = 25000;
    protected final ContainerData data;


    public SculkInfectorEntity(BlockPos pos, BlockState blockState) {
        super(ModBlockEntity.SCULK_INFECTOR_BE.get(), pos, blockState);
        data = new ContainerData() {
            @Override
            public int get(int i) {
                return switch (i) {
                    case 0 -> SculkInfectorEntity.this.chargeBus;
                    case 1 -> SculkInfectorEntity.this.maxCharge;
                    default -> 0;
                };
            }

            @Override
            public void set(int i, int i1) {
                switch (i){
                    case 0: SculkInfectorEntity.this.chargeBus = i1;
                    case 1: SculkInfectorEntity.this.maxCharge = i1;
                };
            }
            @Override
            public int getCount() {
                return 2;
            }
        };
    }



    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider pRegistries) {
        return saveWithoutMetadata(pRegistries);
    }

    @Override
    public @Nullable AbstractContainerMenu createMenu(int i, Inventory inventory, Player player) {
        return new InfectorMenu(i, inventory, this, this.data);
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
    public Component getDisplayName() {
        return Component.literal("Sculk Infector");
    }
    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);
        tag.put("inventory", INVENTORY.serializeNBT(registries));
        tag.putInt("charge", chargeBus);
        tag.putInt("max_charge", maxCharge);
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);
        INVENTORY.deserializeNBT(registries, tag.getCompound("inventory"));
        chargeBus = tag.getInt("charge");
        maxCharge = tag.getInt("max_charge");
    }
    @Nullable
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    public void tick(Level uwu, BlockPos blockPos, BlockState blockState) {
        if (INVENTORY.getStackInSlot(0).is(ModItems.EXP_BATTERY) && chargeBus != maxCharge && INVENTORY.getStackInSlot(0).getDamageValue() < INVENTORY.getStackInSlot(0).getMaxDamage() - 1){
            INVENTORY.getStackInSlot(0).getItem().setDamage(INVENTORY.getStackInSlot(0), INVENTORY.getStackInSlot(0).getItem().getDamage(INVENTORY.getStackInSlot(0)) + 100);
            chargeBus = chargeBus + 100;
        }
        if(chargeBus >= maxCharge){
            chargeBus = maxCharge;
        }
    }

}
