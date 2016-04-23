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
import com.tree_bit.blockapi.nbt.CompoundBuilder;

import org.eclipse.jdt.annotation.Nullable;
import org.jnbt.IntTag;
import org.jnbt.StringTag;

import jdk.nashorn.internal.ir.annotations.Immutable;

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
public class BeaconEntity extends TileEntity {

    /**
     * Create a new beacon TileEntity.
     *
     * @param lock Optional. When not blank, prevents the container from being
     *        opened unless the opener is holding an item whose name matches
     *        this string
     * @param levels The number of levels available from the pyramid
     * @param primary The primary power selected
     * @param secondary The secondary power selected
     */
    public BeaconEntity(final String lock, final int levels, final IStatusEffect primary, final IStatusEffect secondary) {
        this(lock, levels, primary.getID(), secondary.getID());
    }

    /**
     * Create a new beacon TileEntity.
     *
     * @param lock Optional. When not blank, prevents the container from being
     *        opened unless the opener is holding an item whose name matches
     *        this string
     * @param levels The number of levels available from the pyramid
     * @param primary The primary power selected, see {@link StatusEffect} for
     *        IDs. 0 means none.
     * @param secondary The secondary power selected, see {@link StatusEffect}
     *        for IDs. 0 means none.
     */
    public BeaconEntity(final @Nullable String lock, final int levels, final int primary, final int secondary) {
        super(createBuilder(lock, levels, primary, secondary));
    }

    private static CompoundBuilder createBuilder(final @Nullable String lock, final int levels, final int primary, final int secondary) {
        final CompoundBuilder b = TileEntity.builder("Beacon");
        if (lock != null) {
            b.add(new StringTag("Lock", lock));
        }
        return b.add(new IntTag("Levels", levels)).add(new IntTag("Primary", primary)).add(new IntTag("Secondary", secondary));
    }

    /**
     * Starts building of a new BeaconEntity.
     *
     * @return New BeaconEntity Builder
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Builder for a BeaconEntity
     *
     * @author Sascha Sauermann
     */
    public static class Builder {

        private @Nullable String lock;
        private int levels = 0;
        private int primary = 0;
        private int secondary = 0;

        /**
         * Starts building of a new BeaconEntity.
         */
        public Builder() {
            this.lock = null;
        }

        /**
         * Sets a lock for this beacon.
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
         * Sets the levels of this beacon.
         *
         * @param levels The number of levels available from the pyramid.
         *        Typical Range: 0-4
         * @return Builder for chaining
         */
        public Builder setLevels(final int levels) {
            this.levels = levels;
            return this;
        }

        /**
         * Sets the primary effect of this beacon.
         *
         * @param effectID The primary power selected, see {@link StatusEffect}
         *        for IDs. 0 means none
         * @return Builder for chaining
         */
        public Builder setPrimary(final int effectID) {
            this.primary = effectID;
            return this;
        }

        /**
         * Sets the primary effect of this beacon.
         *
         * @param primary The primary power selected
         * @return Builder for chaining
         */
        public Builder setPrimary(final IStatusEffect primary) {
            this.primary = primary.getID();
            return this;
        }

        /**
         * Sets the secondary effect of this beacon.
         *
         * @param effectID The secondary power selected, see
         *        {@link StatusEffect} for IDs. 0 means none
         * @return Builder for chaining
         */
        public Builder setSecondary(final int effectID) {
            this.secondary = effectID;
            return this;
        }

        /**
         * Sets the secondary effect of this beacon.
         *
         * @param secondary The secondary power selected
         * @return Builder for chaining
         */
        public Builder setSecondary(final IStatusEffect secondary) {
            this.secondary = secondary.getID();
            return this;
        }

        /**
         * Finishes building and creates a new BeaconEntity
         *
         * @return New beacon entity
         */
        public BeaconEntity build() {
            return new BeaconEntity(this.lock, this.levels, this.primary, this.secondary);
        }
    }

}
