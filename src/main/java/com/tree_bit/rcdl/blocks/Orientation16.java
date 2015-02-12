package com.tree_bit.rcdl.blocks;

import com.tree_bit.math.MathExtended;

/**
 * Enum containing all 16 different orientations minecraft has to offer for
 * blocks.
 *
 * <p>
 * This is used by:
 * <ul>
 * <li>{@link StandingSign}</li>
 * </ul>
 * </p>
 *
 * @author Sascha Sauermann
 * @author Alexander
 */
public enum Orientation16 implements IOrientationEnum, IDataValueEnum {
    South(0), SouthSouthWest(1), SouthWest(2), WestSouthWest(3), West(4), WestNorthWest(5), NorthWest(6), NorthNorthWest(7), North(8), NorthNorthEast(
            9), NorthEast(10), EastNorthEast(11), East(12), EastSouthEast(13), SouthEast(14), SouthSouthEast(15);

    /** Data value */
    private int value;

    /**
     * Creates a new orientation.
     *
     * @param value <b>int</b> data value
     */
    private Orientation16(int value) {
        this.value = value;
    }

    /**
     * @return <b>Orientation</b> next orientation (out of 16 possible)
     */
    @Override
    public Orientation16 next(int i) {
        final Orientation16 temp = values()[MathExtended.mod((this.ordinal() + i), 16)];
        if (temp != null) {
            return temp;
        }
        throw new IllegalStateException();
    }

    /**
     * @return <b>Orientation</b> new orientation (out of 16 possible)
     */
    @Override
    public Orientation16 rotate(int n) {
        return this.next(n);
    }

    /**
     * @return <b>Orientation</b> mirrored orientation (out of 16 possible)
     */
    @Override
    public Orientation16 mirror(boolean xAxis) {
        Orientation16 returnV;
        if (xAxis) {
            if (this.ordinal() <= 8) {
                // 0-8 => 8-number
                returnV = values()[8 - this.ordinal()];
            } else {
                // 9-15 => 24-number
                returnV = values()[24 - this.ordinal()];
            }
        } else {
            // 16-number mod 16
            returnV = values()[(16 - this.ordinal()) % 16];
        }
        if (returnV != null) {
            return returnV;
        }
        throw new IllegalStateException();
    }

    @Override
    public int getDataValue() {
        return this.value;
    }
}
