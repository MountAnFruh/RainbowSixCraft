package r6c.r6cmod;

import api.player.client.ClientPlayerAPI;
import api.player.client.ClientPlayerBase;
import api.player.server.ServerPlayerAPI;
import api.player.server.ServerPlayerBase;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.util.DamageSource;

public class R6CServerPlayerBase extends ServerPlayerBase {

    public R6CServerPlayerBase(ServerPlayerAPI serverPlayerAPI) {
        super(serverPlayerAPI);
    }

}
