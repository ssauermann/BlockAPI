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

import org.immutables.value.Value;
import org.immutables.value.Value.Immutable;
import org.immutables.value.Value.Lazy;
import org.immutables.value.Value.Parameter;

import java.util.Optional;

import javax.annotation.concurrent.NotThreadSafe;

/**
 * Represents an item entity.
 */
@Immutable
public abstract class Item implements Entity {

    @Override
    public String id() {
        return "Item";
    }

    @Override
    @Parameter(order = 0)
    public abstract Coordinates pos();


    @Override
    @Lazy
    public CompoundTag compound() {
        return NBT.begin().Short("Age", this.age()).Short("Health", this.health()).Short("PickupDelay", this.pickupDelay())
                .String("Owner", this.owner()).String("Thrower", this.thrower()).add(this.item().compoundWithoutSlot()).addAll(this._entityTags())
                .build();
    }

    /**
     * The number of ticks the item has been "untouched". After 6000 ticks (5
     * minutes) the item is destroyed. If set to -32768, the Age will not
     * increase, thus the item will not automatically despawn.
     *
     * <p>
     * <i>Default:</i> 0
     *
     * @return current age
     */
    @Value.Default
    @SuppressWarnings("static-method")
    public short age() {
        return 0;
    }

    /**
     * The health of the item, which starts at 5. Items take damage from fire,
     * lava, falling anvils, and explosions. The item is destroyed when its
     * health reaches 0.
     *
     * <p>
     * <i>Default:</i> 5
     *
     * @return current health
     */
    @Value.Default
    @SuppressWarnings("static-method")
    public short health() {
        return 5;
    }

    /**
     * The number of ticks the item cannot be picked up. Decreases by 1 per
     * tick. If set to 32767, the PickupDelay will not decrease, thus the item
     * can never be picked up.
     *
     * <p>
     * <i>Default:</i> 0
     *
     * @return current delay
     */
    @Value.Default
    @SuppressWarnings("static-method")
    public short pickupDelay() {
        return 0;
    }

    /**
     * If not an empty string, only the named player will be able to pick up
     * this item, until it is within 10 seconds of despawning. Used by the give
     * command (and can be set in a summon command) to prevent the wrong player
     * from picking up the spawned item entity.
     *
     * <p>
     * <i>Default:</i> ""
     *
     * @return current owner
     */
    @Value.Default
    @SuppressWarnings("static-method")
    public String owner() {
        return "";
    }


    /**
     * Construct a new immutable {@code Item} instance.
     *
     * @param position The value for the {@code pos} attribute
     * @param item The value for the {@code item} attribute
     * @return An immutable Item instance
     */
    public static Item of(final Coordinates position, final InventoryItem item) {
        return ImmutableItem.of(position, item);
    }

    /**
     * Creates a builder for {@link Item Item}.
     *
     * @return A new ImmutableItem builder
     */
    public static Builder builder() {
        return ImmutableItem.builder();
    }


    /**
     * Set to the name of the player who dropped the item, if dropped by a
     * player. Used by the "Diamonds to you!" achievement.
     *
     * <p>
     * <i>Optional</i>
     *
     * @return current owner
     */
    public abstract Optional<String> thrower();

    /**
     * The inventory item, without the Slot tag.
     *
     * @return item
     */
    @Parameter(order = 1)
    public abstract InventoryItem item();


    /**
     * Copy the current immutable object by setting a value for the
     * {@link Item#age() age} attribute. A value equality check is used to
     * prevent copying of the same value by returning {@code this}.
     *
     * @param age A new value for age
     * @return A modified copy of the {@code this} object
     */
    public abstract Item withAge(short age);

    /**
     * Copy the current immutable object by setting a value for the
     * {@link Item#health() health} attribute. A value equality check is used to
     * prevent copying of the same value by returning {@code this}.
     *
     * @param health A new value for health
     * @return A modified copy of the {@code this} object
     */
    public abstract Item withHealth(short health);

    /**
     * Copy the current immutable object by setting a value for the
     * {@link Item#pickupDelay() pickupDelay} attribute. A value equality check
     * is used to prevent copying of the same value by returning {@code this}.
     *
     * @param pickupDelay A new value for pickupDelay
     * @return A modified copy of the {@code this} object
     */
    public abstract Item withPickupDelay(short pickupDelay);

