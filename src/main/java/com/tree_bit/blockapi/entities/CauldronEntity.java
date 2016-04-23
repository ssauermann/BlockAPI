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

import com.tree_bit.blockapi.nbt.NBT;

import org.eclipse.jdt.annotation.Nullable;
import org.jnbt.CompoundTag;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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
public class CauldronEntity extends TileEntity {

    /**
     * Slots of a brewing stand.
     *
     * @author Sascha Sauermann
     */
    public enum Slot {
        /** Left potion slot */
        Left(0),
        /** Middle potion slot */
        Middle(1),
        /** Right potion slot */
        Right(2),
        /** Ingredient slot */
        Ingredient(3),
        /** Fuel slot */
        Fuel(4);

        private int slot;

        Slot(final int slot) {
            this.slot = slot;
        }

    }

    /**
     * Creates a cauldron entity.
     *
     * @param customName Optional. The name of this container, which will
     *        display in its GUI where the default name ordinarily is.
     * @param lock Optional. When not blank, prevents the container from being
     *        opened unless the opener is holding an item whose name matches
     *        this string.
     * @param items List of items in the container.
     * @param brewtime The number of ticks the potions have been brewing for.
     * @param fuel Remaining fuel for the brewing stand.
     */
    public CauldronEntity(@Nullable final String customName, @Nullable final String lock, final Collection<CompoundTag> items, final int brewtime,
            final byte fuel) {
        super(NBT.begin().String("CustomName", customName).String("Lock", lock).add(NBT.Compound("Items").addAll(items).build())
                .Int("BrewTime", brewtime).Byte("Fuel", fuel));
    }

    /**
     * Starts building of a new {@link CauldronEntity}
     *
     * @return New {@link CauldronEntity} builder
     */
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private @Nullable final String customName;
        private @Nullable String lock;
        private final List<CompoundTag> items;
        private int brewTime;
        private byte fuel;


        /**
         * Starts building of a new {@link CauldronEntity}.
         */
        public Builder() {
            this.lock = null;
            this.customName = null;
            this.items = new ArrayList<>();
        }

        /**
         * Sets a lock for this brewing stand.
         *
         * @param lock When not blank, prevents the container from being opened
         *        unless the opener is holding an item whose name matches this
         *        string
         * @return Builder for chaining
         */
        public Builder setLock(final @Nullable String lock) {
            this.lock = lock;
            return this;
        }



        /**
         * Finishes building and creates a new BeaconEntity
         *
         * @return New beacon entity
         */
        public CauldronEntity build() {
            return new CauldronEntity(this.customName, this.lock, this.items, this.brewTime, this.fuel);
        }
    }


}
