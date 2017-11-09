package r6c.r6cmod.item;

import net.minecraft.client.Minecraft;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import r6c.r6cmod.R6CMod;
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

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer player, EnumHand handIn) {
        ItemStack stack = player.getHeldItem(handIn);
        NBTTagCompound nbtTag = getNBT(stack);
        if(worldIn.isRemote) { // Client Side
            Minecraft.getMinecraft().displayGuiScreen(new GUIDroneTerminal(worldIn,player,handIn));
        } else { // Server Side
            if(!nbtTag.hasUniqueId("ownerID")) {
                nbtTag.setUniqueId("ownerID", player.getUniqueID());
            }
        }
        stack.setTagCompound(nbtTag);
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
