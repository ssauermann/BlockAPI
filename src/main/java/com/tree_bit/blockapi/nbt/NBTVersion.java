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
package com.tree_bit.blockapi.nbt;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import com.tree_bit.blockapi.Version;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;


/**
 * Minecraft version in which this NBTTag is valid and will be added.
 * <p>
 * Both bounds are inclusive.
 */
@Documented
@Retention(RUNTIME)
@Target(TYPE)
public @interface NBTVersion {

    /**
     * Only if minecraft version is greater (or equal) than this value the tag
     * will be added.
     *
     * <p>
     * <i>Default: {@link Version#EVERY}</i>
     *
     * @return Minimum version
     */
    Version min() default Version.EVERY;

    /**
     * Only if minecraft version is less (or equal) than this value the tag will
     * be added.
     * <p>
     * <i>Default: {@link Version#LATEST}</i>
     *
     * @return Maximum version
     */
    Version max() default Version.LATEST;
}
