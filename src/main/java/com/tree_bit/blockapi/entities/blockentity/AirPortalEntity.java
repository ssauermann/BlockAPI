package com.tree_bit.blockapi.entities.blockentity;

import com.tree_bit.blockapi.nbt.NBT;
import com.tree_bit.blockapi.nbt.tags.CompoundTag;

import org.immutables.value.Value.Immutable;
import org.immutables.value.Value.Lazy;

/**
 * Tile entity of a end portal block.
 *
 * <p>
 * Used by:
 * <ul>
 * <li>{@link EndPortal}</li>
 * </ul>
 */
@Immutable(singleton = true, builder = false)
public abstract class AirPortalEntity implements BlockEntity {

    @Override
    @Lazy
    public CompoundTag compound() {
        return NBT.Compound(this.id()).build();
    }

    @Override
    public String id() {
        return "Airportal";
    }

    /**
     * Returns the default immutable singleton value of {@code AirPortalEntity}
     *
     * @return An immutable instance of AirPortalEntity
     */
    public static AirPortalEntity of() {
        return ImmutableAirPortalEntity.of();
    }

}
