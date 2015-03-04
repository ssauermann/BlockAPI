package com.tree_bit.rcdl.blocks.dv;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

@SuppressWarnings("javadoc")
public class TestSlabType2 {

    @SuppressWarnings("static-method")
    @Test
    public void testGetDataValue() {
        assertEquals(0, SlabType2.RED_SANDSTONE.getDataValue());
    }
}
