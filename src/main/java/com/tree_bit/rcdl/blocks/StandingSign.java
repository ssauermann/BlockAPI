package com.tree_bit.rcdl.blocks;

/**
 * A sign which is placed on top of another block (if you want a sign which is
 * on the side of a block use {@link HangingSign} instead).
 *
 * To configure the orientation of this sign use the values of the
 * {@link Orientation16 Orientation16 Enum}.
 *
 * @author Alexander
 * @author Sascha Sauermann
 *
 */
public class StandingSign extends Blocks {

    /** Current orientation */
    private Orientation16 orientation;

    /**
     * Creates a new standing sign with the given orientation.
     *
     * @param orientation <b>Orientation16</b> orientation (
     *        {@link Orientation16})
     * @param <b>String[]</b> sign text, one line per entry (max array length 4)
     */
    public StandingSign(Orientation16 orientation, String[] text) {
        super(63, orientation.getDataValue());
        if (text.length > 4) {
            throw new IllegalArgumentException(Messages.getString("StandingSign.IllegalTextArray") + text.length); //$NON-NLS-1$
        }
        this.text = text;
        this.orientation = orientation;
    }

    @Override
    public void rotateCount(int count) {
        this.setOrientation(this.orientation.rotate(count * 4));
    }

    @Override
    public void mirror(boolean rotateX) {
        this.setOrientation(this.orientation.mirror(rotateX));
    }

    /**
     * Sets the orientation of this block.
     *
     * @param orientation <b>Orientation16</b> orientation
     */
    public void setOrientation(Orientation16 orientation) {
        this.orientation = orientation;
        this.datavalue = orientation.getDataValue();
    }

    /**
     * Returns the orientation of this block.
     *
     * @return <b>Orientation16</b> orientation
     */
    public Orientation16 getOrientation() {
        return this.orientation;
    }
}
