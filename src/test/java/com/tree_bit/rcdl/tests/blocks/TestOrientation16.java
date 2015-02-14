package com.tree_bit.rcdl.tests.blocks;

import static org.junit.Assert.assertEquals;

import com.tree_bit.rcdl.blocks.Axis;
import com.tree_bit.rcdl.blocks.Orientation16;

import org.junit.Test;

import java.util.EnumSet;


@SuppressWarnings("javadoc")
public class TestOrientation16 {

    @SuppressWarnings("static-method")
    @Test
    public void testNext() {
        assertEquals(Orientation16.S, Orientation16.SSE.next(1));
        assertEquals(Orientation16.NW, Orientation16.N.next(-2));
        assertEquals(Orientation16.NW, Orientation16.SSW.next(5));
    }

    @SuppressWarnings("static-method")
    @Test
    public void testRotateY() {
        assertEquals(Orientation16.S, Orientation16.SSE.rotate(Axis.Y, 1));
        assertEquals(Orientation16.NW, Orientation16.N.rotate(Axis.Y, -2));
        assertEquals(Orientation16.NW, Orientation16.SSW.rotate(Axis.Y, 5));
    }

    @SuppressWarnings("static-method")
    @Test(expected = UnsupportedOperationException.class)
    public void testRotateX() {
        Orientation16.N.rotate(Axis.X, 4);
    }

    @SuppressWarnings({"static-method", "null"})
    @Test
    public void testMirrorXY() {
        assertEquals(Orientation16.SE, Orientation16.NE.mirror(EnumSet.of(Axis.X, Axis.Y)));
        assertEquals(Orientation16.WNW, Orientation16.WSW.mirror(EnumSet.of(Axis.X, Axis.Y)));
    }

    @SuppressWarnings({"static-method", "null"})
    @Test
    public void testMirrorZY() {
        assertEquals(Orientation16.NW, Orientation16.NE.mirror(EnumSet.of(Axis.Z, Axis.Y)));
    }

    @SuppressWarnings({"static-method", "null"})
    @Test(expected = UnsupportedOperationException.class)
    public void testMirrorZX() {
        Orientation16.NE.mirror(EnumSet.of(Axis.Z, Axis.X));
    }

    @SuppressWarnings({"static-method"})
    @Test
    public void testGetDataValue() {
        assertEquals(8, Orientation16.N.getDataValue());
    }
}
