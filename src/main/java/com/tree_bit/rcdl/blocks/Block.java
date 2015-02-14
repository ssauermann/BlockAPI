package com.tree_bit.rcdl.blocks;

import org.eclipse.jdt.annotation.NonNull;


/**
 * Minecraft Block
 */
public abstract class Block {

    /** The Minecraft Id describes the Block which is used */
    protected int minecraftID;

    /** The Datavalues describe things like Orientation */
    protected int datavalue;

    /** sign text */
    // TODO: Not an attribute of all blocks
    protected String[] text = new String @NonNull [4];

    /**
     * Sets the Object Up with the Information about its ID and value
     *
     * @param mcID (Minecraft ID)
     * @param datavalue (Value of the Block)
     */
    private Block(final int mcID, final int datavalue) {
        if ((datavalue > 15) || (datavalue < 0)) {
            throw new IllegalArgumentException("Datavalues higher than 15 and lower than 0 are permitted");
        }
        this.minecraftID = mcID;
        this.datavalue = datavalue;
        this.text = new String @NonNull [] {"Bla"};
    }

    /**
     * Retrieves the Minecraft ID of the Object
     *
     * @return <b>Integer</b>
     */
    public int getMinecraftID() {
        return this.minecraftID;
    }

    /**
     * Retrieves Information about the Orientation or the state of the Block
     *
     * @return <b>Integer</b> The Data Value of this Object
     */
    public int getDatavalue() {
        return this.datavalue;
    }

    /**
     * Retrieves Information concerning the text a sign should display later
     *
     * @return <b>String[]</b> text
     */
    public String[] getText() {
        return this.text;
    }

    /**
     * Mirrors the orientation of this block at the x(east-west) or the
     * z(south-north) axis.
     *
     * <p>
     * Child classes should overwrite this method if they have an orientation.
     * </p>
     *
     * @param rotateX <b>boolean</b> Mirrors the block at the x (true) or the
     *        y-axis (true).
     */
    public void mirror(final boolean rotateX) {
        // Normal blocks are the same after mirroring.
    }

    /**
     * Rotate the block by the given amount of degree.
     *
     * <p>
     * Child classes should overwrite {@link #rotateCount(int)} if they have an
     * orientation.
     * </p>
     *
     * @param degree <b>int</b> degree (only multiples of 90 are allowed)
     */
    public final void rotate(final int degree) {
        if ((degree % 90) != 0) {
            throw new IllegalArgumentException("Rotation is only allowed for multiples of 90 degree");
        }

        int count = degree / 90;

        if (degree < 0) {
            count--;
        } else {
            count++;
        }

        this.rotateCount(count);
    }

    /**
     * Rotates the block the given amount of times (90 degree each turn).
     *
     * @param count <b>int</b> rotation amount
     */
    protected void rotateCount(final int count) {
        // No rotation for normal blocks
    }
}
