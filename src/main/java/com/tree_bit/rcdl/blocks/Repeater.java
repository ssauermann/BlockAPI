package com.tree_bit.rcdl.blocks;

import javax.annotation.concurrent.Immutable;

/**
 * Data values of a 'Repeater' block.
 *
 * <p>
 * Data enum: {@link OrientationNESW}, {@link Delay}
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
public final class Repeater extends BlockData {

    /**
     * Representing the delay of a 'Repeater' block.
     */
    public enum Delay implements IDataValueEnum {
        /** 1-Tick Delay */
        D1(0),
        /** 2-Tick Delay */
        D2(4),
        /** 3-Tick Delay */
        D3(8),
        /** 4-Tick Delay */
        D4(12);

        private int value;

        private Delay(final int value) {
            this.value = value;
        }

        @Override
        public int getDataValue() {
            return this.value;
        }
    }

    static {
        @SuppressWarnings("null")
        final Class<Repeater> clazz = Repeater.class;
        for (final OrientationNESW orientation : OrientationNESW.values()) {
            if (orientation == null) {
                throw new NullPointerException();
            }
            for (final Delay delay : Delay.values()) {
                if (delay == null) {
                    throw new NullPointerException();
                }
                BlockDataFactory.register(clazz, new Repeater(orientation, delay));
            }
        }
        BlockDataFactory.registerDefault(clazz, new Repeater(OrientationNESW.North, Delay.D1));
    }

    private Repeater(final OrientationNESW orientation, final Delay delay) {
        super(orientation, delay);
    }

    /**
     * Returns an instance of the 'Repeater' data with the given orientation and
     * delay.
     *
     * @param orientation Orientation
     * @param delay Delay
     * @return Instance of a repeater
     */
    @SuppressWarnings("null")
    public static Repeater getInstance(final OrientationNESW orientation, final Delay delay) {
        return BlockDataFactory.getInstance(Repeater.class, orientation, delay);
    }

    /**
     * Returns an instance of the 'Repeater' data with a default orientation
     * (North) and default delay (1 Tick).
     *
     * @return Instance of a repeater
     */
    @SuppressWarnings("null")
    public static Repeater getInstance() {
        return BlockDataFactory.getDefaultInstance(Repeater.class);
    }

}
