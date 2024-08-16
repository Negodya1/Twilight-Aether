package com.negodya1.twilightaether.events;

import com.aetherteam.aether.item.EquipmentUtil;
import com.negodya1.twilightaether.TwilightAether;
import com.negodya1.twilightaether.TwilightAetherItems;
import net.minecraft.network.protocol.game.ClientboundAnimatePacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.Nullable;
import top.theillusivec4.curios.api.SlotResult;
import twilightforest.TwilightForestMod;
import twilightforest.data.tags.BlockTagGenerator;
import twilightforest.init.TFItems;
import twilightforest.init.TFMobEffects;
import twilightforest.item.EnderBowItem;
import twilightforest.item.MazebreakerPickItem;
import twilightforest.item.MinotaurAxeItem;

import java.util.Optional;
import java.util.UUID;

@Mod.EventBusSubscriber(modid = TwilightAether.MODID)
public class ToolEvents {

	private static final int KNIGHTMETAL_BONUS_DAMAGE = 1;

	public static InteractionHand INTERACTION_HAND;

	@SubscribeEvent
	public static void onGloveDamage(LivingHurtEvent event) {
		LivingEntity target = event.getEntity();

		if (!target.level.isClientSide() && event.getSource().getDirectEntity() instanceof LivingEntity living) {
			if (event.getSource().getDirectEntity()instanceof Player player) {
				if (target.getArmorValue() == 0) {
					getAccessory(player, TwilightAetherItems.KNIGHTMETAL_GLOVES.get()).ifPresent((slot) -> {
						if (EquipmentUtil.isFullStrength(player)) {
							if (target.getArmorCoverPercentage() > 0) {
								int moreBonus = (int) (KNIGHTMETAL_BONUS_DAMAGE * target.getArmorCoverPercentage());
								event.setAmount(event.getAmount() + moreBonus);
							} else {
								event.setAmount(event.getAmount() + KNIGHTMETAL_BONUS_DAMAGE);
							}
							// enchantment attack sparkles
							((ServerLevel) target.level).getChunkSource().broadcastAndSend(target, new ClientboundAnimatePacket(target, 5));
						}
					});
				}
				if (player.getMainHandItem().isEmpty() && EquipmentUtil.isFullStrength(player)) {
					getAccessory(player, TwilightAetherItems.YETI_GLOVES.get()).ifPresent((slot) -> target.addEffect(new MobEffectInstance(TFMobEffects.FROSTY.get(), 20, 1)));
					getAccessory(player, TwilightAetherItems.FIERY_GLOVES.get()).ifPresent((slot) -> target.setSecondsOnFire(2));
				}
			}
		}
	}

	public static Optional<SlotResult> getAccessory(Player player, Item item) {
		return Optional.ofNullable(EquipmentUtil.getCurio(player, item));
	}

}
