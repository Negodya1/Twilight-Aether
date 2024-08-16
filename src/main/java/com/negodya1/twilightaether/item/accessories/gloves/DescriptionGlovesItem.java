package com.negodya1.twilightaether.item.accessories.gloves;

import com.aetherteam.aether.item.accessories.gloves.GlovesItem;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.List;
import java.util.function.Supplier;

public class DescriptionGlovesItem extends GlovesItem {

    public DescriptionGlovesItem(ArmorMaterial material, double punchDamage, String glovesName, Supplier<? extends SoundEvent> glovesSound, Properties properties) {
        this(material, punchDamage, new ResourceLocation("twilightaether", glovesName), glovesSound, properties);
    }

    public DescriptionGlovesItem(ArmorMaterial material, double punchDamage, ResourceLocation glovesName, Supplier<? extends SoundEvent> glovesSound, Properties properties) {
        super(material, punchDamage, glovesName, glovesSound, properties);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> tooltips, TooltipFlag flags) {
        super.appendHoverText(stack, world, tooltips, flags);
        tooltips.add(Component.translatable(this.getDescriptionId() + ".desc").withStyle(ChatFormatting.GRAY));
    }

}
