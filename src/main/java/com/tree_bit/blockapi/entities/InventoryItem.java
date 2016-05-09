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

import com.tree_bit.blockapi.id.minecraft.BlockID;
import com.tree_bit.blockapi.nbt.NBT;
import com.tree_bit.blockapi.nbt.NBTData;
import com.tree_bit.blockapi.nbt.tags.CompoundTag;

import org.immutables.value.Value.Default;
import org.immutables.value.Value.Immutable;
import org.immutables.value.Value.Lazy;
import org.immutables.value.Value.Parameter;

import java.util.Optional;

import javax.annotation.concurrent.NotThreadSafe;


/**
 * Represents an item in an inventory slot.
 */
@Style
@Immutable
public abstract class InventoryItem implements NBTData {

    @Override
    @Lazy
    public CompoundTag compound() {
        return NBT.begin().Byte("Count", this.count()).Byte("Slot", this.slot()).Short("Damage", this.damage()).String("id", this.id())
                .add(this.tag()).build();
    }

    /**
     * Creates the compound tag without the slot tag.
     * 
     * @return compound tag
     */
    CompoundTag compoundWithoutSlot() {
        return NBT.begin().Byte("Count", this.count()).Short("Damage", this.damage()).String("id", this.id()).add(this.tag()).build();
    }

    /**
     * Number of items stacked in this inventory slot. Any item can be stacked,
     * including tools, armor, and vehicles. Range is -128 to 127. Values of 1
     * are not displayed in-game. Values below 1 are displayed in red.
     *
     * <p>
     * <i>Default:</i> 1
     *
     * @return current count
     */
    @Parameter(order = 2)
    @Default
    @SuppressWarnings("static-method")
    public byte count() {
        return 0;
    }

    /**
     * The inventory slot the item is in.
     *
     * <p>
     * <i>Optional</i>
     *
     * @return current slot number
     */
    public abstract Optional<Byte> slot();

    /**
     * The data value for this item. The name "Damage" comes from when only
     * tools used this value, now many other items use this value for other
     * purposes. For blocks, it is the 4-bit "block data" tag that determines a
     * variant of the block. Defaults to 0.
     *
     * <p>
     * <i>Default:</i> 0
     *
     * @return current damage value
     */
    @Parameter(order = 3)
    @Default
    @SuppressWarnings("static-method")
    public short damage() {
        return 0;
    }

    /**
     * Item/Block ID. If not specified, Minecraft changes the item to stone
     * (setting ID to 1 and Damage to 0, and ignoring any existing Damage value)
     * when loading the chunk or summoning the item.
     *
     * <p>
     * <i>Default:</i> {@link BlockID#STONE}
     *
     * @return current damage value
     */
    @Parameter(order = 1)
    @Default
    @SuppressWarnings("static-method")
    public String id() {
        // TODO Should this be the alphabetical id?
        return BlockID.STONE.getAlphabeticalID();
    }

    /**
     * Additional information about the item, discussed in the below sections.
     * This tag is optional for most items.
     *
     * <p>
     * <i>Optional</i>
     *
     * @return compound tag with additional informations
     */
    public abstract Optional<CompoundTag> tag();


    /**
     * Copy the current immutable object by setting a value for the
     * {@link InventoryItem#count() count} attribute. A value equality check is
     * used to prevent copying of the same value by returning {@code this}.
     *
     * @param count A new value for count
     * @return A modified copy of the {@code this} object
     */
    public abstract InventoryItem withCount(byte count);

    /**
     * Copy the current immutable object by setting a value for the
     * {@link InventoryItem#slot() slot} attribute. A value equality check is
     * used to prevent copying of the same value by returning {@code this}.
     *
     * @param slot A new value for slot
     * @return A modified copy of the {@code this} object
     */
    public abstract InventoryItem withSlot(byte slot);

    /**
     * Copy the current immutable object by setting a value for the
     * {@link InventoryItem#damage() damage} attribute. A value equality check
     * is used to prevent copying of the same value by returning {@code this}.
     *
     * @param damage A new value for damage
     * @return A modified copy of the {@code this} object
     */
    public abstract InventoryItem withDamage(short damage);

