package com.tree_bit.rcdl.blocks;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

import java.util.HashSet;
import java.util.Set;

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

    @SuppressWarnings("null")
    private static Table<OrientationNESW, Delay, Repeater> instances = HashBasedTable.create();

    static {
        for (final OrientationNESW orientation : OrientationNESW.values()) {
            if (orientation == null) {
                throw new NullPointerException();
            }
            for (final Delay delay : Delay.values()) {
                if (delay == null) {
                    throw new NullPointerException();
                }
                instances.put(orientation, delay, new Repeater(orientation, delay));
            }
        }
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
    public static Repeater getInstance(final OrientationNESW orientation, final Delay delay) {
        return instances.get(orientation, delay);
    }

    /**
     * Returns an instance of the 'Repeater' data with a default orientation
     * (North) and default delay (1 Tick).
     *
     * @return Instance of a repeater
     */
    public static Repeater getInstance() {
        return getInstance(OrientationNESW.North, Delay.D1);
    }

    /**
     * Returns all data instances of 'Repeater'.
     *
     * @return Set of all instances
     */
    static Set<Repeater> getInstances() {
        return new HashSet<>(instances.values());
    }

}
