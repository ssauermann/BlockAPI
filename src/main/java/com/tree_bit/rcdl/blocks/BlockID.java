package com.tree_bit.rcdl.blocks;

import com.google.common.base.MoreObjects;

/**
 * Mapping of block names to id.
 */
public enum BlockID {
    /** Air */
    AIR(0),
    /** Stone */
    STONE(1),
    /** Grass */
    GRASS(2),
    /** Dirt */
    DIRT(3),
    /** Cobblestone */
    COBBLESTONE(4),
    /** Wood */
    PLANKS(5),
    /** Sapling */
    SAPLING(6),
    /** Bedrock */
    BEDROCK(7),
    /** Flowing Water */
    FLOWING_WATER(8),
    /** Still water */
    WATER(9),
    /** Flowing Lava */
    FLOWING_LAVA(10),
    /** Still Lava */
    LAVA(11),
    /** Sand */
    SAND(12),
    /** Gravel */
    GRAVEL(13),
    /** Gold ore */
    GOLD_ORE(14),
    /** Iron ore */
    IRON_ORE(15),
    /** Coal ore */
    COAL_ORE(16),
    /** Log */
    LOG(17),
    /** Leaves */
    LEAVES(18),
    /** Sponge */
    SPONGE(19),
    /** Glass */
    GLASS(20),
    /** Lapis ore */
    LAPIS_ORE(21),
    /** Lapis block */
    LAPIS_BLOCK(22),
    /** Dispenser */
    DISPENSER(23),
    /** Sandstone */
    SANDSTONE(24),
    /** Noteblock */
    NOTEBLOCK(25),
    /** Bed */
    BED(26),
    /** Powered rail */
    GOLDEN_RAIL(27),
    /** Detector rail */
    DETECTOR_RAIL(28),
    /** Sticky piston */
    STICKY_PISTON(29),
    /** Cobweb */
    WEB(30),
    /** Tallgrass */
    TALLGRASS(31),
    /** Dead Shrub */
    DEADBUSH(32),
    /** Piston */
    PISTON(33),
    /** Piston Head */
    PISTON_HEAD(34),
    /** Wool */
    WOOL(35, ColorBlock.class),
    /** Technical piston block */
    PISTON_MOVING_PIECE(36),
    /** Dandelion */
    YELLOW_FLOWER(37),
    /** Poppy */
    RED_ROSE(38),
    /** Brown mushroom */
    BROWN_MUSHROOM(39),
    /** Red mushroom */
    RED_MUSHROOM(40),
    /** Gold block */
    GOLD_BLOCK(41),
    /** Iron block */
    IRON_BLOCK(42),
    /** Double stone slab */
    DOUBLE_STONE_SLAB(43),
    /** Stone slab */
    STONE_SLAB(44, HalfSlab1.class),
    /** Bricks */
    BRICK_BLOCK(45),
    /** TNT */
    TNT(46),
    /** Bookshelf */
    BOOKSHELF(47),
    /** Moss stone */
    MOSSY_COBBLESTONE(48),
    /** Obsidian */
    OBSIDIAN(49),
    /** Torch */
    TORCH(50, Torch.class),
    /** Fire */
    FIRE(51),
    /** Monster spawner */
    MOB_SPAWNER(52),
    /** Oak wood stairs */
    OAK_STAIRS(53),
    /** Chest */
    CHEST(54),
    /** Redstone Wire */
    REDSTONE_WIRE(55, Redstone.class),
    /** Diamond ore */
    DIAMOND_ORE(56),
    /** Diamond block */
    DIAMOND_BLOCK(57),
    /** Crafting table */
    CRAFTING_TABLE(58),
    /** Wheat crops */
    WHEAT(59),
    /** Farmland */
    FARMLAND(60),
    /** Furnace */
    FURNACE(61),
    /** Burning furnace */
    BURNING_FURNACE(62),
    /** Standing sign block */
    STANDING_SIGN(63, StandingSign.class),
    /** Oak door block */
    WOODEN_DOOR(64),
    /** Ladder */
    LADDER(65),
    /** Rail */
    RAIL(66),
    /** Cobblestone stairs */
    STONE_STAIRS(67),
    /** Wall-mounted sign block */
    WALL_SIGN(68, HangingSign.class),
    /** lever */
    LEVER(69),
    /** Stone pressure plate */
    STONE_PLATE(70),
    /** Iron door block */
    IRON_DOOR(71),
    /** Wooden pressure plate */
    WOODEN_PLATE(72),
    /** Redstone ore */
    REDSTONE_ORE(73),
    /** Glowing redstone ore */
    GLOWING_REDSTONE_ORE(74),
    /** Redstone torch (off) */
    REDSTONE_TORCH_OFF(75, Torch.class),
    /** Redstone torch (on) */
    REDSTONE_TORCH(76, Torch.class),
    /** Stone button */
    STONE_BUTTON(77),
    /** Snow layer */
    SNOW_LAYER(78),
    /** Ice */
    ICE(79),
    /** Snow */
    SNOW(80),
    /** Cactus */
    CACTUS(81),
    /** Clay */
    CLAY(82),
    /** Sugar Canes */
    SUGAR_CANES(83),
    /** Jukebox */
    JUKEBOX(84),
    /** Oak fence */
    FENCE(85),
    /** Pumpkin */
    PUMPKIN(86),
    /** Netherrack */
    NETHERRACK(87),
    /** Soul sand */
    SOUL_SAND(88),
    /** Glowstone */
    GLOWSTONE(89),
    /** Nether portal */
    PORTAL(90),
    /** Jack o'Lantern */
    JACK_O_LANTERN(91),
    /** Cake */
    CAKE(92),
    /** Redstone repeater (off) */
    REPEATER_OFF(93, Repeater.class),
    /** Redstone repeater (on) */
    REPEATER_ON(94, Repeater.class),
    /** Stained glass */
    STAINED_GLASS(95, ColorBlock.class),
    /** Wooden trapdoor */
    TRAPDOOR(96),
    /** Monster egg */
    MONSTER_EGG(97),
    /** Stone brick */
    STONEBRICK(98),
    /** Red mushroom cap */
    RED_MUSHROOM_CAP(99),
    /** Brown mushroom cap */
    BROWN_MUSHROOM_CAP(100),
    /** Iron bars */
    IRON_BARS(101),
    /** Glass pane */
    GLASS_PANE(102),
    /** Melon block */
    MELON_BLOCK(103),
    /** Pumpkin stem */
    PUMPKIN_STEM(104),
    /** Melon stem */
    MELON_STEM(105),
    /** Vines */
    VINE(106),
    /** Oak fence gate */
    FENCE_GATE(107),
    /** Brick stairs */
    BRICK_STAIRS(108),
    /** Stone brick stairs */
    STONE_BRICK_STAIRS(109),
    /** Mycelium */
    MYCELIUM(110),
    /** Lily Pad */
    WATERLILY(111),
    /** Nether brick */
    NETHER_BRICK(112),
    /** Nether brick fence */
    NETHER_BRICK_FENCE(113),
    /** Nether brick stairs */
    NETHER_BRICK_STAIRS(114),
    /** Nether wart */
    NETHER_WART(115),
    /** Enchantment table */
    ENCHANTMENT_TABLE(116),
    /** Brewing stand */
    BREWING_STAND(117),
    /** Cauldron */
    CAULDRON(118),
    /** End portal */
    END_PORTAL(119),
    /** End portal frame */
    END_PORTAL_FRAME(120),
    /** End stone */
    END_STONE(121),
    /** Dragon egg */
    DRAGON_EGG(122),
    /** Redstone lamp (off) */
    REDSTONE_LAMP_OFF(123),
    /** Redstone lamp (on) */
    REDSTONE_LAMP_ON(124),
    /** Double wood slab */
    DOUBLE_WOOD_SLAB(125),
    /** Wood slab */
    WOOD_SLAB(126),
    /** Cocoa */
    COCOA(127),
    /** Sandstone stairs */
    SANDSTONE_STAIRS(128),
    /** Emerald ore */
    EMERALD_ORE(129),
    /** Ender chest */
    ENDER_CHEST(130),
    /** Tripwire hook */
    TRIPWIRE_HOOK(131),
    /** Tripwire */
    TRIPWIRE(132),
    /** Emerald block */
    EMERALD_BLOCK(133),
    /** Spruce wood stairs */
    SPRUCE_STAIRS(134),
    /** Birch wood stairs */
    BIRCH_STAIRS(135),
    /** Jungle wood stairs */
    JUNGLE_STAIRS(136),
    /** Command block */
    COMMAND_BLOCK(137),
    /** Beacon */
    BEACON(138),
    /** Cobblestone wall */
    COBBLESTONE_WALL(139),
    /** Flower pot */
    FLOWER_POT(140),
    /** Carrots */
    CARROTS(141),
    /** Potatoes */
    POTATOES(142),
    /** Wooden button */
    WOODEN_BUTTON(143),
    /** Mob head */
    SKULL(144),
    /** Anvil */
    ANVIL(145),
    /** Trapped chest */
    TRAPPED_CHEST(146),
    /** Weighted pressure plate (light) */
    GOLD_PLATE(147),
    /** Weighted pressure plate (heavy) */
    IRON_PLATE(148),
    /** Redstone comparator (off) */
    REDSTONE_COMPARATOR_OFF(149),
    /** Redstone comparator (on) */
    REDSTONE_COMPARATOR_ON(150),
    /** Daylight sensor */
    DAYLIGHT_DETECTOR(151),
    /** Redstone block */
    REDSTONE_BLOCK(152),
    /** Nether quartz ore */
    QUARTZ_ORE(153),
    /** Hopper */
    HOPPER(154),
    /** Quartz block */
    QUARTZ_BLOCK(155),
    /** Quartz stairs */
    QUARTZ_STAIRS(156),
    /** Activator rail */
    ACTIVATOR_RAIL(157),
    /** Dropper */
    DROPPER(158),
    /** Stained clay */
    STAINED_CLAY(159, ColorBlock.class),
    /** Stained glass pane */
    STAINED_GLASS_PANE(160, ColorBlock.class),
    /** Leaves 2 */
    LEAVES_2(161),
    /** Log 2 */
    LOG_2(162),
    /** Acacia wood stairs */
    ACACIA_STAIRS(163),
    /** Dark oak wood stairs */
    DARK_OAK_STAIRS(164),
    /** Slime block */
    SLIME(165),
    /** Barrier */
    BARRIER(166),
    /** Iron trapdoor */
    IRON_TRAPDOOR(167),
    /** Prismarine */
    PRISMARINE(168),
    /** Sea lantern */
    SEA_LANTERN(169),
    /** Hay bale */
    HAY_BLOCK(170),
    /** Carpet */
    CARPET(171),
    /** Hardened clay */
    HARDENED_CLAY(172),
    /** Block of coal */
    COAL_BLOCK(173),
    /** Packed ice */
    PACKED_ICE(174),
    /** Double plant */
    DOUBLE_PLANT(175),
    /** Standing banner */
    STANDING_BANNER(176),
    /** Wall mounted banner */
    WALL_BANNER(177),
    /** Inverted daylight sensor */
    DAYLIGHT_DETECTOR_INVERTED(178),
    /** Red sandstone */
    RED_SANDSTONE(179),
    /** Red sandstone stairs */
    RED_SANDSTONE_STAIRS(180),
    /** Double stone slabs 2 */
    DOUBLE_STONE_SLAB_2(181),
    /** Stone slabs 2 */
    STONE_SLAB_2(182, HalfSlab2.class),
    /** Spruce fence gate */
    SPRUCE_FENCE_GATE(183),
    /** Birch fence gate */
    BIRCH_FENCE_GATE(184),
    /** Jungle fence gate */
    JUNGLE_FENCE_GATE(185),
    /** dark oak fence gate */
    DARK_OAK_FENCE_GATE(186),
    /** Acacia fence gate */
    ACACIA_FENCE_GATE(187),
    /** Spruce fence */
    SPRUCE_FENCE(188),
    /** Birch fence */
    BIRCH_FENCE(189),
    /** Jungle fence */
    JUNGLE_FENCE(190),
    /** dark oak fence */
    DARK_OAK_FENCE(191),
    /** Acacia fence */
    ACACIA_FENCE(192),
    /** Spruce door */
    SPRUCE_DOOR(193),
    /** Birch door */
    BIRCH_DOOR(194),
    /** Jungle door */
    JUNGLE_DOOR(195),
    /** Acacia door */
    ACACIA_DOOR(196),
    /** Dark oak door */
    DARK_OAK_DOOR(197);

    private final int id;
    private final Class<? extends BlockData> dataClass;

    private BlockID(final int id) {
        this(id, GenericBlockData.class);
    }

    private BlockID(final int id, final Class<? extends BlockData> dataClass) {
        this.id = id;
        this.dataClass = dataClass;
    }

    /**
     * Returns the block id.
     *
     * @return Block id
     */
    int getId() {
        return this.id;
    }

    /**
     * Returns the data class of this block.
     *
     * @return Data class
     */
    Class<? extends BlockData> getDataClass() {
        return this.dataClass;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this).addValue(super.toString()).add("id", this.id).toString();
    }

}
