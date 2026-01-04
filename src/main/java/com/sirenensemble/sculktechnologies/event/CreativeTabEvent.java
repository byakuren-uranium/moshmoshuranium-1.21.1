package com.sirenensemble.sculktechnologies.event;

import com.sirenensemble.sculktechnologies.SculkTechnologies;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import static com.sirenensemble.sculktechnologies.items.ModItems.*;

public class CreativeTabEvent {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, SculkTechnologies.MODID);
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> EXAMPLE_TAB = CREATIVE_MODE_TAB.register("example_tab", () -> CreativeModeTab.builder().title(Component.translatable("itemGroup.moshmoshuranium")) //The language key for the title of your CreativeModeTab
            .withTabsBefore(CreativeModeTabs.COMBAT)
            .icon(() -> SCULK_EX_BLOCK_ITEM.get().getDefaultInstance())
            .displayItems((parameters, output) -> {
                output.accept(SCULK_HELMET);
                output.accept(SCULK_CHESTPLATE);
                output.accept(SCULK_LEGGINGS);
                output.accept(SCULK_BOOTS);
                output.accept(SOUL_SEEKER);

                output.accept(SCULK_WOOD_ITEM_LOG);
                output.accept(SCULK_WOOD_ITEM_WOOD);
                output.accept(STRIPPED_SCULK_WOOD_ITEM_LOG);
                output.accept(STRIPPED_SCULK_WOOD_ITEM_WOOD);
                output.accept(SCULK_WOOD_ITEM_PLANKS);
                output.accept(SCULK_WOOD_ITEM_SAPLING);
                output.accept(SCULK_WOOD_ITEM_LEAVES);

                output.accept(SCULK_SWORD);
                output.accept(SCULK_SHOVEL);
                output.accept(SCULK_HOE);
                output.accept(SCULK_PICKAXE);
                output.accept(SCULK_AXE);

                output.accept(SCULK_DOOR_BLOCK);
                output.accept(SCULK_TRAPDOOR_BLOCK);
                output.accept(SCULK_SLAB_BLOCK);
                output.accept(SCULK_STAIR_BLOCK);
                output.accept(SCULK_PRESSUREPLATE_BLOCK);
                output.accept(SCULK_BUTTON_BLOCK);
                output.accept(SCULK_FENCE_BLOCK);
                output.accept(SCULK_FENCE_GATE_BLOCK);

                output.accept(BLOCK_OF_SCULK);
                output.accept(SCULK_ORE_BLOCK_ITEM);
                output.accept(DEEPSLATE_SCULK_ORE);
                output.accept(SCULK_ORE);
                output.accept(SCULK_INGOT);
                output.accept(REINFORCE_FRAGMENT);
                output.accept(TORMENTED_SOUL);
                output.accept(SCULK_EX_BLOCK_ITEM);
                output.accept(EXPSCULKCONVERTER);
                output.accept(SCULK_INJECTOR);
                output.accept(SCULK_INFECTOR);
                output.accept(EXP_BATTERY);

                //output.accept(SCULK_TORCHFLOWER.get());
                //output.accept(SCULK_TORCHFLOWER_SEEDS.get());    //IDK WHY BUT THIS THING MAKES MINECRAFT FEEL BAD
                output.accept(SCULK_BEETROOT);
                output.accept(SCULK_BEETROOT_SEEDS);
                output.accept(SHORT_SCULK_GRASS);
                output.accept(SCULK_POTATO);
                output.accept(SCULK_BAKED_POTATO);
                output.accept(SCULK_CARROT);
                output.accept(SCULK_CARROT_SEEDS);
                output.accept(SCULK_SWEET_BERRY);


                output.accept(SIREN_SONG_MUSIC_DISC);
            }).build());
}
