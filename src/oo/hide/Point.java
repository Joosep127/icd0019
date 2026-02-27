package oo.hide;

import java.util.Objects;

public class Point {

    public final int x;
    public final int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return String.format("(%d, %d)", x, y);
    }

    @Override
    public boolean equals(Object obj) {
        if (!obj instanceof Point other) {
            return false;
        }
        return (other.x == this.x && other.y == this.y);
    }

    @Override
    public int hashCode() {
        return 0; // no need to change this
    }
}
