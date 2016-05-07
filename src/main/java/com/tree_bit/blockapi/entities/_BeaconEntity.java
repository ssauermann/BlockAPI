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

import com.tree_bit.blockapi.id.IStatusEffect;
import com.tree_bit.blockapi.id.minecraft.StatusEffect;
import com.tree_bit.blockapi.nbt.NBT;
import com.tree_bit.blockapi.nbt.tags.CompoundTag;

import org.immutables.value.Value.Default;
import org.immutables.value.Value.Derived;
import org.immutables.value.Value.Immutable;
import org.immutables.value.Value.Parameter;

/**
 * Tile entity of a beacon block.
 *
 * <p>
 * Used by:
 * <ul>
 * <li>{@link Beacon}</li>
 * </ul>
 *
 * @author Sascha Sauermann
 */
@Immutable
public abstract class _BeaconEntity implements TileEntity {

    /**
     * When not blank, prevents the container from being opened unless the
     * opener is holding an item whose name matches this string
     *
     * @return lock name
     */
    @Default
    @SuppressWarnings("static-method")
    public String lock() {
        return "";
    }

    /**
     * The number of levels available from the pyramid.
     *
     * @return levels
     */
    @Parameter(order = 1)
    @Default
    @SuppressWarnings("static-method")
    public int levels() {
        return 0;
    }

    /**
     * The primary power selected.
     *
     * @return primary status effect
     */
    @Parameter(order = 2)
    @Default
    @SuppressWarnings("static-method")
    public IStatusEffect primary() {
        return StatusEffect.NONE;
    }

    /**
     * The secondary power selected.
     *
     * @return secondary status effect
     */
    @Parameter(order = 3)
    @Default
    @SuppressWarnings("static-method")
    public IStatusEffect secondary() {
        return StatusEffect.NONE;
    }

    @Override
    @Derived
    public CompoundTag compound() {
        return NBT.Compound(this.id()).String("Lock", this.lock()).Int("Levels", this.levels()).Int("Primary", this.primary().getID())
                .Int("Secondary", this.secondary().getID()).build();
    }

    @Override
    public String id() {
        return "Beacon";
    }

}
