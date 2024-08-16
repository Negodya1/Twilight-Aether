package com.negodya1.twilightaether;

import com.aetherteam.aether.client.renderer.accessory.GlovesRenderer;
import com.mojang.logging.LogUtils;
import com.negodya1.twilightaether.client.renderer.accessory.LayeredGlovesRenderer;
import net.minecraft.core.cauldron.CauldronInteraction;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.EnchantmentInstance;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import org.slf4j.Logger;
import top.theillusivec4.curios.api.client.CuriosRendererRegistry;
import twilightforest.TFConfig;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(TwilightAether.MODID)
public class TwilightAether {
    public static final String MODID = "twilightaether";
    private static final Logger LOGGER = LogUtils.getLogger();
    public static void logThis(String str) {
        LOGGER.info(str);
    }
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);

    public static final RegistryObject<CreativeModeTab> EXAMPLE_TAB = CREATIVE_MODE_TABS.register("twilightaether", () -> CreativeModeTab.builder()
            .withTabsBefore(CreativeModeTabs.COMBAT)
            .title(Component.translatable("itemGroup." + MODID))
            .icon(() -> TwilightAetherItems.FIERY_GLOVES.get().getDefaultInstance())
            .displayItems((parameters, output) -> {
                output.accept(TwilightAetherItems.ARCTIC_GLOVES.get());
                output.accept(TwilightAetherItems.FIERY_GLOVES.get());
                generateGearWithEnchants(output, TwilightAetherItems.IRONWOOD_GLOVES.get(), new EnchantmentInstance(Enchantments.UNBREAKING, 2));
                output.accept(TwilightAetherItems.KNIGHTMETAL_GLOVES.get());
                generateGearWithEnchants(output, TwilightAetherItems.NAGA_GLOVES.get(), new EnchantmentInstance(Enchantments.MENDING, 1));
                output.accept(TwilightAetherItems.PHANTOM_GLOVES.get());
                generateGearWithEnchants(output, TwilightAetherItems.STEELEAF_GLOVES.get(), new EnchantmentInstance(Enchantments.UNBREAKING, 1));
                output.accept(TwilightAetherItems.YETI_GLOVES.get());
                output.accept(TwilightAetherItems.FIERY_CAPE.get());
            }).build());

    public TwilightAether() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        modEventBus.addListener(this::commonSetup);

        TwilightAetherItems.ITEMS.register(modEventBus);

        CREATIVE_MODE_TABS.register(modEventBus);

        MinecraftForge.EVENT_BUS.register(this);
    }

    public static ResourceLocation locate(String name) {
        return new ResourceLocation(MODID, name);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        CauldronInteraction.WATER.put(TwilightAetherItems.ARCTIC_GLOVES.get(), CauldronInteraction.DYED_ITEM);
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {

    }

    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            CuriosRendererRegistry.register(TwilightAetherItems.STEELEAF_GLOVES.get(), GlovesRenderer::new);
            CuriosRendererRegistry.register(TwilightAetherItems.IRONWOOD_GLOVES.get(), GlovesRenderer::new);
            CuriosRendererRegistry.register(TwilightAetherItems.KNIGHTMETAL_GLOVES.get(), GlovesRenderer::new);
            CuriosRendererRegistry.register(TwilightAetherItems.NAGA_GLOVES.get(), GlovesRenderer::new);
            CuriosRendererRegistry.register(TwilightAetherItems.PHANTOM_GLOVES.get(), GlovesRenderer::new);
            CuriosRendererRegistry.register(TwilightAetherItems.YETI_GLOVES.get(), GlovesRenderer::new);
            CuriosRendererRegistry.register(TwilightAetherItems.FIERY_GLOVES.get(), GlovesRenderer::new);
            CuriosRendererRegistry.register(TwilightAetherItems.ARCTIC_GLOVES.get(), LayeredGlovesRenderer::new);
        }
    }

    private static void generateGearWithEnchants(CreativeModeTab.Output output, ItemLike item, EnchantmentInstance... instances) {
        ItemStack stack = new ItemStack(item);
        if (TFConfig.COMMON_CONFIG.defaultItemEnchants.get()) {
            for (EnchantmentInstance enchant : instances) {
                stack.enchant(enchant.enchantment, enchant.level);
            }
        }
        output.accept(stack);
    }
}
