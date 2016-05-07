package com.tree_bit.rcdl.blocks.dv;

import com.tree_bit.blockapi.data.IDataValue;

/**
 * Texture types of slabs. (Type 2)
 */
public enum SlabType2 implements IDataValue {
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
