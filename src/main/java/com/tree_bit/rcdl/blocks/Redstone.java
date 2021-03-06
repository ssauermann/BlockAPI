package com.tree_bit.rcdl.blocks;

import com.tree_bit.rcdl.blocks.dv.DummyOrientation;
import com.tree_bit.rcdl.blocks.dv.IDataValueEnum;

import javax.annotation.concurrent.Immutable;

/**
 * Data values of a 'Redstone Wire' block.
 *
 * <p>
 * Data enum: {@link PowerLevel}
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
@Immutable
public final class Redstone extends BlockData {

    /**
     * Representing the power level of a 'Redstone Wire' block.
     */
    public enum PowerLevel implements IDataValueEnum {
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

        private PowerLevel(final int power) {
            this.datavalue = power;
        }

        @Override
        public int getDataValue() {
            return this.datavalue;
        }
    }

    private Redstone() {
        super(PowerLevel.L0, DummyOrientation.NONE);
    }

    private Redstone(final PowerLevel level) {
        super(level, DummyOrientation.NONE);
    }

    private Redstone(final IDataValueEnum[] values) {
        super(validateDV(values, PowerLevel.class, DummyOrientation.class));
    }

    /**
     * Returns an instance of the 'Redstone Wire' data with a power level of
     * zero.
     *
     * @return Instance of 'Redstone Wire' data
     */
    public static Redstone getInstance() {
        @SuppressWarnings("null")
        final Class<Redstone> clazz = Redstone.class;
        return BlockDataFactory.getDefaultInstance(clazz);
    }

    /**
     * Returns an instance of the 'Redstone Wire' data with the given power
     * level.
     *
     * @param level Redstone power level
     * @return Instance of 'Redstone Wire' data
     */
    public static Redstone getInstance(final PowerLevel level) {
        @SuppressWarnings("null")
        final Class<Redstone> clazz = Redstone.class;
        return BlockDataFactory.getInstance(clazz, level, DummyOrientation.NONE);
    }


}
