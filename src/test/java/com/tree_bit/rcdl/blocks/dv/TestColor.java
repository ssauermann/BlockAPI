package com.tree_bit.rcdl.blocks.dv;

import static org.junit.Assert.assertEquals;

import com.tree_bit.blockapi.data.minecraft.Color;

import org.junit.Test;

@SuppressWarnings("javadoc")
public class TestColor {

    @SuppressWarnings("static-method")
    @Test
    public void testGetDataValue() {
        assertEquals(1, Color.Orange.getDataValue());
    }
}
