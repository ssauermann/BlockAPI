package com.tree_bit.rcdl.blocks;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
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

    private final SlabType2 type;
    private final SlabPosition position;

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
        this.type = type;
        this.position = position;
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

    @Override
    public BlockData rotate(final Axis axis, final int degree) {

        if (axis == Axis.Y) {
            final int count = BlockData.toCount(degree, 90);
            return getInstance(this.type, this.position.rotate(axis, count));
        }
        final int count = BlockData.toCount(degree, 180);
        return getInstance(this.type, this.position.rotate(axis, count));
    }

    @Override
    public BlockData mirror(final Set<Axis> plain) {
        Axis.checkPlain(plain);
        return getInstance(this.type, this.position.mirror(plain));
    }

    @Override
    @SuppressWarnings("null")
    public Map<Class<? extends IDataValueEnum>, IDataValueEnum> getData() {
        final Map<Class<? extends IDataValueEnum>, IDataValueEnum> map = new HashMap<>();
        map.put(SlabPosition.class, this.position);
        map.put(SlabType2.class, this.type);
        return map;
    }
}
