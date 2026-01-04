package com.sirenensemble.sculktechnologies;


import com.sirenensemble.sculktechnologies.blocks.blockentity.ModBlockEntity;
import com.sirenensemble.sculktechnologies.component.ModDataComponents;
import com.sirenensemble.sculktechnologies.entity.ModEntity;
import com.sirenensemble.sculktechnologies.loot.ModLootModifiers;
import com.sirenensemble.sculktechnologies.recipe.ModRecipes;
import com.sirenensemble.sculktechnologies.screen.ModMenuTypes;
import com.sirenensemble.sculktechnologies.sound.ModSounds;
import org.slf4j.Logger;
import com.mojang.logging.LogUtils;
import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import com.sirenensemble.sculktechnologies.items.*;
import static com.sirenensemble.sculktechnologies.blocks.ModBlock.*;
import static com.sirenensemble.sculktechnologies.event.CreativeTabEvent.CREATIVE_MODE_TAB;
import static com.sirenensemble.sculktechnologies.items.ModItems.*;
import com.sirenensemble.sculktechnologies.event.*;


@Mod(SculkTechnologies.MODID)
public class SculkTechnologies {
    public static final String MODID = "moshmoshuranium";
    public static final Logger LOGGER = LogUtils.getLogger();
    public SculkTechnologies(IEventBus modEventBus, ModContainer modContainer) {
        modEventBus.addListener(this::commonSetup);
        BLOCKS.register(modEventBus);
        ModItems.ITEMS.register(modEventBus);
        ModDataComponents.register(modEventBus);
        ModSounds.register(modEventBus);
        ModEntity.register(modEventBus);
        ModLootModifiers.register(modEventBus);
        ModBlockEntity.register(modEventBus);
        ModMenuTypes.register(modEventBus);
        ModRecipes.register(modEventBus);
        CREATIVE_MODE_TAB.register(modEventBus);
        NeoForge.EVENT_BUS.register(this);
        // Register the item to a creative tab
   
        // Register our mod's ModConfigSpec so that FML can create and load the config file for us
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(FMLCommonSetupEvent event) {
        // Some common setup code
        LOGGER.info("HELLO FROM COMMON SETUP");

    }


    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        // Do something when the server starts
        LOGGER.info("HELLO from server starting");
    }
}
