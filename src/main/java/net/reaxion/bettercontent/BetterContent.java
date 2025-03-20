package net.reaxion.bettercontent;

import com.mojang.logging.LogUtils;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.reaxion.bettercontent.block.RubyBlocks;
import net.reaxion.bettercontent.item.RubyItems;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(BetterContent.MOD_ID)
public class BetterContent
{
    // Define mod id in a common place for everything to reference
    public static final String MOD_ID = "bettercontent";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();

    public BetterContent(FMLJavaModLoadingContext context)
    {
        IEventBus modEventBus = context.getModEventBus();
        modEventBus.addListener(this::commonSetup);


        // Register Items
        RubyItems.register(modEventBus);
        RubyBlocks.register(modEventBus);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);

        modEventBus.addListener(this::addCreative);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
    }

    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.INGREDIENTS) {
            event.accept(RubyItems.RUBY);
        }
    }


    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {
        // Do something when the server starts
        LOGGER.info("HELLO from server starting");
    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
        }
    }
}
