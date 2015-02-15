package com.tree_bit.rcdl.blocks;

import java.util.Arrays;
import java.util.Set;

/**
 * Data values of the 'Redstone Wire' block.
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
public enum Redstone implements IBlockData {
    /** Signal strength zero (0) */
    L0(0),
    /** Signal strength one (1) */
    L1(1),
    /** Signal strength two (2) */
    L2(2),
    /** Signal strength three (3) */
    L3(3),
    /** Signal strength four (4) */
    L4(4),
    /** Signal strength five (5) */
    L5(5),
    /** Signal strength six (6) */
    L6(6),
    /** Signal strength seven (7) */
    L7(7),
    /** Signal strength eight (8) */
    L8(8),
    /** Signal strength nine (9) */
    L9(9),
    /** Signal strength ten (10) */
    L10(10),
    /** Signal strength eleven (11) */
    L11(11),
    /** Signal strength twelve (12) */
    L12(12),
    /** Signal strength thirteen (13) */
    L13(13),
    /** Signal strength fourteen (14) */
    L14(14),
    /** Signal strength fifteen (15) */
    L15(15);

    private final int datavalue;

    private Redstone(final int power) {
        this.datavalue = power;
    }

    @Override
    public int getDataValue() {
        return this.datavalue;
    }

    @Override
    public void mirror(final Set<Axis> plain) {
        if (!plain.contains(Axis.Y)) {
            throw new UnsupportedOperationException("Can't mirror at this plain: " + Arrays.toString(plain.toArray(new Axis[] {})));
        }
        // No changes
    }

    @Override
    public void rotate(final Axis a, final int degree) {
        if (a != Axis.Y) {
            throw new UnsupportedOperationException("Can't rotate at this axis: " + a);
        }
        BlockData.toCount(degree, 90); // Validates 'degree' as multiple of 90
    }

}
