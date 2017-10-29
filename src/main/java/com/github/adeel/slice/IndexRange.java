package com.github.adeel.slice;

class IndexRange {
    private int start;
    private int length;
    private boolean forwards;

    private static final IndexRange EMPTY_RANGE = new IndexRange(0, 0, true);

    // Prevent other from creating an instance
    private IndexRange() {}

    private IndexRange(int start, int length, boolean forwards) {
        this.start = start;
        this.length = length;
        this.forwards = forwards;
    }

    static IndexRange range(int length) {
        return range(length, 0);
    }

    static IndexRange range(int length, int from) {
        return range(length, from, length - 1);
    }

    static IndexRange range(int length, int from, int to) {
        assert length >= 0;

        if (length == 0) return EMPTY_RANGE;

        from = normalizeFrom(length, from);
        to = normalizeTo(length, to);

        if (from == to) return EMPTY_RANGE;

        IndexRange range = new IndexRange();
        if ( from < to ) {
            range.start = from;
            range.length = to - from;
            range.forwards = true;
        } else {
            range.start = to;
            range.length = from - to;
            range.forwards = false;
        }

        return range;
    }

    private static int normalizeFrom(int length, int from) {
        if (from < -length ) from = 0;
        if (from > length -1) from = length - 1;
        if (from < 0) from = length - from;
        return from;
    }

    private static int normalizeTo(int length, int to) {
        if (to < -length) to = 0;
        if (to > length -1 ) to = length -1;
        if (to < 0) to = length - to;
        return to;
    }

    IndexRange subRangeFrom(int from) {
        from = normalizeFrom(this.length, from);
        if (from == 0) return this;
        if (from == this.length - 1) return EMPTY_RANGE;
        IndexRange range = new IndexRange();
        range.start = this.start + from;
        range.length = this.length - from;
        range.forwards = true;
        return range;
    }

    public int start() {
        return start;
    }

    public int length() {
        return length;
    }

    public boolean forwards() {
        return forwards;
    }
}
