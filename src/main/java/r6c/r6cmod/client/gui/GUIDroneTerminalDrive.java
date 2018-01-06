package r6c.r6cmod.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import org.lwjgl.input.Mouse;
import r6c.r6cmod.entity.EntityDrone;
import r6c.r6cmod.item.ItemDroneTerminal;
import r6c.r6cmod.proxy.ClientProxy;

import java.io.IOException;

public class GUIDroneTerminalDrive extends GuiScreen {

    public World worldIn;
    public EntityPlayer player;
    public EnumHand handIn;
    public ItemStack stack;
    public EntityDrone drone;

    public GUIDroneTerminalDrive(World worldIn, EntityPlayer player, EnumHand handIn, EntityDrone drone) {
        this.worldIn = worldIn;
        this.player = player;
        this.handIn = handIn;
        this.stack = player.getHeldItem(handIn);
        this.drone = drone;
        Minecraft mc = Minecraft.getMinecraft();
        ClientProxy.hideHand = true;
        mc.setRenderViewEntity(drone);
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    public void initGui() {
        super.initGui();
        Mouse.setGrabbed(true);
    }

    @Override
    protected void keyTyped(char typedChar, int keyCode) throws IOException {
        if(typedChar == 'e') {
            Minecraft.getMinecraft().displayGuiScreen(null);
        }
        super.keyTyped(typedChar, keyCode);
    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }

    @Override
    public void onGuiClosed() {
        Minecraft mc = Minecraft.getMinecraft();
        ClientProxy.hideHand = false;
        mc.setRenderViewEntity(player);
        Minecraft.getMinecraft().playerController.processRightClick(this.player, this.worldIn, this.handIn);
        super.onGuiClosed();
    }

}
