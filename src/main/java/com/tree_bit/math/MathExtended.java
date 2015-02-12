package com.tree_bit.math;

/**
 * Extra mathematical functions.
 *
 * @author Sascha Sauermann
 */
public class MathExtended {

    /**
     * Mathematically correct modolo. (-1 mod 16 = 15)
     *
     * @param n <b>int</b> number
     * @param m <b>int</b> modolus
     * @return <b>int</b> n mod m
     */
    public static int mod(int n, int m) {
        return (n < 0) ? (m - (Math.abs(n) % m)) % m : (n % m);
    }

}
