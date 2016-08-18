package com.tree_bit.rcdl.blocks;

import com.tree_bit.blockapi.data.IDataValue;
import com.tree_bit.blockapi.entities.todo.GenericTileEntity;
import com.tree_bit.blockapi.entities.todo.SignEntity;
import com.tree_bit.rcdl.blocks.dv.SignOrientation;

import org.eclipse.jdt.annotation.NonNull;


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

    private HangingSign() {
        super(SignEntity.empty(), SignOrientation.North);
    }

    private HangingSign(final @NonNull IDataValue[] values) {
        super(SignEntity.empty(), validateDV(values, SignOrientation.class));
    }

    private HangingSign(final @NonNull IDataValue[] values, final GenericTileEntity entity) {
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
