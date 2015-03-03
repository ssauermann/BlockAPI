package com.tree_bit.rcdl.blocks;

import com.tree_bit.rcdl.blocks.dv.IOrientationEnum;

import static org.junit.Assert.assertEquals;

import org.junit.Test;


@SuppressWarnings("javadoc")
public class TestIOrientationEnum {


    @SuppressWarnings("static-method")
    @Test
    public void testToCount() {
        assertEquals(2, IOrientationEnum.toCount(180, 90));
        assertEquals(-3, IOrientationEnum.toCount(-270, 90));
        assertEquals(4, IOrientationEnum.toCount(120, 30));
    }

}
