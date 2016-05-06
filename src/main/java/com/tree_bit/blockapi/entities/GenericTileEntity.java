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
package com.tree_bit.blockapi.entities;

import com.google.common.base.Joiner;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.tree_bit.blockapi.nbt.CompoundBuilder;
import com.tree_bit.blockapi.nbt.NBT;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.jnbt.CompoundTag;
import org.jnbt.Tag;

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
public class GenericTileEntity implements TileEntity {

    private final ImmutableMap<@NonNull String, @NonNull Tag> tags;

    /**
     * Creates a new TileEntity with the given builder.
     *
     * @param builder Compound builder
     */
    GenericTileEntity(final CompoundBuilder builder) {
        this.tags = builder.getTagMap();
    }

    /**
     * Returns the current tags as a set.
     *
     * @return Tag set
     */
    public Set<@NonNull Tag> getTags() {
        return ImmutableSet.copyOf(this.tags.values());
    }

    /**
     * Gets a tag by name.
     *
     * @param name Tag name
     * @return Tag
     */
    public Tag get(final String name) {
        return this.tags.get(name);
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
        if (!(obj instanceof GenericTileEntity)) {
            return false;
        }
        final GenericTileEntity other = (GenericTileEntity) obj;
        return Objects.equal(this.tags, other.tags);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this).add("Tags", Joiner.on(',').skipNulls().join(this.getTags().toArray())).toString();
    }

    /**
     * Creates a new Compound builder.
     *
     * @param id Entity id
     * @return Builder instance
     */
    public static CompoundBuilder builder(final String id) {
        return NBT.Compound(id);
    }


    @Override
    public @NonNull CompoundTag compound() {
        // TODO Auto-generated method stub
        return NBT.Compound("TODO", this.tags);
    }

}
