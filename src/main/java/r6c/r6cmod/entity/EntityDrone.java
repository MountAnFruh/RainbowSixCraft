package r6c.r6cmod.entity;

import com.sun.javafx.geom.Vec3f;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityMoveHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.play.client.CPacketEntityAction;
import net.minecraft.network.play.client.CPacketInput;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.network.play.client.CPacketVehicleMove;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.*;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import r6c.r6cmod.client.animation.AnimationHandlerDrone;
import r6c.r6cmod.item.ItemDroneTerminal;
import r6c.r6cmod.item.R6CItems;
import r6c.r6cmod.mcalibrary.MCACommonLibrary.IMCAnimatedEntity;
import r6c.r6cmod.mcalibrary.MCACommonLibrary.animation.AnimationHandler;
import r6c.r6cmod.proxy.ClientProxy;

import javax.annotation.Nullable;
import java.util.UUID;

public class EntityDrone extends EntityLiving implements IMCAnimatedEntity {

    protected static final String NAME = "r6c_entity_drone";
    protected AnimationHandler animHandler = new AnimationHandlerDrone(this);

    private int jumpCooldown;
    private final int maxJumpCooldown = 30;

    public EntityDrone(World worldIn) {
        super(worldIn);
        this.setSize(0.4F,0.2F);
        this.experienceValue = 200;
        this.jumpCooldown = 0;
    }

    @Override
    public boolean shouldRiderSit() {
        return false;
    }

    @Override
    public boolean canBeSteered() {
        Entity entity = this.getControllingPassenger();
        return entity instanceof EntityLivingBase;
    }

