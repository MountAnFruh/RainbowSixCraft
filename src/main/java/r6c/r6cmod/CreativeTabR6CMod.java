package r6c.r6cmod;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import r6c.r6cmod.item.R6CItems;

public class CreativeTabR6CMod extends CreativeTabs {

    public CreativeTabR6CMod(int index, String label) {
        super(index, label);
    }

    @Override
    public ItemStack getTabIconItem() {
        return new ItemStack(R6CItems.r6c_drone_terminal);
    }
}