    /**
     * Copy the current immutable object by setting a value for the
     * {@link Item#owner() owner} attribute. An equals check used to prevent
     * copying of the same value by returning {@code this}.
     *
     * @param owner A new value for owner
     * @return A modified copy of the {@code this} object
     */
    public abstract Item withOwner(String owner);

    /**
     * Copy the current immutable object by setting a <i>present</i> value for
     * the optional {@link Item#thrower() thrower} attribute.
     *
     * @param value The value for thrower
     * @return A modified copy of {@code this} object
     */
    public abstract Item withThrower(String value);

    /**
     * Copy the current immutable object by setting an optional value for the
     * {@link Item#thrower() thrower} attribute. An equality check is used on
     * inner nullable value to prevent copying of the same value by returning
     * {@code this}.
     *
     * @param optional A value for thrower
     * @return A modified copy of {@code this} object
     */
    public abstract Item withThrower(Optional<String> optional);

    /**
     * Copy the current immutable object by setting a value for the
     * {@link Item#item() item} attribute. A shallow reference equality check is
     * used to prevent copying of the same value by returning {@code this}.
     *
     * @param item A new value for item
     * @return A modified copy of the {@code this} object
     */
    public abstract Item withItem(InventoryItem item);

    /**
     * Builds instances of type {@link Item Item}. Initialize attributes and
     * then invoke the {@link #build()} method to create an immutable instance.
     * <p>
     * <em>{@code Builder} is not thread-safe and generally should not be stored
     * in a field or collection, but instead used immediately to create
     * instances.</em>
     */
    @NotThreadSafe
    interface Builder extends Entity.Builder<Builder> {

        /**
         * Fill a builder with attribute values from the provided {@code Item}
         * instance. Regular attribute values will be replaced with those from
         * the given instance. Absent optional values will not replace present
         * values.
         *
         * @param instance The instance from which to copy values
         * @return {@code this} builder for use in a chained invocation
         */
        Builder from(Item instance);

        /**
         * Initializes the value for the {@link Item#age() age} attribute.
         * <p>
         * <em>If not set, this attribute will have a default value as returned
         * by the initializer of {@link Item#age() age}.</em>
         *
         * @param age The value for age
         * @return {@code this} builder for use in a chained invocation
         */
        Builder age(short age);

        /**
         * Initializes the value for the {@link Item#health() health} attribute.
         * <p>
         * <em>If not set, this attribute will have a default value as returned
         * by the initializer of {@link Item#health() health}.</em>
         *
         * @param health The value for health
         * @return {@code this} builder for use in a chained invocation
         */
        Builder health(short health);

        /**
         * Initializes the value for the {@link Item#pickupDelay() pickupDelay}
         * attribute.
         * <p>
         * <em>If not set, this attribute will have a default value as returned
         * by the initializer of {@link Item#pickupDelay() pickupDelay}.</em>
         *
         * @param pickupDelay The value for pickupDelay
         * @return {@code this} builder for use in a chained invocation
         */
        Builder pickupDelay(short pickupDelay);

        /**
         * Initializes the value for the {@link Item#owner() owner} attribute.
         * <p>
         * <em>If not set, this attribute will have a default value as returned
         * by the initializer of {@link Item#owner() owner}.</em>
         *
         * @param owner The value for owner
         * @return {@code this} builder for use in a chained invocation
         */
        Builder owner(String owner);

        /**
         * Initializes the optional value {@link Item#thrower() thrower} to
         * thrower.
         *
         * @param thrower The value for thrower
         * @return {@code this} builder for chained invocation
         */
        Builder thrower(String thrower);

        /**
         * Initializes the optional value {@link Item#thrower() thrower} to
         * thrower.
         *
         * @param thrower The value for thrower
         * @return {@code this} builder for use in a chained invocation
         */
        Builder thrower(Optional<String> thrower);

        /**
         * Initializes the value for the {@link Item#item() item} attribute.
         *
         * @param item The value for item
         * @return {@code this} builder for use in a chained invocation
         */
        Builder item(InventoryItem item);

        /**
         * Builds a new {@link Item Item}.
         *
         * @return An immutable instance of Item
         * @throws java.lang.IllegalStateException if any required attributes
         *         are missing
         */
        @Override
        Item build();

    }

}

