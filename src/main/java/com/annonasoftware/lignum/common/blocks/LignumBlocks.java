package com.annonasoftware.lignum.common.blocks;

import com.annonasoftware.lignum.common.blockentities.LignumBlockEntities;
import com.annonasoftware.lignum.common.blocks.devices.ChoppingBlock;
import com.annonasoftware.lignum.common.items.LignumItems;
import net.dries007.tfc.TerraFirmaCraft;
import net.dries007.tfc.common.TFCTags;
import net.dries007.tfc.common.blocks.ExtendedProperties;
import net.dries007.tfc.common.blocks.TFCBlocks;
import net.dries007.tfc.common.blocks.wood.Wood;
import net.dries007.tfc.util.Helpers;
import net.minecraft.world.entity.animal.Bee;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.pathfinder.FlyNodeEvaluator;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import javax.annotation.Nullable;
import java.util.Locale;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;

import static net.dries007.tfc.common.TFCItemGroup.WOOD;

@SuppressWarnings("unused")
public class LignumBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, com.annonasoftware.lignum.Lignum.MOD_ID);


    //public static final RegistryObject<Block> WATTLE_FENCE = register("wattle_fence", () -> new WattleFenceBlock(ExtendedProperties.of(Material.WOOD).strength(0.3F).noOcclusion().sound(SoundType.SCAFFOLDING).flammable(60, 30)), WOOD);

    //DEVICES
    public static final Map<Wood, RegistryObject<Block>> CHOPPING_BLOCKS = Helpers.mapOfKeys(Wood.class, wood -> register("wood/chopping_block/" + wood.getSerializedName(), () -> new ChoppingBlock(choppingBlockProperties()), WOOD));

    public static int lightEmission(BlockState state)
    {
        return state.getValue(BlockStateProperties.LIT) ? 15 : 0;
    }

    public static ExtendedProperties choppingBlockProperties()
    {
        return ExtendedProperties.of(Material.WOOD).strength(0.3F).sound(SoundType.WOOD).noOcclusion().blockEntity(LignumBlockEntities.CHOPPING_BLOCK);
    }

    private static <T extends Block> RegistryObject<T> register(String name, Supplier<T> blockSupplier)
    {
        return register(name, blockSupplier, (Function<T, ? extends BlockItem>) null);
    }

    private static <T extends Block> RegistryObject<T> register(String name, Supplier<T> blockSupplier, CreativeModeTab group)
    {
        return register(name, blockSupplier, block -> new BlockItem(block, new Item.Properties().tab(group)));
    }

    private static <T extends Block> RegistryObject<T> register(String name, Supplier<T> blockSupplier, Item.Properties blockItemProperties)
    {
        return register(name, blockSupplier, block -> new BlockItem(block, blockItemProperties));
    }

    private static <T extends Block> RegistryObject<T> register(String name, Supplier<T> blockSupplier, @Nullable Function<T, ? extends BlockItem> blockItemFactory)
    {
        final String actualName = name.toLowerCase(Locale.ROOT);
        final RegistryObject<T> block = BLOCKS.register(actualName, blockSupplier);
        if (blockItemFactory != null)
        {
            LignumItems.ITEMS.register(actualName, () -> blockItemFactory.apply(block.get()));
        }
        return block;
    }
}
