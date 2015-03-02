package com.tree_bit.rcdl.blocks;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import com.tree_bit.rcdl.blocks.Repeater.Delay;

import org.junit.Test;

import java.util.EnumSet;


@SuppressWarnings({"javadoc"})
public class TestRepeater {

    Repeater someInstance = Repeater.getInstance();
    Repeater fixedInstance = Repeater.getInstance(OrientationNESW.East, Delay.D3);

    @SuppressWarnings("null")
    @Test
    public void testGetData() {
        assertEquals(Repeater.Delay.D3, this.fixedInstance.getData().get(Repeater.Delay.class));
        assertEquals(OrientationNESW.East, this.fixedInstance.getData().get(OrientationNESW.class));
    }

    @Test
    public void testGetInstanceOrientationNESWDelay() {
        assertTrue(this.fixedInstance == Repeater.getInstance(OrientationNESW.East, Delay.D3));
        assertEquals(this.fixedInstance, Repeater.getInstance(OrientationNESW.East, Delay.D3));
    }

    @Test
    public void testGetInstance() {
        assertTrue(this.someInstance == Repeater.getInstance());
        assertEquals(this.someInstance, Repeater.getInstance());

        assertEquals(Repeater.getInstance(OrientationNESW.North, Delay.D1), Repeater.getInstance());
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
    public void testRotateAxisInt() {
        assertEquals(Repeater.getInstance(OrientationNESW.West, Delay.D3), this.fixedInstance.rotate(Axis.Y, 180));
        assertNotEquals(Repeater.getInstance(OrientationNESW.West, Delay.D1), this.fixedInstance.rotate(Axis.Y, 180));
    }

    @Test
    public void testMirrorSetOfAxis() {
        assertEquals(this.fixedInstance, this.fixedInstance.mirror(Axis.plain(Axis.X, Axis.Y)));
        assertEquals(Repeater.getInstance(OrientationNESW.West, Delay.D3), this.fixedInstance.mirror(Axis.plain(Axis.Z, Axis.Y)));
        assertNotEquals(Repeater.getInstance(OrientationNESW.West, Delay.D1), this.fixedInstance.mirror(Axis.plain(Axis.Z, Axis.Y)));
    }

}
