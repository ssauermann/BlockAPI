package com.tree_bit.rcdl.blocks;

import com.google.common.collect.ImmutableSet;

import org.eclipse.jdt.annotation.NonNull;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Set that can contain a maximum of one instance per class.
 *
 * @param <T> Type
 */
final class SingleInstanceSet<T> implements Iterable<T> {

    private final Map<Class<? extends T>, T> map = new HashMap<>();

    private final Set<Class<? extends T>> constraints;

    /**
     * Creates an empty SingleInstanceSet.
     */
    public SingleInstanceSet() {
        this.constraints = new HashSet<>();
    }

    /**
     * Creates an empty SingleInstanceSet with the given constraints.
     *
     * <p>
     * Constraint: Only one instance of a subtype of each class given as
     * constraint is allowed in this set.
     *
     * <p>
     * Example (classes A, B extends A, C extends A): <br>
     * <code>
     * A<br>
     * B --> A<br>
     * C --> A<br>
     * <br>
     * If A is added as constraint, only a single instance of A,B or C can be added to this set.
     * </code>
     *
     *
     * @param constraints Set of classes where only one subtype instance is
     *        allowed
     */
    public SingleInstanceSet(final Set<Class<? extends T>> constraints) {
        this.constraints = constraints;
    }

    /**
     * Creates an empty SingleInstanceSet with the given constraint.
     *
     * <p>
     * Constraint: Only one instance of a subtype of each class given as
     * constraint is allowed in this set.
     *
     * <p>
     * Example (classes A, B extends A, C extends A): <br>
     * <code>
     * A<br>
     * B --> A<br>
     * C --> A<br>
     * <br>
     * If A is added as constraint, only a single instance of A,B or C can be added to this set.
     * </code>
     *
     *
     * @param constraint Class where only one subtype instance is allowed
     */
    public SingleInstanceSet(final Class<? extends T> constraint) {
        final Set<Class<? extends T>> set = new HashSet<>();
        set.add(constraint);
        this.constraints = set;
    }

    /**
     * Adds a value to this set. A value with the same class as this one or
     * classes with constraints are replaced.
     *
     * @param value Value
     */
    @SuppressWarnings({"unchecked", "null"})
    public void add(final T value) {
        final Class<? extends T> clazz = (Class<? extends T>) value.getClass();
        if (!this.checkConstraints(clazz)) {
            this.map.remove(clazz);
        }
        this.map.put(clazz, value);
    }

    private boolean checkConstraints(final Class<? extends Object> clazz) {
        for (final Class<? extends T> constraint : this.constraints) {
            if (constraint.isInstance(clazz)) {
                return false;
            }
        }
        return true;
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
    @SuppressWarnings("unchecked")
    // Cast is safe
    public <X extends T> X get(final Class<X> clazz) {
        final T ret = this.map.get(clazz);
        if (ret.getClass() == clazz) {
            return (@NonNull X) ret;
        }
        throw new AssertionError("Can't cast " + ret + " to " + clazz.getName());
    }

    /**
     * Gets all values that pass a instanceof check against the given class.
     *
     * @param clazz Class
     * @return Set containing the values
     */
    @SuppressWarnings({"null", "unchecked"})
    // Cast is safe
    // set is never null
    public <X extends T> ImmutableSet<X> getInstancesOf(final Class<X> clazz) {
        final Set<X> set = new HashSet<>();
        for (final Class<? extends T> c : this.map.keySet()) {
            if (clazz.isInstance(c)) {
                final T ret = this.map.get(c);
                if (ret.getClass() == clazz) {
                    set.add((X) ret);
                } else {
                    throw new AssertionError("Can't cast " + ret + " to " + clazz.getName());
                }
            }
        }
        return ImmutableSet.copyOf(set);
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
