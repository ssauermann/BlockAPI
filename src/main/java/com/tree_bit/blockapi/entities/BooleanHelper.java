/**
 * Copyright (c) 2016 The BlockAPI authors
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.tree_bit.blockapi.entities;

import java.util.function.Function;

/**
 * Helper class for conversions of booleans.
 */
final class BooleanHelper {

    private BooleanHelper() {}

    /**
     * Convert a boolean to a byte (0 or 1).
     *
     * @param b boolean value
     * @return 0 for false, 1 for true
     */
    public static byte toByte(final boolean b) {
        if (b) {
            return 1;
        }
        return 0;
    }

    /**
     * Convert a byte to a boolean.
     *
     * @param b byte value
     * @return false for 0, else true
     */
    public static boolean fromByte(final byte b) {
        if (b == 0) {
            return false;
        }
        return true;
    }

    /**
     * Converter from boolean to byte.
     */
    public static class ToByte implements Function<Boolean, Byte> {

        @Override
        public Byte apply(final Boolean t) {
            return toByte(t);
        }

    }
}
