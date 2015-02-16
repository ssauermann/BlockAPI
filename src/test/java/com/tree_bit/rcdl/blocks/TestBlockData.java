package com.tree_bit.rcdl.blocks;

import static org.junit.Assert.assertEquals;

import com.tree_bit.rcdl.blocks.Repeater.Delay;

import org.junit.Test;


@SuppressWarnings({"javadoc", "static-method"})
public class TestBlockData {

    @Test
    public void testToCount() {
        assertEquals(2, BlockData.toCount(180, 90));
        assertEquals(-3, BlockData.toCount(-270, 90));
        assertEquals(4, BlockData.toCount(120, 30));
    }

    @Test
    public void testGetDataValue() {
        assertEquals(0, Repeater.getInstance(OrientationNESW.North, Delay.D1).getDataValue());
        assertEquals(5, Repeater.getInstance(OrientationNESW.East, Delay.D2).getDataValue());
    }
}
