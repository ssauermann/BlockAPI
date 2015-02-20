package com.tree_bit.rcdl.blocks;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Data values of a 'Hanging Sign' block.
 *
 * <p>
 * Data enum: {@link Orientation16}, {@link FormatText}
 *
 * <p>
 * Allowed axes for rotation (multiple of 90 degree) are:
 * <ul>
 * <li>y</li>
 * </ul>
 *
 * <p>
 * Allowed plains for mirroring are: *
 * <ul>
 * <li>x-y</li>
 * <li>z-y</li>
 * </ul>
 * *Note: This mirroring does not include the text.
 */
public class StandingSign extends BlockData implements HasTileEntity {

    private final Orientation16 orientation;
    private final TileEntity entity;

    @SuppressWarnings("null")
    private static Table<Orientation16, TileEntity, StandingSign> instances = HashBasedTable.create();

    private StandingSign(final Orientation16 orientation, final TileEntity entity) {
        this.orientation = orientation;
        this.entity = entity;
    }

    /**
     * Returns an instance of the 'StandingSign' data with a default orientation
     * (North) and no text.
     *
     * @return Instance of a StandingSign
     */
    public static StandingSign getInstance() {
        @SuppressWarnings("null")
        final TileEntity e = HangingSign.createEntity(new FormatText[0]);

        return StandingSign.getOrCreate(Orientation16.N, e);

    }

    /**
     * Returns an instance of the 'StandingSign' data with the given orientation
     * and text.
     *
     * @param orientation Orientation
     * @param text Array of text containing a maximum of 4 entries (one per each
     *        line)
     * @return Instance of a StandingSign
     *
     * @throws IllegalArgumentException if text has {@literal length>4}
     */
    public static StandingSign getInstance(final Orientation16 orientation, final FormatText[] text) {
        final TileEntity e = HangingSign.createEntity(text);
        return getOrCreate(orientation, e);
    }

    /**
     * Returns all data instances of 'StandingSign'.
     *
     * @return Set of all instances
     */
    static Set<StandingSign> getInstances() {
        return new HashSet<>(instances.values());
    }

    @SuppressWarnings({"null", "unused"})
    // THIS IS NOT DEAD CODE!!!! instance can be null
    private static StandingSign getOrCreate(final Orientation16 orientation, final TileEntity e) {
        StandingSign instance = instances.get(orientation, e);
        // No dead code
        if (instance == null) {
            synchronized (StandingSign.class) {
                instance = instances.get(orientation, e);
                if (instance == null) {
                    instance = new StandingSign(orientation, e);
                }
                instances.put(orientation, e, instance);
            }
        }
        return instance;
    }

    @Override
    public TileEntity getTileEntity() {
        return this.entity;
    }

    @Override
    public BlockData rotate(final Axis axis, final int degree) {
        if (axis != Axis.Y) {
            throw new UnsupportedOperationException("Can't rotate at this axis: " + axis);
        }

        final int count = BlockData.toCount(degree, 30);
        return getOrCreate(this.orientation.rotate(axis, count), this.entity);
    }

    @Override
    public BlockData mirror(final Set<Axis> plain) {
        Axis.checkPlain(plain);
        if (!plain.contains(Axis.Y)) {
            throw new UnsupportedOperationException("Can't mirror at this plain: " + Arrays.toString(plain.toArray(new Axis[] {})));
        }

        return getOrCreate(this.orientation.mirror(plain), this.entity);
    }

    @Override
    @SuppressWarnings("null")
    public Map<Class<? extends IDataValueEnum>, IDataValueEnum> getData() {
        final Map<Class<? extends IDataValueEnum>, IDataValueEnum> map = new HashMap<>();
        map.put(Orientation16.class, this.orientation);
        return map;
    }


}
