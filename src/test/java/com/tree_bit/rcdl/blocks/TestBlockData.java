package com.tree_bit.rcdl.blocks;

import static org.junit.Assert.assertEquals;

import com.tree_bit.rcdl.blocks.Repeater.Delay;
import com.tree_bit.rcdl.blocks.dv.OrientationNESW;

import org.junit.Test;


@SuppressWarnings({"javadoc", "static-method"})
public class TestBlockData {

    @Test
    public void testGetDataValue() {
        assertEquals(0, Repeater.getInstance(OrientationNESW.North, Delay.D1).getDataValue());
        assertEquals(5, Repeater.getInstance(OrientationNESW.East, Delay.D2).getDataValue());
    }
}
