package com.tree_bit.rcdl.blocks;

import com.tree_bit.rcdl.blocks.dv.IDataValueEnum;
import com.tree_bit.rcdl.blocks.dv.IOrientationEnum;

import com.google.common.base.Joiner;
import com.google.common.base.Objects;
import com.google.common.collect.ImmutableSet;

import org.eclipse.jdt.annotation.Nullable;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * Representing a data value object of a block.
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


    private final @Nullable TileEntity entity;

    /**
     * Creates a BlockData instance and sets the data value and the tile entity
     * of this instance.
     *
     * <p>
     * Only one orientation data value could be added. If more than one are
     * added a single one is chosen depending on the implementation.
     *
     * <p>
     * Child classes have to have a constructor without parameters which returns
     * a default Data object. Additional a constructor which accepts an
     * {@link IDataValueEnum} array has to exist. The validity of the array
     * should be checked by
     * {@link BlockData#validateDV(IDataValueEnum[], Class...)}. A default
     * instance of each class should be registered at the
     * {@link BlockDataFactory}
     *
     * @param entity Tile entity
     * @param data Data values
     */
    @SuppressWarnings("null")
    protected BlockData(@Nullable final TileEntity entity, final Set<IDataValueEnum> data) {
        this.data = SingleInstanceSet.copyOf(data, IOrientationEnum.class);
        this.entity = entity;
    }

    /**
     * Creates a BlockData instance and sets the data value of this instance.
     *
     * <p>
     * Only one orientation data value could be added. If more than one are
     * added a single one is chosen depending on the implementation.
     *
     * <p>
     * Child classes have to have a constructor without parameters which returns
     * a default Data object. Additional a constructor which accepts an
     * {@link IDataValueEnum} array has to exist. The validity of the array
     * should be checked by
     * {@link BlockData#validateDV(IDataValueEnum[], Class...)}. A default
     * instance of each class should be registered at the
     * {@link BlockDataFactory}
     *
     * @param data Data values
     */
    protected BlockData(final Set<IDataValueEnum> data) {
        this(null, data);
    }

    /**
     * Creates a BlockData instance and sets the data value of this instance.
     *
     * <p>
     * Only one orientation data value could be added. If more than one are
     * added a single one is chosen depending on the implementation.
     *
     * <p>
     * Child classes have to have a constructor without parameters which returns
     * a default Data object. Additional a constructor which accepts an
     * {@link IDataValueEnum} array has to exist. The validity of the array
     * should be checked by
     * {@link BlockData#validateDV(IDataValueEnum[], Class...)}. A default
     * instance of each class should be registered at the
     * {@link BlockDataFactory}
     *
     * @param data Data values
     */
    protected BlockData(final IDataValueEnum... data) {
        this(null, data);
    }

    /**
     * Creates a BlockData instance and sets the data value and the tile entity
     * of this instance.
     *
     * <p>
     * Only one orientation data value could be added. If more than one are
     * added a single one is chosen depending on the implementation.
     *
     * <p>
     * Child classes have to have a constructor without parameters which returns
     * a default Data object. Additional a constructor which accepts an
     * {@link IDataValueEnum} array has to exist. The validity of the array
     * should be checked by
     * {@link BlockData#validateDV(IDataValueEnum[], Class...)}. A default
     * instance of each class should be registered at the
     * {@link BlockDataFactory}
     *
     * @param entity Tile entity
     * @param data Data values
     */
    @SuppressWarnings("null")
    protected BlockData(@Nullable final TileEntity entity, final IDataValueEnum... data) {
        this.data = SingleInstanceSet.copyOf(Arrays.asList(data), IOrientationEnum.class);
        this.entity = entity;
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

    /**
     * Returns the TileEntity of this block.
     *
     * @return TileEntity
     */
    @SuppressWarnings("null")
    public Optional<TileEntity> getTileEntity() {
        if (this.entity != null) {
            return Optional.of(this.entity);
        }
        return Optional.empty();
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
        // Exclude Orientation.NONE
        if (data instanceof Orientation) {
            return this;
        }
        newData.add(data);
        if (newData.equals(this.getData())) {
            return this;
        }
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
        if (this.getTileEntity().isPresent()) {
            tileInfo = this.getTileEntity().get().toString();
        }
        return Objects.toStringHelper(this).add("Combined", this.getDataValue())
                .add("Data", Joiner.on(',').skipNulls().join(this.getData().asSet().toArray())).addValue(tileInfo).omitNullValues().toString();
    }

    /**
     * Checks the validity of data values as constructor parameters. Each data
     * value has to match exactly one of the given classes.
     *
     * @param data Data values
     * @param clas Allowed data value class
     * @param classes Additional data value classes
     * @return Checked data values
     *
     * @throws IllegalArgumentException if the given data values are invalid for
     *         the given class
     */
    @SuppressWarnings("null")
    @SafeVarargs
    protected static IDataValueEnum[] validateDV(final IDataValueEnum[] data, final Class<? extends IDataValueEnum> clas,
            final Class<? extends IDataValueEnum>... classes) {
        final Set<Class<? extends IDataValueEnum>> clazzes = new HashSet<>();
        clazzes.add(clas);
        clazzes.addAll(Arrays.asList(classes));
        if (data.length != clazzes.size()) {
            throw new IllegalArgumentException("The amount of given data values has to match the number of classes. Given: " + Arrays.toString(data)
                    + " Expected: " + clazzes.toString());
        }
        outer: for (final IDataValueEnum dv : data) {
            for (final Class<? extends IDataValueEnum> clazz : clazzes) {
                if (dv.getClass() == clazz) {
                    continue outer;
                }
            }
            throw new IllegalArgumentException("Can't construct a BlockData object with the given data values. Given: " + Arrays.toString(data)
                    + " Expected: " + clazzes.toString());
        }
        return data;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = (prime * result) + (this.data.hashCode());
        result = (prime * result) + ((this.entity != null) ? this.entity.hashCode() : 0);
        return result;
    }

    @Override
    public boolean equals(final @Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof BlockData)) {
            return false;
        }
        final BlockData other = (BlockData) obj;
        if (!this.data.equals(other.data)) {
            return false;
        }
        if (this.entity != null) {
            if (!this.entity.equals(other.entity)) {
                return false;
            }
        } else if (other.entity != null) {
            return false;
        }
        return true;
    }
}
