package com.sirenensemble.sculktechnologies.items.tools;

import com.sirenensemble.sculktechnologies.blocks.ModBlock;
import com.sirenensemble.sculktechnologies.component.ModDataComponents;
import com.sirenensemble.sculktechnologies.items.ModItems;
import com.sirenensemble.sculktechnologies.sound.ModSounds;
import net.minecraft.advancements.critereon.ItemDamagePredicate;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.event.entity.item.ItemEvent;

import java.util.Map;


public class SoulSeeker extends Item {

    private static final Map<Block, Block> SEEKER_MAP =
            Map.of(
                    ModBlock.SCULK_EX_BLOCK.get(), Blocks.SCULK
            );
    public SoulSeeker(Properties properties) {
        super(properties);
    }
    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        Block clickedBlock = level.getBlockState(context.getClickedPos()).getBlock();

        if(SEEKER_MAP.containsKey(clickedBlock)) {
            if(!level.isClientSide()) {
                level.setBlockAndUpdate(context.getClickedPos(), SEEKER_MAP.get(clickedBlock).defaultBlockState());

                context.getItemInHand().hurtAndBreak(1, ((ServerLevel) level), context.getPlayer(),
                        item -> context.getPlayer().onEquippedItemBroken(item, EquipmentSlot.MAINHAND));
                level.playSound(null, context.getClickedPos(), ModSounds.SOUL_SEEKER_USE.get(), SoundSource.BLOCKS);
                ((ServerLevel) level).sendParticles(ParticleTypes.SOUL, context.getClickedPos().getX() + 0.5, context.getClickedPos().getY() + 1.0, context.getClickedPos().getZ() + 0.5, 3, 0, 0, 0, 1);
                context.getItemInHand().set(ModDataComponents.COORDINATES, context.getClickedPos());
                context.getItemInHand().getItem().getDamage(context.getItemInHand());
                ItemStack itemStack = new ItemStack(ModItems.TORMENTED_SOUL.get());
                if(!context.getPlayer().getInventory().add(itemStack)){
                    context.getPlayer().drop(itemStack, false);
                }
                else{context.getPlayer().addItem(itemStack);}
            }

        }else{return InteractionResult.FAIL;}
        return InteractionResult.SUCCESS;

    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity livingEntity) {
        super.finishUsingItem(stack, level, livingEntity);
        if (stack.isEmpty()) {
            return new ItemStack(ModItems.TORMENTED_SOUL.get());
        } else {
            if (livingEntity instanceof Player) {
                Player player = (Player) livingEntity;
                if (!player.hasInfiniteMaterials()) {
                    ItemStack itemstack = new ItemStack(ModItems.TORMENTED_SOUL.get());
                    if (!player.getInventory().add(itemstack)) {
                        player.drop(itemstack, false);
                    }
                }
            }

            return stack;
    }
}
}
