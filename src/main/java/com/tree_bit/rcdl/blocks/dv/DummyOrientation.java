package com.tree_bit.rcdl.blocks.dv;

import com.tree_bit.rcdl.blocks.Axis;

import org.eclipse.jdt.annotation.NonNull;

import java.util.Arrays;
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
    public int getDataValue() {
        return 0;
    }

    @Override
    public @NonNull IOrientationEnum rotate(final Axis axis, final int n) {
        if (axis == Axis.Y) {
            return this.next(n);
        }
        throw new UnsupportedOperationException("Can't rotate at this axis: " + axis);
    }

    @Override
    public @NonNull IOrientationEnum mirror(@NonNull final Set<@NonNull Axis> plain) {
        Axis.checkPlain(plain);
        if (plain.contains(Axis.Y) && (plain.contains(Axis.X) || plain.contains(Axis.Z))) {
            return this;
        }
        throw new UnsupportedOperationException("Can't mirror at this plain: " + Arrays.toString(plain.toArray(new Axis[] {})));
    }

    @Override
    public @NonNull IOrientationEnum next(final int n) {
        return this;
    }

    @Override
    public int getStep() {
        return 90;
    }
}
