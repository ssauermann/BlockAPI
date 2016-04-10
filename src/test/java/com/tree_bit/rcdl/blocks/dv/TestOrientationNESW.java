package com.tree_bit.rcdl.blocks.dv;

import static org.junit.Assert.assertEquals;

import com.tree_bit.rcdl.blocks.Axis;

import org.junit.Test;

import java.util.EnumSet;


@SuppressWarnings("javadoc")
public class TestOrientationNESW {

    @SuppressWarnings("static-method")
    @Test
    public void testNext() {
        assertEquals(OrientationNESW.North, OrientationNESW.West.next(1));
        assertEquals(OrientationNESW.East, OrientationNESW.West.next(-2));
        assertEquals(OrientationNESW.East, OrientationNESW.North.next(5));
    }

    @SuppressWarnings("static-method")
    @Test
    public void testRotateY() {
        assertEquals(OrientationNESW.East, OrientationNESW.East.rotate(Axis.Y, 0));
        assertEquals(OrientationNESW.South, OrientationNESW.East.rotate(Axis.Y, 1));
        assertEquals(OrientationNESW.South, OrientationNESW.North.rotate(Axis.Y, -2));
        assertEquals(OrientationNESW.North, OrientationNESW.West.rotate(Axis.Y, 5));
    }

    @SuppressWarnings("static-method")
    @Test(expected = UnsupportedOperationException.class)
    public void testRotateX() {
        OrientationNESW.North.rotate(Axis.X, 4);
    }

    @SuppressWarnings({"static-method"})
    @Test
    public void testMirrorXY() {
        assertEquals(OrientationNESW.South, OrientationNESW.North.mirror(EnumSet.of(Axis.X, Axis.Y)));
        assertEquals(OrientationNESW.East, OrientationNESW.East.mirror(EnumSet.of(Axis.X, Axis.Y)));
    }

    @SuppressWarnings({"static-method"})
    @Test
    public void testMirrorZY() {
        assertEquals(OrientationNESW.East, OrientationNESW.West.mirror(EnumSet.of(Axis.Z, Axis.Y)));
        assertEquals(OrientationNESW.North, OrientationNESW.North.mirror(EnumSet.of(Axis.Z, Axis.Y)));
    }

    @SuppressWarnings({"static-method"})
    @Test(expected = UnsupportedOperationException.class)
    public void testMirrorZX() {
        OrientationNESW.North.mirror(EnumSet.of(Axis.Z, Axis.X));
    }

    @SuppressWarnings({"static-method"})
    @Test
    public void testGetDataValue() {
        assertEquals(1, OrientationNESW.East.getDataValue());
    }
}
