package r6c.r6cmod.entity;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import r6c.r6cmod.R6CMod;
import r6c.r6cmod.client.renderer.entity.RenderDrone;

public class R6CEntities {

    private static int id = 0;
    private static final int TRACKINGRANGE = 64;
    private static final int UPDATEFREQUENCY = 3;

    public static void init() {
        Minecraft.getMinecraft().getRenderManager().entityRenderMap.put(EntityDrone.class, new RenderDrone(
                Minecraft.getMinecraft().getRenderManager()
        ));
    }

    public static void registerEntities(RegistryEvent.Register<EntityEntry> event) {
        ResourceLocation droneLocation = new ResourceLocation(R6CMod.MODID, EntityDrone.NAME);
        registerEntity(droneLocation, EntityDrone.class, EntityDrone.NAME);
    }

    public static void registerEntity(ResourceLocation entityLocation, Class entityClass, String entityName) {
        EntityRegistry.registerModEntity(entityLocation, entityClass, entityName, id++,
                R6CMod.instance, TRACKINGRANGE, UPDATEFREQUENCY, true);
    }
}
