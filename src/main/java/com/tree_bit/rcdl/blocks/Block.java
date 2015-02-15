package com.tree_bit.rcdl.blocks;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Set;


/**
 * Minecraft Block
 */
public final class Block implements Comparable<Block> {

    private final BlockID block;
    private final BlockData data;

    private Block(final BlockID block, final BlockData data) {
        this.block = block;
        this.data = data;
    }

    /**
     * Returns the block data.
     *
     * @return Block data
     */
    public BlockData getData() {
        return this.data;
    }

    /**
     * Returns the block id.
     *
     * @return Block id
     */
    public BlockID getBlock() {
        return this.block;
    }

    /**
     * Mirrors this block at the given axis.
     *
     * @param plain Mirror at the given plain
     * @throws PlacementInvalidException Block placement after the mirroring
     *         would be invalid
     */
    public void mirror(final Set<Axis> plain) throws PlacementInvalidException {
        try {
            this.data.mirror(plain);
        } catch (final UnsupportedOperationException e) {
            throw new PlacementInvalidException(e);
        }
    }

    /**
     * Rotates the block at the given axis by the given amount of degree. (Axis
     * viewed from +infinity to -infinity/zero)
     *
     * @param axis Axis of rotation
     * @param degree Degree (only multiples of 90 are allowed)
     * @throws PlacementInvalidException Block placement after the rotation
     *         would be invalid
     */
    public final void rotate(final Axis axis, final int degree) throws PlacementInvalidException {
        try {
            this.data.rotate(axis, degree);
        } catch (final UnsupportedOperationException e) {
            throw new PlacementInvalidException(e);
        }
    }

    @Override
    public int compareTo(final Block o) {
        final Block b = checkNotNull(o);
        return b.block.getId() - this.block.getId();
    }
}
