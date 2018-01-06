package r6c.r6cmod.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import r6c.r6cmod.R6CMod;
import r6c.r6cmod.entity.EntityDrone;
import r6c.r6cmod.item.ItemDroneTerminal;
import r6c.r6cmod.item.R6CItems;

import java.io.IOException;
import java.util.UUID;

public class GUIDroneTerminal extends GuiScreen {

    public final int EXITBUTTON = 0;

    private final ResourceLocation texture = new ResourceLocation(R6CMod.MODID, "textures/gui/r6c_drone_terminal_screen.png");
    private int guiWidth = 256;
    private int guiHeight = 192;
    private int guiBorderX = 24;
    private int guiBorderY = 17;
    private int guiX, guiY, minX, minY, maxX, maxY;

    private GuiButton bExit;

    private World worldIn;
    private EntityPlayer player;
    private EnumHand handIn;
    private ItemStack stack;
    private EntityDrone drone;

    private UUID terminalID;

    public GUIDroneTerminal(World worldIn, EntityPlayer player, EnumHand handIn) {
        this(worldIn, player, handIn, null);
    }

    public GUIDroneTerminal(World worldIn, EntityPlayer player, EnumHand handIn, EntityDrone drone) {
        this.worldIn = worldIn;
        this.player = player;
        this.handIn = handIn;
        this.stack = player.getHeldItem(handIn);
        NBTTagCompound nbtTag = stack.getTagCompound();
        if(nbtTag == null) nbtTag = new NBTTagCompound();
        this.drone = drone;
        this.terminalID = nbtTag.getUniqueId("id");
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawDefaultBackground();
        Minecraft.getMinecraft().renderEngine.bindTexture(texture);
        this.drawTexturedModalRect(guiX,guiY,0,0,guiWidth,guiHeight);
        this.drawString(fontRenderer, "Drone Terminal, ID: " + terminalID, minX + 2, minY + 2, 0xFFFFFF);
        if(drone != null) {
            this.drawString(fontRenderer, "Drone-ID: " + drone.getUniqueID(), minX + 2, minY + fontRenderer.FONT_HEIGHT + 4, 0xFFFFFF);
        }
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    public void initGui() {
        super.initGui();
        guiX = (width / 2) - guiWidth / 2;
        guiY = (height / 2) - guiHeight / 2;
        minX = guiX + guiBorderX;
        maxX = guiX + guiWidth - guiBorderX;
        minY = guiY + guiBorderY;
        maxY = guiY + guiHeight - guiBorderY;
        buttonList.clear();
        int buttonHeight = 20;
        buttonList.add(bExit = new GuiButton(EXITBUTTON, minX + 2, maxY - buttonHeight - 2, fontRenderer.getStringWidth("Exit") + 20, buttonHeight, "Exit"));
    }

    public void updateButtons() {

    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        switch (button.id) {
            case EXITBUTTON:
                Minecraft.getMinecraft().displayGuiScreen(null);
                break;
        }
        this.updateButtons();
        super.actionPerformed(button);
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
        super.onGuiClosed();
    }
}
