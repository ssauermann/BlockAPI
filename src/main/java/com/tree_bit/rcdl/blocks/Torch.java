package com.tree_bit.rcdl.blocks;

import com.google.common.math.IntMath;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javafx.geometry.Orientation;

/**
 * Data values of a 'Torch' block.
 *
 * <p>
 * Data enum: {@link Orientation}
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
public class Torch extends BlockData {

    /**
     * Orientations of a Torch
     */
    public enum TorchOrientation implements IDataValueEnum, IOrientationEnum {

        /** The Facing east. */
        East(1),
        /** The Facing south. */
        South(3),
        /** The Facing west. */
        West(2),
        /** The Facing north. */
        North(4),
        /** The Facing up. */
        Up(5);

        private final int value;

        private TorchOrientation(final int value) {
            this.value = value;
        }

        @Override
        public int getDataValue() {
            return this.value;
        }

        @Override
        public TorchOrientation rotate(final Axis axis, final int n) {
            if (axis != Axis.Y) {
                throw new UnsupportedOperationException("Can't rotate at this axis: " + axis);
            }
            if (this.value == Up.value) {
                return Up;
            }
            return this.next(n + ((n + this.ordinal()) / 4));
        }

        @Override
        public TorchOrientation mirror(final Set<Axis> plain) {
            if ((this == Up) && plain.contains(Axis.Y)) {
                return Up;
            } else if (plain.contains(Axis.Y) && plain.contains(Axis.X)) {
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
        public TorchOrientation next(final int i) {
            final TorchOrientation temp = values()[IntMath.mod((this.ordinal() + i), 5)];
            if (temp != null) {
                return temp;
            }
            throw new NullPointerException();
        }

    }

    private static Map<TorchOrientation, Torch> instances = new HashMap<>();

    static {
        for (final TorchOrientation orientation : TorchOrientation.values()) {
            if (orientation == null) {
                throw new NullPointerException();
            }
            instances.put(orientation, new Torch(orientation));
        }
    }

    private final TorchOrientation data;

    private Torch(final TorchOrientation data) {
        this.data = data;
    }

    /**
     * Returns an instance of the Torch data with the given orientation.
     *
     * @param orientation Torch orientation
     * @return Instance of Torch data
     */
    public static Torch getInstance(final TorchOrientation orientation) {
        return instances.get(orientation);
    }

    /**
     * Returns an instance of the Torch data with a default orientation (UP).
     *
     * @return Instance of Torch data
     */
    public static Torch getInstance() {
        return getInstance(TorchOrientation.Up);
    }

    /**
     * Returns all data instances of 'Torch'.
     *
     * @return Set of all instances
     */
    static Set<Torch> getInstances() {
        return new HashSet<>(instances.values());
    }

    @Override
    public Torch rotate(final Axis axis, final int degree) {
        final int count = BlockData.toCount(degree, 90);
        return Torch.getInstance(this.data.rotate(axis, count));
    }

    @Override
    public Torch mirror(final Set<Axis> plain) {
        Axis.checkPlain(plain);
        return Torch.getInstance(this.data.mirror(plain));
    }

    @Override
    @SuppressWarnings("null")
    public Map<Class<? extends IDataValueEnum>, IDataValueEnum> getData() {
        final Map<Class<? extends IDataValueEnum>, IDataValueEnum> map = new HashMap<>();
        map.put(TorchOrientation.class, this.data);
        return map;
    }
}
