package com.github.adeel.slice;

public interface Slice {
    /**
     * No effect.
     * @return Returned slice has the same range as the original.
     */
    Slice slice();

    /**
     * Get a new slice starting at the 'from' index till the end of the previous slice. The
     * index can be positive or negative. If positive it is treated as the a normal array index,
     * if negative it is assumed to be counted backwards from the last element index.
     *
     * E.g.
     * Slice A = { 12, 23,  18,  6,  0,  99, 52}.  It's indicies are
     *              0   1    2   3   4    5   6
     *             -7  -6   -5  -4  -3   -2  -1
     *
     * Thus,
     * A.slice(0)   => {12, 23, 18, 6, 0, 99, 52}
     * A.slice(3)   => {6, 0, 99, 52}
     * A.slice(-3)  => {0, 99, 52}
     * A.slice(22)  => {}  // Empty Slice
     * A.slice(-12) => {12, 23, 18, 6, 0, 99, 52} // Index capped at both ends.
     *
     * @param from Index from which the get the new slice. The index is inclusive.
     * @return A new slice starting from the given index.
     */
    Slice slice(int from);

    /**
     * Get a new slice starting at the 'from' index till the 'to' index'. The indicies can be
     * positive or negative. If positive it is treated as the a normal array index, if negative
     * it is assumed to be counted backwards from the last element index. Both the indicies are
     * inclusive. If 'from' lies positionally after 'to' then the slice order reverses.
     *
     * E.g.
     * Slice A = { 12, 23,  18,  6,  0,  99, 52}.  It's indicies are
     *              0   1    2   3   4    5   6
     *             -7  -6   -5  -4  -3   -2  -1
     *
     * Thus,
     * A.slice(0, 6)     => {12, 23, 18, 6, 0, 99, 52}
     * A.slice(3, 5)     => {6, 0, 99}
     * A.slice(-5, 5)    => {18, 6, 0, 99}
     * A.slice(4, -2)    => {0, 99, 52}
     * A.slice(-9, 1)    => {12, 23}
     * A.slice(66, 99)   => {}
     * A.slice(-33, -23) => {}
     * A.slice(4, 1)     => {0, 6, 18, 23}
     * A.slice(-3, -6)   => {0, 6, 18, 23}
     *
     * @param from Index from where to start the slice
     * @param to Index where to end the slice.
     * @return A new slice starting at 'to' and ending at 'from'.
     */
    Slice slice(int from, int to);

    /**
     * Same behaviour as {@link #slice(int)}.
     * @param from {@link #slice(int)}
     * @return {@link #slice(int)}
     */
    Slice from(int from);

    /**
     * Similar behaviour as {@link #slice(int, int)}.
     * Equivalent to invoking Slice.slice(from, from + length).
     *
     * @param from {@see #slice(int, int)}
     * @param length Can be negative
     * @return {@see #slice(int, int)}
     */
    Slice from(int from, int length);

    /**
     * Similar behaviour as {@link #slice(int, int)}
     * Equivalent to invoking Slice.slice(0, to).
     *
     * @param to {@see #slice(int, int)}
     * @return {@see #slice(int, int)}
     */
    Slice to(int to);

    /**
     * Similar in behaviour as {@link #slice(int, int)}.
     * Equivalent to invoking Slice.slice(to - length, to).
     *
     * @param to {@see #slice(int, int)}
     * @param length {@see #slice(int, int)} Can be negative.
     * @return {@see #slice(int, int)}
     */
    Slice to(int to, int length);

    /**
     * Similar in behaviour as {@link #from(int, int)}
     * Equivalent to invoking Slice.from(0, length)
     *
     * @param length The length of the new Slice.
     * @return  {@see #from(int, int)}
     */
    Slice withLength(int length);

    /**
     * @return An iterator over the Slice.
     */
    SliceIterator iterator();

    static <T> ObjectSlice<T> slice(T[] source) {
        return new ObjectSlice<T>(source);
    }

    static <T> ObjectSlice<T> slice(T[] source, int from) {
        return new ObjectSlice<T>(source, from);
    }

    static <T> ObjectSlice<T> slice(T[] source, int from, int to) {
        return new ObjectSlice<T>(source, from, to);
    }

}
