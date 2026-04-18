package gol;

import java.util.*;

public class Game {

    private List<Point> points = new ArrayList<Point>();

    public void markAlive(int x, int y) {
        points.add(new Point(x, y));
    }

    public boolean isAlive(int x, int y) {
        for (Point point : points) {
            if (point.x() == x && point.y() == y) {
                return true;
            }
        }
        return false;
    }

    public void toggle(int x, int y) {
        for (Point point : points) {
            if (point.x() == x && point.y() == y) {
                points.remove(point);
                return;
            }
        }
        points.add(new Point(x, y));
    }

    public Integer getNeighbourCount(int x, int y) {
        int output = 0;
        for (int i = 0; i < 9; i++) {
            if (i == 4) {
                continue;
            }
            int c = x + 1-(i/3);
            int v = y + 1-(i%3);
            if (points.contains(new Point(c, v))) {
                output++;
            }
        }
        return output;
    }

    private void incrementPoint(Map<Point, Integer> map, Point point) {
        map.put(point, map.getOrDefault(point, 0) + 1);
    }

    private Point[] getNeighbours(Point point) {
        Point[] output = new Point[8];
        int con = 0;
        for (int i = 0; i < 9; i++) {
            if (i == 4) {
                con = 1;
                continue;
            }
            int x = point.x() + 1-(i/3);
            int y = point.y() + 1-(i%3);
            if (x < 0 || y < 0) {
                continue;
            }
            output[i - con] = new Point(x, y);
        }
        return output;
    }

    public void nextFrame() {
        List<Point> newPoints = new ArrayList<>();
        HashMap<Point, Integer> allPoints = new HashMap<>();

        for (Point point : points) {
            allPoints.put(point, allPoints.getOrDefault(point, 9) + 1);

            for (Point neighbour : getNeighbours(point)) {
                if (neighbour != null) {
                    incrementPoint(allPoints, neighbour);
                }
            }
        }

        for (Map.Entry<Point, Integer> point : allPoints.entrySet()) {
            if (nextState(point.getValue()/10 == 1, point.getValue()%10)) {
                newPoints.add(point.getKey());
            }
        }

        points = newPoints;
    }

    public void clear() {
        points = new ArrayList<Point>();
    }

    public boolean nextState(boolean isLiving, int neighborCount) {
        if (isLiving) {
            return neighborCount == 2 || neighborCount == 3;
        }
        return neighborCount == 3;
    }
}
