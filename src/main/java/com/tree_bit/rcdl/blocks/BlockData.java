package com.tree_bit.rcdl.blocks;

import com.google.common.base.Joiner;
import com.google.common.base.Objects;
import com.google.common.collect.ImmutableSet;

import java.util.Arrays;
import java.util.Set;

/**
 * Representing a data value object of a block.
 *
 * <p>
 * Child classes have to implement the singleton pattern (for each valid data
 * combination) and have a static method 'public static getInstance()' without
 * parameters which returns a default Data object. Additional
 * getInstance(params) methods may be added.
 *
 * <p>
 * Additionally a method 'static getInstances()' without parameters has to
 * provide all instances of this data object as a Set.
 */
public abstract class BlockData {

    private enum Orientation implements IOrientationEnum {
        NONE;

        @Override
        public IOrientationEnum rotate(final Axis axis, final int n) {
            return NONE;
        }

        @Override
        public IOrientationEnum mirror(final Set<Axis> plain) {
            Axis.checkPlain(plain);
            return NONE;
        }

        @Override
        public IOrientationEnum next(final int n) {
            return NONE;
        }

        @Override
        public int getDataValue() {
            return 0;
        }

        @Override
        public int getStep() {
            return 90;
        }
    }

    private final SingleInstanceSet<IDataValueEnum> data;

    /**
     * Creates a BlockData instance and sets the data value of this instance.
     *
     * <p>
     * Only one orientation data value could be added. If more than one are
     * added a single one is chosen depending on the implementation.
     *
     * @param data Data values
     */
    @SuppressWarnings("null")
    protected BlockData(final Set<IDataValueEnum> data) {
        this.data = SingleInstanceSet.copyOf(data, IOrientationEnum.class);
    }

    /**
     * Creates a BlockData instance and sets the data value of this instance.
     *
     * <p>
     * Only one orientation data value could be added. If more than one are
     * added a single one is chosen depending on the implementation.
     *
     * @param data Data values
     */
    @SuppressWarnings("null")
    protected BlockData(final IDataValueEnum... data) {
        this.data = SingleInstanceSet.copyOf(Arrays.asList(data), IOrientationEnum.class);
    }

    /**
     * Rotates the block the given amount of degree. (Axis viewed from +infinity
     * to -infinity/zero)
     *
     * <p>
     * The given degree have to be a multiple of the allowed rotation step (e.g.
     * 30°, 90°). This is dependent on the block.
     *
     * <p>
     * Axes may be excluded for some blocks and throw an
     * {@link UnsupportedOperationException}.
     *
     *
     * @param axis Axis of rotation
     * @param degree Degree
     * @return Data with new orientation
     *
     * @throws UnsupportedOperationException if the axis is not supported
     * @throws IllegalArgumentException if the given degree are no multiple of
     *         the allowed step
     */
    public final BlockData rotate(final Axis axis, final int degree) {
        return this.setData(this.getOrientation().rotateDegree(axis, degree));
    }

    /**
     * Mirrors the block at the given plain.
     * <p>
     * To create a plain you should use {@link Axis#plain(Axis, Axis)}.
     *
     * <p>
     * Plains may be excluded for some blocks and throw an
     * {@link UnsupportedOperationException}.
     *
     *
     * @param plain Mirror at the given plain
     * @return Data with new orientation
     *
     * @throws UnsupportedOperationException if the axes are not supported
     */
    public final BlockData mirror(final Set<Axis> plain) {
        return this.setData(this.getOrientation().mirror(plain));
    }

    /**
     * Returns the data of this block as a SingleInstanceSet.
     *
     * @return Map containing all data of the block
     */
    public final SingleInstanceSet<IDataValueEnum> getData() {
        return SingleInstanceSet.copyOf(this.data.asSet());
    }

    @SuppressWarnings("null")
    // Classes are not null
    private IOrientationEnum getOrientation() {
        final ImmutableSet<IOrientationEnum> instances = this.data.getInstancesOf(IOrientationEnum.class);
        if (instances.isEmpty()) {
            return Orientation.NONE;
        }
        return instances.asList().get(0);
    }

    /**
     * Sets a data value of this BlockData to the given value.
     *
     * @param data New data value
     * @return BlockData instance with the changed data values.
     */
    @SuppressWarnings("null")
    final BlockData setData(final IDataValueEnum data) {
        final SingleInstanceSet<IDataValueEnum> newData = this.getData();
        newData.add(data);
        return BlockDataFactory.getInstance(this.getClass(), newData.asSet());
    }

    /**
     * Returns the combined data value for this block data.
     *
     * @return Combined data value
     */
    final int getDataValue() {
        int sum = 0;
        for (final IDataValueEnum data : this.getData()) {
            sum += data.getDataValue();
        }
        return sum;
    }

    @Override
    @SuppressWarnings("null")
    public String toString() {
        String tileInfo = null;
        if (this instanceof HasTileEntity) {
            tileInfo = ((HasTileEntity) this).getTileEntity().toString();
        }
        return Objects.toStringHelper(this).add("Combined", this.getDataValue())
                .add("Data", Joiner.on(',').skipNulls().join(this.getData().asSet().toArray())).addValue(tileInfo).omitNullValues().toString();
    }

}
