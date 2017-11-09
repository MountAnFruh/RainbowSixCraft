package r6c.r6cmod;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.awt.event.MouseEvent;

public class R6CModEventHandler {

    @SubscribeEvent
    public void keyInput(InputEvent.KeyInputEvent event) {

    }

    @SubscribeEvent
    @SideOnly(value = Side.CLIENT)
    public void mouseEvent(MouseEvent e){
    }
}
