package com.tree_bit.rcdl.blocks;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import com.tree_bit.rcdl.blocks.Redstone.PowerLevel;

import org.junit.Test;

import java.util.EnumSet;


@SuppressWarnings("javadoc")
public class TestRedstone {

    Redstone someInstance = Redstone.getInstance();
    Redstone fixedInstance = Redstone.getInstance(PowerLevel.L10);

    @SuppressWarnings("null")
    @Test
    public void testGetData() {
        assertEquals(PowerLevel.L10, this.fixedInstance.getData().get(PowerLevel.class));
    }

    @Test
    public void testGetInstance() {
        assertTrue(this.someInstance == Redstone.getInstance());
        assertEquals(this.someInstance, Redstone.getInstance());

        assertEquals(Redstone.getInstance(PowerLevel.L0), Redstone.getInstance());
    }

    @Test
    public void testGetInstancePowerLevel() {
        assertTrue(this.fixedInstance == Redstone.getInstance(PowerLevel.L10));
        assertEquals(this.fixedInstance, Redstone.getInstance(PowerLevel.L10));
    }

    @SuppressWarnings("null")
    @Test(expected = IllegalArgumentException.class)
    public void testMirrorSetOfAxisXX() {
        this.someInstance.mirror(EnumSet.of(Axis.X, Axis.X));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testMirrorSetOfAxisXZ() {
        this.someInstance.mirror(Axis.plain(Axis.X, Axis.Z));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testRotateAxisIntZ() {
        this.someInstance.rotate(Axis.Z, 90);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testRotateAxisIntX() {
        this.someInstance.rotate(Axis.X, 90);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRotateAxisIntY50() {
        this.someInstance.rotate(Axis.Y, 50);
    }

    @Test
    public void testMirrorSetOfAxis() {
        assertEquals(this.fixedInstance, this.fixedInstance.mirror(Axis.plain(Axis.X, Axis.Y)));
        assertEquals(this.fixedInstance, this.fixedInstance.mirror(Axis.plain(Axis.Z, Axis.Y)));
        assertNotEquals(Redstone.getInstance(PowerLevel.L7), this.fixedInstance.mirror(Axis.plain(Axis.Z, Axis.Y)));
    }

    @Test
    public void testRotateAxisInt() {
        assertEquals(this.fixedInstance, this.fixedInstance.rotate(Axis.Y, 180));
        assertNotEquals(Redstone.getInstance(PowerLevel.L7), this.fixedInstance.rotate(Axis.Y, 180));
    }

}
