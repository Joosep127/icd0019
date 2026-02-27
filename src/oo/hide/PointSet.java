package oo.hide;

public class PointSet {
    private int filled = 0;
    private Point[] points;

    public PointSet(int capacity) {
        points = new Point[capacity];
    }

    public PointSet() {
        this(0);
    }

    public void add(Point point) {
        if (this.contains(point)) {
            return;
        }
        if (filled < points.length) {
            points[filled] = point;
            filled += 1;
            return;
        }
        Point[] newPoints = new Point[points.length == 0 ? 1 : points.length * 2];
        for (int i = 0; i < filled; i++) {
            newPoints[i] = points[i];
        }

        points = newPoints;
        this.add(point);
    }

    public int size() {
        return points.length;
    }

    public boolean contains(Point point) {
        if (point == null) {
            return false;
        }
        for (int i = 0; i < filled; i++) {
            if (points[i] == null) {
                continue;
            }
            if (points[i].equals(point)) {
                return true;
            }
        }
        return false;
    }

    public PointSet subtract(PointSet other) {
        PointSet newSet = new PointSet();
        for (int i = 0; i < filled; i++) {
            if (!other.contains(points[i])) {
                newSet.add(points[i]);
            }
        }
        return newSet;
    }

    public PointSet intersect(PointSet other) {
        PointSet newSet = new PointSet();
        for (Point point : points) {
            if (other.contains(point)) {
                newSet.add(point);
            }
        }
        return newSet;
    }

    public void remove(Point point) {
        int foundIdx = -1;
        for (int i = 0; i < filled; i++) {
            if (points[i].equals(point)) {
                foundIdx = i;
                break;
            }
        }
        if (foundIdx == -1) {
            return;
        }

        for (int i = foundIdx; i < filled; i++) {
            points[i] = points[i+1];
        }

        filled -= 1;
    }

    @Override
    public int hashCode() {
        return 0; // no need to change this
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < filled; i++){
            if (points[i] == null) {
                sb.append("null");
            } else {
                sb.append(points[i].toString());
            }
            if (i == filled-1) {
                break;
            }
            sb.append(", ");
        }
        return sb.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof PointSet other)) {
            return false;
        }
        if (other.size() != this.size()) {
            return false;
        }
        for (Point point : points) {
            if (!other.contains(point)) {
                return false;
            }
        }
        return true;
    }
}
