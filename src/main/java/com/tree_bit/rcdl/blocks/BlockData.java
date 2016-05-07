package com.tree_bit.rcdl.blocks;

import static com.google.common.base.Preconditions.checkNotNull;

import com.google.common.base.Joiner;
import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableSet;
import com.tree_bit.blockapi.data.IDataValue;
import com.tree_bit.blockapi.entities.TileEntity;
import com.tree_bit.rcdl.blocks.dv.IOrientationEnum;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

/**
 * Representing a data value object of a block.
 */
public abstract class BlockData {

    private enum Orientation implements IOrientationEnum {
        NONE;

        @Override
        @NonNull
        public IOrientationEnum rotate(final Axis axis, final int n) {
            return NONE;
        }

        @Override
        @NonNull
        public IOrientationEnum mirror(final Set<Axis> plain) {
            Axis.checkPlain(plain);
            return NONE;
        }

        @Override
        @NonNull
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

    private final SingleInstanceSet<IDataValue> data;

    @Nullable
    private final TileEntity entity;

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
     * {@link IDataValue} array has to exist. The validity of the array
     * should be checked by
     * {@link #validateDV(IDataValue[], Class, Class...)}. A default
     * instance of each class should be registered at the
     * {@link BlockDataFactory}
     *
     * @param entity Tile entity
     * @param data Data values
     */
    protected BlockData(@Nullable final TileEntity entity, final Set<IDataValue> data) {
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
     * {@link IDataValue} array has to exist. The validity of the array
     * should be checked by
     * {@link #validateDV(IDataValue[], Class, Class...)}. A default
     * instance of each class should be registered at the
     * {@link BlockDataFactory}
     *
     * @param data Data values
     */
    protected BlockData(final Set<IDataValue> data) {
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
     * {@link IDataValue} array has to exist. The validity of the array
     * should be checked by
     * {@link #validateDV(IDataValue[], Class, Class...)}. A default
     * instance of each class should be registered at the
     * {@link BlockDataFactory}
     *
     * @param data Data values
     */
    protected BlockData(final @NonNull IDataValue... data) {
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
     * {@link IDataValue} array has to exist. The validity of the array
     * should be checked by
     * {@link #validateDV(IDataValue[], Class, Class...)}. A default
     * instance of each class should be registered at the
     * {@link BlockDataFactory}
     *
     * @param entity Tile entity
     * @param data Data values
     */
    protected BlockData(@Nullable final TileEntity entity, final @NonNull IDataValue... data) {
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
    public final SingleInstanceSet<IDataValue> getData() {
        return SingleInstanceSet.copyOf(this.data.asSet());
    }

    /**
     * Returns the TileEntity of this block. This value may not be present.
     *
     * @return TileEntity
     */
    public Optional<TileEntity> getTileEntity() {
        return Optional.ofNullable(this.entity);
    }

    private IOrientationEnum getOrientation() {
        final ImmutableSet<IOrientationEnum> instances = this.data.getInstancesOf(IOrientationEnum.class);
        if (instances.isEmpty()) {
            return Orientation.NONE;
        }
        final List<IOrientationEnum> l = instances.asList();
        return checkNotNull(l.get(0));
    }

    /**
     * Sets a data value of this BlockData to the given value.
     *
     * @param data New data value
     * @return BlockData instance with the changed data values.
     */
    final BlockData setData(final IDataValue data) {
        final SingleInstanceSet<IDataValue> newData = this.getData();
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
        for (final IDataValue data : this.getData()) {
            sum += data.getDataValue();
        }
        return sum;
    }

    @Override
    @NonNull
    public String toString() {
        String tileInfo = null;
        if (this.getTileEntity().isPresent()) {
            tileInfo = this.getTileEntity().get().toString();
        }
        return MoreObjects.toStringHelper(this).add("Combined", this.getDataValue())
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
    @SafeVarargs
    @NonNull
    protected static IDataValue[] validateDV(final @NonNull IDataValue[] data, final Class<? extends com.tree_bit.blockapi.data.IDataValue> clas,
            final @NonNull Class<? extends IDataValue>... classes) {

        final Set<Class<? extends IDataValue>> clazzes = new HashSet<>();
        clazzes.add(clas);
        clazzes.addAll(Arrays.asList(classes));
        if (data.length != clazzes.size()) {
            throw new IllegalArgumentException("The amount of given data values has to match the number of classes. Given: " + Arrays.toString(data)
                    + " Expected: " + clazzes.toString());
        }
        outer: for (final IDataValue dv : data) {
            for (final Class<? extends IDataValue> clazz : clazzes) {
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
        return Objects.hash(this.data, this.entity);
    }

    @Override
    public boolean equals(@Nullable final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof BlockData)) {
            return false;
        }
        final BlockData other = (BlockData) obj;

        return Objects.equals(this.data, other.data) && Objects.equals(this.entity, other.entity);
    }
}
