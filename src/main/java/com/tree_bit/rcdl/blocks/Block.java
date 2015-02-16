package com.tree_bit.rcdl.blocks;

import static com.google.common.base.Preconditions.checkNotNull;

import com.google.common.base.Objects;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Iterables;
import com.google.common.collect.Table;

import java.lang.reflect.InvocationTargetException;
import java.security.InvalidParameterException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;


/**
 * Minecraft Block
 */
public final class Block implements Comparable<Block> {

    private final BlockID block;
    private final BlockData data;

    @SuppressWarnings("null")
    private static final Table<BlockID, BlockData, Block> instances = HashBasedTable.create();


    static {
        for (final BlockID id : BlockID.values()) {

            if (id == null) {
                throw new NullPointerException();
            }

            for (final BlockData data : getInstances(id)) {
                instances.put(id, data, new Block(id, data));
            }
        }
    }

    @SuppressWarnings("unchecked")
    private static Set<BlockData> getInstances(final BlockID id) {
        Set<BlockData> instances;
        try {
            final Object o = id.getDataClass().getDeclaredMethod("getInstances").invoke((Class<?>[]) null);

            instances = new HashSet<>((Collection<? extends BlockData>) o);

        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
            throw new IllegalStateException(e);
        }
        return instances;
    }

    private Block(final BlockID block, final BlockData data) {
        this.block = block;
        this.data = data;
    }

    /**
     * Gets an instance of a block with the given id and data.
     *
     * @param block Id of the block
     * @param data Data of the block
     * @return Instance of a block
     */
    public static Block getInstance(final BlockID block, final BlockData data) {
        if (!block.getDataClass().isInstance(data)) {
            throw new InvalidParameterException("BlockData [" + data + "] has to match the given BlockID [" + block + "]. \n(Use "
                    + block.getDataClass() + ")");
        }
        return instances.get(block, data);
    }

    /**
     * Gets an instance of a block with no special data specified. Use this for
     * blocks which have no extra data. If used for a block which needs extra
     * data the state/data of the block is unspecified.
     *
     * @param block Id of the block
     * @return Instance of a block
     */
    public static Block getInstance(final BlockID block) {
        return Iterables.get(instances.row(block).values(), 0);
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
     * Returns a new block instance that has the given data.
     *
     * @param data Data to set
     * @return Block with the given data
     */
    public Block setData(final BlockData data) {
        return getInstance(this.block, data);
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
     *
     * @return Block with new orientation
     *
     * @throws PlacementInvalidException Block placement after the mirroring
     *         would be invalid
     */
    public Block mirror(final Set<Axis> plain) {
        try {
            return getInstance(this.block, this.data.mirror(plain));
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
     *
     * @return Block with new orientation
     *
     * @throws PlacementInvalidException Block placement after the rotation
     *         would be invalid
     */
    public final Block rotate(final Axis axis, final int degree) {
        try {
            return getInstance(this.block, this.data.rotate(axis, degree));
        } catch (final UnsupportedOperationException e) {
            throw new PlacementInvalidException(e);
        }
    }

    @Override
    public int compareTo(final Block o) {
        final Block b = checkNotNull(o);
        return this.block.getId() - b.block.getId();
    }

    @Override
    @SuppressWarnings("null")
    public String toString() {
        return Objects.toStringHelper(this).addValue(this.block).addValue(this.data).toString();
    }
}
