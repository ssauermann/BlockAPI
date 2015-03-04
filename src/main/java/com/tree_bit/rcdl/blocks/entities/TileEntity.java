package com.tree_bit.rcdl.blocks.entities;

import static com.google.common.base.Preconditions.checkNotNull;

import com.google.common.base.Joiner;
import com.google.common.base.Objects;
import com.google.common.collect.ImmutableSet;

import org.eclipse.jdt.annotation.Nullable;
import org.jnbt.StringTag;
import org.jnbt.Tag;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.annotation.concurrent.Immutable;

/**
 * Representing a tile entity containing some tags. The positioning tags (x, y,
 * z) are excluded from this tag list although they have to be saved in files.
 */
@Immutable
public class TileEntity {

    private final ImmutableSet<Tag> tags;

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
    public Set<Tag> getTags() {
        return this.tags;
    }

    /**
     * Builder for a TileEntity.
     */
    static class Builder {

        private final Map<String, Tag> tags = new HashMap<>();

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
        @SuppressWarnings("null")
        // Checked by Guava
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
        public Builder addAll(final Collection<Tag> tags) {
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
        @SuppressWarnings("null")
        // Guava
        ImmutableSet<Tag> getTags() {
            return ImmutableSet.copyOf(this.tags.values());
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = (prime * result) + ((this.tags == null) ? 0 : this.tags.hashCode());
            return result;
        }

        @Override
        public boolean equals(final Object obj) {
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
            if (this.tags == null) {
                if (other.tags != null) {
                    return false;
                }
            } else if (!this.tags.equals(other.tags)) {
                return false;
            }
            return true;
        }

        @Override
        public String toString() {
            return Objects.toStringHelper(this).addValue(this.tags).toString();
        }
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = (prime * result) + this.tags.hashCode();
        return result;
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
        if (!this.tags.equals(other.tags)) {
            return false;
        }
        return true;
    }

    @Override
    @SuppressWarnings("null")
    public String toString() {
        return Objects.toStringHelper(this).add("Tags", Joiner.on(',').skipNulls().join(this.getTags().toArray())).toString();
    }

}
