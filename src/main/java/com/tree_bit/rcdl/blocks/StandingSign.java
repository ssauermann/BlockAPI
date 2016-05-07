package com.tree_bit.rcdl.blocks;

import com.tree_bit.blockapi.data.IDataValue;
import com.tree_bit.blockapi.entities.FormatText;
import com.tree_bit.blockapi.entities.SignEntity;
import com.tree_bit.blockapi.entities.GenericTileEntity;
import com.tree_bit.rcdl.blocks.dv.Orientation16;

import org.eclipse.jdt.annotation.NonNull;

/**
 * Data values of a 'Hanging Sign' block.
 *
 * <p>
 * Data enum: {@link Orientation16}, {@link FormatText}
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
public class StandingSign extends BlockData {

    private StandingSign() {
        super(SignEntity.empty(), Orientation16.N);
    }

    private StandingSign(final @NonNull IDataValue[] values) {
        super(SignEntity.empty(), validateDV(values, Orientation16.class));
    }

    private StandingSign(final @NonNull IDataValue[] values, final GenericTileEntity entity) {
        super(entity, validateDV(values, Orientation16.class));
    }


    /**
     * Returns an instance of the 'StandingSign' data with a default orientation
     * (North) and no text.
     *
     * @return Instance of a StandingSign
     */
    public static StandingSign getInstance() {
        return BlockDataFactory.getDefaultInstance(StandingSign.class);
    }

    /**
     * Returns an instance of the 'StandingSign' data with the given orientation
     * and no text.
     *
     * @param orientation Orientation
     * @return Instance of a StandingSign
     */
    public static StandingSign getInstance(final Orientation16 orientation) {
        return getInstance(orientation, SignEntity.empty());
    }

    /**
     * Returns an instance of the 'StandingSign' data with the given orientation
     * and text.
     *
     * @param orientation Orientation
     * @param entity Sign tile entity
     * @return Instance of a StandingSign
     *
     * @throws IllegalArgumentException if text has {@literal length>4}
     */
    public static StandingSign getInstance(final Orientation16 orientation, final SignEntity entity) {
        return BlockDataFactory.getInstance(StandingSign.class, entity, orientation);
    }
}
