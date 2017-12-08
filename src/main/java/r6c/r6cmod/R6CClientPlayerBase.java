package r6c.r6cmod;

import api.player.client.ClientPlayerAPI;
import api.player.client.ClientPlayerBase;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.MoverType;
import net.minecraft.util.DamageSource;

public class R6CClientPlayerBase extends ClientPlayerBase {

    public R6CClientPlayerBase(ClientPlayerAPI clientPlayerAPI) {
        super(clientPlayerAPI);
    }

}