    /**
     * Copy the current immutable object by setting a value for the
     * {@link InventoryItem#id() id} attribute. An equals check used to prevent
     * copying of the same value by returning {@code this}.
     *
     * @param id A new value for id
     * @return A modified copy of the {@code this} object
     */
    public abstract InventoryItem withId(String id);

    /**
     * Copy the current immutable object by setting a <i>present</i> value for
     * the optional {@link InventoryItem#tag() tag} attribute.
     *
     * @param value The value for tag
     * @return A modified copy of {@code this} object
     */
    public abstract InventoryItem withTag(CompoundTag value);

    /**
     * Copy the current immutable object by setting an optional value for the
     * {@link InventoryItem#tag() tag} attribute. A shallow reference equality
     * check is used on unboxed optional value to prevent copying of the same
     * value by returning {@code this}.
     *
     * @param optional A value for tag
     * @return A modified copy of {@code this} object
     */
    public abstract InventoryItem withTag(Optional<CompoundTag> optional);

    /**
     * Construct a new immutable {@code InventoryItem} instance.
     *
     * @param id The value for the {@code id} attribute
     * @param count The value for the {@code count} attribute
     * @param damage The value for the {@code damage} attribute
     * @return An immutable InventoryItem instance
     */
    public static InventoryItem of(final String id, final byte count, final short damage) {
        return ImmutableInventoryItem.of(id, count, damage);
    }

    /**
     * Creates a builder for {@link InventoryItem InventoryItem}.
     *
     * @return A new InventoryItem builder
     */
    public static ImmutableInventoryItem.Builder builder() {
        return ImmutableInventoryItem.builder();
    }

    /**
     * Builds instances of type {@link InventoryItem InventoryItem}. Initialize
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
         * Fill a builder with attribute values from the provided
         * {@code InventoryItem} instance. Regular attribute values will be
         * replaced with those from the given instance. Absent optional values
         * will not replace present values.
         *
         * @param instance The instance from which to copy values
         * @return {@code this} builder for use in a chained invocation
         */
        Builder from(InventoryItem instance);

        /**
         * Initializes the value for the {@link InventoryItem#count() count}
         * attribute.
         * <p>
         * <em>If not set, this attribute will have a default value as returned
         * by the initializer of {@link InventoryItem#count() count}.</em>
         *
         * @param count The value for count
         * @return {@code this} builder for use in a chained invocation
         */
        Builder count(byte count);

        /**
         * Initializes the value for the {@link InventoryItem#slot() slot}
         * attribute.
         * <p>
         * <em>If not set, this attribute will have a default value as returned
         * by the initializer of {@link InventoryItem#slot() slot}.</em>
         *
         * @param slot The value for slot
         * @return {@code this} builder for use in a chained invocation
         */
        Builder slot(byte slot);

        /**
         * Initializes the value for the {@link InventoryItem#damage() damage}
         * attribute.
         * <p>
         * <em>If not set, this attribute will have a default value as returned
         * by the initializer of {@link InventoryItem#damage() damage}.</em>
         *
         * @param damage The value for damage
         * @return {@code this} builder for use in a chained invocation
         */
        Builder damage(short damage);

        /**
         * Initializes the value for the {@link InventoryItem#id() id}
         * attribute.
         * <p>
         * <em>If not set, this attribute will have a default value as returned
         * by the initializer of {@link InventoryItem#id() id}.</em>
         *
         * @param id The value for id
         * @return {@code this} builder for use in a chained invocation
         */
        Builder id(String id);

        /**
         * Initializes the optional value {@link InventoryItem#tag() tag} to
         * tag.
         *
         * @param tag The value for tag
         * @return {@code this} builder for chained invocation
         */
        Builder tag(CompoundTag tag);

        /**
         * Initializes the optional value {@link InventoryItem#tag() tag} to
         * tag.
         *
         * @param tag The value for tag
         * @return {@code this} builder for use in a chained invocation
         */
        Builder tag(Optional<CompoundTag> tag);

        /**
         * Builds a new {@link InventoryItem InventoryItem}.
         *
         * @return An immutable instance of InventoryItem
         * @throws java.lang.IllegalStateException if any required attributes
         *         are missing
         */
        InventoryItem build();

    }

}
