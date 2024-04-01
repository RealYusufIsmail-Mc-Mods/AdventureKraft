package com.devmaster.dangerzone.items;

import com.devmaster.dangerzone.configs.DZConfig;
import com.devmaster.dangerzone.misc.DangerZone;
import com.devmaster.dangerzone.util.RegistryHandler;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.AxeItem;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import java.util.List;

public class TitaniumAxe extends AxeItem {
    private static IItemTier iItemTier = new IItemTier() {

        public int getMaxUses() {
            return (Integer) DZConfig.TITANIUM_AXE_DURABILITY.get();
        }

        public float getEfficiency() {
            return (Integer) DZConfig.TITANIUM_AXE_EFFICIENCY.get();
        }

        public float getAttackDamage() {
            return -1F;
        }

        public int getHarvestLevel() {
            return (Integer) DZConfig.TITANIUM_AXE_HARVEST_LEVEL.get();
        }

        public int getEnchantability() {
            return (Integer) DZConfig.TITANIUM_AXE_ENCHANTABILITY.get();

        }

        public Ingredient getRepairMaterial() {
            return null;
        }
    };

    public TitaniumAxe() {
        super(iItemTier, (Integer)DZConfig.TITANIUM_AXE_DAMAGE.get(), (Integer) DZConfig.TITANIUM_AXE_ATTACK_SPEED.get(), (new Properties()).group(DangerZone.TOOLS));
    }

    public void unlockDestroyACH(PlayerEntity entity, World world) {
        if (world instanceof ServerWorld) {
            ServerPlayerEntity serverPlayerEntity = (ServerPlayerEntity)entity;
        }
    }


    public boolean hitEntity(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        stack.damageItem((Integer)DZConfig.TITANIUM_AXE_HIT_COST.get(), attacker, (playerEntity) -> {
            if (attacker instanceof PlayerEntity) {
                this.unlockDestroyACH((PlayerEntity)attacker, attacker.getEntityWorld());
            }

            playerEntity.sendBreakAnimation(EquipmentSlotType.MAINHAND);
        });
        return true;
    }
    @Override
    public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn){
        tooltip.add(new StringTextComponent("\u00A7b" + "The titanium extracted from the crust of the overworld made this" + "\u00A7b"));
    }
}
