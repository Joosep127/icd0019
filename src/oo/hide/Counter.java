package oo.hide;

public class Counter {
    private int start;
    private final int step;

    public Counter(int start, int step) {
        this.start = start;
        this.step = step;
    }

    public int nextValue() {
        start += step;
        return start - step;
    }
}
