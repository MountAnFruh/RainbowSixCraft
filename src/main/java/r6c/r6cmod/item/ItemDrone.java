package r6c.r6cmod.item;

import net.minecraft.client.renderer.EnumFaceDirection;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
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
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if(!worldIn.isRemote) {
            EntityDrone drone = new EntityDrone(worldIn);
            double x = pos.getX() + hitX + facing.getFrontOffsetX() * (drone.width - 0.1);
            double y = pos.getY() + hitY + facing.getFrontOffsetY() * (drone.height + 0.1);
            double z = pos.getZ() + hitZ + facing.getFrontOffsetZ() * (drone.width - 0.1);
            drone.setPosition(x,y,z);
            worldIn.spawnEntity(drone);
            return EnumActionResult.SUCCESS;
        } else {
            return EnumActionResult.FAIL;
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
