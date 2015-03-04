package com.tree_bit.rcdl.blocks;

import com.tree_bit.rcdl.blocks.dv.IDataValueEnum;
import com.tree_bit.rcdl.blocks.entities.TileEntity;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.ImmutableSet;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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

    private static class Loader extends CacheLoader<DataKey<Class<? extends BlockData>, ImmutableSet<IDataValueEnum>, TileEntity>, BlockData> {

        Loader() {}

        @Override
        public BlockData load(final DataKey<Class<? extends BlockData>, ImmutableSet<IDataValueEnum>, TileEntity> key) throws Exception {
            final Class<? extends BlockData> clazz = key.getRow();
            final ImmutableSet<IDataValueEnum> dv = key.getColumn();
            final TileEntity entity = key.getLayer();
            return create(clazz, dv, entity);
        }

        private static <T extends BlockData> T create(final Class<T> clazz, final ImmutableSet<IDataValueEnum> dv, @Nullable final TileEntity entity) {

            try {
                if (entity == null) {
                    final Constructor<T> construct = clazz.getDeclaredConstructor(IDataValueEnum[].class);
                    construct.setAccessible(true);
                    return construct.newInstance(new Object[] {dv.toArray(new IDataValueEnum[0])});
                }
                // Else with TileEntity
                final Constructor<T> construct = clazz.getDeclaredConstructor(IDataValueEnum[].class, TileEntity.class);
                construct.setAccessible(true);
                return construct.newInstance(new Object[] {dv.toArray(new IDataValueEnum[0]), entity});

            } catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException
                    | InvocationTargetException e) {
                throw new AssertionError("Class: " + clazz + " DV: " + dv.toString(), e);
            }
        }

    }


    private static final LoadingCache<DataKey<Class<? extends BlockData>, ImmutableSet<IDataValueEnum>, TileEntity>, BlockData> cache;

    static {
        final LoadingCache<DataKey<Class<? extends BlockData>, ImmutableSet<IDataValueEnum>, TileEntity>, BlockData> c =
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
    static <T extends BlockData> void register(final Class<T> clazz, final T instance, final @Nullable TileEntity entity,
            final Collection<IDataValueEnum> dataValues) {
        // Defensive copy
        final SingleInstanceSet<IDataValueEnum> dv = SingleInstanceSet.copyOf(dataValues);
        final DataKey<Class<? extends BlockData>, ImmutableSet<IDataValueEnum>, TileEntity> key = DataKey.of(clazz, dv.asSet(), entity);
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
    static <T extends BlockData> void register(final Class<T> clazz, final T instance, final @Nullable TileEntity entity,
            final IDataValueEnum... dataValues) {
        Collection<IDataValueEnum> dv;
        if (dataValues.length == 0) {
            dv = instance.getData().asSet();
        } else {
            dv = Arrays.asList(dataValues);
        }
        BlockDataFactory.register(clazz, instance, entity, dv);
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
    static <T extends BlockData> T getInstance(final Class<T> clazz, final @Nullable TileEntity entity, final Collection<IDataValueEnum> dataValues) {

        // Defensive copy
        final ImmutableSet<IDataValueEnum> dv = SingleInstanceSet.copyOf(dataValues).asSet();

        final DataKey<Class<? extends BlockData>, ImmutableSet<IDataValueEnum>, TileEntity> key = DataKey.of(clazz, dv, entity);
        final BlockData bd = cache.getUnchecked(key);
        if (bd.getClass() == clazz) {
            return clazz.cast(bd);
        }
        throw new AssertionError();
    }

    static <T extends BlockData> T getInstance(final Class<T> clazz, final Collection<IDataValueEnum> dataValues) {
        return getInstance(clazz, null, dataValues);
    }

    static <T extends BlockData> T getInstance(final Class<T> clazz, final IDataValueEnum dataValue, final IDataValueEnum... dataValues) {
        return getInstance(clazz, null, dataValue, dataValues);
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
     * @param dataValue Data value this BlockData should have
     * @param dataValues Additional data values this BlockData should have
     * @return BlockData of the given class with the given data values
     *
     * @throws IllegalArgumentException if the given data values are invalid for
     *         the given class
     */
    @SuppressWarnings("null")
    static <T extends BlockData> T getInstance(final Class<T> clazz, final @Nullable TileEntity entity, final IDataValueEnum dataValue,
            final IDataValueEnum... dataValues) {

        final Set<IDataValueEnum> dvs = new HashSet<>();
        dvs.addAll(Arrays.asList(dataValues));
        dvs.add(dataValue);
        // @NonNull IDataValueEnum[] == IDataValueEnum @NonNull[]
        final T ret = getInstance(clazz, entity, dvs);
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
    static <T extends BlockData> void registerDefault(final Class<T> clazz, final T instance, final @Nullable TileEntity entity,
            final IDataValueEnum... dataValues) {
        Collection<IDataValueEnum> dv;
        if (dataValues.length == 0) {
            dv = instance.getData().asSet();
        } else {
            dv = Arrays.asList(dataValues);
        }
        BlockDataFactory.registerDefault(clazz, instance, entity, dv);
    }

    static <T extends BlockData> void registerDefault(final Class<T> clazz, final T instance, final IDataValueEnum... dataValues) {
        registerDefault(clazz, instance, instance.getTileEntity().orElse(null), dataValues);
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
    static <T extends BlockData> void registerDefault(final Class<T> clazz, final T instance, final @Nullable TileEntity entity,
            final Collection<IDataValueEnum> dataValues) {
        final T instanceReg = BlockDataFactory.equalOrThis(clazz, instance, dataValues);
        register(clazz, instanceReg, entity, dataValues);
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
                        final T existingInstance = (T) cache.getUnchecked(DataKey.of(clazz, instance.getData().asSet(), null));
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
