package com.tree_bit.rcdl.blocks;

import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableSet;

import org.eclipse.jdt.annotation.Nullable;

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
    @SuppressWarnings("unchecked")
    public void add(final T value) {
        final Class<? extends T> clazz = (Class<? extends T>) value.getClass();
        final Class<? extends T> theConstraint = this.checkConstraints(value);
        if (theConstraint != null) {
            this.removeAllInstances(theConstraint);
        }
        this.map.put(clazz, value);
    }

    private void removeAllInstances(final Class<? extends T> clazz) {
        for (final Iterator<T> it = this.map.values().iterator(); it.hasNext();) {
            final T value = it.next();
            if (clazz.isInstance(value)) {
                it.remove();
            }
        }
    }

    @Nullable
    private Class<? extends T> checkConstraints(final T obj) {
        for (final Class<? extends T> constraint : this.constraints) {
            if (constraint.isInstance(obj)) {
                return constraint;
            }
        }
        return null;
    }

    /**
     * Returns an immutable Java Collections set representation of this set.
     *
     * @return Set
     */
    public ImmutableSet<T> asSet() {
        return ImmutableSet.copyOf(this.map.values());
    }

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
            return (X) ret;
        }
        throw new AssertionError("Can't cast " + ret + " to " + clazz.getName());
    }

    /**
     * Gets all values that pass a instanceof check against the given class.
     *
     * @param clazz Class
     * @return Set containing the values
     */
    @SuppressWarnings("unchecked")
    // Cast is safe
    public <X extends T> ImmutableSet<X> getInstancesOf(final Class<X> clazz) {
        final Set<X> set = new HashSet<>();
        for (final T v : this.map.values()) {
            if (clazz.isInstance(v)) {
                set.add((X) v);
            }
        }
        return ImmutableSet.copyOf(set);
    }

    /**
     * Returns the set constraints.
     *
     * @return Collection of constraints
     */
    // Set is not null
    public Collection<Class<? extends T>> getConstraints() {
        return ImmutableSet.copyOf(this.constraints);
    }

    /**
     * Creates a SingleInstanceSet with the elements of given collection.
     *
     * @param collection Collection
     * @return Set
     */
    public static <E> SingleInstanceSet<E> copyOf(final Collection<E> collection) {
        return copyOf(collection, new HashSet<>());
    }

    /**
     * Creates a SingleInstanceSet with the elements of given collection with
     * the given constraints.
     *
     * @param collection Collection
     * @param constraints Set of constraints
     * @return Set
     */
    public static <E> SingleInstanceSet<E> copyOf(final Collection<E> collection, final Set<Class<? extends E>> constraints) {
        final SingleInstanceSet<E> set = new SingleInstanceSet<>(constraints);
        for (final E element : collection) {
            set.add(element);
        }
        return set;
    }

    /**
     * Creates a SingleInstanceSet with the elements of given collection with
     * the given constraints.
     *
     * @param collection Collection
     * @param constraint Constraint class
     * @return Set
     */
    public static <E> SingleInstanceSet<E> copyOf(final Collection<E> collection, final Class<? extends E> constraint) {
        return copyOf(collection, ImmutableSet.of(constraint));
    }

    @Override
    public String toString() {
        return "" + MoreObjects.toStringHelper(this).add("Constraints", this.constraints).add("Map", this.map).toString();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = (prime * result) + this.constraints.hashCode();
        result = (prime * result) + this.map.hashCode();
        return result;
    }

    @Override
    public boolean equals(@Nullable final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SingleInstanceSet)) {
            return false;
        }
        final SingleInstanceSet<?> other = (SingleInstanceSet<?>) obj;
        if (!this.constraints.equals(other.constraints)) {
            return false;
        }
        if (!this.map.equals(other.map)) {
            return false;
        }
        return true;
    }

}
