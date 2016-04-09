package com.tree_bit.rcdl.blocks.entities;

import com.tree_bit.rcdl.blocks.HangingSign;
import com.tree_bit.rcdl.blocks.StandingSign;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
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
     */
    public SignEntity() {
        this(new @Nullable FormatText[0]);
    }

    /**
     * Creates a Sign TileEntity with no text.
     *
     * @return Empty sign entity
     */
    public static SignEntity empty() {
        return new SignEntity();
    }

    /**
     * Creates a Sign TileEntity from a FormatText array.
     *
     * @param text Array containing text for each line of the sign. (max 4)
     *
     * @throws IllegalArgumentException if text[] has {@literal length>4}
     */
    public SignEntity(final @Nullable FormatText[] text) {
        super(createBuilder(text));
    }

    /**
     * Creates a Sign TileEntity from a String array.
     *
     * @param text Array containing text for each line of the sign. (max 4)
     *
     * @throws IllegalArgumentException if text[] has {@literal length>4}
     */
    public SignEntity(final @Nullable String[] text) {
        super(createBuilder(toFText(text)));
    }

    private static @NonNull FormatText[] toFText(final @Nullable String[] text) {
        final @NonNull FormatText[] fText = new @NonNull FormatText[text.length];
        for (int i = 0; i < text.length; i++) {
            final String tmp = text[i];
            fText[i] = new FormatText((tmp != null) ? tmp : "");
        }
        return fText;
    }

    private static Builder createBuilder(final FormatText[] text) {
        if (text.length > 4) {
            throw new IllegalArgumentException("Sign can't have more than 4 lines of text! Given: " + text.length);
        }
        final TileEntity.Builder b = new TileEntity.Builder("Sign");
        for (int i = 0; i < 4; i++) {
            String s = "";
            if (i < text.length) {
                final FormatText tmp = text[i];
                if (tmp != null) {
                    s = tmp.getStringWithCodes();
                }
            }
            b.add(new StringTag("Text" + (i + 1), s));
        }
        return b;
    }

}
