package com.sirenensemble.sculktechnologies.util;

import com.sirenensemble.sculktechnologies.SculkTechnologies;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class ModTags {
    public static class Blocks{
           public static final TagKey<Block> NEEDS_SCULK_TOOL = createTag("need_sculk_tool");
           public static final TagKey<Block> INCORRECT_FOR_SCULK_TOOL = createTag("incorrect_for_sculk_tool");
            private static TagKey<Block> createTag(String name){
                return BlockTags.create(ResourceLocation.fromNamespaceAndPath(SculkTechnologies.MODID, name));
            }
        }
    public static class Items{
        public static final TagKey<Item> ECHO_PISTOL_ITEM = createTag("echo_pistol_item");

        private static TagKey<Item> createTag(String name){
         return ItemTags.create(ResourceLocation.fromNamespaceAndPath(SculkTechnologies.MODID, name));

        }
    }
}