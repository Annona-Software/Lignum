package com.annonasoftware.games.woodworking.objects.item;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableList.Builder;

import net.dries007.tfc.api.registries.TFCRegistries;
import net.dries007.tfc.api.types.Tree;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;

import static net.dries007.tfc.objects.CreativeTabsTFC.*;
import com.annonasoftware.games.woodworking.ConfigFlasks;
import com.annonasoftware.games.woodworking.objects.block.BlocksWoodworking;

import static com.annonasoftware.games.woodworking.Woodworking.MOD_ID;


@Mod.EventBusSubscriber(modid = MOD_ID)
@GameRegistry.ObjectHolder(MOD_ID)
public class ItemsWoodworking {

    public static ItemBase leatherSide = new ItemBase("leather_side").setCreativeTab(CT_MISC);
    public static ItemBase bladder = new ItemBase("bladder").setCreativeTab(CT_MISC);
    public static ItemLeatherFlask leatherFlask = new ItemLeatherFlask();
    public static ItemBase unfinishedFlask = new ItemBase("unfinished_iron_flask").setCreativeTab(CT_METAL);
    public static ItemBase brokenLeatherFlask = new ItemBase("broken_leather_flask").setCreativeTab(CT_MISC);
    public static ItemBase brokenIronFlask = new ItemBase("broken_iron_flask").setCreativeTab(CT_METAL);
    public static ItemIronFlask ironFlask = new ItemIronFlask();

    private static ImmutableList<Item> allSimpleItems;

    public static ImmutableList<Item> getAllSimpleItems()
    {
        return allSimpleItems;
    }

    @SubscribeEvent
    public static void register(RegistryEvent.Register<Item> event)
    {
        IForgeRegistry<Item> r = event.getRegistry();
        Builder<Item> simpleItems = ImmutableList.builder();


        r.registerAll(
                leatherSide,
                bladder,
                leatherFlask,
                brokenLeatherFlask
        );
        if (ConfigFlasks.GENERAL.enableIron) {
            r.register(unfinishedFlask);
            r.register(ironFlask);
            r.register(brokenIronFlask);
        }

        BlocksWoodworking.getAllNormalItemBlocks().forEach(x -> registerItemBlock(r, x));

        for (Tree wood : TFCRegistries.TREES.getValuesCollection())
        {
            simpleItems.add(register(r, "wood/split_log/" + wood.getRegistryName().getPath(), new ItemSplitLog(wood), CT_WOOD));
        }

        allSimpleItems = simpleItems.build();
    }

    public static void registerModels() {
        leatherFlask.registerItemModel();
        leatherSide.registerItemModel();
        bladder.registerItemModel();
        brokenLeatherFlask.registerItemModel();
        if (ConfigFlasks.GENERAL.enableIron) {
            unfinishedFlask.registerItemModel();
            ironFlask.registerItemModel();
            brokenIronFlask.registerItemModel();
        }
    }
    
    //GENERIC REGISTRATION METHODS
    private static void registerItemBlock(IForgeRegistry<Item> r, ItemBlock item)
    {
        item.setRegistryName(item.getBlock().getRegistryName());
        item.setCreativeTab(item.getBlock().getCreativeTab());
        r.register(item);
    }

    private static <T extends Item> T register(IForgeRegistry<Item> r, String name, T item, CreativeTabs ct)
    {
        item.setRegistryName(MOD_ID, name);
        item.setTranslationKey(MOD_ID + "." + name.replace('/', '.'));
        item.setCreativeTab(ct);
        r.register(item);
        return item;
    }
}
