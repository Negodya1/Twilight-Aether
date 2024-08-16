package com.negodya1.twilightaether.item.accessories.gloves;

import com.aetherteam.aether.item.accessories.gloves.GlovesItem;
import net.minecraft.core.NonNullList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantments;

import java.util.function.Supplier;

public class IronwoodGlovesItem extends GlovesItem {

    public IronwoodGlovesItem(ArmorMaterial material, double punchDamage, String glovesName, Supplier<? extends SoundEvent> glovesSound, Properties properties) {
        this(material, punchDamage, new ResourceLocation("twilightaether", glovesName), glovesSound, properties);
    }

    public IronwoodGlovesItem(ArmorMaterial material, double punchDamage, ResourceLocation glovesName, Supplier<? extends SoundEvent> glovesSound, Properties properties) {
        super(material, punchDamage, glovesName, glovesSound, properties);
    }

    @Override
    public void fillItemCategory(CreativeModeTab tab, NonNullList<ItemStack> items) {
        if (this.allowedIn(tab)) {
            ItemStack stack = new ItemStack(this);
            stack.enchant(Enchantments.UNBREAKING, 2);
            items.add(stack);
        }
    }

}
