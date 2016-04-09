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
package com.tree_bit.rcdl.schematic;

import org.jnbt.NBTConstants;

/**
 * Enum, containing important fields of a schematic file.
 */
public enum ESchematicFields {
    /** Size along the X axis. */
    WIDTH("Width", NBTConstants.TYPE_SHORT),
    /** Size along the Y axis. */
    HEIGHT("Height", NBTConstants.TYPE_SHORT),
    /** Size along the Z axis. */
    LENGTH("Length", NBTConstants.TYPE_SHORT),
    /**
     * This will be "Classic" for schematics exported from Minecraft Classic
     * levels, and "Alpha" for those from Minecraft Alpha and newer levels.
     */
    MATERIALS("Materials", NBTConstants.TYPE_STRING),
    /**
     * Block IDs defining the terrain. 8 bits per block. Sorted by height
     * (bottom to top) then length then width -- the address of the block at
     * X,Y,Z is (Y×length + Z)×width + X.
     */
    BLOCKS("Blocks", NBTConstants.TYPE_BYTE_ARRAY),
    /**
     * Block data additionally defining parts of the terrain. Only the lower 4
     * bits of each byte are used. (Unlike in the chunk format, the block data
     * in the schematic format occupies a full byte per block.)
     */
    DATA("Data", NBTConstants.TYPE_BYTE_ARRAY),
    /**
     * List of Compound tags including one entity each.
     */
    ENTITIES("Entities", NBTConstants.TYPE_LIST),
    /**
     * List of Compound tags including one tile entity each.
     */
    TILEENTITIES("TileEntities", NBTConstants.TYPE_LIST);


    /**
     * Key of the tag
     */
    private String key;
    /**
     * Type of the tag
     */
    private int type;

    /**
     * Creates a new ESchematicFields enum.
     *
     * @param key <b>String</b> key
     * @param type <b>int</b> type
     */
    private ESchematicFields(final String key, final int type) {
        this.key = key;
        this.type = type;
    }

    /**
     * Returns the key of a field to access the child tags of a compound tag by
     * key.
     *
     * @return <b>String</b> key
     */
    public String getKey() {
        return this.key;
    }

    /**
     * Returns the type of a field.
     *
     * @return <b>int</b> type
     */
    public int getType() {
        return this.type;
    }
}
