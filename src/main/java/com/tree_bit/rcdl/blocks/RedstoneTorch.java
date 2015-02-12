package com.tree_bit.rcdl.blocks;

import com.tree_bit.math.MathExtended;

/**
 * This Class holds all information about a Redstone Torch.
 *
 * @author Alexander
 * @author Sascha Sauermann
 */
public class RedstoneTorch extends Blocks {

    /** orientation. */
    private Orientation facing;

    /**
     * Creates a new RedstoneTorch with the given type and parameter.
     *
     * @param torchtype <b>Type</b> ({@link Type})
     * @param torchdatavalues <b>Orientation</b> ({@link Orientation})
     */
    public RedstoneTorch(Type torchtype, Orientation torchdatavalues) {
        super(torchtype.getID(), torchdatavalues.getDataValue());
        this.facing = torchdatavalues;
    }

    /**
     * Redstone Torch Type.
     *
     * @author Sascha Sauermann
     * @author Alexander
     */
    public enum Type implements IBlockTypeEnum {

        /** Off state. */
        Off(75),
        /** On state */
        On(76);

        /** Minecraft block id. */
        private int mcID;

        /**
         * Creates a new Torch Type.
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
     * Redstone Torch Orientation.
     *
     * @author Sascha Sauermann
     * @author Alexander
     */
    public enum Orientation implements IDataValueEnum, IOrientationEnum {

        /** The Facing east. */
        FacingEast(1),
        /** The Facing south. */
        FacingSouth(3),
        /** The Facing west. */
        FacingWest(2),
        /** The Facing north. */
        FacingNorth(4),
        /** The Facing up. */
        FacingUp(5);

        /** data value. */
        private int value;

        /**
         * Creates a new Redstone Torch Orientation.
         *
         * @param value the value
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
            if (this.value == FacingUp.value) {
                return FacingUp;
            }
            return this.next(n + ((n + this.ordinal()) / 4));
        }

        /**
         * @return <b>Orientation</b> orientation
         */
        @Override
        public Orientation mirror(boolean xAxis) {
            if (this.value == FacingUp.value) {
                return FacingUp;
            }
            if (xAxis) {
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
            final Orientation temp = values()[MathExtended.mod((this.ordinal() + i), 16)];
            if (temp != null) {
                return temp;
            }
            throw new IllegalStateException();
        }

    }

    @Override
    public void rotateCount(int n) {
        this.setOrientation(this.facing.rotate(n));
    }

    @Override
    public void mirror(boolean xAxis) {
        this.setOrientation(this.facing.mirror(xAxis));
    }

    /**
     * Sets the orientation of this block.
     *
     * @param orientation <b>Orientation</b> orientation
     */
    public void setOrientation(Orientation orientation) {
        this.facing = orientation;
        this.datavalue = orientation.getDataValue();
    }

    /**
     * Returns the orientation of this block.
     *
     * @return <b>Orientation</b> orientation
     */
    public Orientation getOrientation() {
        return this.facing;
    }
}
