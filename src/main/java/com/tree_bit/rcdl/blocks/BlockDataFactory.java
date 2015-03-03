package com.tree_bit.rcdl.blocks;

import com.tree_bit.rcdl.blocks.dv.IDataValueEnum;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.ImmutableSet;

import org.eclipse.jdt.annotation.NonNull;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Factory class for BlockData instances.
 *
 * <p>
 * Each supported class has to have a constructor (can be private) with no
 * arguments which returns a default data object and one with one parameter of
 * type: IDataValueEnum[] <br>
 * If the given data values are invalid for the block data type, a
 * IllegalArgumentException should be thrown.
 */
class BlockDataFactory {

    private static class Loader extends CacheLoader<DataKey<Class<? extends BlockData>, ImmutableSet<IDataValueEnum>>, BlockData> {

        Loader() {}

        @Override
        public BlockData load(final DataKey<Class<? extends BlockData>, ImmutableSet<IDataValueEnum>> key) throws Exception {
            final Class<? extends BlockData> clazz = key.getRow();
            final ImmutableSet<IDataValueEnum> dv = key.getColumn();
            return create(clazz, dv);
        }

        private static <T extends BlockData> T create(final Class<T> clazz, final ImmutableSet<IDataValueEnum> dv) {

            try {
                final Constructor<T> construct = clazz.getDeclaredConstructor(IDataValueEnum[].class);
                construct.setAccessible(true);
                final T instance = construct.newInstance(new Object[] {dv.toArray(new IDataValueEnum[0])});
                return instance;
            } catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException
                    | InvocationTargetException e) {
                throw new AssertionError("Class: " + clazz + " DV: " + dv.toString(), e);
            }
        }

    }


    private static final LoadingCache<DataKey<Class<? extends BlockData>, ImmutableSet<IDataValueEnum>>, BlockData> cache;

    static {
        final LoadingCache<DataKey<Class<? extends BlockData>, ImmutableSet<IDataValueEnum>>, BlockData> c =
                CacheBuilder.newBuilder().weakValues().build(new Loader());
        if (c != null) {
            cache = c;
        } else {
            throw new AssertionError();
        }
    }

    private static final Map<Class<? extends BlockData>, BlockData> defaults = new HashMap<>();


    @SuppressWarnings("unchecked")
    private static <T extends BlockData> T equalOrThis(final Class<T> clazz, final T instance, final Collection<IDataValueEnum> dataValues) {
        // Defensive copy
        final SingleInstanceSet<IDataValueEnum> dv = SingleInstanceSet.copyOf(dataValues);
        final BlockData current = cache.getIfPresent(DataKey.of(clazz, dv.asSet()));
        if (instance.equals(current)) {
            return (@NonNull T) current;
        }
        return instance;
    }

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
        final SingleInstanceSet<IDataValueEnum> dv = SingleInstanceSet.copyOf(dataValues);
        final DataKey<Class<? extends BlockData>, ImmutableSet<IDataValueEnum>> key = DataKey.of(clazz, dv.asSet());
        cache.put(key, instance);
    }

    /**
     * Registers an BlockData instance of the given class with the given data
     * values. If no data values are given, they are extracted from the
     * instance.
     *
     * @param clazz Class of a subtype of BlockData
     * @param instance Instance
     * @param dataValues Data values
     */
    @SuppressWarnings("null")
    // @NonNull IDataValueEnum[] == IDataValueEnum @NonNull[]
    static <T extends BlockData> void register(final Class<T> clazz, final T instance, final IDataValueEnum... dataValues) {
        Collection<IDataValueEnum> dv;
        if (dataValues.length == 0) {
            dv = instance.getData().asSet();
        } else {
            dv = Arrays.asList(dataValues);
        }
        BlockDataFactory.register(clazz, instance, dv);
    }


    /**
     * Returns a BlockData instance of the given class and data values. The
     * given data values have to match the allowed values of this class.
     *
     * <p>
     * Only one instance per data value class is accepted. If given more than
     * one instances per class a single one is selected. Which one gets selected
     * is unspecified and dependent of the implementation.
     *
     * @param clazz Class of a subtype of BlockData
     * @param dataValues Collection of data values this BlockData should have
     * @return BlockData of the given class with the given data values
     *
     * @throws IllegalArgumentException if the given data values are invalid for
     *         the given class
     */
    // Return value of map can be null
    static <T extends BlockData> T getInstance(final Class<T> clazz, final Collection<IDataValueEnum> dataValues) {

        // Defensive copy
        final ImmutableSet<IDataValueEnum> dv = SingleInstanceSet.copyOf(dataValues).asSet();

        final DataKey<Class<? extends BlockData>, ImmutableSet<IDataValueEnum>> key = DataKey.of(clazz, dv);
        final BlockData bd = cache.getUnchecked(key);
        if (bd.getClass() == clazz) {
            return clazz.cast(bd);
        }
        throw new AssertionError();
    }

    /**
     * Returns a BlockData instance of the given class and data values. The
     * given data values have to match the allowed values of this class.
     *
     * <p>
     * Only one instance per data value class is accepted. If given more than
     * one instances per class a single one is selected. Which one gets selected
     * is unspecified and dependent of the implementation.
     *
     * @param clazz Class of a subtype of BlockData
     * @param dataValues Data values this BlockData should have
     * @return BlockData of the given class with the given data values
     *
     * @throws IllegalArgumentException if the given data values are invalid for
     *         the given class
     */
    static <T extends BlockData> T getInstance(final Class<T> clazz, final IDataValueEnum... dataValues) {
        @SuppressWarnings("null")
        // @NonNull IDataValueEnum[] == IDataValueEnum @NonNull[]
        final T ret = getInstance(clazz, Arrays.asList(dataValues));
        return ret;
    }

    /**
     * Registers an BlockData instance of the given class with the given data
     * values as default. An existing default reference will be overwritten. If
     * no data values are given, they are extracted from the instance.
     *
     * <p>
     * If an equal instance was already registered the equal one will be used as
     * default.
     *
     * @param clazz Class of a subtype of BlockData
     * @param instance Instance
     * @param dataValues Data values
     */
    @SuppressWarnings("null")
    static <T extends BlockData> void registerDefault(final Class<T> clazz, final T instance, final IDataValueEnum... dataValues) {
        Collection<IDataValueEnum> dv;
        if (dataValues.length == 0) {
            dv = instance.getData().asSet();
        } else {
            dv = Arrays.asList(dataValues);
        }
        BlockDataFactory.registerDefault(clazz, instance, dv);
    }

    /**
     * Registers an BlockData instance of the given class with the given data
     * values as default. An existing default reference will be overwritten.
     *
     * <p>
     * If an equal instance was already registered the equal one will be used as
     * default.
     *
     * @param clazz Class of a subtype of BlockData
     * @param instance Instance
     * @param dataValues Collection of data values
     */
    static <T extends BlockData> void registerDefault(final Class<T> clazz, final T instance, final Collection<IDataValueEnum> dataValues) {
        final T instanceReg = BlockDataFactory.equalOrThis(clazz, instance, dataValues);
        register(clazz, instanceReg, dataValues);
        defaults.put(clazz, instanceReg);
    }

    /**
     * Returns the default BlockData instance of the given class.
     *
     * @param clazz Class of a subtype of BlockData
     * @return BlockData of the given class with the default data values
     */
    @SuppressWarnings({"unused", "null"})
    // Cast is safe
    static <T extends BlockData> T getDefaultInstance(final Class<T> clazz) {

        BlockData bd = defaults.get(clazz);
        if (bd == null) {
            // Create new instance if not existing
            synchronized (BlockDataFactory.class) {
                bd = defaults.get(clazz);
                if (bd == null) {
                    try {
                        final Constructor<T> construct = clazz.getDeclaredConstructor();
                        construct.setAccessible(true);
                        final T instance = construct.newInstance();
                        // Check if equal instance was already existing?
                        // Y: Register existing as default and return.
                        // N: Register new one and return.
                        @SuppressWarnings("unchecked")
                        // Cast is safe
                        final T existingInstance = (T) cache.getUnchecked(DataKey.of(clazz, instance.getData().asSet()));
                        if (instance.equals(existingInstance)) {
                            BlockDataFactory.registerDefault(clazz, existingInstance);
                            return existingInstance;
                        }
                        BlockDataFactory.registerDefault(clazz, instance);
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

}
