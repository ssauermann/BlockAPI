package com.tree_bit.rcdl.blocks;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.annotation.concurrent.Immutable;

/**
 * Data class for Stone Half Slabs. (Type 1)
 */
@Immutable
public class HalfSlab1 extends BlockData {

    private final SlabType1 type;
    private final SlabPosition position;

    @SuppressWarnings("null")
    private static Table<SlabType1, SlabPosition, HalfSlab1> instances = HashBasedTable.create();

    static {
        for (final SlabType1 type : SlabType1.values()) {
            if (type == null) {
                throw new NullPointerException();
            }
            for (final SlabPosition pos : SlabPosition.values()) {
                if (pos == null) {
                    throw new NullPointerException();
                }
                instances.put(type, pos, new HalfSlab1(type, pos));
            }
        }
    }

    private HalfSlab1(final SlabType1 type, final SlabPosition position) {
        this.type = type;
        this.position = position;
    }

    /**
     * Returns an instance of the 'HalfSlab1' data with the given type and
     * position.
     *
     * @param type Type
     * @param position Position
     * @return Instance of a HalfSlab (Type 1)
     */
    public static HalfSlab1 getInstance(final SlabType1 type, final SlabPosition position) {
        return instances.get(type, position);
    }

    /**
     * Returns an instance of the 'HalfSlab1' data with a default type (Stone)
     * and position (Down).
     *
     * @return Instance of a HalfSlab (Type 1)
     */
    public static HalfSlab1 getInstance() {
        return instances.get(SlabType1.Stone, SlabPosition.Down);
    }

    /**
     * Returns all data instances of 'HalfSlab1'.
     *
     * @return Set of all instances
     */
    static Set<HalfSlab1> getInstances() {
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
        map.put(SlabType1.class, this.type);
        return map;
    }
}
