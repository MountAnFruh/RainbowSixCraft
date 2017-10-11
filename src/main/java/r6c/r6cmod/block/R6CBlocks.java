package r6c.r6cmod.block;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import r6c.r6cmod.R6CMod;

import java.util.ArrayList;
import java.util.List;

public class R6CBlocks {

    private static List<Block> r6cBlocks = new ArrayList<>();

    public static void preInit() {
        preInitItems();
    }

    private static void preInitItems() {
        for(Block block : r6cBlocks) {
            block.setCreativeTab(R6CMod.tabR6CMod);
        }
    }

    public static void registerBlocks(RegistryEvent.Register<Block> event) {

    }

    public static void registerRenders(ModelRegistryEvent event) {

    }

    public static void registerRender(Block block) {
        ModelLoader.setCustomModelResourceLocation(new ItemBlock(block), 0
                , new ModelResourceLocation(block.getRegistryName(), "inventory"));
    }

}
