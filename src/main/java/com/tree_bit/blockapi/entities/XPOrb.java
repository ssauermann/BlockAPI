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

import com.tree_bit.blockapi.Coordinates;
import com.tree_bit.blockapi.nbt.NBT;
import com.tree_bit.blockapi.nbt.tags.CompoundTag;

import org.immutables.value.Value.Default;
import org.immutables.value.Value.Immutable;
import org.immutables.value.Value.Lazy;
import org.immutables.value.Value.Parameter;

import javax.annotation.concurrent.NotThreadSafe;


/**
 * Represents a XPOrb entity.
 */
@Immutable
public abstract class XPOrb implements Entity {

    @Override
    public String id() {
        return "XPOrb";
    }

    @Override
    @Parameter(order = 0)
    public abstract Coordinates pos();

    @Override
    @Lazy
    public CompoundTag compound() {
        return NBT.begin().Short("Age", this.age()).Byte("Health", this.health()).Short("Value", this.value()).addAll(this._entityTags()).build();
    }

    /**
     * The number of ticks the XP orb has been "untouched". After 6000 ticks (5
     * minutes) the orb is destroyed. If set to -32768, the Age will not
     * increase, thus the XP orb will not automatically despawn.
     *
     * <p>
     * <i>Default:</i> 0
     *
     * @return current age
     */
    @Default
    @SuppressWarnings("static-method")
    public short age() {
        return 0;
    }

    /**
     * The health of XP orbs. XP orbs take damage from fire, lava, falling
     * anvils, and explosions. The orb is destroyed when its health reaches 0.
     * However, this value is stored as a byte in saved data, and read as a
     * short but clipped to the range of a byte. As a result, its range is
     * 0-255, always positive, and values exceeding 255 will overflow.
     *
     * <p>
     * <i>Default:</i> 5
     *
     * @return current health
     */
    @Default
    @SuppressWarnings("static-method")
    public byte health() {
        // TODO validate default
        return 5;
    }

    /**
     * The amount of experience the orb gives when picked up.
     *
     * <p>
     * <i>Default:</i> 1
     *
     * @return current value
     */
    @Parameter(order = 1)
    @Default
    @SuppressWarnings("static-method")
    public short value() {
        return 1;
    }

    /**
     * Construct a new immutable {@code XPOrb} instance.
     *
     * @param position The value for the {@code pos} attribute
     * @param value The value for the {@code value} attribute
     * @return An immutable Item instance
     */
    public static XPOrb of(final Coordinates position, final short value) {
        return ImmutableXPOrb.of(position, value);
    }

    /**
     * Construct a new immutable {@code XPOrb} instance.
     *
     * @param position The value for the {@code pos} attribute
     * @return An immutable Item instance
     */
    public static XPOrb of(final Coordinates position) {
        return of(position, (short) 1);
    }

    /**
     * Creates a builder for {@link XPOrb XPOrb}.
     *
     * @return A new XPOrb builder
     */
    public static Builder builder() {
        return ImmutableXPOrb.builder();
    }


    /**
     * Builds instances of type {@link XPOrb XPOrb}. Initialize attributes and
     * then invoke the {@link #build()} method to create an immutable instance.
     * <p>
     * <em>{@code Builder} is not thread-safe and generally should not be stored
     * in a field or collection, but instead used immediately to create
     * instances.</em>
     */
    @NotThreadSafe
    interface Builder extends Entity.Builder<Builder> {

        /**
         * Fill a builder with attribute values from the provided
         * {@code com.tree_bit.blockapi.entities.XPOrb} instance.
         *
         * @param instance The instance from which to copy values
         * @return {@code this} builder for use in a chained invocation
         */
        Builder from(XPOrb instance);

        /**
         * Fill a builder with attribute values from the provided
         * {@code com.tree_bit.blockapi.entities.Entity} instance.
         *
         * @param instance The instance from which to copy values
         * @return {@code this} builder for use in a chained invocation
         */
        @Override
        Builder from(Entity instance);

        /**
         * Initializes the value for the {@link XPOrb#age() age} attribute.
         * <p>
         * <em>If not set, this attribute will have a default value as returned
         * by the initializer of {@link XPOrb#age() age}.</em>
         *
         * @param age The value for age
         * @return {@code this} builder for use in a chained invocation
         */
        Builder age(short age);

        /**
         * Initializes the value for the {@link XPOrb#health() health}
         * attribute.
         * <p>
         * <em>If not set, this attribute will have a default value as returned
         * by the initializer of {@link XPOrb#health() health}.</em>
         *
         * @param health The value for health
         * @return {@code this} builder for use in a chained invocation
         */
        Builder health(byte health);

        /**
         * Initializes the value for the {@link XPOrb#value() value} attribute.
         * <p>
         * <em>If not set, this attribute will have a default value as returned
         * by the initializer of {@link XPOrb#value() value}.</em>
         *
         * @param value The value for value
         * @return {@code this} builder for use in a chained invocation
         */
        Builder value(short value);

        /**
         * Builds a new {@link XPOrb XPOrb}.
         *
         * @return An immutable instance of XPOrb
         * @throws java.lang.IllegalStateException if any required attributes
         *         are missing
         */
        @Override
        XPOrb build();

    }

    /**
     * Copy the current immutable object by setting a value for the
     * {@link XPOrb#age() age} attribute. A value equality check is used to
     * prevent copying of the same value by returning {@code this}.
     *
     * @param age A new value for age
     * @return A modified copy of the {@code this} object
     */
    public abstract XPOrb withAge(short age);

    /**
     * Copy the current immutable object by setting a value for the
     * {@link XPOrb#health() health} attribute. A value equality check is used
     * to prevent copying of the same value by returning {@code this}.
     *
     * @param health A new value for health
     * @return A modified copy of the {@code this} object
     */
    public abstract XPOrb withHealth(byte health);

    /**
     * Copy the current immutable object by setting a value for the
     * {@link XPOrb#value() value} attribute. A value equality check is used to
     * prevent copying of the same value by returning {@code this}.
     *
     * @param value A new value for value
     * @return A modified copy of the {@code this} object
     */
    public abstract XPOrb withValue(short value);

}
