package r6c.r6cmod.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.Vec3d;
import r6c.r6cmod.entity.EntityDrone;
import r6c.r6cmod.mcalibrary.MCACommonLibrary.MCAVersionChecker;
import r6c.r6cmod.mcalibrary.MCACommonLibrary.animation.AnimationHandler;
import r6c.r6cmod.mcalibrary.MCACommonLibrary.math.Matrix4f;
import r6c.r6cmod.mcalibrary.MCACommonLibrary.math.Quaternion;
import r6c.r6cmod.mcalibrary.MCAModelRenderer;

import java.util.HashMap;

public class ModelDrone extends ModelBase {
    public final int MCA_MIN_REQUESTED_VERSION = 5;
    public HashMap<String, MCAModelRenderer> parts = new HashMap<String, MCAModelRenderer>();

    MCAModelRenderer base;
    MCAModelRenderer leftWheel;
    MCAModelRenderer rightWheel;
    MCAModelRenderer outerBase;
    MCAModelRenderer cube1;
    MCAModelRenderer cube2;
    MCAModelRenderer cube3;
    MCAModelRenderer cube4;
    MCAModelRenderer cube5;
    MCAModelRenderer cube6;
    MCAModelRenderer cube7;
    MCAModelRenderer cube8;
    MCAModelRenderer cube9;
    MCAModelRenderer cube10;
    MCAModelRenderer cube11;
    MCAModelRenderer cube12;
    MCAModelRenderer cube13;
    MCAModelRenderer cube14;
    MCAModelRenderer cube15;
    MCAModelRenderer cube16;
    MCAModelRenderer display;

