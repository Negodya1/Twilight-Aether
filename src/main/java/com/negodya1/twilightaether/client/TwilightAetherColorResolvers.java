package com.negodya1.twilightaether.client;

import com.aetherteam.aether.Aether;
import com.aetherteam.aether.block.AetherBlocks;
import com.aetherteam.aether.item.AetherItems;
import com.aetherteam.aether.item.miscellaneous.MoaEggItem;
import com.aetherteam.aether.mixin.mixins.client.accessor.BlockColorsAccessor;
import com.negodya1.twilightaether.TwilightAether;
import com.negodya1.twilightaether.TwilightAetherItems;
import net.minecraft.client.color.block.BlockColor;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.world.item.DyeableLeatherItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.HashMap;
import java.util.Map;

@Mod.EventBusSubscriber(value = Dist.CLIENT, modid = TwilightAether.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class TwilightAetherColorResolvers {

    @SubscribeEvent
    static void registerItemColor(RegisterColorHandlersEvent.Item event) {
        event.register((color, itemProvider) -> itemProvider > 0 ? -1 : ((DyeableLeatherItem) color.getItem()).getColor(color), TwilightAetherItems.ARCTIC_GLOVES.get());
    }
}
