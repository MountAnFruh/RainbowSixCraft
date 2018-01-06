package r6c.r6cmod.client.animation;

import r6c.r6cmod.mcalibrary.MCACommonLibrary.IMCAnimatedEntity;
import r6c.r6cmod.mcalibrary.MCACommonLibrary.animation.AnimationHandler;
import r6c.r6cmod.mcalibrary.MCACommonLibrary.animation.Channel;

import java.util.HashMap;

public class AnimationHandlerDrone extends AnimationHandler {

    public static final String DRIVEANIMATION = "driveAnimation";
    public static final String BACKDRIVEANIMATION = "backdriveAnimation";

    /** Map with all the animations. */
    public static HashMap<String, Channel> animChannels = new HashMap<String, Channel>();

    static
    {
        animChannels.put("driveAnimation", new ChannelDriveAnimation(DRIVEANIMATION, 30.0F, 60, Channel.LOOP));
        animChannels.put("backdriveAnimation", new ChannelBackDriveAnimation(BACKDRIVEANIMATION, 30.0F, 60, Channel.LOOP));
    }

    public AnimationHandlerDrone(IMCAnimatedEntity entity) {
        super(entity);
    }

    @Override
    public void activateAnimation(String name, float startingFrame) {
        super.activateAnimation(animChannels, name, startingFrame);
    }

    @Override
    public void stopAnimation(String name) {
        super.stopAnimation(animChannels, name);
    }

    @Override
    public void fireAnimationEventClientSide(Channel anim, float prevFrame, float frame) {
    }

    @Override
    public void fireAnimationEventServerSide(Channel anim, float prevFrame, float frame) {
    }
}
