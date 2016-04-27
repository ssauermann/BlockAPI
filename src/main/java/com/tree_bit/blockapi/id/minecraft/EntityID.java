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
import com.tree_bit.blockapi.id.IEntityID;

import org.eclipse.jdt.annotation.NonNull;

/**
 * Mapping of entity names to id's.
 *
 * @author Sascha Sauermann
 */
public enum EntityID implements IEntityID {
    /** Dropped item */
    ITEM(1, "Item"),
    /** Experience Orb */
    XP_ORB(2, "XPOrb");
    // TODO: Add entities

    private int id;
    private String savegameId;

    private EntityID(final int id, final String savegameId) {
        this.id = id;
        this.savegameId = savegameId;
    }


    @Override
    public int getID() {
        return this.id;
    }

    @Override
    public String getSavegameID() {
        return this.savegameId;
    }


    @Override
    public @NonNull String toString() {
        return MoreObjects.toStringHelper(this).addValue(super.toString()).add("id", this.id).add("savegameID", this.savegameId).toString();
    }
}
