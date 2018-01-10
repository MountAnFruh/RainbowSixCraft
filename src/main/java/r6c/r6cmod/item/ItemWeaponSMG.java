package r6c.r6cmod.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import r6c.r6cmod.entity.EntityBullet;

public class ItemWeaponSMG extends Item
{

    public ItemWeaponSMG(String name) {
        this.setUnlocalizedName(name);
        this.setRegistryName(name);
        this.setCreativeTab(CreativeTabs.COMBAT);
        this.setFull3D();
    }

    public boolean onItemLeftClick(World worldIn, EntityPlayer playerIn, ItemStack itemstack) {
        itemstack.damageItem(10, playerIn);
        if (!worldIn.isRemote)
        {
            double x = playerIn.posX;
            double y = playerIn.posY + playerIn.getEyeHeight();
            double z = playerIn.posZ;
            Vec3d look = playerIn.getLookVec();
            EntityBullet bullet = new EntityBullet(worldIn, playerIn, look.x * 2, look.y * 2, look.z * 2 , x, y, z);
            worldIn.spawnEntity(bullet);
        }
        return true;
    }

    @Override
    public boolean onEntitySwing(EntityLivingBase entityLiving, ItemStack stack) {
        EntityPlayer playerIn = (EntityPlayer)entityLiving;
        return onItemLeftClick(playerIn.world, playerIn, playerIn.getHeldItemMainhand());
    }

    @Override
    public boolean onBlockStartBreak(ItemStack itemstack, BlockPos pos, EntityPlayer player) {
        return false;
    }

    @Override
    public boolean canDestroyBlockInCreative(World world, BlockPos pos, ItemStack stack, EntityPlayer player) {
        return false;
    }
}