    public ModelDrone()
    {
        MCAVersionChecker.checkForLibraryVersion(getClass(), MCA_MIN_REQUESTED_VERSION);

        textureWidth = 256;
        textureHeight = 256;

        base = new MCAModelRenderer(this, "base", 0, 40);
        base.mirror = false;
        base.addBox(-30.0F, 15.0F, -5.0F, 60, 10, 10);
        base.setInitialRotationPoint(0.0F, -30.0F, 0.0F);
        base.setInitialRotationMatrix(new Matrix4f().set(new Quaternion(0.0F, 1.0F, 0.0F, -4.371139E-8F)).transpose());
        base.setTextureSize(textureWidth, textureHeight);
        parts.put(base.boxName, base);

        leftWheel = new MCAModelRenderer(this, "leftWheel", 100, 80);
        leftWheel.mirror = false;
        leftWheel.addBox(-12.0F, -5.0F, -5.0F, 10, 10, 10);
        leftWheel.setInitialRotationPoint(-30.0F, 20.0F, 0.0F);
        leftWheel.setInitialRotationMatrix(new Matrix4f().set(new Quaternion(0.0F, 0.0F, 0.0F, 1.0F)).transpose());
        leftWheel.setTextureSize(textureWidth, textureHeight);
        parts.put(leftWheel.boxName, leftWheel);
        base.addChild(leftWheel);

        rightWheel = new MCAModelRenderer(this, "rightWheel", 0, 80);
        rightWheel.mirror = false;
        rightWheel.addBox(2.0F, -5.0F, -5.0F, 10, 10, 10);
        rightWheel.setInitialRotationPoint(30.0F, 20.0F, 0.0F);
        rightWheel.setInitialRotationMatrix(new Matrix4f().set(new Quaternion(0.0F, 0.0F, 0.0F, 1.0F)).transpose());
        rightWheel.setTextureSize(textureWidth, textureHeight);
        parts.put(rightWheel.boxName, rightWheel);
        base.addChild(rightWheel);

        outerBase = new MCAModelRenderer(this, "outerBase", 0, 0);
        outerBase.mirror = false;
        outerBase.addBox(-20.0F, -10.0F, -10.0F, 40, 20, 20);
        outerBase.setInitialRotationPoint(0.0F, 20.0F, 0.0F);
        outerBase.setInitialRotationMatrix(new Matrix4f().set(new Quaternion(0.0F, 0.0F, 0.0F, 1.0F)).transpose());
        outerBase.setTextureSize(textureWidth, textureHeight);
        parts.put(outerBase.boxName, outerBase);
        base.addChild(outerBase);

        cube1 = new MCAModelRenderer(this, "cube1", 100, 60);
        cube1.mirror = false;
        cube1.addBox(-5.0F, -10.0F, -20.0F, 10, 20, 40);
        cube1.setInitialRotationPoint(-5.0F, 0.0F, 0.0F);
        cube1.setInitialRotationMatrix(new Matrix4f().set(new Quaternion(0.0F, 0.0F, 0.0F, 1.0F)).transpose());
        cube1.setTextureSize(textureWidth, textureHeight);
        parts.put(cube1.boxName, cube1);
        leftWheel.addChild(cube1);

        cube2 = new MCAModelRenderer(this, "cube2", 100, 60);
        cube2.mirror = false;
        cube2.addBox(-5.11F, -10.0F, -20.0F, 10, 20, 40);
        cube2.setInitialRotationPoint(-5.0F, 0.0F, 0.0F);
        cube2.setInitialRotationMatrix(new Matrix4f().set(new Quaternion(0.19509032F, 0.0F, 0.0F, 0.98078525F)).transpose());
        cube2.setTextureSize(textureWidth, textureHeight);
        parts.put(cube2.boxName, cube2);
        leftWheel.addChild(cube2);

        cube3 = new MCAModelRenderer(this, "cube3", 100, 60);
        cube3.mirror = false;
        cube3.addBox(-4.9F, -10.0F, -20.0F, 10, 20, 40);
        cube3.setInitialRotationPoint(-5.0F, 0.0F, 0.0F);
        cube3.setInitialRotationMatrix(new Matrix4f().set(new Quaternion(0.38268346F, 0.0F, 0.0F, 0.9238795F)).transpose());
        cube3.setTextureSize(textureWidth, textureHeight);
        parts.put(cube3.boxName, cube3);
        leftWheel.addChild(cube3);

        cube4 = new MCAModelRenderer(this, "cube4", 100, 60);
        cube4.mirror = false;
        cube4.addBox(-5.2F, -10.0F, -20.0F, 10, 20, 40);
        cube4.setInitialRotationPoint(-5.0F, 0.0F, 0.0F);
        cube4.setInitialRotationMatrix(new Matrix4f().set(new Quaternion(0.55557024F, 0.0F, 0.0F, 0.8314696F)).transpose());
        cube4.setTextureSize(textureWidth, textureHeight);
        parts.put(cube4.boxName, cube4);
        leftWheel.addChild(cube4);

        cube5 = new MCAModelRenderer(this, "cube5", 100, 60);
        cube5.mirror = false;
        cube5.addBox(-4.8F, -10.0F, -20.0F, 10, 20, 40);
        cube5.setInitialRotationPoint(-5.0F, 0.0F, 0.0F);
        cube5.setInitialRotationMatrix(new Matrix4f().set(new Quaternion(0.70710677F, 0.0F, 0.0F, 0.70710677F)).transpose());
        cube5.setTextureSize(textureWidth, textureHeight);
        parts.put(cube5.boxName, cube5);
        leftWheel.addChild(cube5);

        cube6 = new MCAModelRenderer(this, "cube6", 100, 60);
        cube6.mirror = false;
        cube6.addBox(-5.3F, -10.0F, -20.0F, 10, 20, 40);
        cube6.setInitialRotationPoint(-5.0F, 0.0F, 0.0F);
        cube6.setInitialRotationMatrix(new Matrix4f().set(new Quaternion(0.8314696F, 0.0F, 0.0F, 0.55557024F)).transpose());
        cube6.setTextureSize(textureWidth, textureHeight);
        parts.put(cube6.boxName, cube6);
        leftWheel.addChild(cube6);

        cube7 = new MCAModelRenderer(this, "cube7", 100, 60);
        cube7.mirror = false;
        cube7.addBox(-4.7F, -10.0F, -20.0F, 10, 20, 40);
        cube7.setInitialRotationPoint(-5.0F, 0.0F, 0.0F);
        cube7.setInitialRotationMatrix(new Matrix4f().set(new Quaternion(0.9238795F, 0.0F, 0.0F, 0.38268343F)).transpose());
        cube7.setTextureSize(textureWidth, textureHeight);
        parts.put(cube7.boxName, cube7);
        leftWheel.addChild(cube7);

        cube8 = new MCAModelRenderer(this, "cube8", 100, 60);
        cube8.mirror = false;
        cube8.addBox(-5.4F, -10.0F, -20.0F, 10, 20, 40);
        cube8.setInitialRotationPoint(-5.0F, 0.0F, 0.0F);
        cube8.setInitialRotationMatrix(new Matrix4f().set(new Quaternion(0.98078525F, 0.0F, 0.0F, 0.19509035F)).transpose());
        cube8.setTextureSize(textureWidth, textureHeight);
        parts.put(cube8.boxName, cube8);
        leftWheel.addChild(cube8);

        cube9 = new MCAModelRenderer(this, "cube9", 0, 60);
        cube9.mirror = false;
        cube9.addBox(-5.0F, -10.0F, -20.0F, 10, 20, 40);
        cube9.setInitialRotationPoint(5.0F, 0.0F, 0.0F);
        cube9.setInitialRotationMatrix(new Matrix4f().set(new Quaternion(0.0F, 0.0F, 0.0F, 1.0F)).transpose());
        cube9.setTextureSize(textureWidth, textureHeight);
        parts.put(cube9.boxName, cube9);
        rightWheel.addChild(cube9);

        cube10 = new MCAModelRenderer(this, "cube10", 0, 60);
        cube10.mirror = false;
        cube10.addBox(-4.9F, -10.0F, -20.0F, 10, 20, 40);
        cube10.setInitialRotationPoint(5.0F, 0.0F, 0.0F);
        cube10.setInitialRotationMatrix(new Matrix4f().set(new Quaternion(0.19509032F, 0.0F, 0.0F, 0.98078525F)).transpose());
        cube10.setTextureSize(textureWidth, textureHeight);
        parts.put(cube10.boxName, cube10);
        rightWheel.addChild(cube10);

        cube11 = new MCAModelRenderer(this, "cube11", 0, 60);
        cube11.mirror = false;
        cube11.addBox(-5.1F, -10.0F, -20.0F, 10, 20, 40);
        cube11.setInitialRotationPoint(5.0F, 0.0F, 0.0F);
        cube11.setInitialRotationMatrix(new Matrix4f().set(new Quaternion(0.38268346F, 0.0F, 0.0F, 0.9238795F)).transpose());
        cube11.setTextureSize(textureWidth, textureHeight);
        parts.put(cube11.boxName, cube11);
        rightWheel.addChild(cube11);

        cube12 = new MCAModelRenderer(this, "cube12", 0, 60);
        cube12.mirror = false;
        cube12.addBox(-4.8F, -10.0F, -20.0F, 10, 20, 40);
        cube12.setInitialRotationPoint(5.0F, 0.0F, 0.0F);
        cube12.setInitialRotationMatrix(new Matrix4f().set(new Quaternion(0.55557024F, 0.0F, 0.0F, 0.8314696F)).transpose());
        cube12.setTextureSize(textureWidth, textureHeight);
        parts.put(cube12.boxName, cube12);
        rightWheel.addChild(cube12);

        cube13 = new MCAModelRenderer(this, "cube13", 0, 60);
        cube13.mirror = false;
        cube13.addBox(-5.2F, -10.0F, -20.0F, 10, 20, 40);
        cube13.setInitialRotationPoint(5.0F, 0.0F, 0.0F);
        cube13.setInitialRotationMatrix(new Matrix4f().set(new Quaternion(0.70710677F, 0.0F, 0.0F, 0.70710677F)).transpose());
        cube13.setTextureSize(textureWidth, textureHeight);
        parts.put(cube13.boxName, cube13);
        rightWheel.addChild(cube13);

        cube14 = new MCAModelRenderer(this, "cube14", 0, 60);
        cube14.mirror = false;
        cube14.addBox(-4.7F, -10.0F, -20.0F, 10, 20, 40);
        cube14.setInitialRotationPoint(5.0F, 0.0F, 0.0F);
        cube14.setInitialRotationMatrix(new Matrix4f().set(new Quaternion(0.8314696F, 0.0F, 0.0F, 0.55557024F)).transpose());
        cube14.setTextureSize(textureWidth, textureHeight);
        parts.put(cube14.boxName, cube14);
        rightWheel.addChild(cube14);

        cube15 = new MCAModelRenderer(this, "cube15", 0, 60);
        cube15.mirror = false;
        cube15.addBox(-5.3F, -10.0F, -20.0F, 10, 20, 40);
        cube15.setInitialRotationPoint(5.0F, 0.0F, 0.0F);
        cube15.setInitialRotationMatrix(new Matrix4f().set(new Quaternion(0.9238795F, 0.0F, 0.0F, 0.38268343F)).transpose());
        cube15.setTextureSize(textureWidth, textureHeight);
        parts.put(cube15.boxName, cube15);
        rightWheel.addChild(cube15);

        cube16 = new MCAModelRenderer(this, "cube16", 0, 60);
        cube16.mirror = false;
        cube16.addBox(-4.6F, -10.0F, -20.0F, 10, 20, 40);
        cube16.setInitialRotationPoint(5.0F, 0.0F, 0.0F);
        cube16.setInitialRotationMatrix(new Matrix4f().set(new Quaternion(0.9799247F, 0.0F, 0.0F, 0.19936794F)).transpose());
        cube16.setTextureSize(textureWidth, textureHeight);
        parts.put(cube16.boxName, cube16);
        rightWheel.addChild(cube16);

        display = new MCAModelRenderer(this, "display", 120, 20);
        display.mirror = false;
        display.addBox(-5.0F, -5.0F, -5.0F, 10, 10, 10);
        display.setInitialRotationPoint(-5.0F, 6.0F, -6.0F);
        display.setInitialRotationMatrix(new Matrix4f().set(new Quaternion(0.0F, 0.0F, 0.0F, 1.0F)).transpose());
        display.setTextureSize(textureWidth, textureHeight);
        parts.put(display.boxName, display);
        outerBase.addChild(display);

    }

    @Override
    public void render(Entity par1Entity, float par2, float par3, float par4, float par5, float par6, float par7)
    {
        EntityDrone entity = (EntityDrone)par1Entity;

        AnimationHandler.performAnimationInModel(parts, entity);

        //Render every non-child part
        base.render(par7);
    }

    @Override
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) {

    }

    public MCAModelRenderer getModelRendererFromName(String name)
    {
        return parts.get(name);
    }
}
