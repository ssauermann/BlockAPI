package com.tree_bit.rcdl.blocks.entities;

import com.tree_bit.rcdl.blocks.HangingSign;
import com.tree_bit.rcdl.blocks.StandingSign;

import org.jnbt.StringTag;

/**
 * Tile entity of a sign block.
 *
 * <p>
 * Used by:
 * <ul>
 * <li>{@link HangingSign}</li>
 * <li>{@link StandingSign}</li>
 * </ul>
 */
public class SignEntity extends TileEntity {

    /**
     * Creates a Sign TileEntity with no text.
     *
     * @return TileEntity
     */
    public static SignEntity empty() {
        return new SignEntity(new FormatText[0]);
    }

    /**
     * Creates a Sign TileEntity from a FormatText array.
     *
     * @param text Array containing text for each line of the sign. (max 4)
     *
     * @throws IllegalArgumentException if text[] has {@literal length>4}
     */
    public SignEntity(final FormatText[] text) {
        super(createBuilder(text));
    }

    /**
     * Creates a Sign TileEntity from a String array.
     *
     * @param text Array containing text for each line of the sign. (max 4)
     *
     * @throws IllegalArgumentException if text[] has {@literal length>4}
     */
    public SignEntity(final String[] text) {
        super(createBuilder(toFText(text)));
    }

    private static FormatText[] toFText(final String[] text) {
        final FormatText[] fText = new FormatText[text.length];
        for (int i = 0; i < text.length; i++) {
            fText[i] = new FormatText(text[i]);
        }
        return fText;
    }

    private static Builder createBuilder(final FormatText[] text) {
        if (text.length > 4) {
            throw new IllegalArgumentException("Sign can't have more than 4 lines of text! Given: " + text.length);
        }
        final TileEntity.Builder b = new TileEntity.Builder("Sign");
        for (int i = 0; i < 4; i++) {
            final String s = (i < text.length) ? text[i].getStringWithCodes() : "";
            b.add(new StringTag("Text" + (i + 1), s));
        }
        return b;
    }

}
