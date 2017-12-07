package r6c.r6cmod.entity;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderFireball;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import r6c.r6cmod.R6CMod;
import r6c.r6cmod.client.renderer.entity.RenderBullet;
import r6c.r6cmod.client.renderer.entity.RenderDrone;

public class R6CEntities {

    private static int id = 0;
    private static final int TRACKINGRANGE = 64;
    private static final int UPDATEFREQUENCY = 1;

    public static void init() {
        Minecraft.getMinecraft().getRenderManager().entityRenderMap.put(EntityDrone.class, new RenderDrone(
                Minecraft.getMinecraft().getRenderManager()

        ));
        Minecraft.getMinecraft().getRenderManager().entityRenderMap.put(EntityBullet.class, new RenderBullet(
                Minecraft.getMinecraft().getRenderManager(), 0.05F
        ));
    }

    public static void registerEntities(RegistryEvent.Register<EntityEntry> event) {
        ResourceLocation droneLocation = new ResourceLocation(R6CMod.MODID, EntityDrone.NAME);
        registerEntity(droneLocation, EntityDrone.class, EntityDrone.NAME);
        ResourceLocation bulletLocation = new ResourceLocation(R6CMod.MODID, EntityBullet.NAME);
        registerEntity(bulletLocation, EntityBullet.class, EntityBullet.NAME);
    }

    public static void registerEntity(ResourceLocation entityLocation, Class entityClass, String entityName) {
        EntityRegistry.registerModEntity(entityLocation, entityClass, entityName, id++,
                R6CMod.instance, TRACKINGRANGE, UPDATEFREQUENCY, false);
    }
}
