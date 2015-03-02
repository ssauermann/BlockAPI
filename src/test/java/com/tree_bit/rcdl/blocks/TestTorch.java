package com.tree_bit.rcdl.blocks;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.tree_bit.rcdl.blocks.Torch.TorchOrientation;

import org.junit.Test;

import java.util.EnumSet;


@SuppressWarnings("javadoc")
public class TestTorch {

    Torch someInstance = Torch.getInstance();
    Torch fixedInstance = Torch.getInstance(TorchOrientation.South);

    @SuppressWarnings("null")
    @Test
    public void testGetData() {
        assertEquals(TorchOrientation.South, this.fixedInstance.getData().get(TorchOrientation.class));
    }

    @Test
    public void testGetInstanceTorchOrientation() {
        assertTrue(this.fixedInstance == Torch.getInstance(TorchOrientation.South));
        assertEquals(this.fixedInstance, Torch.getInstance(TorchOrientation.South));
    }

    @Test
    public void testGetInstance() {
        assertTrue(this.someInstance == Torch.getInstance());
        assertEquals(this.someInstance, Torch.getInstance());

        assertEquals(Torch.getInstance(TorchOrientation.Up), Torch.getInstance());
    }

    @Test
    public void testGetInstances() {
        assertTrue(Torch.getInstances().contains(this.fixedInstance));
        assertTrue(Torch.getInstances().contains(this.someInstance));
        assertTrue(Torch.getInstances().contains(Torch.getInstance(TorchOrientation.North)));
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
        assertEquals(Torch.getInstance(TorchOrientation.West), this.fixedInstance.rotate(Axis.Y, 90));
        assertEquals(this.someInstance, this.someInstance.rotate(Axis.Y, 180));
    }

    @Test
    public void testMirrorSetOfAxis() {

        assertEquals(this.someInstance, this.someInstance.mirror(Axis.plain(Axis.X, Axis.Y)));

        assertEquals(Torch.getInstance(TorchOrientation.North), this.fixedInstance.mirror(Axis.plain(Axis.X, Axis.Y)));
        assertEquals(this.fixedInstance, this.fixedInstance.mirror(Axis.plain(Axis.Z, Axis.Y)));
    }

}
