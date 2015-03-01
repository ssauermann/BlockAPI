package com.tree_bit.rcdl.blocks;

import com.google.common.collect.ImmutableSet;

import org.eclipse.jdt.annotation.NonNull;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Set that can contain a maximum of one instance per class.
 *
 * @param <T> Type
 */
final class SingleInstanceSet<T> implements Iterable<T> {

    private final Map<Class<? extends T>, T> map = new HashMap<>();

    /**
     * Creates an empty SingleInstanceSet.
     */
    public SingleInstanceSet() {}

    /**
     * Adds a value to this set. A value with the same class as this one is
     * replaced.
     *
     * @param value Value
     */
    @SuppressWarnings("unchecked")
    public void add(final T value) {
        this.map.put((@NonNull Class<? extends T>) value.getClass(), value);
    }

    /**
     * Returns an immutable Java Collections set representation of this set.
     *
     * @return Set
     */
    @SuppressWarnings("null")
    public ImmutableSet<T> asSet() {
        return ImmutableSet.copyOf(this.map.values());
    }

    @SuppressWarnings("null")
    @Override
    public Iterator<T> iterator() {
        return this.map.values().iterator();
    }

    /**
     * Gets an value by its class.
     *
     * @param clazz Class of the value
     * @return Value
     */
    @SuppressWarnings("null")
    public T get(final Class<? extends T> clazz) {
        return this.map.get(clazz);
    }

    /**
     * Creates a SingleInstanceSet with the elements of given collection.
     *
     * @param collection Collection
     * @return Set
     */
    public static <@NonNull E> SingleInstanceSet<E> copyOf(final Collection<E> collection) {
        final SingleInstanceSet<E> set = new SingleInstanceSet<>();
        for (final E element : collection) {
            set.add(element);
        }
        return set;
    }

}
