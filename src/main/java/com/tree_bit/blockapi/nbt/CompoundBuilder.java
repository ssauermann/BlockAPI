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

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
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
    public CompoundBuilder addAll(final Collection<? extends @NonNull Tag> tags) {
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

    /**
     * Returns the current tags as an immutable map.
     *
     * @return Tag set
     */
    public ImmutableMap<String, Tag> getTagMap() {
        return ImmutableMap.copyOf(this.tags);
    }

    @Override
    protected CompoundBuilder addTag(final Tag tag) {
        this.tags.put(checkNotNull(tag.getName()), tag);
        return this;
    }

    /////////////////////////////////////////////////////

    @Override
    public CompoundBuilder ByteArray(final String name, final byte[] value) {
        return (CompoundBuilder) super.ByteArray(name, value);
    }

    @Override
    public CompoundBuilder Byte(final String name, final byte value) {
        return (CompoundBuilder) super.Byte(name, value);
    }

    @Override
    public CompoundBuilder Compound(final String name, final Map<String, Tag> value) {
        return (CompoundBuilder) super.Compound(name, value);
    }

    @Override
    public CompoundBuilder Double(final String name, final double value) {
        return (CompoundBuilder) super.Double(name, value);
    }

    @Override
    public CompoundBuilder End() {
        return (CompoundBuilder) super.End();
    }


    @Override
    public CompoundBuilder Float(final String name, final float value) {
        return (CompoundBuilder) super.Float(name, value);
    }

    @Override
    public CompoundBuilder IntArray(final String name, final int[] value) {
        return (CompoundBuilder) super.IntArray(name, value);
    }

    @Override
    public CompoundBuilder Int(final String name, final int value) {
        return (CompoundBuilder) super.Int(name, value);
    }

    @Override
    public CompoundBuilder List(final String name, final Class<? extends Tag> type, final java.util.List<Tag> value) {
        return (CompoundBuilder) super.List(name, type, value);
    }


    @Override
    public CompoundBuilder Long(final String name, final long value) {
        return (CompoundBuilder) super.Long(name, value);
    }


    @Override
    public CompoundBuilder Short(final String name, final short value) {
        return (CompoundBuilder) super.Short(name, value);
    }

    @Override
    public CompoundBuilder String(final String name, final @Nullable String value) {
        return (CompoundBuilder) super.String(name, value);
    }
}
