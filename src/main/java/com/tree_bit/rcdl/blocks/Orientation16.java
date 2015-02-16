package com.tree_bit.rcdl.blocks;

import com.google.common.math.IntMath;

import java.util.Arrays;
import java.util.Set;

/**
 * Enum of the cardinal directions, mapping those to their block data value.
 *
 * <p>
 * Allowed axes for rotation (multiple of 30 degree) are:
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
public enum Orientation16 implements IOrientationEnum, IDataValueEnum {
    /** South */
    S(0),
    /** South-south-west */
    SSW(1),
    /** South-west */
    SW(2),
    /** West-south-west */
    WSW(3),
    /** West */
    W(4),
    /** West-north-west */
    WNW(5),
    /** North-west */
    NW(6),
    /** North-north-west */
    NNW(7),
    /** North */
    N(8),
    /** North-north-east */
    NNE(9),
    /** North-east */
    NE(10),
    /** East-north-east */
    ENE(11),
    /** East */
    E(12),
    /** East-south-east */
    ESE(13),
    /** South-east */
    SE(14),
    /** South-south-east */
    SSE(15);

    private final int value;

    private Orientation16(final int value) {
        this.value = value;
    }

    @Override
    public Orientation16 next(final int i) {
        final Orientation16 temp = values()[IntMath.mod((this.ordinal() + i), 16)];
        if (temp != null) {
            return temp;
        }
        throw new NullPointerException();
    }

    @Override
    public Orientation16 rotate(final Axis axis, final int n) {
        if (axis == Axis.Y) {
            return this.next(n);
        }
        throw new UnsupportedOperationException("Can't rotate at this axis: " + axis);
    }

    @Override
    public Orientation16 mirror(final Set<Axis> plain) {

        Axis.checkPlain(plain);

        Orientation16 returnV;

        if (plain.contains(Axis.Y) && plain.contains(Axis.X)) {
            if (this.ordinal() <= 8) {
                // 0-8 => 8-number
                returnV = values()[8 - this.ordinal()];
            } else {
                // 9-15 => 24-number
                returnV = values()[24 - this.ordinal()];
            }
        } else if (plain.contains(Axis.Y) && plain.contains(Axis.Z)) {
            // 16-number mod 16
            returnV = values()[(16 - this.ordinal()) % 16];
        } else {
            throw new UnsupportedOperationException("Can't mirror at this plain: " + Arrays.toString(plain.toArray(new Axis[] {})));
        }

        if (returnV != null) {
            return returnV;
        }
        throw new NullPointerException();
    }

    @Override
    public int getDataValue() {
        return this.value;
    }
}
