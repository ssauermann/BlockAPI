package com.tree_bit.rcdl.blocks.dv;

import static org.junit.Assert.assertEquals;

import com.tree_bit.rcdl.blocks.Axis;

import org.junit.Test;

import java.util.EnumSet;


@SuppressWarnings("javadoc")
public class TestSignOrientation {

    @SuppressWarnings("static-method")
    @Test
    public void testRotateY() {
        assertEquals(SignOrientation.North, SignOrientation.North.rotate(Axis.Y, 0));
        assertEquals(SignOrientation.East, SignOrientation.North.rotate(Axis.Y, 1));
        assertEquals(SignOrientation.South, SignOrientation.North.rotate(Axis.Y, -2));
        assertEquals(SignOrientation.West, SignOrientation.South.rotate(Axis.Y, 5));
    }

    @SuppressWarnings("static-method")
    @Test(expected = UnsupportedOperationException.class)
    public void testRotateX() {
        SignOrientation.North.rotate(Axis.X, 4);
    }

    @SuppressWarnings("static-method")
    @Test(expected = UnsupportedOperationException.class)
    public void testRotateZ() {
        SignOrientation.North.rotate(Axis.Z, 4);
    }

    @SuppressWarnings({"static-method", "null"})
    @Test
    public void testMirrorXY() {
        assertEquals(SignOrientation.North, SignOrientation.South.mirror(EnumSet.of(Axis.X, Axis.Y)));
        assertEquals(SignOrientation.South, SignOrientation.North.mirror(EnumSet.of(Axis.X, Axis.Y)));
        assertEquals(SignOrientation.West, SignOrientation.West.mirror(EnumSet.of(Axis.X, Axis.Y)));
    }

    @SuppressWarnings({"static-method", "null"})
    @Test
    public void testMirrorZY() {
        assertEquals(SignOrientation.West, SignOrientation.East.mirror(EnumSet.of(Axis.Z, Axis.Y)));
        assertEquals(SignOrientation.North, SignOrientation.North.mirror(EnumSet.of(Axis.Z, Axis.Y)));
    }

    @SuppressWarnings({"static-method", "null"})
    @Test(expected = UnsupportedOperationException.class)
    public void testMirrorZX() {
        SignOrientation.North.mirror(EnumSet.of(Axis.Z, Axis.X));
    }

    @SuppressWarnings("static-method")
    @Test
    public void testNext() {
        assertEquals(SignOrientation.North, SignOrientation.West.next(1));
        assertEquals(SignOrientation.South, SignOrientation.West.next(-1));
        assertEquals(SignOrientation.East, SignOrientation.South.next(3));
    }

    @SuppressWarnings("static-method")
    @Test
    public void testGetDataValue() {
        assertEquals(2, SignOrientation.North.getDataValue());
    }
}
