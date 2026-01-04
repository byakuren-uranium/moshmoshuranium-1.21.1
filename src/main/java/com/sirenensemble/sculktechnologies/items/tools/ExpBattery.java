package com.sirenensemble.sculktechnologies.items.tools;


import com.sirenensemble.sculktechnologies.items.ModItems;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.energy.EnergyStorage;


public class ExpBattery extends Item {
    public ExpBattery(Properties properties) {
        super(properties);
    }

    @Override
    public boolean isDamageable(ItemStack stack) {
        return stack.getDamageValue() > 0;
    }

    @Override
    public boolean isBarVisible(ItemStack stack) {
        return true;
    }

    @Override
    public void onCraftedBy(ItemStack stack, Level level, Player player) {
        super.onCraftedBy(stack, level, player);
        if (stack.is(ModItems.EXP_BATTERY)) {
            stack.setDamageValue(50000);
        }
    }
}
