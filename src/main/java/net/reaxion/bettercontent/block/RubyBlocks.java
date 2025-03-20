package net.reaxion.bettercontent.block;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.reaxion.bettercontent.BetterContent;
import net.reaxion.bettercontent.item.RubyItems;

import java.util.function.Supplier;

public class RubyBlocks {
    public static final DeferredRegister<Block> BLOCKS
            = DeferredRegister.create(ForgeRegistries.BLOCKS, BetterContent.MOD_ID);

    public static final RegistryObject<Block> RUBY_BLOCK = registerBlock("ruby_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)));

    public static final RegistryObject<Block> RUBY_ORE = registerBlock("ruby_ore",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)));


    public static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }
    public static <T extends Block> void registerBlockItem(String name, RegistryObject<T> block) {
        RubyItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }


    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
