package r6c.r6cmod.item;

import net.minecraft.client.Minecraft;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
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

import javax.annotation.Nullable;
import java.util.List;

public class ItemDroneTerminal extends Item {

    public ItemDroneTerminal(String name) {
        this.setUnlocalizedName(name);
        this.setRegistryName(name);
        this.setMaxStackSize(1);
    }

    private NBTTagCompound getNBT(ItemStack stack) {
        NBTTagCompound nbtTag = this.getNBTShareTag(stack);
        if(nbtTag == null) {
            nbtTag = new NBTTagCompound();
            stack.setTagCompound(nbtTag);
        }
        return nbtTag;
    }

    @SideOnly(value = Side.CLIENT)
    private void displayGUIDroneTerminal(World worldIn, EntityPlayer player, EnumHand handIn) {
        Minecraft.getMinecraft().displayGuiScreen(new GUIDroneTerminal(worldIn,player,handIn));
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer player, EnumHand handIn) {
        ItemStack stack = player.getHeldItem(handIn);
        NBTTagCompound nbtTag = getNBT(stack);
        if(!nbtTag.hasUniqueId("ownerID")) {
            nbtTag.setUniqueId("ownerID", player.getUniqueID());
        }
        stack.setTagCompound(nbtTag);
        displayGUIDroneTerminal(worldIn, player, handIn);
        return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, stack);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        NBTTagCompound nbtTag = getNBT(stack);
        tooltip.add("A Terminal for a Drone");
        tooltip.add("Owner: " + (nbtTag.hasUniqueId("ownerID") ? nbtTag.getUniqueId("ownerID") : "not available"));
        super.addInformation(stack, worldIn, tooltip, flagIn);
    }

    @Override
    public EnumRarity getRarity(ItemStack stack) {
        return EnumRarity.RARE;
    }
}
