package com.tree_bit.rcdl.blocks;

import com.google.common.math.IntMath;

import java.util.Arrays;
import java.util.Set;

/**
 * Enum of the four directions (North, East, South, West), mapping those to
 * their block data value.
 *
 * <p>
 * This orientation is used by:
 * <ul>
 * <li>{@link Repeater}</li>
 * <li>{@link Comparator}</li>
 * </ul>
 * </p>
 *
 * <p>
 * Allowed axes for rotation (multiple of 90 degree) are:
 * <ul>
 * <li>y</li>
 * </ul>
 * </p>
 *
 * <p>
 * Allowed plains for mirroring are:
 * <ul>
 * <li>x-y</li>
 * <li>z-y</li>
 * </ul>
 * </p>
 */
public enum OrientationNESW implements IDataValueEnum, IOrientationEnum {

    /** The Facing north. */
    North(0),
    /** The Facing east. */
    East(1),
    /** The Facing south. */
    South(2),
    /** The Facing west. */
    West(3);

    private int value;

    private OrientationNESW(final int value) {
        this.value = value;
    }

    @Override
    public int getDataValue() {
        return this.value;
    }

    @Override
    public OrientationNESW rotate(final Axis axis, final int n) {
        if (axis == Axis.Y) {
            return this.next(n);
        }
        throw new UnsupportedOperationException("Can't rotate at this axis: " + axis);
    }

    @Override
    public OrientationNESW mirror(final Set<Axis> plain) {
        if (plain.contains(Axis.Y) && plain.contains(Axis.X)) {
            if (this.next(0) == South) {
                return North;
            } else if (this.next(0) == North) {
                return South;
            }
        } else if (plain.contains(Axis.Y) && plain.contains(Axis.Z)) {
            if (this.next(0) == East) {
                return West;
            } else if (this.next(0) == West) {
                return East;
            }
        } else {
            throw new UnsupportedOperationException("Can't mirror at this plain: " + Arrays.toString(plain.toArray(new Axis[] {})));
        }
        return this;
    }

    @Override
    public OrientationNESW next(final int i) {
        final OrientationNESW temp = values()[IntMath.mod((this.ordinal() + i), 4)];
        if (temp != null) {
            return temp;
        }
        throw new NullPointerException();
    }
}
