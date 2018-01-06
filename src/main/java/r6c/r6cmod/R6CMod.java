package r6c.r6cmod;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.common.ForgeChunkManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import r6c.r6cmod.proxy.CommonProxy;

@Mod(modid = R6CMod.MODID, name = R6CMod.NAME, version = R6CMod.VERSION)
public class R6CMod {

    public static final String MODID = "r6cmod";
    public static final String VERSION = "0.1";
    public static final String NAME = "Rainbow Six | Craft";

    @SidedProxy(clientSide = "r6c.r6cmod.proxy.ClientProxy"
              , serverSide = "r6c.r6cmod.proxy.CommonProxy")
    public static CommonProxy proxy;

    @Mod.Instance
    public static R6CMod instance;

    public static CreativeTabR6CMod tabR6CMod;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        tabR6CMod = new CreativeTabR6CMod(CreativeTabs.getNextID(), "tab_r6c");
        ForgeChunkManager.setForcedChunkLoadingCallback(R6CMod.instance, null);
        proxy.preInit(event);
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init(event);
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        proxy.postInit(event);
    }
}
