package com.tree_bit.blockapi.entities;

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
public class AirPortalEntity extends TileEntity {

    /**
     * Creates an air portal entity.
     */
    AirPortalEntity() {
        super(TileEntity.builder("Airportal"));
    }


}
