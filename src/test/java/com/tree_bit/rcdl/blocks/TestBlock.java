package com.tree_bit.rcdl.blocks;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import com.tree_bit.rcdl.blocks.Repeater.Delay;

import org.junit.Test;


@SuppressWarnings("javadoc")
public class TestBlock {

    private final Block diamondBlock = Block.getInstance(BlockID.DIAMOND_BLOCK);
    private final Block repeaterBlock = Block.getInstance(BlockID.REPEATER_OFF, Repeater.getInstance(OrientationNESW.South, Delay.D2));

    @Test
    public void testGetInstanceBlockIDBlockData() {
        assertTrue(this.repeaterBlock == Block.getInstance(BlockID.REPEATER_OFF, Repeater.getInstance(OrientationNESW.South, Delay.D2)));
        assertEquals(this.repeaterBlock, Block.getInstance(BlockID.REPEATER_OFF, Repeater.getInstance(OrientationNESW.South, Delay.D2)));
        assertNotEquals(this.repeaterBlock, Block.getInstance(BlockID.REPEATER_OFF, Repeater.getInstance(OrientationNESW.South, Delay.D1)));
    }

    @SuppressWarnings("static-method")
    @Test(expected = IllegalArgumentException.class)
    public void testGetInstanceBlockIDBlockDataException() {
        Block.getInstance(BlockID.DIAMOND_BLOCK, Repeater.getInstance());
    }

    @Test
    public void testGetInstanceBlockID() {
        assertTrue(this.diamondBlock == Block.getInstance(BlockID.DIAMOND_BLOCK));
        assertEquals(this.diamondBlock, Block.getInstance(BlockID.DIAMOND_BLOCK));

        Block.getInstance(BlockID.REPEATER_OFF);
    }

    @Test
    public void testGetData() {
        assertTrue(this.diamondBlock.getData() == GenericBlockData.getInstance());
        assertTrue(this.repeaterBlock.getData() == Repeater.getInstance(OrientationNESW.South, Delay.D2));
    }

    @Test
    public void testSetData() {
        assertEquals(Block.getInstance(BlockID.REPEATER_OFF, Repeater.getInstance(OrientationNESW.South, Delay.D3)),
                this.repeaterBlock.setData(Repeater.getInstance(OrientationNESW.South, Delay.D3)));
    }

    @Test
    public void testGetBlock() {
        assertEquals(BlockID.DIAMOND_BLOCK, this.diamondBlock.getBlock());
    }

    @Test
    public void testMirror() {
        assertEquals(Block.getInstance(BlockID.REPEATER_OFF, Repeater.getInstance(OrientationNESW.North, Delay.D2)),
                this.repeaterBlock.mirror(Axis.plain(Axis.X, Axis.Y)));
    }

    @Test
    public void testRotate() {
        assertEquals(Block.getInstance(BlockID.REPEATER_OFF, Repeater.getInstance(OrientationNESW.North, Delay.D2)),
                this.repeaterBlock.rotate(Axis.Y, 180));
    }

    @Test
    public void testCompareTo() {
        assertTrue(this.diamondBlock.compareTo(this.repeaterBlock) < 0);
        assertTrue(this.repeaterBlock.compareTo(this.diamondBlock) > 0);
        assertTrue(this.repeaterBlock.compareTo(Block.getInstance(BlockID.REPEATER_OFF)) == 0);
    }

}
