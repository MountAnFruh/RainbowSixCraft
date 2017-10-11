package r6c.r6cmod.entity;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import r6c.r6cmod.client.animation.AnimationHandlerDrone;
import r6c.r6cmod.entity.ai.EntityAIDronePlayerControl;
import r6c.r6cmod.item.ItemDroneTerminal;
import r6c.r6cmod.mcalibrary.MCACommonLibrary.IMCAnimatedEntity;
import r6c.r6cmod.mcalibrary.MCACommonLibrary.animation.AnimationHandler;

import javax.annotation.Nullable;

public class EntityDrone extends EntityLiving implements IMCAnimatedEntity {

    protected static final String NAME = "r6c_entity_drone";
    protected AnimationHandler animHandler = new AnimationHandlerDrone(this);
    private ItemDroneTerminal terminal = null;

    public EntityDrone(World worldIn) {
        super(worldIn);
        this.tasks.addTask(0, new EntityAIDronePlayerControl(this));
        this.setSize(0.4F,0.2F);
        this.experienceValue = 200;
    }

    @Override
    public boolean isAIDisabled() {
        return false;
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(1.0);
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(1.0);
        this.getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(0.0);
    }

    @Override
    public void setMoveForward(float amount) {
        onMove(amount);
        super.setMoveForward(amount);
    }

    @Override
    public void setMoveStrafing(float amount) {
        onMove(amount);
        super.setMoveStrafing(amount);
    }

    @Override
    public void setMoveVertical(float amount) {
        onMove(amount);
        super.setMoveVertical(amount);
    }

    protected void onMove(float amount) {
        if(amount != 0) {
            if (!animHandler.isAnimationActive("driveAnimation")) {
                animHandler.activateAnimation("driveAnimation", 0);
            }
        } else {
            if (animHandler.isAnimationActive("driveAnimation")) {
                animHandler.stopAnimation("driveAnimation");
            }
        }
    }

    @Override
    protected boolean processInteract(EntityPlayer player, EnumHand hand) {
        return true;
    }

    @Override
    protected Item getDropItem() {
        return Items.IRON_INGOT;
    }

    protected void dropRareDrop(int item) {
        this.dropItem(Items.GLOWSTONE_DUST, 1);
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return super.getAmbientSound();
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return super.getDeathSound();
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource p_184601_1_) {
        return super.getHurtSound(p_184601_1_);
    }

    @Override
    public AnimationHandler getAnimationHandler() {
        return animHandler;
    }

    public ItemDroneTerminal getTerminal() {
        return terminal;
    }
}
