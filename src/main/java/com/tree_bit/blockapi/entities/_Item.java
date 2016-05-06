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

import org.immutables.value.Value;

import java.util.Optional;

/**
 * Represents an item in an inventory slot.
 */
@Value.Immutable
@SuppressWarnings("static-method")
abstract class _Item implements NBTData {


    @Override
    @Value.Lazy
    public CompoundTag compound() {
        return NBT.begin().Byte("Count", this.count()).Byte("Slot", this.slotNr()).Short("Damage", this.damage()).String("id", this.id())
                .add(this.tag()).build();
    }

    /**
     * Number of items stacked in this inventory slot. Any item can be stacked,
     * including tools, armor, and vehicles. Range is -128 to 127. Values of 1
     * are not displayed in-game. Values below 1 are displayed in red.
     *
     * <p>
     * Default: 1
     *
     * @return current count
     */
    @Value.Parameter(order = 2)
    @Value.Default
    public byte count() {
        return 1;
    }

    /**
     * The inventory slot the item is in.
     *
     * <p>
     * Default: 0
     *
     * @return current slot number
     */
    @Value.Parameter(order = 4)
    @Value.Default
    public byte slotNr() {
        return 0;
    }

    /**
     * The data value for this item. The name "Damage" comes from when only
     * tools used this value, now many other items use this value for other
     * purposes. For blocks, it is the 4-bit "block data" tag that determines a
     * variant of the block. Defaults to 0.
     *
     * <p>
     * Default: 0
     *
     * @return current damage value
     */
    @Value.Parameter(order = 3)
    @Value.Default
    public short damage() {
        return 0;
    }

    /**
     * Item/Block ID. If not specified, Minecraft changes the item to stone
     * (setting ID to 1 and Damage to 0, and ignoring any existing Damage value)
     * when loading the chunk or summoning the item.
     *
     * <p>
     * Default: {@link BlockID#STONE}
     *
     * @return current damage value
     */
    @Value.Parameter(order = 1)
    @Value.Default
    public String id() {
        return BlockID.STONE.getAlphabeticalID();
    }

    /**
     * Additional information about the item, discussed in the below sections.
     * This tag is optional for most items.
     *
     * <p>
     * Optional
     *
     * @return additional informations compound tag
     */
    @Value.Parameter(order = 5)
    public abstract Optional<CompoundTag> tag();

}
