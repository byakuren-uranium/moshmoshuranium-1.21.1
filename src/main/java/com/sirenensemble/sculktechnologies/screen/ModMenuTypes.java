package com.sirenensemble.sculktechnologies.screen;

import com.sirenensemble.sculktechnologies.SculkTechnologies;
import com.sirenensemble.sculktechnologies.screen.realvamp.ConverterMenu;
import com.sirenensemble.sculktechnologies.screen.realvamp.InfectorMenu;
import com.sirenensemble.sculktechnologies.screen.realvamp.InjectorMenu;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;
import net.neoforged.neoforge.network.IContainerFactory;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModMenuTypes {
public static final DeferredRegister<MenuType<?>> MENU_TYPES = DeferredRegister.create(Registries.MENU, SculkTechnologies.MODID);

public static final DeferredHolder<MenuType<?>, MenuType<ConverterMenu>> CONVERTER_MENU =
        registerMenuType("converter_menu", ConverterMenu::new);

public static final DeferredHolder<MenuType<?>, MenuType<InjectorMenu>> INJECTOR_MENU = registerMenuType("sculk_injector_menu", InjectorMenu::new);

public static final DeferredHolder<MenuType<?>, MenuType<InfectorMenu>> INFECTOR_MENU =
        registerMenuType("infector_menu", InfectorMenu::new);

    private static <T extends AbstractContainerMenu>DeferredHolder<MenuType<?>, MenuType<T>> registerMenuType(String name, IContainerFactory<T> factory){
    return MENU_TYPES.register(name, () -> IMenuTypeExtension.create(factory));
}


public static void register(IEventBus eventBus){
    MENU_TYPES.register(eventBus);
}
}
