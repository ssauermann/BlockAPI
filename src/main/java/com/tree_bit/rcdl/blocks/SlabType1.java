package com.tree_bit.rcdl.blocks;

/**
 * Texture types of slabs. (Type 1)
 */
public enum SlabType1 implements IDataValueEnum {
    /** Stone */
    STONE(0),
    /** Sandstone */
    SANDSTONE(1),
    /** Wood (old type of not burning oak wood slab) */
    WOOD(2),
    /** Cobblestone */
    COBBLESTONE(3),
    /** Bricks */
    BRICKS(4),
    /** StoneBrick */
    STONEBRICK(5),
    /** Netherbrick */
    NETHERBRICK(6),
    /** Quartz */
    QUARTZ(7);

    private int value;

    private SlabType1(final int dataValue) {
        this.value = dataValue;
    }

    @Override
    public int getDataValue() {
        return this.value;
    }
}
