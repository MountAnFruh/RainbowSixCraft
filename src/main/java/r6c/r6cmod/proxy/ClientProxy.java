package r6c.r6cmod.proxy;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.network.play.server.SPacketCombatEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.relauncher.Side;
import r6c.r6cmod.R6CModEventHandler;
import r6c.r6cmod.block.R6CBlocks;
import r6c.r6cmod.entity.R6CEntities;
import r6c.r6cmod.item.ItemWeaponPistol;
import r6c.r6cmod.item.R6CItems;

@Mod.EventBusSubscriber(Side.CLIENT)
public class ClientProxy extends CommonProxy {

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        super.preInit(event);
    }

    @Override
    public void init(FMLInitializationEvent event) {
        super.init(event);
        R6CEntities.init();
    }

    @Override
    public void postInit(FMLPostInitializationEvent event) {
        super.postInit(event);
    }

    @SubscribeEvent
    public static void registerRenders(ModelRegistryEvent event) {
        R6CItems.registerRenders(event);
    }

//    @SubscribeEvent(priority = EventPriority.HIGHEST, receiveCanceled = true)
//    public static void onLeftClickEmpty(PlayerInteractEvent.LeftClickEmpty event) {
//        if(event.getItemStack().getItem() == R6CItems.r6c_weaponpistol) {
//            ItemWeaponPistol pistol = (ItemWeaponPistol) event.getItemStack().getItem();
//            pistol.onItemLeftClick(event.getWorld(), event.getEntityPlayer(), event.getItemStack());
//            event.setResult(Event.Result.DENY);
//            event.setPhase(EventPriority.LOWEST);
//        }
//    }


}
