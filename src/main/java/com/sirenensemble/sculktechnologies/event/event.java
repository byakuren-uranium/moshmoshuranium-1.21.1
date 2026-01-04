package com.sirenensemble.sculktechnologies.event;

import com.sirenensemble.sculktechnologies.items.ModItems;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;



@EventBusSubscriber (modid = "moshmoshuranium")
public class event{

    @SubscribeEvent
    public static void onCraftBattery(PlayerEvent.ItemCraftedEvent event){
        if (event.getCrafting().is(ModItems.EXP_BATTERY)){
            event.getCrafting().getItem().setDamage(event.getCrafting(), 50000);
        }
    }
}