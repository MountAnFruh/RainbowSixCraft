package r6c.r6cmod;

import api.player.client.ClientPlayerAPI;
import api.player.server.ServerPlayerAPI;

public class R6CModLoader {

    public static void loadMods() {

        // Player API
        ClientPlayerAPI.register(R6CMod.MODID, R6CClientPlayerBase.class);
        ServerPlayerAPI.register(R6CMod.MODID, R6CServerPlayerBase.class);
    }
}
