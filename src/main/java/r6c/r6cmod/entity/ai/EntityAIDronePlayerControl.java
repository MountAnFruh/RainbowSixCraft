package r6c.r6cmod.entity.ai;

import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.player.EntityPlayer;
import r6c.r6cmod.entity.EntityDrone;
import r6c.r6cmod.item.ItemDroneTerminal;

public class EntityAIDronePlayerControl extends EntityAIBase {

    private final EntityDrone entity;
    private EntityPlayer player = null;
    private ItemDroneTerminal terminal = null;

    public EntityAIDronePlayerControl(EntityDrone entity) {
        this.entity = entity;
        setMutexBits(1);
    }

    @Override
    public boolean shouldExecute() {
        return false;
    }

    @Override
    public void startExecuting() {
        System.out.println("EntityAIPlayerControl starts executing for player " + player.getName());
    }

    @Override
    public boolean shouldContinueExecuting() {
        return super.shouldContinueExecuting();
    }
}
