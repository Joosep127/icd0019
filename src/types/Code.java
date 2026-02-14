package types;

import java.util.Arrays;
import java.util.Random;

// This comment is to push again with a tag (: and this part is to push with a new tag again
// And now I'm doing it again, and this part is again to push it with tag

public class Code {

    public static void main(String[] args) {
        System.out.println(isolatedSquareCount()); // 11
    }

    public static int sum(int[] numbers) {
        int s = 0;
        for (int num : numbers) {
          s += num;
        }
        return s;
    }

    public static double average(int[] numbers) {
        int len = numbers.length;
        int s = sum(numbers);
        return (double) s / len;
    }

    public static Integer minimumElement(int[] integers) {
        if (integers.length == 0) {
          return null;
        }
        int min = integers[0];
        for (int num : integers) {
          min = min < num ? min : num;
        }
        return min;
    }

    public static String asString(int[] elements) {
        StringBuilder sb = new StringBuilder();
        for (int num : elements) {
          sb.append(num);
          sb.append(", ");
        }
        return sb.length() == 0 ? "" : sb.substring(0, sb.length() - 2);
    }

    public static Character mode(String input) {
        // Eeldus, et meil on ainul 128 erinevat sümbolit, sest ma ei taha importida Hashmappe
        if (input.length() == 0) {
          return null;
        }
        int[] array = new int[128];
        for (char c : input.toCharArray()) {
          array[c] += 1;
        }
        int highestIndex = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] != 0 && array[highestIndex] <= array[i]) {
                highestIndex = i;
            }
        }
        return (char) highestIndex;
    }

    public static String squareDigits(String s) {
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) {
          if (Character.isDigit(c)) {
            sb.append((c - '0') * (c - '0'));
          }
          else {
            sb.append(c);
          }
        }
        return sb.toString();
    }

    public static int isolatedSquareCount() {
        boolean[][] table = getSampleTable();

        printTable(table);

        int count = 0;

        for (int rowIdx = 0; rowIdx < table.length; rowIdx++) {
          for (int colIdx = 0; colIdx < table[0].length; colIdx++) {
            if (isIsolated(rowIdx, colIdx)) {
              count++;
            }
          }
        } 
 
        return count;
    }

    public static boolean isIsolated(int row, int col) {
        boolean[][] table = getSampleTable();


        int maxRow = table.length - 1;
        int maxCol = table[0].length - 1;

        if ((row % maxRow == 0) || (col % maxCol == 0)) {
          return false;
        }

        for (int relRow = -1; relRow < 2; relRow++) {
          for (int relCol = -1; relCol < 2; relCol++) {
            if (relCol == 0 && relRow == 0) {
              continue;
            }
            if (!table[relRow + row][relCol + col]) {
              return false;
            }
          }
        }

        return true;
    }

    private static void printTable(boolean[][] table) {
        System.out.println("");
        for (boolean[] row : table) {
            System.out.println(Arrays.toString(row));
        }
    }

    private static boolean[][] getSampleTable() {
        boolean[][] table = new boolean[10][10];

        Random r = new Random(5);
        for (boolean[] row : table) {
            for (int i = 0; i < row.length; i++) {
                row[i] = r.nextInt(5) < 2;
            }
        }

        return table;
    }
}
