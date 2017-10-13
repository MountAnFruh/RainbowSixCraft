package r6c.r6cmod.item;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import r6c.r6cmod.R6CMod;

import java.util.ArrayList;
import java.util.List;

public class R6CItems {

    private static List<Item> r6cItems = new ArrayList<>();
    public static Item r6c_drone_terminal;
    public static Item r6c_drone;
    public static Item r6c_g52tactical;

    public static void preInit() {
        r6cItems.add(r6c_drone_terminal = new ItemDroneTerminal("r6c_drone_terminal"));
        r6cItems.add(r6c_drone = new ItemDrone("r6c_drone"));
        r6cItems.add(r6c_g52tactical = new ItemG52Tactical("r6c_g52tactical"));
        preInitItems();
    }

    private static void preInitItems() {
        for(Item item : r6cItems) {
            item.setCreativeTab(R6CMod.tabR6CMod);
        }
    }

    public static void registerItems(RegistryEvent.Register<Item> event) {
        event.getRegistry().registerAll(r6cItems.toArray(new Item[0]));
    }

    public static void registerRenders(ModelRegistryEvent event) {
        for(Item item : r6cItems) {
            registerRender(item);
        }
    }

    public static void registerRender(Item item) {
        ModelLoader.setCustomModelResourceLocation(item, 0
                , new ModelResourceLocation(item.getRegistryName(), "inventory"));
    }
}
