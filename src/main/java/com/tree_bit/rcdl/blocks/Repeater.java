package com.tree_bit.rcdl.blocks;

import com.google.common.math.IntMath;

/**
 * Holds all the information for a Repeater.
 *
 * @author Alexander
 * @author Sascha Sauermann
 */
public class Repeater extends Blocks {

    /** Orientation. */
    private Orientation facing;

    /** Delay. */
    private Delay delay;

    /**
     * Creates a new Repeater with the given type, orientation and delay.
     *
     * @param repeatertype <b>Type</b> block type ({@link Type})
     * @param orientation <b>Orientation</b> orientation ({@link Orientation})
     * @param delay <b>Delay</b> delay ({@link Delay})
     */
    public Repeater(Type repeatertype, Orientation orientation, Delay delay) {
        super(repeatertype.getID(), orientation.getDataValue() + delay.getDataValue());
        // Delay and orientation are given seperatly, and get converted into one
        // int or later byte
        this.facing = orientation;
        this.delay = delay;
    }

    /**
     * Repeater type.
     *
     * @author Sascha Sauermann
     * @author Alexander
     */
    public enum Type implements IBlockTypeEnum {

        /** Repeater with signal */
        RepeaterBlockOn(94),
        /** Repeater without signal */
        RepeaterBlockOFF(93);

        /** Minecraft block id. */
        private int mcID;

        /**
         * Creates a new Repeater Type.
         *
         * @param id <b>int</b> block id
         */
        private Type(int id) {
            this.mcID = id;
        }

        @Override
        public int getID() {
            return this.mcID;
        }
    }

    /**
     * Repeater orientation.
     *
     * @author Sascha Sauermann
     * @author Alexander
     */
    public enum Orientation implements IDataValueEnum, IOrientationEnum {

        /** The Facing north. */
        FacingNorth(0),
        /** The Facing east. */
        FacingEast(1),
        /** The Facing south. */
        FacingSouth(2),
        /** The Facing west. */
        FacingWest(3);

        /** The value. */
        private int value;

        /**
         * Creates a new orientation.
         *
         * @param value <b>int</b> value
         */
        private Orientation(int value) {
            this.value = value;
        }

        @Override
        public int getDataValue() {
            return this.value;
        }

        /**
         * @return <b>Orientation</b> orientation
         */
        @Override
        public Orientation rotate(int n) {
            return this.next(1);
        }

        /**
         * @return <b>Orientation</b> orientation
         */
        @Override
        public Orientation mirror(boolean rotateX) {
            if (rotateX) {
                if (this.next(0) == FacingSouth) {
                    return FacingNorth;
                } else if (this.next(0) == FacingNorth) {
                    return FacingSouth;
                }
            } else {
                if (this.next(0) == FacingEast) {
                    return FacingWest;
                } else if (this.next(0) == FacingWest) {
                    return FacingEast;
                }
            }
            return this.next(0);
        }

        /**
         * @return <b>Orientation</b> orientation
         */
        @Override
        public Orientation next(int i) {
            final Orientation temp = values()[IntMath.mod((this.ordinal() + i), 4)];
            if (temp != null) {
                return temp;
            }
            throw new IllegalStateException();
        }
    }

    /**
     * Repeater Delay.
     *
     * @author Sascha Sauermann
     * @author Alexander Frank
     */
    public enum Delay implements IDataValueEnum {

        Delay1(0), Delay2(4), Delay3(8), Delay4(12);

        /** data value. */
        private int value;

        /**
         * Creates a new Repeater Delay.
         *
         * @param value <b>int</b> value
         */
        private Delay(int value) {
            this.value = value;
        }

        @Override
        public int getDataValue() {
            return this.value;
        }
    }

    @Override
    public void rotateCount(int n) {
        this.facing = this.facing.rotate(n);
    }

    /**
     * Sets the orientation of this block.
     *
     * @param orientation <b>Orientation</b> orientation
     */
    public void setOrientation(Orientation orientation) {
        this.facing = orientation;
        this.datavalue = orientation.getDataValue() + this.delay.getDataValue();
    }

    /**
     * Returns the orientation of this block.
     *
     * @return <b>Orientation</b> orientation
     */
    public Orientation getOrientation() {
        return this.facing;
    }

    /**
     * Sets the delay.
     *
     * @param delay <b>Delay</b> delay
     */
    public void setDelay(Delay delay) {
        this.delay = delay;
        this.datavalue = this.facing.getDataValue() + delay.getDataValue();
    }

    /**
     * Returns the delay.
     *
     * @return <b>Delay</b> delay
     */
    public Delay getDelay() {
        return this.delay;
    }
}
