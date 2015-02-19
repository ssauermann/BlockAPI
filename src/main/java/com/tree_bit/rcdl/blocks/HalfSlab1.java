package com.tree_bit.rcdl.blocks;

/**
 * Fully functional Halfslab.
 *
 * @author Alexander
 */
public class HalfSlab extends Blocks {

    /**
     * Creates a new HalfSlab.
     *
     * @param type <b>Type</b> slab type ({@link Type})
     * @param position <b>Position</b> slab position ({@link Position})
     */
    public HalfSlab(Type type, Position position) {
        super(44, type.getDataValue() + position.getDataValue());
    }

    /**
     * Type of a halfslab.
     *
     * @author Alexander
     * @author Sascha Sauermann
     */
    public enum Type implements IDataValueEnum {
        Stone(0), Sandstone(1), Wood(2), Cobblestone(3), Bricks(4), StoneBricks(5), Netherbrick(6), Quartz(7);

        /**
         * Data value
         */
        private int value;

        /**
         * Creates a new orientation.
         *
         * @param dataValue <b>int</b> data value
         */
        private Type(int dataValue) {
            this.value = dataValue;
        }

        @Override
        public int getDataValue() {
            return this.value;
        }
    }

    /**
     * Position of a minecraft half slab.
     *
     * @author Alexander
     * @author Sascha Sauermann
     */
    public enum Position implements IDataValueEnum {
        UP(8), Down(0);

        /**
         * Data value
         */
        private int value;

        /**
         * Creates a new orientation.
         *
         * @param dataValue <b>int</b> data value
         */
        private Position(int dataValue) {
            this.value = dataValue;
        }

        @Override
        public int getDataValue() {
            return this.value;
        }
    }

}
