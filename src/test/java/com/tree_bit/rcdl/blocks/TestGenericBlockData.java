package com.tree_bit.rcdl.blocks;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import java.util.EnumSet;


@SuppressWarnings({"javadoc", "null"})
public class TestGenericBlockData {

    private final GenericBlockData instance = GenericBlockData.getInstance();

    @Test
    public void testGetData() {
        assertEquals(GenericBlockData.Data.NONE, this.instance.getData().get(GenericBlockData.Data.class));
    }

    @Test
    public void testGetInstance() {
        assertTrue(this.instance == GenericBlockData.getInstance());
        assertEquals(this.instance, GenericBlockData.getInstance());
    }

    @Test
    public void testGetInstances() {
        assertTrue(GenericBlockData.getInstances().contains(this.instance));
    }

    @Test
    public void testMirrorSetOfAxis() {
        assertEquals(this.instance, this.instance.mirror(Axis.plain(Axis.X, Axis.Y)));
        assertEquals(this.instance, this.instance.mirror(Axis.plain(Axis.Z, Axis.Y)));
        assertEquals(this.instance, this.instance.mirror(Axis.plain(Axis.X, Axis.Z)));
    }


    @Test(expected = IllegalArgumentException.class)
    public void testMirrorSetOfAxisException() {
        this.instance.mirror(EnumSet.of(Axis.X, Axis.X));
    }

    @Test
    public void testRotateAxisInt() {
        assertEquals(this.instance, this.instance.rotate(Axis.Y, 90));
        assertEquals(this.instance, this.instance.rotate(Axis.X, 180));
        assertEquals(this.instance, this.instance.rotate(Axis.Z, -270));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRotateAxisIntException() {
        this.instance.rotate(Axis.Y, 40);
    }

}
