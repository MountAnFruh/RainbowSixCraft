package r6c.r6cmod.client.animation;

import r6c.r6cmod.mcalibrary.MCACommonLibrary.animation.Channel;
import r6c.r6cmod.mcalibrary.MCACommonLibrary.animation.KeyFrame;
import r6c.r6cmod.mcalibrary.MCACommonLibrary.math.Quaternion;
import r6c.r6cmod.mcalibrary.MCACommonLibrary.math.Vector3f;

public class ChannelDriveAnimation extends Channel {
    public ChannelDriveAnimation(String _name, float _fps, int _totalFrames, byte _mode) {
        super(_name, _fps, _totalFrames, _mode);
    }

    @Override
    protected void initializeAllFrames() {
        KeyFrame frame50 = new KeyFrame();
        frame50.modelRenderersRotations.put("leftWheel", new Quaternion(-0.99939084F, 0.0F, 0.0F, 0.034899496F));
        frame50.modelRenderersTranslations.put("leftWheel", new Vector3f(-30.0F, 20.0F, 0.0F));
        frame50.modelRenderersRotations.put("rightWheel", new Quaternion(-0.99939084F, 0.0F, 0.0F, 0.034899496F));
        frame50.modelRenderersTranslations.put("rightWheel", new Vector3f(30.0F, 20.0F, 0.0F));
        keyFrames.put(50, frame50);

        KeyFrame frame20 = new KeyFrame();
        frame20.modelRenderersRotations.put("leftWheel", new Quaternion(0.017452406F, 0.0F, 0.0F, 0.9998477F));
        frame20.modelRenderersTranslations.put("leftWheel", new Vector3f(-30.0F, 20.0F, 0.0F));
        frame20.modelRenderersRotations.put("rightWheel", new Quaternion(0.017452406F, 0.0F, 0.0F, 0.9998477F));
        frame20.modelRenderersTranslations.put("rightWheel", new Vector3f(30.0F, 20.0F, 0.0F));
        keyFrames.put(20, frame20);

        KeyFrame frame40 = new KeyFrame();
        frame40.modelRenderersRotations.put("leftWheel", new Quaternion(0.034899496F, 0.0F, 0.0F, 0.99939084F));
        frame40.modelRenderersTranslations.put("leftWheel", new Vector3f(-30.0F, 20.0F, 0.0F));
        frame40.modelRenderersRotations.put("rightWheel", new Quaternion(0.034899496F, 0.0F, 0.0F, 0.99939084F));
        frame40.modelRenderersTranslations.put("rightWheel", new Vector3f(30.0F, 20.0F, 0.0F));
        keyFrames.put(40, frame40);

        KeyFrame frame10 = new KeyFrame();
        frame10.modelRenderersRotations.put("leftWheel", new Quaternion(-0.9999619F, 0.0F, 0.0F, 0.008726561F));
        frame10.modelRenderersTranslations.put("leftWheel", new Vector3f(-30.0F, 20.0F, 0.0F));
        frame10.modelRenderersRotations.put("rightWheel", new Quaternion(-0.9999619F, 0.0F, 0.0F, 0.008726561F));
        frame10.modelRenderersTranslations.put("rightWheel", new Vector3f(30.0F, 20.0F, 0.0F));
        keyFrames.put(10, frame10);

        KeyFrame frame59 = new KeyFrame();
        frame59.modelRenderersRotations.put("leftWheel", new Quaternion(0.06017748F, 0.0F, 0.0F, 0.9981877F));
        frame59.modelRenderersTranslations.put("leftWheel", new Vector3f(-30.0F, 20.0F, 0.0F));
        frame59.modelRenderersRotations.put("rightWheel", new Quaternion(0.06017748F, 0.0F, 0.0F, 0.9981877F));
        frame59.modelRenderersTranslations.put("rightWheel", new Vector3f(30.0F, 20.0F, 0.0F));
        keyFrames.put(59, frame59);

        KeyFrame frame30 = new KeyFrame();
        frame30.modelRenderersRotations.put("leftWheel", new Quaternion(-0.99965733F, 0.0F, 0.0F, 0.026176995F));
        frame30.modelRenderersTranslations.put("leftWheel", new Vector3f(-30.0F, 20.0F, 0.0F));
        frame30.modelRenderersRotations.put("rightWheel", new Quaternion(-0.99965733F, 0.0F, 0.0F, 0.026176995F));
        frame30.modelRenderersTranslations.put("rightWheel", new Vector3f(30.0F, 20.0F, 0.0F));
        keyFrames.put(30, frame30);
    }
}
