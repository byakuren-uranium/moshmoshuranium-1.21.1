package com.sirenensemble.sculktechnologies.items;

import com.sirenensemble.sculktechnologies.SculkTechnologies;
import com.sirenensemble.sculktechnologies.blocks.ModBlock;
import com.sirenensemble.sculktechnologies.blocks.blockstates.ModBlockStates;
import com.sirenensemble.sculktechnologies.items.armor.ModArmorItem;
import com.sirenensemble.sculktechnologies.items.armor.ModArmorMaterials;
import com.sirenensemble.sculktechnologies.items.food.NewFoodProperties;
import com.sirenensemble.sculktechnologies.items.tools.*;
import com.sirenensemble.sculktechnologies.sound.ModSounds;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.*;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import static com.sirenensemble.sculktechnologies.blocks.ModBlock.*;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(SculkTechnologies.MODID);

    public static final DeferredItem<BlockItem> SCULK_WOOD_ITEM_LOG = ModItems.ITEMS.registerSimpleBlockItem("sculkwood_log", SCULK_WOOD_LOG);
    public static final DeferredItem<BlockItem> SCULK_WOOD_ITEM_WOOD = ModItems.ITEMS.registerSimpleBlockItem("sculkwood_wood", SCULK_WOOD_WOOD);
    public static final DeferredItem<BlockItem> STRIPPED_SCULK_WOOD_ITEM_LOG = ModItems.ITEMS.registerSimpleBlockItem("stripped_sculkwood_log", STRIPPED_SCULK_WOOD_LOG);
    public static final DeferredItem<BlockItem> STRIPPED_SCULK_WOOD_ITEM_WOOD = ModItems.ITEMS.registerSimpleBlockItem("stripped_sculkwood_wood", STRIPPED_SCULK_WOOD_WOOD);
    public static final DeferredItem<BlockItem> SCULK_WOOD_ITEM_PLANKS = ModItems.ITEMS.registerSimpleBlockItem("sculkwood_planks", SCULK_WOOD_PLANKS);
    public static final DeferredItem<BlockItem> SCULK_WOOD_ITEM_SAPLING = ModItems.ITEMS.registerSimpleBlockItem("sculkwood_sapling", SCULK_WOOD_SAPLING);
    public static final DeferredItem<BlockItem> SCULK_WOOD_ITEM_LEAVES = ModItems.ITEMS.registerSimpleBlockItem("sculkwood_leaves", SCULK_WOOD_LEAVES);


    public static final DeferredItem<BlockItem> DEEPSLATE_SCULK_ORE = ITEMS.registerSimpleBlockItem("deepslate_sculk_ore_block", DEEPSLATE_SCULK_ORE_BLOCK);

    public static final DeferredItem<BlockItem> BLOCK_OF_SCULK = ModItems.ITEMS.registerSimpleBlockItem("block_of_sculk", ModBlock.BLOCK_OF_SCULK);

    public static final DeferredItem<BlockItem> SCULK_INFECTOR = ModItems.ITEMS.registerSimpleBlockItem("sculk_infector", ModBlock.SCULK_INFECTOR);
    public static final DeferredItem<BlockItem> SCULK_INJECTOR = ITEMS.registerSimpleBlockItem("sculk_injector", ModBlock.SCULK_INJECTOR);

    public static final DeferredItem<BlockItem> SCULK_ORE_BLOCK_ITEM = ITEMS.registerSimpleBlockItem("sculk_ore_block", SCULK_ORE_BLOCK);

    public static final DeferredItem<BlockItem> EXPSCULKCONVERTER = ITEMS.registerSimpleBlockItem("exp_sculk_converter", ModBlock.EXPSCULKCONVERTER);


    public static final DeferredItem<BlockItem> SCULK_EX_BLOCK_ITEM = ITEMS.registerSimpleBlockItem("soul_infused_sculk_block", SCULK_EX_BLOCK);

    public static final DeferredItem<Item> SCULK_ORE = ITEMS.register("sculk_ore", () -> new Item (new Item.Properties().stacksTo(64)));

    public static final DeferredItem<Item> SCULK_INGOT = ITEMS.register("sculk_ingot", () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> TORMENTED_SOUL = ITEMS.register("tormented_soul",() -> new TormentedSoul(new Item.Properties().stacksTo(16)));


    public static final DeferredItem<BlockItem> SHORT_SCULK_GRASS = ITEMS.registerSimpleBlockItem("sculk_short_grass", SCULK_SHORT_GRASS);

    public static final DeferredItem<Item> EXP_BATTERY = ITEMS.register("exp_battery", () -> new ExpBattery(new Item.Properties().durability(50000).stacksTo(1)));

    public static final DeferredItem<Item> REINFORCE_FRAGMENT = ITEMS.register("reinforce_fragment", () -> new Item(new Item.Properties()));

    public static final DeferredItem<BlockItem> SCULK_TORCHFLOWER = ITEMS.registerSimpleBlockItem("sculk_torchflower", ModBlock.SCULK_TORCHFLOWER);
    public static final DeferredItem<Item> SCULK_TORCHFLOWER_SEEDS = ITEMS.register("sculk_torchflower_seeds", () -> new ItemNameBlockItem(SCULK_TORCHFLOWER_CROP.get(), new Item.Properties()));
    public static final DeferredItem<Item> SCULK_BEETROOT = ITEMS.register("sculk_beetroot", () -> new Item(new Item.Properties().food(NewFoodProperties.SCULK_BEETROOT)));
    public static final DeferredItem<Item> SCULK_BEETROOT_SEEDS = ITEMS.register("sculk_beetroot_seeds", () -> new ItemNameBlockItem(SCULK_BEETROOT_CROP.get(), new Item.Properties()));
    public static final DeferredItem<Item> SCULK_POTATO = ITEMS.register("sculk_potato", () -> new ItemNameBlockItem(ModBlock.SCULK_POTATO_CROP.get(), new Item.Properties().food(NewFoodProperties.SCULK_POTATO)));
    public static final DeferredItem<Item> SCULK_CARROT = ITEMS.register("sculk_carrot", () -> new Item(new Item.Properties().food(NewFoodProperties.SCULK_CARROT)));
    public static final DeferredItem<Item> SCULK_CARROT_SEEDS = ITEMS.register("sculk_carrot_seeds", () -> new ItemNameBlockItem(ModBlock.SCULK_CARROT_CROP.get(), new Item.Properties()));
    public static final DeferredItem<Item> SCULK_SWEET_BERRY = ITEMS.register("sculk_sweet_berry", () -> new ItemNameBlockItem(SCULK_SWEET_BERRY_BUSH.get(), new Item.Properties().food(NewFoodProperties.SCULK_SWEET_BERRY)));
    public static final DeferredItem<Item> SCULK_BAKED_POTATO = ITEMS.register("sculk_baked_potato", () -> new Item(new Item.Properties().food(NewFoodProperties.BAKED_SCULK_POTATO)));

    public static final DeferredItem<SwordItem> SCULK_SWORD = ITEMS.register("sculk_sword", ()-> new SwordItem(ModToolTiers.SCULK, new Item.Properties().attributes(SwordItem.createAttributes(ModToolTiers.SCULK, 7f, -2.7F))));

    public static final DeferredItem<AxeItem> SCULK_AXE = ITEMS.register("sculk_axe", ()-> new AxeItem(ModToolTiers.SCULK, new Item.Properties().attributes(AxeItem.createAttributes(ModToolTiers.SCULK, 10f, -3.5F))));

    public static final DeferredItem<PickaxeItem> SCULK_PICKAXE = ITEMS.register("sculk_pickaxe", ()-> new PickaxeItem(ModToolTiers.SCULK, new Item.Properties().attributes(PickaxeItem.createAttributes(ModToolTiers.SCULK, 3f, -2.7F))));

    public static final DeferredItem<HoeItem> SCULK_HOE = ITEMS.register("sculk_hoe", ()-> new HoeItem(ModToolTiers.SCULK, new Item.Properties().attributes(HoeItem.createAttributes(ModToolTiers.SCULK, 2f, -2.7F))));

    public static final DeferredItem<ShovelItem> SCULK_SHOVEL = ITEMS.register("sculk_shovel", ()-> new ShovelItem(ModToolTiers.SCULK, new Item.Properties().attributes(ShovelItem.createAttributes(ModToolTiers.SCULK, 2f, -2.9F))));

    public static final DeferredItem<Item> SOUL_SEEKER = ITEMS.register("soul_seeker", () -> new SoulSeeker(new Item.Properties().durability(64)));


    public static final DeferredItem<ArmorItem> SCULK_HELMET = ITEMS.register("sculk_helmet", () -> new ModArmorItem(ModArmorMaterials.SCULK_ARMOR_MATERIAL, ArmorItem.Type.HELMET, new Item.Properties().durability(ArmorItem.Type.HELMET.getDurability(40))));
    public static final DeferredItem<ArmorItem> SCULK_CHESTPLATE = ITEMS.register("sculk_chestplate", () -> new ArmorItem(ModArmorMaterials.SCULK_ARMOR_MATERIAL, ArmorItem.Type.CHESTPLATE, new Item.Properties().durability(ArmorItem.Type.CHESTPLATE.getDurability(40))));
    public static final DeferredItem<ArmorItem> SCULK_LEGGINGS = ITEMS.register("sculk_leggings", () -> new ArmorItem(ModArmorMaterials.SCULK_ARMOR_MATERIAL, ArmorItem.Type.LEGGINGS, new Item.Properties().durability(ArmorItem.Type.LEGGINGS.getDurability(40))));
    public static final DeferredItem<ArmorItem> SCULK_BOOTS = ITEMS.register("sculk_boots", () -> new ArmorItem(ModArmorMaterials.SCULK_ARMOR_MATERIAL, ArmorItem.Type.BOOTS, new Item.Properties().durability(ArmorItem.Type.BOOTS.getDurability(40))));


    public static final DeferredItem<BlockItem> SCULK_DOOR_BLOCK = ModItems.ITEMS.registerSimpleBlockItem("sculk_door_block", ModBlockStates.SCULK_DOOR_BLOCK);
    public static final DeferredItem<BlockItem> SCULK_SLAB_BLOCK = ModItems.ITEMS.registerSimpleBlockItem("sculk_slab_block", ModBlockStates.SCULK_SLAB_BLOCK);
    public static final DeferredItem<BlockItem> SCULK_TRAPDOOR_BLOCK = ModItems.ITEMS.registerSimpleBlockItem("sculk_trapdoor_block", ModBlockStates.SCULK_TRAPDOOR_BLOCK);
    public static final DeferredItem<BlockItem> SCULK_FENCE_BLOCK = ModItems.ITEMS.registerSimpleBlockItem("sculk_fence_block", ModBlockStates.SCULK_FENCE_BLOCK);
    public static final DeferredItem<BlockItem> SCULK_FENCE_GATE_BLOCK = ModItems.ITEMS.registerSimpleBlockItem("sculk_fence_gate_block", ModBlockStates.SCULK_FENCE_GATE_BLOCK);
    public static final DeferredItem<BlockItem> SCULK_BUTTON_BLOCK = ModItems.ITEMS.registerSimpleBlockItem("sculk_button_block", ModBlockStates.SCULK_BUTTON_BLOCK);
    public static final DeferredItem<BlockItem> SCULK_PRESSUREPLATE_BLOCK = ModItems.ITEMS.registerSimpleBlockItem("sculk_pressure_plate_block", ModBlockStates.SCULK_PRESSUREPLATE_BLOCK);
    public static final DeferredItem<BlockItem> SCULK_STAIR_BLOCK = ModItems.ITEMS.registerSimpleBlockItem("sculk_stair_block", ModBlockStates.SCULK_STAIR_BLOCK);

    public static final DeferredItem<Item> SIREN_SONG_MUSIC_DISC = ITEMS.register("siren_song_music_disc", () -> new Item(new Item.Properties().jukeboxPlayable(ModSounds.SIREN_SONG_KEY).stacksTo(1)));

    public static void register(IEventBus modEventBus){
        ITEMS.register(modEventBus);
    }
}
