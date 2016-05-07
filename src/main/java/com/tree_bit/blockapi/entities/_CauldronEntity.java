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

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Maps;
import com.tree_bit.blockapi.data.minecraft.CauldronSlot;
import com.tree_bit.blockapi.nbt.NBT;
import com.tree_bit.blockapi.nbt.tags.CompoundTag;

import org.immutables.value.Value.Default;
import org.immutables.value.Value.Derived;
import org.immutables.value.Value.Immutable;
import org.immutables.value.Value.Parameter;

import java.util.Map;

/**
 * Tile entity of a brewing stand block.
 *
 * <p>
 * Used by:
 * <ul>
 * <li>{@link BrewingStand}</li>
 * </ul>
 *
 * @author Sascha Sauermann
 */
@Immutable
public abstract class _CauldronEntity implements TileEntity {

    /**
     * The name of this container, which will display in its GUI where the
     * default name ordinarily is. (Default name is displayed if blank)
     *
     * @return custom name
     */
    @Default
    @SuppressWarnings("static-method")
    public String customName() {
        return "";
    }

    /**
     * When not blank, prevents the container from being opened unless the
     * opener is holding an item whose name matches this string.
     *
     * @return lock string
     */
    @Default
    @SuppressWarnings("static-method")
    public String lock() {
        return "";
    }

    /**
     * Slots and current items. The items may not include the slot.
     *
     * @return items
     */
    @Parameter(order = 1)
    public abstract Map<CauldronSlot, Item> items();

    /**
     * The number of ticks the potions have been brewing for.
     *
     * @return brewing time
     */
    @Default
    @SuppressWarnings("static-method")
    public int brewTime() {
        return 0;
    }

    /**
     * Remaining fuel for the brewing stand.
     *
     * @return fuel
     */
    @Default
    @SuppressWarnings("static-method")
    public byte fuel() {
        return 0;
    }

    @Override
    @Derived
    public CompoundTag compound() {
        return NBT.Compound(this.id()).String("CustomName", this.customName()).String("Lock", this.lock()).Int("BrewTime", this.brewTime())
                .Byte("Fuel", this.fuel())
                .List("Items", CompoundTag.class,
                        ImmutableList.copyOf(
                                (Maps.transformEntries(this.items(), (key, value) -> value.withSlotNr((byte) key.getDV()).compound())).values()))
                .build();
    }


    @Override
    public String id() {
        return "Cauldron";
    }


}
