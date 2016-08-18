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

import com.tree_bit.blockapi.id.IStatusEffect;
import com.tree_bit.blockapi.id.minecraft.StatusEffect;
import com.tree_bit.blockapi.nbt.NBT;
import com.tree_bit.blockapi.nbt.tags.CompoundTag;

import org.immutables.value.Value.Default;
import org.immutables.value.Value.Immutable;
import org.immutables.value.Value.Lazy;
import org.immutables.value.Value.Parameter;

import javax.annotation.concurrent.NotThreadSafe;

/**
 * Tile entity of a beacon block.
 *
 * <p>
 * Used by:
 * <ul>
 * <li>{@link Beacon}</li>
 * </ul>
 */
@Immutable
public abstract class BeaconEntity implements BlockEntity {

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
    @Lazy
    public CompoundTag compound() {
        return NBT.Compound(this.id()).String("Lock", this.lock()).Int("Levels", this.levels()).Int("Primary", this.primary().getID())
                .Int("Secondary", this.secondary().getID()).build();
    }

    @Override
    public String id() {
        return "Beacon";
    }

    /**
     * Copy the current immutable object by setting a value for the
     * {@link BeaconEntity#lock() lock} attribute. An equals check used to
     * prevent copying of the same value by returning {@code this}.
     *
     * @param lock A new value for lock
     * @return A modified copy of the {@code this} object
     */
    public abstract BeaconEntity withLock(String lock);

    /**
     * Copy the current immutable object by setting a value for the
     * {@link BeaconEntity#levels() levels} attribute. A value equality check is
     * used to prevent copying of the same value by returning {@code this}.
     *
     * @param levels A new value for levels
     * @return A modified copy of the {@code this} object
     */
    public abstract BeaconEntity withLevels(int levels);

    /**
     * Copy the current immutable object by setting a value for the
     * {@link BeaconEntity#primary() primary} attribute. A shallow reference
     * equality check is used to prevent copying of the same value by returning
     * {@code this}.
     *
     * @param primary A new value for primary
     * @return A modified copy of the {@code this} object
     */
    public abstract BeaconEntity withPrimary(IStatusEffect primary);

    /**
     * Copy the current immutable object by setting a value for the
     * {@link BeaconEntity#secondary() secondary} attribute. A shallow reference
     * equality check is used to prevent copying of the same value by returning
     * {@code this}.
     *
     * @param secondary A new value for secondary
     * @return A modified copy of the {@code this} object
     */
    public abstract BeaconEntity withSecondary(IStatusEffect secondary);

    /**
     * Creates a builder for {@link BeaconEntity BeaconEntity}.
     *
     * @return A new BeaconEntity builder
     */
    public static Builder builder() {
        return ImmutableBeaconEntity.builder();
    }

    /**
     * Construct a new immutable {@code BeaconEntity} instance.
     *
     * @param levels The value for the {@code levels} attribute
     * @param primary The value for the {@code primary} attribute
     * @param secondary The value for the {@code secondary} attribute
     * @return An immutable BeaconEntity instance
     */
    public static BeaconEntity of(final int levels, final IStatusEffect primary, final IStatusEffect secondary) {
        return ImmutableBeaconEntity.of(levels, primary, secondary);
    }

    /**
     * Builds instances of type {@link BeaconEntity BeaconEntity}. Initialize
     * attributes and then invoke the {@link #build()} method to create an
     * immutable instance.
     * <p>
     * <em>{@code Builder} is not thread-safe and generally should not be stored
     * in a field or collection, but instead used immediately to create
     * instances.</em>
     */
    @NotThreadSafe
    interface Builder {

        /**
         * Initializes the value for the {@link BeaconEntity#lock() lock}
         * attribute.
         * <p>
         * <em>If not set, this attribute will have a default value as returned
         * by the initializer of {@link BeaconEntity#lock() lock}.</em>
         *
         * @param lock The value for lock
         * @return {@code this} builder for use in a chained invocation
         */
        Builder lock(String lock);

        /**
         * Initializes the value for the {@link BeaconEntity#levels() levels}
         * attribute.
         * <p>
         * <em>If not set, this attribute will have a default value as returned
         * by the initializer of {@link BeaconEntity#levels() levels}.</em>
         *
         * @param levels The value for levels
         * @return {@code this} builder for use in a chained invocation
         */
        Builder levels(int levels);

        /**
         * Initializes the value for the {@link BeaconEntity#primary() primary}
         * attribute.
         * <p>
         * <em>If not set, this attribute will have a default value as returned
         * by the initializer of {@link BeaconEntity#primary() primary}.</em>
         *
         * @param primary The value for primary
         * @return {@code this} builder for use in a chained invocation
         */
        Builder primary(IStatusEffect primary);

        /**
         * Initializes the value for the {@link BeaconEntity#secondary()
         * secondary} attribute.
         * <p>
         * <em>If not set, this attribute will have a default value as returned
         * by the initializer of {@link BeaconEntity#secondary()
         * secondary}.</em>
         *
         * @param secondary The value for secondary
         * @return {@code this} builder for use in a chained invocation
         */
        Builder secondary(IStatusEffect secondary);

        /**
         * Fill a builder with attribute values from the provided
         * {@code BeaconEntity} instance. Regular attribute values will be
         * replaced with those from the given instance. Absent optional values
         * will not replace present values.
         *
         * @param instance The instance from which to copy values
         * @return {@code this} builder for use in a chained invocation
         */
        Builder from(BeaconEntity instance);

        /**
         * Builds a new {@link BeaconEntity BeaconEntity}.
         *
         * @return An immutable instance of BeaconEntity
         * @throws java.lang.IllegalStateException if any required attributes
         *         are missing
         */
        BeaconEntity build();
    }

}
