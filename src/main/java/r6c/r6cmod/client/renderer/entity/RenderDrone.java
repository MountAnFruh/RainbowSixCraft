package r6c.r6cmod.client.renderer.entity;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import r6c.r6cmod.R6CMod;
import r6c.r6cmod.client.model.ModelDrone;
import r6c.r6cmod.entity.EntityDrone;

public class RenderDrone extends RenderLiving<EntityDrone> {

    public static final ResourceLocation DRONE_TEXTURE = new ResourceLocation(R6CMod.MODID, "textures/items/r6c_drone.png");
    public static ModelDrone modelDrone = new ModelDrone();
    public static float modelHeight = 3.3F;

    public RenderDrone(RenderManager rendermanagerIn) {
        super(rendermanagerIn, modelDrone, 0.1F);
    }

    @Override
    public void doRender(EntityDrone _entity, double posX, double posY, double posZ, float var8, float var9) {
        EntityDrone entity = (EntityDrone) _entity;

        GL11.glPushMatrix();
        GL11.glDisable(GL11.GL_CULL_FACE);
        super.doRender(_entity, posX, posY, posZ, var8, var9);
        GL11.glEnable(GL11.GL_CULL_FACE);
        GL11.glPopMatrix();
    }

    @Override
    protected void preRenderCallback(EntityDrone entitylivingbaseIn, float partialTickTime) {
        GL11.glScalef(0.1F,0.1F,0.1F);
        GL11.glRotatef(180F, 0, 1F, 0F);
        GL11.glRotatef(180F, 0, 0, 1F);
        GL11.glTranslatef(0, modelHeight, 0);
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityDrone var1) {
        return DRONE_TEXTURE;
    }
}
