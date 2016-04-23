package com.tree_bit.rcdl.blocks;

import static org.junit.Assert.assertEquals;

import com.tree_bit.blockapi.id.minecraft.BlockID;

import org.junit.Test;


@SuppressWarnings({"javadoc", "static-method"})
public class TestBlockID {

    @Test
    public void testGetId() {
        assertEquals(1, BlockID.STONE.getID());
    }

    @Test
    public void testGetDataClass() {
        assertEquals(GenericBlockData.class, BlockID.DIAMOND_BLOCK.getDataClass());
        assertEquals(Repeater.class, BlockID.REPEATER_OFF.getDataClass());
    }

}
