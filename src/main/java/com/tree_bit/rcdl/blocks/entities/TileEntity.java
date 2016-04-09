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
package com.tree_bit.rcdl.blocks.entities;

import static com.google.common.base.Preconditions.checkNotNull;

import com.google.common.base.Joiner;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.collect.ImmutableSet;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.jnbt.StringTag;
import org.jnbt.Tag;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import jdk.nashorn.internal.ir.annotations.Immutable;


/**
 * Representing a tile entity containing some tags. The positioning tags (x, y,
 * z) are excluded from this tag list although they have to be saved in files.
 *
 * <b>With 1.8 TileEntities were renamed to BlockEntities.</b>
 *
 * @see <a href=
 *      "http://minecraft.gamepedia.com/Chunk_format#Block_entity_format">http:/
 *      /minecraft.gamepedia.com/Chunk_format#Block_entity_format</a>
 *
 * @author Sascha Sauermann
 */
@Immutable
public class TileEntity {

    private final ImmutableSet<@NonNull Tag> tags;

    /**
     * Creates a new TileEntity with the given builder.
     *
     * @param builder TileEntity builder
     */
    TileEntity(final Builder builder) {
        this.tags = builder.getTags();
    }

    /**
     * Creates a new TileEntity Builder.
     *
     * @param id Entity id
     * @return Builder instance
     */
    public static Builder builder(final String id) {
        return new Builder(id);
    }

    /**
     * Returns the current tags as a set.
     *
     * @return Tag set
     */
    public Set<@NonNull Tag> getTags() {
        return this.tags;
    }

    /**
     * Builder for a TileEntity.
     * 
     * @author Sascha Sauermann
     */
    static class Builder {

        private final Map<@NonNull String, @NonNull Tag> tags = new HashMap<>();

        /**
         * Creates a new TileEntity builder with the necessary tags. This does
         * not include the coordinates.
         *
         * @param id Entity id
         */
        public Builder(final String id) {
            this.tags.put("id", new StringTag("id", id));
        }

        /**
         * Adds a new tag to the TileEntity. An existing tag with the same name
         * is replaced.
         *
         * @param tag Tag
         * @return Builder for chaining
         *
         * @throws NullPointerException if the given tag has a null value as its
         *         name
         */
        public Builder add(final Tag tag) {
            this.tags.put(checkNotNull(tag.getName()), tag);
            return this;
        }

        /**
         * Adds new tags to the TileEntity. An existing tag with the same name
         * as one in the collection is replaced.
         *
         * @param tags Collection of tags
         * @return Builder for chaining
         *
         * @throws NullPointerException if one of the given tags has a null
         *         value as its name
         */
        public Builder addAll(final Collection<@NonNull Tag> tags) {
            for (final Tag tag : tags) {
                this.add(tag);
            }
            return this;
        }

        /**
         * Builds the TileEntity and returns it.
         *
         * @return New TileEntity
         */
        public TileEntity build() {
            return new TileEntity(this);
        }

        /**
         * Returns the current tags as an immutable set.
         *
         * @return Tag set
         */
        ImmutableSet<Tag> getTags() {
            return ImmutableSet.copyOf(this.tags.values());
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(this.tags);
        }

        @Override
        public boolean equals(@Nullable final Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (!(obj instanceof Builder)) {
                return false;
            }
            final Builder other = (Builder) obj;
            return Objects.equal(this.tags, other.tags);
        }

        @Override
        public String toString() {
            return MoreObjects.toStringHelper(this).addValue(this.tags).toString();
        }
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.tags);
    }

    @Override
    public boolean equals(@Nullable final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof TileEntity)) {
            return false;
        }
        final TileEntity other = (TileEntity) obj;
        return Objects.equal(this.tags, other.tags);
    }


    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this).add("Tags", Joiner.on(',').skipNulls().join(this.getTags().toArray())).toString();
    }

}
