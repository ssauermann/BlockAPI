package com.tree_bit.rcdl.schematic;

import org.jnbt.NBTConstants;

/**
 * Enum, containing important fields of a schematic file.
 *
 * @author Sascha Sauermanns
 */
@SuppressWarnings("nls")
public enum ESchematicFields {
    HEIGHT("Height", NBTConstants.TYPE_SHORT), LENGTH("Length", NBTConstants.TYPE_SHORT), WIDTH("Width", NBTConstants.TYPE_SHORT), BLOCKS("Blocks",
            NBTConstants.TYPE_BYTE_ARRAY), DATA("Data", NBTConstants.TYPE_BYTE_ARRAY), MATERIALS("Materials", NBTConstants.TYPE_STRING);

    /**
     * Key of the tag
     */
    private String key;
    /**
     * Type of the tag
     */
    private int type;

    /**
     * Creates a new ESchematicFields enum.
     *
     * @param key <b>String</b> key
     * @param type <b>int</b> type
     */
    private ESchematicFields(String key, int type) {
        this.key = key;
        this.type = type;
    }

    /**
     * Returns the key of a field to access the child tags of a compound tag by
     * key.
     *
     * @return <b>String</b> key
     */
    public String getKey() {
        return this.key;
    }

    /**
     * Returns the type of a field.
     *
     * @return <b>int</b> type
     */
    public int getType() {
        return this.type;
    }
}
