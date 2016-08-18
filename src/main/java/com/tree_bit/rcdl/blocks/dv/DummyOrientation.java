package com.tree_bit.rcdl.blocks.dv;

import com.tree_bit.rcdl.blocks.Axis;

import java.util.Arrays;
import java.util.Optional;
import java.util.Set;


/**
 * Dummy orientation for blocks that have special rotation and mirroring rules
 * but no orientation.
 *
 * <p>
 * Allowed axes for rotation (multiple of 90 degree) are:
 * <ul>
 * <li>y</li>
 * </ul>
 *
 * <p>
 * Allowed plains for mirroring are:
 * <ul>
 * <li>x-y</li>
 * <li>z-y</li>
 * </ul>
 */
public enum DummyOrientation implements IOrientationEnum {
    /** No orientation */
    NONE;

    @Override
    public int getDV() {
        return 0;
    }

    @Override
    public IOrientationEnum rotate(final Axis axis, final int n) {
        if (axis == Axis.Y) {
            return this.next(n);
        }
        throw new UnsupportedOperationException("Can't rotate at this axis: " + axis);
    }

    @Override
    public IOrientationEnum mirror(final Set<Axis> plain) {
        Axis.checkPlain(plain);
        if (plain.contains(Axis.Y) && (plain.contains(Axis.X) || plain.contains(Axis.Z))) {
            return this;
        }
        throw new UnsupportedOperationException("Can't mirror at this plain: " + Arrays.toString(plain.toArray(new Axis[] {})));
    }

    @Override
    public IOrientationEnum next(final int n) {
        return this;
    }

    @Override
    public int getStep() {
        return 90;
    }

    @Override
    public Optional<? extends DummyOrientation> byDV(final int dv) {
        // TODO
        if (dv == 0) {
            return Optional.of(NONE);
        }
        return Optional.empty();
    }
}
