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
package com.tree_bit.blockapi.entities.blockentity;

import static com.google.common.base.Preconditions.checkNotNull;

import com.tree_bit.blockapi.nbt.NBT;
import com.tree_bit.blockapi.nbt.NBTCompoundData;
import com.tree_bit.blockapi.nbt.tags.CompoundTag;


/**
 * Representing a tile entity containing some tags. The positioning tags (x, y,
 * z) are excluded from this tag list although they have to be saved in files.
 * <p>
 * <b>With 1.8 TileEntities were renamed to BlockEntities.</b>
 *
 * @see <a href=
 *      "http://minecraft.gamepedia.com/Chunk_format#Block_entity_format">http:/
 *      /minecraft.gamepedia.com/Chunk_format#Block_entity_format</a>
 *
 * @author Sascha Sauermann
 */
public interface BlockEntity extends NBTCompoundData {

    /**
     * {@inheritDoc}
     * <p>
     * This compound tag for block entities is missing the coordinate data which
     * must be added for saving but is not currently stored in it.
     * <p>
     * Use {@link #addCoordinates(BlockEntity, int, int, int)} for adding those
     * before saving to file.
     */
    @Override
    public CompoundTag compound();

    /**
     * ID defining the type of the <code>BlockEntity</code>.
     *
     * @return BlockEntity ID
     */
    public String id();

    /**
     * Adds coordinates to a <code>BlockEntity</code> and returns the
     * <code>CompoundTag</code> the block entity is based on, including the
     * coordinates. This should only be done before saving the data to a file.
     *
     * @param e BlockEntity to add the coordinates to
     * @param x X coordinate
     * @param y Y coordinate
     * @param z Z coordinate
     * @return CompoundTag with BlockEntity data and coordinates.
     */
    public static CompoundTag addCoordinates(final BlockEntity e, final int x, final int y, final int z) {
        return NBT.Compound(e.compound().getName()).addAll(checkNotNull(e.compound().getValue().values())).Int("x", x).Int("y", y).Int("z", z)
                .build();
    }

}
