package r6c.r6cmod.item;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import r6c.r6cmod.entity.EntityDrone;

import javax.annotation.Nullable;
import java.util.List;

public class ItemDrone extends Item {

    public ItemDrone(String name) {
        this.setUnlocalizedName(name);
        this.setRegistryName(name);
        this.setMaxStackSize(1);
        this.setFull3D();
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        if(!worldIn.isRemote) {
            EntityDrone drone = new EntityDrone(worldIn);
            double x = playerIn.posX;
            double y = playerIn.posY + playerIn.getEyeHeight();
            double z = playerIn.posZ;
            drone.setPositionAndRotation(x,y,z, playerIn.getRotationYawHead(), playerIn.rotationPitch);
            Vec3d lookVec = playerIn.getLookVec();
            drone.addVelocity(lookVec.x, lookVec.y, lookVec.z);
            worldIn.spawnEntity(drone);
            return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
        } else {
            return new ActionResult<ItemStack>(EnumActionResult.PASS, playerIn.getHeldItem(handIn));
        }
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add("A Drone");
        super.addInformation(stack, worldIn, tooltip, flagIn);
    }

    @Override
    public EnumRarity getRarity(ItemStack stack) {
        return EnumRarity.RARE;
    }

}
