package r6c.r6cmod.proxy;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.Entity;
import net.minecraftforge.client.event.*;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent;
import net.minecraftforge.fml.relauncher.Side;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import r6c.r6cmod.client.gui.GUIDroneTerminalDrive;
import r6c.r6cmod.entity.EntityDrone;
import r6c.r6cmod.entity.R6CEntities;
import r6c.r6cmod.item.R6CItems;
import r6c.r6cmod.networking.R6CPacketHandler;

@Mod.EventBusSubscriber(Side.CLIENT)
public class ClientProxy extends CommonProxy {

    public static boolean hideHand = false;
    private static Entity renderViewEntity;

    @SubscribeEvent
    public static void worldLoad(FMLNetworkEvent.ClientConnectedToServerEvent event) {
        hideHand = false;
    }

    @SubscribeEvent
    public static void worldUnload(FMLNetworkEvent.ClientDisconnectionFromServerEvent event) {
        Minecraft mc = Minecraft.getMinecraft();
        mc.player.dismountRidingEntity();
    }

    @SubscribeEvent
    public static void renderHand(RenderHandEvent event) {
        if(hideHand) {
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public static void renderPlayerPre(RenderPlayerEvent.Pre event) {
        renderViewEntity = event.getRenderer().getRenderManager().renderViewEntity;
        event.getRenderer().getRenderManager().renderViewEntity = event.getEntity();
    }

    @SubscribeEvent
    public static void renderPlayerPost(RenderPlayerEvent.Post event) {
        event.getRenderer().getRenderManager().renderViewEntity = renderViewEntity;
        renderViewEntity = null;
    }

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        super.preInit(event);
    }

    @Override
    public void init(FMLInitializationEvent event) {
        super.init(event);
        R6CEntities.init();
    }

    @Override
    public void postInit(FMLPostInitializationEvent event) {
        super.postInit(event);
    }

    @SubscribeEvent
    public static void registerRenders(ModelRegistryEvent event) {
        R6CItems.registerRenders(event);
    }
}
