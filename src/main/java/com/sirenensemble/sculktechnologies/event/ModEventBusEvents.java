package com.sirenensemble.sculktechnologies.event;


import com.sirenensemble.sculktechnologies.SculkTechnologies;
import com.sirenensemble.sculktechnologies.entity.ModEntity;
import com.sirenensemble.sculktechnologies.entity.alive.InfectorEntity;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;


@EventBusSubscriber(modid = SculkTechnologies.MODID)
public class ModEventBusEvents {

    @SubscribeEvent
    public static void registerAttributesInfector (EntityAttributeCreationEvent event){
        event.put(ModEntity.INFECTOR.get(), InfectorEntity.createAttributes().build());
    }
}
