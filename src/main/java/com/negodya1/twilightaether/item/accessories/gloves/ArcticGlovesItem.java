package com.negodya1.twilightaether.item.accessories.gloves;

import com.aetherteam.aether.item.accessories.gloves.GlovesItem;
import com.negodya1.twilightaether.TwilightAether;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorMaterials;
import net.minecraft.world.item.DyeableLeatherItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.apache.commons.lang3.tuple.ImmutableTriple;
import org.jetbrains.annotations.Nullable;
import twilightforest.enums.TwilightArmorMaterial;

public class ArcticGlovesItem extends GlovesItem implements DyeableLeatherItem {
    public ArcticGlovesItem(double punchDamage, Item.Properties properties) {
        super(TwilightArmorMaterial.ARMOR_ARCTIC, punchDamage, TwilightAether.locate("arctic_gloves"), () -> {
            return SoundEvents.ARMOR_EQUIP_LEATHER;
        }, properties);
    }

    public String getArmorTexture(ItemStack itemstack, Entity entity, EquipmentSlot slot, @Nullable String layer) {
        return slot == EquipmentSlot.LEGS ? "twilightforest:textures/armor/arcticarmor_2" + (layer == null ? "_dyed" : "_overlay") + ".png" : "twilightforest:textures/armor/arcticarmor_1" + (layer == null ? "_dyed" : "_overlay") + ".png";
    }

    public boolean hasCustomColor(ItemStack stack) {
        CompoundTag CompoundNBT = stack.getTag();
        return CompoundNBT != null && CompoundNBT.contains("display", 10) && CompoundNBT.getCompound("display").contains("color", 3);
    }

    public int getColor(ItemStack stack) {
        return this.getColor(stack, 1);
    }

    public void clearColor(ItemStack stack) {
        this.removeColor(stack);
    }

    @Override
    public void setRenderTexture(String modId, String registryName) {
        this.GLOVES_TEXTURE = new ResourceLocation(modId, "textures/models/accessory/gloves/" + registryName + "_accessory");
    }

    public int getColor(ItemStack stack, int type) {
        String string = "";
        CompoundTag stackTagCompound = stack.getTag();
        int color = 12439513;
        if (stackTagCompound != null) {
            CompoundTag displayCompound = stackTagCompound.getCompound("display");
            if (displayCompound.contains("color" + string, 3)) {
                color = displayCompound.getInt("color" + string);
            }
        }

        return type == 0 ? 16777215 : color;
    }

    public void removeColor(ItemStack stack) {
        String string = "";
        CompoundTag stackTagCompound = stack.getTag();
        if (stackTagCompound != null) {
            CompoundTag displayCompound = stackTagCompound.getCompound("display");
            if (displayCompound.contains("color" + string)) {
                displayCompound.remove("color" + string);
            }

            if (displayCompound.contains("hasColor")) {
                displayCompound.putBoolean("hasColor", false);
            }
        }

    }

    public void setColor(ItemStack stack, int color) {
        String string = "";
        CompoundTag stackTagCompound = stack.getTag();
        if (stackTagCompound == null) {
            stackTagCompound = new CompoundTag();
            stack.setTag(stackTagCompound);
        }

        CompoundTag displayCompound = stackTagCompound.getCompound("display");
        if (!stackTagCompound.contains("display", 10)) {
            stackTagCompound.put("display", displayCompound);
        }

        displayCompound.putInt("color" + string, color);
        displayCompound.putBoolean("hasColor", true);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public ImmutableTriple<Float, Float, Float> getColors(ItemStack stack) {
        float red = 1.0F;
        float green = 1.0F;
        float blue = 1.0F;
        if (stack.getItem() instanceof ArcticGlovesItem leatherGlovesItem) {
            int i = leatherGlovesItem.getColor(stack);
            red = (float) (i >> 16 & 255) / 255.0F;
            green = (float) (i >> 8 & 255) / 255.0F;
            blue = (float) (i & 255) / 255.0F;
        }
        return new ImmutableTriple<>(red, green, blue);
    }
}
