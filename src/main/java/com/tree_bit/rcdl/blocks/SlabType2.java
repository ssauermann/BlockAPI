package com.tree_bit.rcdl.blocks;

/**
 * Texture types of slabs. (Type 2)
 */
public enum SlabType2 implements IDataValueEnum {
    /** Red Sandstone */
    RED_SANDSTONE(0);

    private int value;

    private SlabType2(final int dataValue) {
        this.value = dataValue;
    }

    @Override
    public int getDataValue() {
        return this.value;
    }
}
