package r6c.r6cmod.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ItemWeaponPistol extends Item
{

    public ItemWeaponPistol(String name)
    {
        this.setUnlocalizedName(name);
        this.setRegistryName(name);
        this.setCreativeTab(CreativeTabs.COMBAT);
        this.setFull3D();
    }

}
