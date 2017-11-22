package r6c.r6cmod.item;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import r6c.r6cmod.entity.EntityDrone;

import javax.annotation.Nullable;
import java.util.List;
import java.util.UUID;

public class ItemDrone extends Item {

    public ItemDrone(String name) {
        this.setUnlocalizedName(name);
        this.setRegistryName(name);
        this.setMaxStackSize(1);
        this.setFull3D();
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        ItemStack stack = playerIn.getHeldItem(handIn);

        playerIn.inventory.removeStackFromSlot(playerIn.inventory.currentItem);
        ItemStack terminalStack = new ItemStack(R6CItems.r6c_drone_terminal);

        if(!worldIn.isRemote) {
            NBTTagCompound nbtTagStack = this.getNBTShareTag(stack);
            if (nbtTagStack == null) nbtTagStack = new NBTTagCompound();

            EntityDrone drone = new EntityDrone(worldIn);
            if(nbtTagStack.hasUniqueId("id")) {
                drone.setUniqueId(nbtTagStack.getUniqueId("id"));
            }
            double x = playerIn.posX;
            double y = playerIn.posY + playerIn.getEyeHeight();
            double z = playerIn.posZ;
            drone.setPositionAndRotation(x, y, z, playerIn.getRotationYawHead(), playerIn.rotationPitch);
            Vec3d lookVec = playerIn.getLookVec();
            drone.addVelocity(lookVec.x, lookVec.y, lookVec.z);
            worldIn.spawnEntity(drone);

            NBTTagCompound nbtTagNewStack = this.getNBTShareTag(terminalStack);
            if(nbtTagNewStack == null) nbtTagNewStack = new NBTTagCompound();
            nbtTagNewStack.setUniqueId("id", drone.getUniqueID());
            terminalStack.setTagCompound(nbtTagNewStack);
        }

        terminalStack.setCount(1);
        playerIn.inventory.setInventorySlotContents(playerIn.inventory.currentItem, terminalStack);

        return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, stack);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        NBTTagCompound nbtTag = this.getNBTShareTag(stack);
        if (nbtTag == null) nbtTag = new NBTTagCompound();
        tooltip.add("A Drone");
        if(flagIn.isAdvanced()) {
            tooltip.add("ID: " + (nbtTag.hasUniqueId("id") ? nbtTag.getUniqueId("id") : "not set"));
        }
        super.addInformation(stack, worldIn, tooltip, flagIn);
    }

    @Override
    public EnumRarity getRarity(ItemStack stack) {
        return EnumRarity.RARE;
    }

}
