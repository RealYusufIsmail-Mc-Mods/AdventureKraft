package com.devmaster.dangerzone.blocks;

import net.minecraft.block.OreBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.common.ToolType;

import java.util.List;

public class TitaniumOreBlock extends OreBlock {


    public TitaniumOreBlock() {
        super(Properties.create(Material.ROCK)
                .hardnessAndResistance(15.0F, 5.0F)
                .sound(SoundType.STONE)
                .harvestLevel(0)
                .harvestTool(ToolType.PICKAXE)
                .setRequiresTool());
    }

    @Override
    public void addInformation(ItemStack stack, IBlockReader worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        tooltip.add(new StringTextComponent(   "\u00A7b" + "Oh yeah! Get excited! You're on your way to crafting something Ultimate! This is what you've been looking for! Smelt these to get Titanium Nuggets." + "\u00A7b"));
    }
}
