package r6c.r6cmod.proxy;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.event.RenderHandEvent;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import r6c.r6cmod.entity.R6CEntities;
import r6c.r6cmod.item.R6CItems;

@Mod.EventBusSubscriber(Side.CLIENT)
public class ClientProxy extends CommonProxy {

    public static boolean hideHand = false;
    private static Entity renderViewEntity;

    @SubscribeEvent
    public static void worldLoad(FMLNetworkEvent.ClientConnectedToServerEvent event) {
        hideHand = false;
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

    @SubscribeEvent
    public static void breakBlock(BlockEvent.BreakEvent event) {
        Minecraft mc = Minecraft.getMinecraft();
        if(!(mc.getRenderViewEntity() instanceof EntityPlayerSP)) {
            event.setCanceled(true);
        }
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
