package com.tree_bit.rcdl.blocks;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

import java.util.HashSet;
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
public class StandingSign extends BlockData {

    @SuppressWarnings("null")
    private static Table<Orientation16, TileEntity, StandingSign> instances = HashBasedTable.create();

    private StandingSign(final Orientation16 orientation, final TileEntity entity) {
        super(entity, orientation);
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

}
