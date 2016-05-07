package com.tree_bit.blockapi.data;

import java.util.Optional;

/**
 * Defining methods a class representing Minecraft data values has to implement.
 *
 * @author Sascha Sauermann
 */
public interface IDataValue {

    /**
     * Returns the data value of this setting.
     *
     * @return Data value
     */
    int getDV();

    /**
     * Gets the data value object by the id.
     *
     * @param dv data value
     * @return data value object
     */
    Optional<? extends IDataValue> byDV(final int dv);

}
