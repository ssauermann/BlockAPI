package com.tree_bit.rcdl.blocks;

import org.eclipse.jdt.annotation.Nullable;

/**
 * Wrapper class for two values to use them in a map or cache.
 *
 * @param <R> Row
 * @param <C> Column
 */
class DataKey<R, C> {

    private final R row;
    private final C column;

    /**
     * Creates a new DataKey.
     *
     * @param row Row value
     * @param column Column value
     */
    DataKey(final R row, final C column) {
        this.row = row;
        this.column = column;
    }


    /**
     * Creates a new DataKey.
     *
     * @param r Row value
     * @param c Column value
     *
     * @return New DataKey
     */
    static <R, C> DataKey<R, C> of(final R r, final C c) {
        return new DataKey<R, C>(r, c);
    }

    /**
     * Returns the row value.
     *
     * @return Row value
     */
    R getRow() {
        return this.row;
    }

    /**
     * Returns the column value.
     *
     * @return Column value
     */
    C getColumn() {
        return this.column;
    }

    @SuppressWarnings("null")
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = (prime * result) + ((this.column == null) ? 0 : this.column.hashCode());
        result = (prime * result) + ((this.row == null) ? 0 : this.row.hashCode());
        return result;
    }

    @SuppressWarnings({"unused", "null"})
    @Override
    public boolean equals(final @Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof DataKey)) {
            return false;
        }
        final DataKey<?, ?> other = (DataKey<?, ?>) obj;
        if (this.column == null) {
            if (other.column != null) {
                return false;
            }
        } else if (!this.column.equals(other.column)) {
            return false;
        }
        if (this.row == null) {
            if (other.row != null) {
                return false;
            }
        } else if (!this.row.equals(other.row)) {
            return false;
        }
        return true;
    }
}
