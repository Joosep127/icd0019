package oo.hide;

public class Fibonacci {

    int[] values = {0, 1};
    int current = 0;

    public int nextValue() {
        int tempReturn = values[current];
        int tempSum = 0;

        for (int i : values) {
             tempSum += i;
        }

        values[current] = tempSum;
        current = (current + 1) % 2;

        return tempReturn;
    }
}
