package com.tree_bit.blockapi.entities;

import com.tree_bit.blockapi.nbt.NBT;
import com.tree_bit.blockapi.nbt.tags.CompoundTag;

import org.immutables.value.Value.Derived;
import org.immutables.value.Value.Immutable;

/**
 * Tile entity of a end portal block
 *
 * <p>
 * Used by:
 * <ul>
 * <li>{@link EndPortal}</li>
 * </ul>
 *
 * @author Sascha Sauermann
 */

@Immutable(singleton = true)
public abstract class _AirPortalEntity implements TileEntity {

    @Override
    @Derived
    public CompoundTag compound() {
        return NBT.Compound(this.id()).build();
    }

    @Override
    public String id() {
        return "Airportal";
    }
}
