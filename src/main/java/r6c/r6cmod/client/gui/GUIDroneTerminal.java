package r6c.r6cmod.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import r6c.r6cmod.R6CMod;

import java.io.IOException;

public class GUIDroneTerminal extends GuiScreen {

    public final int EXITBUTTON = 0;

    private final ResourceLocation texture = new ResourceLocation(R6CMod.MODID, "textures/gui/tablet_texture.png");
    private int guiWidth = 256;
    private int guiHeight = 178;
    private int guiX = 0;
    private int guiY = 0;

    private GuiButton bExit;

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawDefaultBackground();
        Minecraft.getMinecraft().renderEngine.bindTexture(texture);
        this.drawTexturedModalRect(guiX,guiY,0,0,guiWidth,guiHeight);
        this.drawString(fontRenderer, "Drone Terminal", guiX + 18, guiY + 15, 0xFFFFFF);
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    public void initGui() {
        guiX = (width / 2) - guiWidth / 2;
        guiY = (height / 2) - guiHeight / 2;
        buttonList.clear();
        buttonList.add(bExit = new GuiButton(EXITBUTTON, guiX + 18, guiY + guiHeight - 35, fontRenderer.getStringWidth("Exit") + 20, 20, "Exit"));
        super.initGui();
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
