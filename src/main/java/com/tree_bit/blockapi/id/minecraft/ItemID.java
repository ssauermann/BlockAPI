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
package com.tree_bit.blockapi.id.minecraft;

import com.google.common.base.MoreObjects;
import com.tree_bit.blockapi.id.IItemID;

/**
 * Mapping of item names to id's.
 *
 * @author Sascha Sauermann
 */
public enum ItemID implements IItemID {
    /** Iron Shovel */
    IRON_SHOVEL(256, "minecraft:iron_shovel");
    // TODO: Add items

    private ItemID(final int id, final String name) {
        this.id = id;
        this.name = name;
    }

    private int id;
    private String name;


    @Override
    public int getID() {
        return this.id;
    }

    @Override
    public String getAlphabeticalID() {
        return this.name;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this).addValue(super.toString()).add("id", this.id).add("alphabeticalID", this.name).toString();
    }
}
