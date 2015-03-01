package com.tree_bit.rcdl.blocks;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

final class SingleInstanceSet<E> implements Set<E> {

    private Map<Class<? extends E>, E> map = new HashMap<>();

    @Override
    public int size() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public boolean isEmpty() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean contains(final Object o) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return this.map.values().iterator();
    }

    @Override
    public Object[] toArray() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <T> T[] toArray(final T[] a) {
        // TODO Auto-generated method stub
        return null;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean add(final @Nullable E e) {
        if (e == null) {
            throw new NullPointerException("Parameter musn't be null");
        }
        if (this.map.put((@NonNull Class<? extends E>) e.getClass(), e) == null) {
            return false;
        }
        return true;
    }

    @Override
    public boolean remove(final @Nullable Object o) {
        if (o == null) {
            throw new NullPointerException("Parameter musn't be null");
        }
        return (this.map.remove(o.getClass()) != null);
    }

    @Override
    public boolean containsAll(final @Nullable Collection<?> c) {
        if (c == null) {
            throw new NullPointerException("Parameter musn't be null");
        }
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean addAll(final @Nullable Collection<? extends E> c) {
        if (c == null) {
            throw new NullPointerException("Parameter musn't be null");
        }
        boolean wasChanged = false;
        for (final E element : c) {
            wasChanged |= this.add(element);
        }
        return wasChanged;
    }

    @Override
    public boolean retainAll(final @Nullable Collection<?> c) {
        if (c == null) {
            throw new NullPointerException("Parameter musn't be null");
        }
        boolean wasChanged = false;
        final Map<Class<? extends E>, E> hm = new HashMap<>();
        for (final Class<? extends E> elementClass : this.map.keySet()) {
            final E element = this.map.get(elementClass);
            if (c.contains(element)) {
                hm.put(elementClass, element);
                wasChanged = true;
            }
        }
        this.map = hm;
        return wasChanged;
    }

    @Override
    public boolean removeAll(final @Nullable Collection<?> c) {
        if (c == null) {
            throw new NullPointerException("Parameter musn't be null");
        }
        boolean wasChanged = false;
        for (final Object o : c) {
            wasChanged |= this.remove(o);
        }
        return wasChanged;
    }

    @Override
    public void clear() {
        this.map.clear();
    }



}
