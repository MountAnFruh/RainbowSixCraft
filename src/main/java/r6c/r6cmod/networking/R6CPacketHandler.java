package r6c.r6cmod.networking;

import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;
import r6c.r6cmod.R6CMod;

public class R6CPacketHandler {

    public static SimpleNetworkWrapper INSTANCE;

    private static int id = 0;

    public static void preInit(FMLPreInitializationEvent event) {
        INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(R6CMod.MODID);
        // Register Packet Messages here
    }

    private static void registerMessage(Class messageHandler, Class requestMessageType, Side side) {
        INSTANCE.registerMessage(messageHandler, requestMessageType, id++, side);
    }
}
