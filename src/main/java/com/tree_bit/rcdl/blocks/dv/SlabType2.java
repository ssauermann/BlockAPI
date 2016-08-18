package com.tree_bit.rcdl.blocks.dv;

import com.tree_bit.blockapi.data.IDataValue;

import java.util.Optional;

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
    public int getDV() {
        return this.value;
    }

    @Override
    public Optional<? extends IDataValue> byDV(final int dv) {
        // TODO Auto-generated method stub
        return Optional.empty();
    }
}
