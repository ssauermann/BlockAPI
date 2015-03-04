package com.tree_bit.rcdl.blocks;

import com.tree_bit.rcdl.blocks.dv.IDataValueEnum;
import com.tree_bit.rcdl.blocks.dv.SignOrientation;
import com.tree_bit.rcdl.blocks.entities.SignEntity;
import com.tree_bit.rcdl.blocks.entities.TileEntity;


/**
 * Data values of a 'Hanging Sign' block.
 *
 * <p>
 * Data enum: {@link SignOrientation}
 *
 * <p>
 * Allowed axes for rotation (multiple of 90 degree) are:
 * <ul>
 * <li>y</li>
 * </ul>
 *
 * <p>
 * Allowed plains for mirroring are: *
 * <ul>
 * <li>x-y</li>
 * <li>z-y</li>
 * </ul>
 * *Note: This mirroring does not include the text.
 */
public class HangingSign extends BlockData {

    private HangingSign(final SignOrientation orientation, final SignEntity entity) {
        super(entity, orientation);
    }

    private HangingSign() {
        super(SignEntity.empty(), SignOrientation.North);
    }

    private HangingSign(final IDataValueEnum[] values, final TileEntity entity) {
        super(entity, validateDV(values, SignOrientation.class));
    }


    /**
     * Returns an instance of the 'HangingSign' data with a default orientation
     * (North) and no text.
     *
     * @return Instance of a HangingSign
     */
    public static HangingSign getInstance() {
        return BlockDataFactory.getDefaultInstance(HangingSign.class);
    }

    /**
     * Returns an instance of the 'HangingSign' data with the given orientation
     * and no text.
     *
     * @param orientation Orientation
     * @return Instance of a HangingSign
     */
    public static HangingSign getInstance(final SignOrientation orientation) {
        return getInstance(orientation, SignEntity.empty());
    }

    /**
     * Returns an instance of the 'HangingSign' data with the given orientation
     * and text.
     *
     * @param orientation Orientation
     * @param entity Sign tile entity
     * @return Instance of a HangingSign
     *
     * @throws IllegalArgumentException if text has {@literal length>4}
     */
    public static HangingSign getInstance(final SignOrientation orientation, final SignEntity entity) {
        return BlockDataFactory.getInstance(HangingSign.class, entity, orientation);
    }

}
