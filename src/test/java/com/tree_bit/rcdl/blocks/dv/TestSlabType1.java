package com.tree_bit.rcdl.blocks.dv;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

@SuppressWarnings("javadoc")
public class TestSlabType1 {

    @SuppressWarnings("static-method")
    @Test
    public void testgetDV() {
        assertEquals(3, SlabType1.COBBLESTONE.getDV());
    }
}
