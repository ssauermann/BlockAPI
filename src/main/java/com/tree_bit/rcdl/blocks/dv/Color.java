package com.tree_bit.rcdl.blocks.dv;

/**
 * Representing the Minecraft colors.
 */
public enum Color implements IDataValueEnum {
    /** White */
    White(0),
    /** Orange */
    Orange(1),
    /** Magenta */
    Magenta(2),
    /** Light Blue */
    LightBlue(3),
    /** Yellow */
    Yellow(4),
    /** Lime */
    Lime(5),
    /** Pink */
    Pink(6),
    /** Dark Gray */
    Gray(7),
    /** Light Gray */
    LightGray(8),
    /** Cyan */
    Cyan(9),
    /** Purple */
    Purple(10),
    /** Blue */
    Blue(11),
    /** Brown */
    Brown(12),
    /** Green */
    Green(13),
    /** Red */
    Red(14),
    /** Black */
    Black(15);

    private final int value;

    private Color(final int value) {
        this.value = value;
    }

    @Override
    public int getDataValue() {
        return this.value;
    }

    /**
     * Gets this color by id.
     *
     * @param id Color id
     * @return matching color or this
     */
    public Color byID(final int id) {
        for (final Color e : values()) {
            if (e.getDataValue() == id) {
                return e;
            }
        }
        return this;
    }
}
