package com.tree_bit.rcdl.blocks;

import static org.junit.Assert.assertTrue;

import org.junit.Ignore;
import org.junit.Test;

import java.util.EnumSet;


@SuppressWarnings("javadoc")
@Ignore
public class TestAxis {


    @SuppressWarnings("static-method")
    @Test
    public void testPlainXY() {
        final EnumSet<Axis> set = Axis.plain(Axis.X, Axis.Y);
        assertTrue("Plain contains X and Y axis. (Not Z)", set.contains(Axis.X) && set.contains(Axis.Y) && !set.contains(Axis.Z));
    }

    @SuppressWarnings("static-method")
    @Test(expected = IllegalArgumentException.class)
    public void testPlainXX() {
        Axis.plain(Axis.X, Axis.X);
    }

    @SuppressWarnings("static-method")
    @Test(expected = IllegalArgumentException.class)
    public void testcheckPlainsXX() {
        Axis.checkPlain(EnumSet.of(Axis.X, Axis.X));
    }

}
