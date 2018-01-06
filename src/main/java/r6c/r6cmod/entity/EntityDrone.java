package r6c.r6cmod.entity;

import com.sun.javafx.geom.Vec3f;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.*;
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
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.*;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeChunkManager;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import r6c.r6cmod.R6CMod;
import r6c.r6cmod.client.animation.AnimationHandlerDrone;
import r6c.r6cmod.item.ItemDroneTerminal;
import r6c.r6cmod.item.R6CItems;
import r6c.r6cmod.mcalibrary.MCACommonLibrary.IMCAnimatedEntity;
import r6c.r6cmod.mcalibrary.MCACommonLibrary.animation.AnimationHandler;
import r6c.r6cmod.networking.R6CPacketHandler;
import r6c.r6cmod.proxy.ClientProxy;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.Set;
import java.util.UUID;

public class EntityDrone extends EntityLiving implements IMCAnimatedEntity {

    protected static final String NAME = "r6c_entity_drone";
    protected AnimationHandler animHandler = new AnimationHandlerDrone(this);

    private int jumpCooldown;
    private final int maxJumpCooldown = 30;

    private int prevChunkX;
    private int prevChunkZ;

    private ForgeChunkManager.Ticket ticket;

    private double prevPosX_Animation;
    private double prevPosY_Animation;
    private double prevPosZ_Animation;

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
        ForgeChunkManager.releaseTicket(this.ticket);
        super.onDeath(cause);
    }

    @Override
    public boolean canPickUpLoot() {
        return false;
    }

    @Override
    public boolean isInRangeToRenderDist(double distance) {
        return true;
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
    public boolean canBreatheUnderwater() {
        return true;
    }

    public void onChunkLoadUpdate() {
        if(this.ticket == null) {
            this.ticket = ForgeChunkManager.requestTicket(R6CMod.instance, this.world, ForgeChunkManager.Type.ENTITY);
            this.ticket.bindEntity(this);
        }
        if(this.ticket == null) return;

        int chunkX = (int)(this.posX / 16.0D);
        int chunkZ = (int)(this.posZ / 16.0D);
        this.chunkCoordX = chunkX;
        this.chunkCoordZ = chunkZ;

        int radius = 1;

        Set<ChunkPos> loadedChunks = this.ticket.getChunkList();

        if(prevChunkX != chunkX || prevChunkZ != chunkZ) {
            if(!this.world.isRemote) {
                for(ChunkPos pos : loadedChunks) {
                    if(pos.x < chunkX - radius || pos.x > chunkX + radius ||
                       pos.z < chunkZ - radius || pos.z > chunkZ + radius) {
                        ForgeChunkManager.unforceChunk(this.ticket, pos);
                    }
                }
                for(int i = -radius;i <= radius;i++) {
                    for(int j = -radius;j <= radius;j++) {
                        boolean containsPos = false;
                        for(ChunkPos pos : loadedChunks) {
                            if(pos.x == chunkX + i && pos.z == chunkZ + j) {
                                containsPos = true;
                            }
                        }
                        if(!containsPos) {
                            ForgeChunkManager.forceChunk(this.ticket, new ChunkPos(chunkX + i, chunkZ + j));
                        }
                    }
                }
            }
        }

        prevChunkX = chunkX;
        prevChunkZ = chunkZ;
    }

    @SideOnly(value = Side.CLIENT)
    public void updateDroneMoveState()
    {
        Minecraft mc = Minecraft.getMinecraft();
        GameSettings gameSettings = mc.gameSettings;

        float moveStrafe = 0.0F;
        float moveForward = 0.0F;
        boolean jump, sneak;

        if (Keyboard.isKeyDown(gameSettings.keyBindForward.getKeyCode()))
        {
            ++moveForward;
        }

        if (Keyboard.isKeyDown(gameSettings.keyBindBack.getKeyCode()))
        {
            --moveForward;
        }

        if (Keyboard.isKeyDown(gameSettings.keyBindLeft.getKeyCode()))
        {
            ++moveStrafe;
        }

        if (Keyboard.isKeyDown(gameSettings.keyBindRight.getKeyCode()))
        {
            --moveStrafe;
        }

        jump = Keyboard.isKeyDown(gameSettings.keyBindJump.getKeyCode());
        sneak = Keyboard.isKeyDown(gameSettings.keyBindSneak.getKeyCode());

        if (sneak)
        {
            moveStrafe = (float)((double)moveStrafe * 0.3D);
            moveForward = (float)((double)moveForward * 0.3D);
        }

        this.setMoveStrafing(moveStrafe);
        this.setMoveForward(moveForward);
        this.setJumping(jump);
        this.setSneaking(sneak);

        int dx = Mouse.getDX();
        int dy = Mouse.getDY();
        float f = mc.gameSettings.mouseSensitivity * 0.6F + 0.2F;
        float f1 = f * f * f * 8.0F;
        float f2 = dx * f1;
        float f3 = dy * f1;
        int i = 1;

        if (mc.gameSettings.invertMouse)
        {
            i = -1;
        }

        float yaw = f2;
        float pitch = f3 * i;

        this.prevRotationYaw = this.rotationYaw;
        this.prevRotationPitch = this.rotationPitch;
        this.setRotation(this.rotationYaw + yaw * 0.15F, this.rotationPitch - pitch * 0.15F);
        this.setRotationYawHead(this.rotationYaw);
    }

    @Override
    public void onLivingUpdate() {
        if(jumpCooldown > 0) {
            jumpCooldown--;
        }
        if(world.isRemote) {
            if (this.isCurrentControllingEntity()) {
                this.updateDroneMoveState();
            }
        }
        double dx = this.posX - this.prevPosX_Animation;
        double dy = this.posY - this.prevPosY_Animation;
        double dz = this.posZ - this.prevPosZ_Animation;
        this.prevPosX_Animation = this.posX;
        this.prevPosY_Animation = this.posY;
        this.prevPosZ_Animation = this.posZ;
        if(dx != 0 || dy != 0 || dz != 0) {
            if (!animHandler.isAnimationActive(AnimationHandlerDrone.DRIVEANIMATION)) {
                animHandler.activateAnimation(AnimationHandlerDrone.DRIVEANIMATION, 0);
            }
        } else {
            animHandler.stopAnimation(AnimationHandlerDrone.DRIVEANIMATION);
        }
//        if(this.moveForward > 0 || this.moveStrafing != 0 || this.moveVertical != 0) {
//            animHandler.stopAnimation(AnimationHandlerDrone.BACKDRIVEANIMATION);
//            if (!animHandler.isAnimationActive(AnimationHandlerDrone.DRIVEANIMATION)) {
//                animHandler.activateAnimation(AnimationHandlerDrone.DRIVEANIMATION, 0);
//            }
//        } else if(this.moveForward < 0){
//            animHandler.stopAnimation(AnimationHandlerDrone.DRIVEANIMATION);
//            if (!animHandler.isAnimationActive(AnimationHandlerDrone.BACKDRIVEANIMATION)) {
//                animHandler.activateAnimation(AnimationHandlerDrone.BACKDRIVEANIMATION, 0);
//            }
//        } else {
//            animHandler.stopAnimation(AnimationHandlerDrone.DRIVEANIMATION);
//            animHandler.stopAnimation(AnimationHandlerDrone.BACKDRIVEANIMATION);
//        }
        super.onLivingUpdate();
        this.onChunkLoadUpdate();
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

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        // TODO: Add Ambient Sound
        return super.getAmbientSound();
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        // TODO: Add Death Sound
        return super.getDeathSound();
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource p_184601_1_) {
        // TODO: Maybe add Hurt Sound if the drone has more health than usual
        return super.getHurtSound(p_184601_1_);
    }

    @Override
    public AnimationHandler getAnimationHandler() {
        return animHandler;
    }
}
