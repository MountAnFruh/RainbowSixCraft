package r6c.r6cmod.proxy;

import ibxm.Player;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.resources.I18n;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.client.event.RenderHandEvent;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.common.ForgeChunkManager;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.input.Mouse;
import r6c.r6cmod.CreativeTabR6CMod;
import r6c.r6cmod.R6CMod;
import r6c.r6cmod.block.R6CBlocks;
import r6c.r6cmod.client.gui.GUIDroneTerminalDrive;
import r6c.r6cmod.entity.EntityDrone;
import r6c.r6cmod.entity.R6CEntities;
import r6c.r6cmod.item.R6CItems;
import r6c.r6cmod.networking.R6CPacketHandler;

@Mod.EventBusSubscriber
public class CommonProxy {

    public void preInit(FMLPreInitializationEvent event) {
        R6CItems.preInit();
        R6CBlocks.preInit();
        R6CPacketHandler.preInit(event);
    }

    public void init(FMLInitializationEvent event) {
        R6CItems.init();
        R6CBlocks.init();
    }

    public void postInit(FMLPostInitializationEvent event) {

    }

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        R6CBlocks.registerBlocks(event);
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        R6CItems.registerItems(event);
    }

    @SubscribeEvent
    public static void registerEntities(RegistryEvent.Register<EntityEntry> event) {
        R6CEntities.registerEntities(event);
    }
}
