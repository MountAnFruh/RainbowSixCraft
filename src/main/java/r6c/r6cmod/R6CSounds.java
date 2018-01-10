package r6c.r6cmod;

import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.event.RegistryEvent;

import java.util.ArrayList;
import java.util.List;

public class R6CSounds {

    private static List<SoundEvent> r6cSounds = new ArrayList<>();
    public static SoundEvent r6c_drone_driving = new SoundEvent(new ResourceLocation(R6CMod.MODID, "r6c_drone_driving"))
            .setRegistryName("r6c_drone_driving");
    public static SoundEvent r6c_drone_floor_landing = new SoundEvent(new ResourceLocation(R6CMod.MODID, "r6c_drone_floor_landing"))
            .setRegistryName("r6c_drone_floor_landing");
    public static SoundEvent r6c_drone_jump_reload = new SoundEvent(new ResourceLocation(R6CMod.MODID, "r6c_drone_jump_reload"))
            .setRegistryName("r6c_drone_jump_reload");
    public static SoundEvent r6c_drone_jumping = new SoundEvent(new ResourceLocation(R6CMod.MODID, "r6c_drone_jumping"))
            .setRegistryName("r6c_drone_jumping");
    public static SoundEvent r6c_drone_switch = new SoundEvent(new ResourceLocation(R6CMod.MODID, "r6c_drone_switch"))
            .setRegistryName("r6c_drone_switch");
    public static SoundEvent r6c_drone_destroyed = new SoundEvent(new ResourceLocation(R6CMod.MODID, "r6c_drone_destroyed"))
            .setRegistryName("r6c_drone_destroyed");
    public static SoundEvent r6c_g52tactical_reload = new SoundEvent(new ResourceLocation(R6CMod.MODID, "r6c_g52tactical_reload"))
            .setRegistryName("r6c_g52tactical_reload");
    public static SoundEvent r6c_g52tactical_use = new SoundEvent(new ResourceLocation(R6CMod.MODID, "r6c_g52tactical_use"))
            .setRegistryName("r6c_g52tactical_use");

    public static void registerSounds(RegistryEvent.Register<SoundEvent> event) {
        r6cSounds.add(r6c_drone_driving);
        r6cSounds.add(r6c_drone_floor_landing);
        r6cSounds.add(r6c_drone_jump_reload);
        r6cSounds.add(r6c_drone_jumping);
        r6cSounds.add(r6c_drone_switch);
        r6cSounds.add(r6c_drone_destroyed);
        r6cSounds.add(r6c_g52tactical_reload);
        r6cSounds.add(r6c_g52tactical_use);
        event.getRegistry().registerAll(r6cSounds.toArray(new SoundEvent[0]));
    }
}
