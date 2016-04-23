package com.tree_bit.rcdl.blocks;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;

import java.util.Optional;

/**
 * Wrapper class for two or three values to use them in a map or cache.
 *
 * @param <R> Row
 * @param <C> Column
 * @param <L> Layer
 */
class DataKey<R, C, L> {

    @NonNull
    private final R row;
    @NonNull
    private final C column;
    @Nullable
    private final L layer;

    /**
     * Creates a new DataKey.
     *
     * @param row Row value
     * @param column Column value
     */
    DataKey(final @NonNull R row, final @NonNull C column) {
        this.row = row;
        this.column = column;
        this.layer = null;
    }

    /**
     * Creates a new DataKey.
     *
     * @param row Row value
     * @param column Column value
     * @param layer Layer value
     */
    DataKey(final @NonNull R row, final @NonNull C column, @Nullable final L layer) {
        this.row = row;
        this.column = column;
        this.layer = layer;
    }


    /**
     * Creates a new DataKey.
     *
     * @param r Row value
     * @param c Column value
     *
     * @return New DataKey
     */
    static <R, C> DataKey<R, C, ?> of(final @NonNull R r, final @NonNull C c) {
        return new DataKey<>(r, c);
    }

    /**
     * Creates a new DataKey.
     *
     * @param r Row value
     * @param c Column value
     * @param l Layer value
     *
     * @return New DataKey
     */
    static <R, C, L> DataKey<R, C, L> of(final @NonNull R r, final @NonNull C c, @Nullable final L l) {
        return new DataKey<>(r, c, l);
    }

    /**
     * Returns the row value.
     *
     * @return Row value
     */
    @NonNull
    R getRow() {
        return this.row;
    }

    /**
     * Returns the column value.
     *
     * @return Column value
     */
    @NonNull
    C getColumn() {
        return this.column;
    }

    /**
     * Returns the layer value. This value may not be present.
     *
     * @return Layer value
     */
    Optional<L> getLayer() {
        return Optional.ofNullable(this.layer);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.row, this.column, this.layer);
    }

    @Override
    public boolean equals(@Nullable final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof DataKey)) {
            return false;
        }
        final DataKey<?, ?, ?> other = (DataKey<?, ?, ?>) obj;
        return Objects.equal(this.row, other.row) && Objects.equal(this.column, other.column) && Objects.equal(this.layer, other.layer);
    }

    @Override
    @NonNull
    public String toString() {
        return MoreObjects.toStringHelper(this).add("Row", this.row).add("Column", this.column).add("Layer", this.layer).toString();
    }

}
