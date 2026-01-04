package com.sirenensemble.sculktechnologies;

import com.sirenensemble.sculktechnologies.blocks.blockentity.ModBlockEntity;
import com.sirenensemble.sculktechnologies.blocks.blockentity.renderer.ExpSculkConverterRenderer;
import com.sirenensemble.sculktechnologies.screen.ModMenuTypes;
import com.sirenensemble.sculktechnologies.screen.realvamp.ConverterScreen;
import com.sirenensemble.sculktechnologies.screen.realvamp.InfectorScreen;
import com.sirenensemble.sculktechnologies.screen.realvamp.InjectorScreen;
import net.minecraft.client.Minecraft;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;

// This class will not load on dedicated servers. Accessing client side code from here is safe.
@Mod(value = SculkTechnologies.MODID, dist = Dist.CLIENT)
// You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
@EventBusSubscriber(modid = SculkTechnologies.MODID, value = Dist.CLIENT)
public class SculkTechnologiesClient {
    public SculkTechnologiesClient(ModContainer container) {
        // Allows NeoForge to create a config screen for this mod's configs.
        // The config screen is accessed by going to the Mods screen > clicking on your mod > clicking on config.
        // Do not forget to add translations for your config options to the en_us.json file.
        container.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);
    }

    @SubscribeEvent
    static void onClientSetup(FMLClientSetupEvent event) {
        // Some client setup code
        SculkTechnologies.LOGGER.info("HELLO FROM CLIENT SETUP");
        SculkTechnologies.LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getName());
    }

    @SubscribeEvent
    public static void regBER(EntityRenderersEvent.RegisterRenderers event){
        event.registerBlockEntityRenderer(ModBlockEntity.EXPSCULKCONVERTER_BE.get(), ExpSculkConverterRenderer::new);
    }
    @SubscribeEvent
public static void registerScreen(RegisterMenuScreensEvent event){
        event.register(ModMenuTypes.CONVERTER_MENU.get(), ConverterScreen::new);
    }
    @SubscribeEvent
    public static void registerScreen2(RegisterMenuScreensEvent event){
        event.register(ModMenuTypes.INJECTOR_MENU.get(), InjectorScreen::new);
    }
    @SubscribeEvent
    public static void registerScreen3(RegisterMenuScreensEvent event){
        event.register(ModMenuTypes.INFECTOR_MENU.get(), InfectorScreen::new);
    }
}
