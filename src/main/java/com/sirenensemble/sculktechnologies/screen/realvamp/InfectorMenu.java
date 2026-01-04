package com.sirenensemble.sculktechnologies.screen.realvamp;

import com.sirenensemble.sculktechnologies.blocks.ModBlock;
import com.sirenensemble.sculktechnologies.blocks.blockentity.SculkInfectorEntity;
import com.sirenensemble.sculktechnologies.entity.ModEntity;
import com.sirenensemble.sculktechnologies.items.ModItems;
import com.sirenensemble.sculktechnologies.screen.ModMenuTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SculkSpreader;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.neoforged.neoforge.items.SlotItemHandler;


public class InfectorMenu extends AbstractContainerMenu {
    public static SculkInfectorEntity blockEntity;
    private final Level level;
    private final ContainerData data;


    public InfectorMenu(int containerId, Inventory inv, FriendlyByteBuf extraData) {
        this(containerId, inv, inv.player.level().getBlockEntity(extraData.readBlockPos()), new SimpleContainerData(2));
    }
    public InfectorMenu(int containerId, Inventory inv, BlockEntity blockEntity, ContainerData data) {
        super(ModMenuTypes.INFECTOR_MENU.get(), containerId);
        this.blockEntity = ((SculkInfectorEntity) blockEntity);
        this.level = inv.player.level();
        this.data = data;

        addPlayerInventory(inv);
        addPlayerHotbar(inv);

        this.addSlot(new SlotItemHandler(this.blockEntity.INVENTORY, 0, 81, 23){
            @Override
            public boolean mayPlace(ItemStack stack) {
                return stack.is(ModItems.EXP_BATTERY);
            }
        });


        addDataSlots(data);
    }

    public boolean validCharge(){return this.data.get(0) >= 25000;}

    public int getChargeScale(){
        int chargeBus = this.data.get(0);
        int maxCharge = this.data.get(1);
        int chargePixelSize = 170;

        return maxCharge != 0 && chargeBus != 0 ? chargeBus * chargePixelSize / maxCharge : 0;
    }
    public int getChargeBus(){
        return this.data.get(0);
    }
    public int getMaxCharge(){
        return this.data.get(1);
    }

    private static final int HOTBAR_SLOT_COUNT = 9;
    private static final int PLAYER_INVENTORY_ROW_COUNT = 3;
    private static final int PLAYER_INVENTORY_COLUMN_COUNT = 9;
    private static final int PLAYER_INVENTORY_SLOT_COUNT = PLAYER_INVENTORY_COLUMN_COUNT * PLAYER_INVENTORY_ROW_COUNT;
    private static final int VANILLA_SLOT_COUNT = HOTBAR_SLOT_COUNT + PLAYER_INVENTORY_SLOT_COUNT;
    private static final int VANILLA_FIRST_SLOT_INDEX = 0;
    private static final int TE_INVENTORY_FIRST_SLOT_INDEX = VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT;

    private static final int TE_INVENTORY_SLOT_COUNT = 1;
    @Override
    public ItemStack quickMoveStack(Player playerIn, int pIndex) {
        Slot sourceSlot = slots.get(pIndex);
        if (sourceSlot == null || !sourceSlot.hasItem()) return ItemStack.EMPTY;  //EMPTY_ITEM
        ItemStack sourceStack = sourceSlot.getItem();
        ItemStack copyOfSourceStack = sourceStack.copy();

        // Check if the slot clicked is one of the vanilla container slots
        if (pIndex < VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT) {
            // This is a vanilla container slot so merge the stack into the tile inventory
            if (!moveItemStackTo(sourceStack, TE_INVENTORY_FIRST_SLOT_INDEX, TE_INVENTORY_FIRST_SLOT_INDEX
                    + TE_INVENTORY_SLOT_COUNT, false)) {
                return ItemStack.EMPTY;  // EMPTY_ITEM
            }
        } else if (pIndex < TE_INVENTORY_FIRST_SLOT_INDEX + TE_INVENTORY_SLOT_COUNT) {
            // This is a TE slot so merge the stack into the players inventory
            if (!moveItemStackTo(sourceStack, VANILLA_FIRST_SLOT_INDEX, VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT, false)) {
                return ItemStack.EMPTY;
            }
        } else {
            System.out.println("Invalid slotIndex:" + pIndex);
            return ItemStack.EMPTY;
        }
        // If stack size == 0 (the entire stack was moved) set slot contents to null
        if (sourceStack.getCount() == 0) {
            sourceSlot.set(ItemStack.EMPTY);
        } else {
            sourceSlot.setChanged();
        }
        sourceSlot.onTake(playerIn, sourceStack);
        return copyOfSourceStack;
    }


    @Override
    public boolean stillValid(Player player) {
        return stillValid(ContainerLevelAccess.create(level, blockEntity.getBlockPos()), player, ModBlock.SCULK_INFECTOR.get());
    }

    private void addPlayerInventory(Inventory playerInventory) {
        for (int i = 0; i < 3; ++i) {
            for (int l = 0; l < 9; ++l) {
                this.addSlot(new Slot(playerInventory, l + i * 9 + 9, 8 + l * 18, 84 + i * 18));
            }
        }
    }

    private void addPlayerHotbar(Inventory playerInventory) {
        for (int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 142));
        }
    }


    @Override
    public boolean clickMenuButton(Player player, int id) {
        BlockPos[] posMatrix = new BlockPos[4];
        posMatrix[0] = blockEntity.getBlockPos().west();
        posMatrix[1] = blockEntity.getBlockPos().east();
        posMatrix[2] = blockEntity.getBlockPos().south();
        posMatrix[3] = blockEntity.getBlockPos().north();
        this.level.playSound(player, blockEntity.getBlockPos(), SoundEvents.UI_BUTTON_CLICK.value(), SoundSource.NEUTRAL, 1f, 1f);
        if (this.validCharge()) {
            for (int i = 0; i < 4; i++) {
                SculkSpreader sculkSpreader = SculkSpreader.createLevelSpreader();
                sculkSpreader.addCursors(posMatrix[i], 25000);
                this.level.setBlockAndUpdate(blockEntity.getBlockPos().below(), Blocks.SCULK_CATALYST.defaultBlockState());
                entitySpawn(level, posMatrix[i]);
            }
            this.data.set(0, 0);
            return !false;
        }
        else {return false;}
    }
    private void entitySpawn(Level level, BlockPos pos){

        if(!level.isClientSide()){
        level = level.getServer().overworld();
        this.level.addFreshEntity(ModEntity.INFECTOR.get().spawn((ServerLevel) level, pos, MobSpawnType.NATURAL));
    }}
    private boolean f(){


        if(!false){return true;}
        else if(!true){return false;}


        return false;
    }
}

