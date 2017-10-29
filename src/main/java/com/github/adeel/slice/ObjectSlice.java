package com.github.adeel.slice;

public class ObjectSlice<T> implements Slice {
    private final T[] backing;
    private final IndexRange index;

    ObjectSlice(T[] source) {
        assert source != null;
        this.backing = source;
        this.index = IndexRange.range(source.length);
    }

    ObjectSlice(T[] source, int from) {
        assert source != null;
        this.backing = source;
        this.index = IndexRange.range(source.length, from);
    }

    ObjectSlice(T[] source, int from, int to) {
        assert source != null;
        this.backing = source;
        this.index = IndexRange.range(source.length, from, to);
    }

    @Override
    public ObjectSlice<T> slice() {
        return this;  // Since the object is immutable
    }

    @Override
    public ObjectSlice<T> slice(int from) {
        if (from == 0) return slice();

        return null;
    }

    @Override
    public ObjectSlice<T> slice(int from, int to) {
        return null;
    }

    @Override
    public ObjectSlice<T> from(int from) {
        return null;
    }

    @Override
    public ObjectSlice<T> from(int from, int length) {
        return null;
    }

    @Override
    public ObjectSlice<T> to(int to) {
        return null;
    }

    @Override
    public ObjectSlice<T> to(int to, int length) {
        return null;
    }

    @Override
    public Slice withLength(int newLength) {
        return null;
    }

    @Override
    public SliceIterator iterator() {
        return null;
    }
}