    @Override
    protected boolean canDespawn() {
        return false;
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.5D);
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(1.0D);
        this.getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(0.0D);
    }

    @Override
    public boolean attackEntityFrom(DamageSource source, float amount) {
        if(source == DamageSource.FALL) {
            return false;
        } else if(source == DamageSource.FLY_INTO_WALL) {
            return false;
        } else if(source == DamageSource.DROWN) {
            return false;
        } else if(source == DamageSource.IN_WALL) {
            return false;
        }
        return super.attackEntityFrom(source, amount);
    }

    @Override
    @SideOnly(value = Side.CLIENT)
    public void onDeath(DamageSource cause) {
        ClientProxy.hideHand = false;
        Minecraft mc = Minecraft.getMinecraft();
        if(mc.getRenderViewEntity() == this) {
            mc.setRenderViewEntity(mc.player);
        }
        super.onDeath(cause);
    }

    @Override
    public boolean canPickUpLoot() {
        return false;
    }

    @Nullable
    @Override
    public Entity getControllingPassenger() {
        return this.getPassengers().isEmpty() ? null : this.getPassengers().get(0);
    }

    @Override
    public void updatePassenger(Entity passenger) {

    }

    public void mountTo(EntityPlayer player)
    {
        player.rotationYaw = this.rotationYaw;
        player.rotationPitch = this.rotationPitch;

        if (!this.world.isRemote)
        {
            player.startRiding(this);
        }
    }

    public void dismount(EntityPlayer player) {
        if(this.isPassenger(player)) {
            double x = player.posX;
            double y = player.posY;
            double z = player.posZ;

            if (!this.world.isRemote) {
                player.dismountRidingEntity();
            }
            player.setPositionAndUpdate(x,y,z);
        }
    }

    protected boolean isCurrentControllingEntity()
    {
        return Minecraft.getMinecraft().getRenderViewEntity() == this
                && Minecraft.getMinecraft().player != null
                && this.getControllingPassenger() != null
                && this.getControllingPassenger().equals(Minecraft.getMinecraft().player);
    }

    @Override
    public void onLivingUpdate() {
        if(jumpCooldown > 0) {
            jumpCooldown--;
        }
        if(world.isRemote) {
            if (this.isCurrentControllingEntity()) {
                this.setMoveStrafing(Minecraft.getMinecraft().player.movementInput.moveStrafe);
                this.setMoveForward(Minecraft.getMinecraft().player.movementInput.moveForward);
                this.setJumping(Minecraft.getMinecraft().player.movementInput.jump);
                this.setRotationYawHead(Minecraft.getMinecraft().player.rotationYawHead);
                this.setRotation(Minecraft.getMinecraft().player.rotationYaw, Minecraft.getMinecraft().player.rotationPitch);
            }
        }
        if(this.moveForward != 0 || this.moveStrafing != 0 || this.moveVertical != 0) {
            if (!animHandler.isAnimationActive("driveAnimation")) {
                animHandler.activateAnimation("driveAnimation", 0);
            }
        } else {
            if (animHandler.isAnimationActive("driveAnimation")) {
                animHandler.stopAnimation("driveAnimation");
            }
        }
        super.onLivingUpdate();
    }

    @Override
    public void fall(float distance, float damageMultiplier) {
        super.fall(distance, damageMultiplier);
        if(onGround) {
            jumpCooldown = maxJumpCooldown;
        }
    }

    @Override
    protected void jump() {
        if(jumpCooldown <= 0) {
            float multiplier = 1.2f;
            Vec3d lookVec = this.getLookVec();

            if (this.isPotionActive(MobEffects.JUMP_BOOST)) {
                multiplier += (double) ((float) (this.getActivePotionEffect(MobEffects.JUMP_BOOST).getAmplifier() + 1) * 0.1F);
            }
            if (this.rotationPitch < 0) {
                this.addVelocity(lookVec.x * multiplier, lookVec.y * multiplier + 0.2, lookVec.z * multiplier);
            }
        }
    }

    @Override
    public void travel(float strafe, float vertical, float forward) {
        if (this.isBeingRidden() && this.canBeSteered())
        {
            float movementSpeed = (float)this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getAttributeValue();
            EntityLivingBase entitylivingbase = (EntityLivingBase) this.getControllingPassenger();

            forward *= 0.5F * movementSpeed;
            strafe *= 0.4F * movementSpeed;

            if (forward <= 0.0F)
            {
                forward *= 0.8F;
            }

            this.jumpMovementFactor = movementSpeed * 0.1F;

            if (this.canPassengerSteer())
            {
                this.setAIMoveSpeed((float)this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getAttributeValue());
                super.travel(strafe, vertical, forward);
            }
            else if (entitylivingbase instanceof EntityPlayer)
            {
                this.motionX = 0.0D;
                this.motionY = 0.0D;
                this.motionZ = 0.0D;
            }
        }
        else
        {
            this.jumpMovementFactor = 0.02F;
            super.travel(strafe, vertical, forward);
        }
    }

    @Override
    protected boolean processInteract(EntityPlayer player, EnumHand hand) {
        ItemStack stack = player.getHeldItem(hand);
        if(stack.getItem() == R6CItems.r6c_drone_terminal) {
            if(player.isSneaking()) {
                NBTTagCompound nbtTag = stack.getTagCompound();
                if (nbtTag == null) nbtTag = new NBTTagCompound();
                if (nbtTag.hasUniqueId("id")) {
                    UUID terminalID = nbtTag.getUniqueId("id");
                    if(terminalID.equals(this.getUniqueID())) {
                        ItemStack itemDroneStack = new ItemStack(R6CItems.r6c_drone);
                        NBTTagCompound itemDroneNBTTag = itemDroneStack.getTagCompound();
                        if(itemDroneNBTTag == null) itemDroneNBTTag = new NBTTagCompound();
                        itemDroneNBTTag.setUniqueId("id", terminalID);
                        itemDroneStack.setTagCompound(itemDroneNBTTag);
                        player.inventory.setInventorySlotContents(player.inventory.currentItem, itemDroneStack);
                        if(!world.isRemote) {
                            world.removeEntity(this);
                        }
                        return true;
                    }
                }
            } else {
                ItemDroneTerminal terminal = (ItemDroneTerminal) stack.getItem();
                return terminal.processInteract(world, player, hand, this);
            }
        }
        return false;
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
}
