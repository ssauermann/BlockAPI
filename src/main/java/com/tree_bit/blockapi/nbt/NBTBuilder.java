/**
 * Copyright (c) 2016 The BlockAPI authors
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.tree_bit.blockapi.nbt;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.collect.ImmutableCollection;

import org.eclipse.jdt.annotation.Nullable;
import org.jnbt.Tag;

import java.util.Collection;

/**
 * Superclass of NBT tag builders.
 * 
 * @param <T> Type of subtags
 */
public abstract class NBTBuilder<T extends Tag> {

    private final String name;

    /**
     * Creates a NBT builder
     *
     * @param name Name of this tag
     */
    public NBTBuilder(final String name) {
        this.name = name;
    }

    /**
     * Adds a new tag to this list tag. An existing tag with the same name is
     * replaced.
     *
     * @param tag Tag
     * @return Builder for chaining
     *
     * @throws NullPointerException if the given tag has a null value as its
     *         name
     */
    public abstract NBTBuilder<T> add(final T tag);

    /**
     * Adds new tags to this list tag. An existing tag with the same name as one
     * in the collection is replaced.
     *
     * @param tags Collection of tags
     * @return Builder for chaining
     *
     * @throws NullPointerException if one of the given tags has a null value as
     *         its name
     */
    public abstract NBTBuilder<T> addAll(final Collection<T> tags);

    /**
     * Gets the name of this nbt tag.
     *
     * @return Name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns the current tags as an immutable collection.
     *
     * @return Tag set
     */
    public abstract ImmutableCollection<T> getTags();

    /**
     * Builds the nbt tag and returns it.
     *
     * @return New nbt tag
     */
    public abstract Tag build();

    @Override
    public int hashCode() {
        return Objects.hashCode(this.getTags());
    }

    @Override
    public boolean equals(@Nullable final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof NBTBuilder)) {
            return false;
        }
        final NBTBuilder<?> other = (NBTBuilder<?>) obj;
        return Objects.equal(this.getTags(), other.getTags());
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this).addValue(this.getTags()).toString();
    }


}
