package com.tree_bit.blockapi.data;

import java.util.Optional;
import java.util.function.Function;

/**
 * Defining methods a class representing Minecraft data values has to implement.
 *
 * @author Sascha Sauermann
 */
public interface IDataValue {

    /**
     * Returns the data value of this setting.
     *
     * @return Data value
     */
    int getDV();

    /**
     * Gets the data value object by the id.
     *
     * @param dv data value
     * @return data value object
     */
    Optional<? extends IDataValue> byDV(final int dv);

    /**
     * Returns an NONE instance for a IDataValue.
     *
     * @return NONE instance
     */
    public static IDataValue none() {
        return new IDataValue() {

            @Override
            public int getDV() {
                return 0;
            }

            @Override
            public Optional<? extends IDataValue> byDV(final int dv) {
                if (dv == 0) {
                    return Optional.of(this);
                }
                return Optional.empty();
            }

            @Override
            public boolean equals(final Object obj) {
                if (obj.getClass().equals(this.getClass())) {
                    return true;
                }
                return false;
            }

            @Override
            public int hashCode() {
                return 23;
            }
        };
    }

    /**
     * Converter to integer.
     */
    public static class ToInt implements Function<IDataValue, Integer> {

        @Override
        public Integer apply(final IDataValue t) {
            return t.getDV();
        }

    }
}
