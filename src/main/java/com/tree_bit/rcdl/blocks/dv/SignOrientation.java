package com.tree_bit.rcdl.blocks.dv;

import com.tree_bit.rcdl.blocks.Axis;

import com.google.common.math.IntMath;

import java.util.Arrays;
import java.util.Set;

/**
 * Enum of the four directions (North, East, South, West), mapping those to
 * their block data value.
 */
public enum SignOrientation implements IOrientationEnum {
    /** North */
    North(2),
    /** East */
    East(5),
    /** South */
    South(3),
    /** West */
    West(4);

    private int value;

    private SignOrientation(final int value) {
        this.value = value;
    }

    @Override
    public SignOrientation rotate(final Axis axis, final int n) {
        if (axis == Axis.Y) {
            return this.next(n);
        }
        throw new UnsupportedOperationException("Can't rotate at this axis: " + axis);
    }

    @Override
    public SignOrientation mirror(final Set<Axis> plain) {
        Axis.checkPlain(plain);
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
    public SignOrientation next(final int i) {
        final SignOrientation temp = values()[IntMath.mod(this.ordinal() + i, values().length)];
        if (temp != null) {
            return temp;
        }
        throw new NullPointerException();
    }

    @Override
    public int getDataValue() {
        return this.value;
    }

    @Override
    public int getStep() {
        return 30;
    }
}
