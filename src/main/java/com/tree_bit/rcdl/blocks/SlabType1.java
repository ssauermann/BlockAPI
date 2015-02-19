package com.tree_bit.rcdl.blocks;

/**
 * Texture types of slabs. (Type 1)
 */
public enum SlabType1 implements IDataValueEnum {
    /** Stone */
    Stone(0),
    /** Sandstone */
    Sandstone(1),
    /** Wood (old type of not burning oak wood slab) */
    Wood(2),
    /** Cobblestone */
    Cobblestone(3),
    /** Bricks */
    Bricks(4),
    /** StoneBricks */
    StoneBricks(5),
    /** Netherbrick */
    Netherbrick(6),
    /** Quartz */
    Quartz(7);

    private int value;

    private SlabType1(final int dataValue) {
        this.value = dataValue;
    }

    @Override
    public int getDataValue() {
        return this.value;
    }
}
