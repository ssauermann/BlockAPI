package com.tree_bit.rcdl.blocks;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Table;

import org.eclipse.jdt.annotation.NonNull;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Set;

/**
 * Factory class for BlockData instances.
 */
class BlockDataFactory {

    @SuppressWarnings("null")
    // Never null
    private static final Table<Class<? extends BlockData>, Set<? extends IDataValueEnum>, BlockData> map = HashBasedTable.create();

    /**
     * Registers an BlockData instance of the given class with the given data
     * values.
     *
     * @param clazz Class of a subtype of BlockData
     * @param instance Instance
     * @param dataValues Collection of data values
     */
    static <T extends BlockData> void register(final Class<T> clazz, final T instance, final Collection<IDataValueEnum> dataValues) {

        // Defensive copy
        @SuppressWarnings("null")
        // Never null
        final @NonNull ImmutableSet<IDataValueEnum> dv = ImmutableSet.copyOf(dataValues);

        map.put(clazz, dv, instance);
    }

    /**
     * Registers an BlockData instance of the given class with the given data
     * values.
     *
     * @param clazz Class of a subtype of BlockData
     * @param instance Instance
     * @param dataValues Data values
     */
    @SuppressWarnings("null")
    // @NonNull IDataValueEnum[] == IDataValueEnum @NonNull[]
    static <T extends BlockData> void register(final Class<T> clazz, final T instance, final IDataValueEnum... dataValues) {
        BlockDataFactory.register(clazz, instance, Arrays.asList(dataValues));
    }


    /**
     * Returns a BlockData instance of the given class and data values. The
     * given data values have to match the allowed values of this class.
     *
     * @param clazz Class of a subtype of BlockData
     * @param dataValues Collection of data values this BlockData should have
     * @return BlockData of the given class with the given data values
     *
     * @throws IllegalArgumentException if the given data values are invalid for
     *         the given class.
     */
    @SuppressWarnings({"null", "unused"})
    // Return value of map can be null
    static <T extends BlockData> T getInstance(final Class<T> clazz, final Collection<IDataValueEnum> dataValues) {

        // Defensive copy
        final ImmutableSet<IDataValueEnum> dv = ImmutableSet.copyOf(dataValues);

        BlockData bd = BlockDataFactory.map.get(clazz, dv);
        if (bd == null) {
            // Create new instance if not existing
            synchronized (BlockDataFactory.class) {
                bd = BlockDataFactory.map.get(clazz, dv);
                if (bd == null) {
                    try {
                        final Constructor<T> construct = clazz.getDeclaredConstructor(IDataValueEnum[].class);
                        construct.setAccessible(true);
                        final T instance = construct.newInstance(dv);
                        BlockDataFactory.register(clazz, instance, dv);
                        return instance;
                    } catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException
                            | InvocationTargetException e) {
                        throw new AssertionError(e);
                    }
                }
            }
        } else if (bd.getClass() == clazz) {
            return clazz.cast(bd);
        }
        throw new AssertionError();
    }

    /**
     * Returns a BlockData instance of the given class and data values. The
     * given data values have to match the allowed values of this class.
     *
     * @param clazz Class of a subtype of BlockData
     * @param dataValues Data values this BlockData should have
     * @return BlockData of the given class with the given data values
     *
     * @throws IllegalArgumentException if the given data values are invalid for
     *         the given class.
     */
    static <T extends BlockData> T getInstance(final Class<T> clazz, final IDataValueEnum... dataValues) {
        @SuppressWarnings("null")
        // @NonNull IDataValueEnum[] == IDataValueEnum @NonNull[]
        final T ret = getInstance(clazz, Arrays.asList(dataValues));
        return ret;
    }
}
