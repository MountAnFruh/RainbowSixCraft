package r6c.r6cmod.item;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityOtherPlayerMP;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import r6c.r6cmod.client.gui.GUIDroneTerminal;
import r6c.r6cmod.client.gui.GUIDroneTerminalDrive;
import r6c.r6cmod.entity.EntityDrone;
import r6c.r6cmod.proxy.ClientProxy;

import javax.annotation.Nullable;
import java.util.List;
import java.util.UUID;

public class ItemDroneTerminal extends Item {

    public final String name;

    public ItemDroneTerminal(String name) {
        this.name = name;
        this.setUnlocalizedName(name);
        this.setRegistryName(name);
        this.setMaxStackSize(1);
    }

    @Override
    public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
//        if(!worldIn.isRemote) {
        if(worldIn.isRemote) {
            NBTTagCompound nbtTag = this.getNBTShareTag(stack);
            if(nbtTag == null) nbtTag = new NBTTagCompound();
            final UUID uniqueID = nbtTag.getUniqueId("id");
            if(worldIn.getEntities(EntityDrone.class, e -> e.getUniqueID().equals(uniqueID)).size() > 0) {
                nbtTag.setBoolean("dronedead",false);
            } else {
                nbtTag.setBoolean("dronedead",true);
            }
        }
        super.onUpdate(stack, worldIn, entityIn, itemSlot, isSelected);
    }

    @Override
    protected boolean isInCreativeTab(CreativeTabs targetTab) {
        return false;
    }

    @SideOnly(value = Side.CLIENT)
    private void displayGUIDroneTerminal(World worldIn, EntityPlayer player, EnumHand handIn, EntityDrone drone) {
        GUIDroneTerminal guiTerminal;
        if(drone == null) {
            guiTerminal = new GUIDroneTerminal(worldIn, player, handIn);
        } else {
            guiTerminal = new GUIDroneTerminal(worldIn, player, handIn, drone);
        }
        Minecraft.getMinecraft().displayGuiScreen(guiTerminal);
    }

    public boolean processInteract(World worldIn, EntityPlayer player, EnumHand hand, EntityDrone drone) {
        displayGUIDroneTerminal(worldIn, player, hand, drone);
        return true;
    }

    @SideOnly(value = Side.CLIENT)
    public void displayDriveScreen(World worldIn, EntityPlayer player, EnumHand handIn, EntityDrone drone) {
        GUIDroneTerminalDrive guiDriveTerminal = new GUIDroneTerminalDrive(worldIn, player, handIn, drone);
        Minecraft.getMinecraft().displayGuiScreen(guiDriveTerminal);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer player, EnumHand handIn) {
        ItemStack stack = player.getHeldItem(handIn);
        NBTTagCompound nbtTag = this.getNBTShareTag(stack);
        if (nbtTag == null) nbtTag = new NBTTagCompound();
        if (!nbtTag.getBoolean("dronedead")) {
            if (nbtTag.hasUniqueId("id")) {
                UUID id = nbtTag.getUniqueId("id");
                List<EntityDrone> drones = worldIn.getEntities(EntityDrone.class, d -> d.getUniqueID().equals(id));
                if (drones.size() > 0) {
                    EntityDrone drone = drones.get(0);
                    List<Entity> passengers = drone.getPassengers();
                    if(passengers.isEmpty()) {
                        drone.mountTo(player);
                        player.moveForward = 0.0F;
                        player.moveStrafing = 0.0F;
                        player.moveVertical = 0.0F;
                        if(worldIn.isRemote) {
                            displayDriveScreen(worldIn, player, handIn, drone);
                        }
                    } else if (drone.isPassenger(player)){
                        drone.dismount(player);
                        drone.setMoveForward(0.0F);
                        drone.setMoveStrafing(0.0F);
                        drone.setMoveVertical(0.0F);
                    }
                }
            }
        }
        return new ActionResult<ItemStack>(EnumActionResult.PASS, stack);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        NBTTagCompound nbtTag = this.getNBTShareTag(stack);
        if (nbtTag == null) nbtTag = new NBTTagCompound();
        tooltip.add("A Terminal for a Drone");
        if(flagIn.isAdvanced()) {
            tooltip.add("ID: " + (nbtTag.hasUniqueId("id") ? nbtTag.getUniqueId("id") : "not set"));
            if(worldIn != null) {
                tooltip.add("Drone Dead?: " + nbtTag.getBoolean("dronedead"));
            }
        }
        super.addInformation(stack, worldIn, tooltip, flagIn);
    }

    @Override
    public EnumRarity getRarity(ItemStack stack) {
        return EnumRarity.RARE;
    }
}
