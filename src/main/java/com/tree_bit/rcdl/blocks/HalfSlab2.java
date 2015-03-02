package com.tree_bit.rcdl.blocks;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.concurrent.Immutable;

/**
 * Data class for Stone Half Slabs. (Type 2)
 *
 * <p>
 * Allowed axes for rotation (multiple of 90/180 degree) are:
 * <ul>
 * <li>x (180°)</li>
 * <li>y (90°)</li>
 * <li>z (180°)</li>
 * </ul>
 *
 * <p>
 * Allowed plains for mirroring are:
 * <ul>
 * <li>x-y</li>
 * <li>z-y</li>
 * <li>x-z</li>
 * </ul>
 */
@Immutable
public final class HalfSlab2 extends BlockData {

    @SuppressWarnings("null")
    private static Table<SlabType2, SlabPosition, HalfSlab2> instances = HashBasedTable.create();

    static {
        for (final SlabType2 type : SlabType2.values()) {
            if (type == null) {
                throw new NullPointerException();
            }
            for (final SlabPosition pos : SlabPosition.values()) {
                if (pos == null) {
                    throw new NullPointerException();
                }
                instances.put(type, pos, new HalfSlab2(type, pos));
            }
        }
    }

    private HalfSlab2(final SlabType2 type, final SlabPosition position) {
        super(type, position);
    }

    /**
     * Returns an instance of the 'HalfSlab2' data with the given type and
     * position.
     *
     * @param type Type
     * @param position Position
     * @return Instance of a HalfSlab (Type 2)
     */
    public static HalfSlab2 getInstance(final SlabType2 type, final SlabPosition position) {
        return instances.get(type, position);
    }

    /**
     * Returns an instance of the 'HalfSlab2' data with a default type (Stone)
     * and position (Down).
     *
     * @return Instance of a HalfSlab (Type 2)
     */
    public static HalfSlab2 getInstance() {
        return instances.get(SlabType2.RED_SANDSTONE, SlabPosition.DOWN);
    }

    /**
     * Returns all data instances of 'HalfSlab2'.
     *
     * @return Set of all instances
     */
    static Set<HalfSlab2> getInstances() {
        return new HashSet<>(instances.values());
    }
}
