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

import static com.google.common.base.Preconditions.checkNotNull;

import com.google.common.collect.ImmutableSet;

import org.eclipse.jdt.annotation.NonNull;
import org.jnbt.CompoundTag;
import org.jnbt.Tag;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Builder for NBT Compound Tags
 */
public class CompoundBuilder extends NBTBuilder<Tag> {

    private final Map<@NonNull String, @NonNull Tag> tags = new HashMap<>();

    /**
     * Creates a new compound tag builder.
     *
     * @param name Name of this compound
     */
    CompoundBuilder(final String name) {
        super(name);
    }

    /**
     * Adds a new tag to this compound tag. An existing tag with the same name
     * is replaced.
     *
     * @param tag Tag
     * @return Builder for chaining
     *
     * @throws NullPointerException if the given tag has a null value as its
     *         name
     */
    @Override
    public CompoundBuilder add(final Tag tag) {
        this.tags.put(checkNotNull(tag.getName()), tag);
        return this;
    }

    /**
     * Adds new tags to this compound tag. An existing tag with the same name as
     * one in the collection is replaced.
     *
     * @param tags Collection of tags
     * @return Builder for chaining
     *
     * @throws NullPointerException if one of the given tags has a null value as
     *         its name
     */
    @Override
    public CompoundBuilder addAll(final Collection<@NonNull Tag> tags) {
        for (final Tag tag : tags) {
            this.add(tag);
        }
        return this;
    }

    /**
     * Builds the CompoundTag and returns it.
     *
     * @return New CompoundTag
     */
    @Override
    public CompoundTag build() {
        return NBT.Compound(this.getName(), this.tags);
    }

    /**
     * Returns the current tags as an immutable set.
     *
     * @return Tag set
     */
    @Override
    public ImmutableSet<Tag> getTags() {
        return ImmutableSet.copyOf(this.tags.values());
    }


}
