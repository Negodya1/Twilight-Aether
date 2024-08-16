package com.negodya1.twilightaether.item.accessories.capes;

import com.aetherteam.aether.item.accessories.cape.CapeItem;
import com.negodya1.twilightaether.TwilightAether;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import top.theillusivec4.curios.api.SlotContext;

import javax.annotation.Nullable;
import java.util.List;

public class FieryCapeItem extends CapeItem {

    public FieryCapeItem(Properties properties) {
        super(TwilightAether.locate("fiery_cape"), properties);
    }

    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        if (slotContext.entity().wasOnFire && slotContext.entity().getEffect(MobEffects.FIRE_RESISTANCE) == null) {
            slotContext.entity().addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 300));
            if (stack.isDamageableItem())
                stack.hurtAndBreak(1, slotContext.entity(), consumer -> {});
        }
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> tooltips, TooltipFlag flags) {
        super.appendHoverText(stack, world, tooltips, flags);
        tooltips.add(Component.translatable(this.getDescriptionId() + ".desc").withStyle(ChatFormatting.GRAY));
    }

}
