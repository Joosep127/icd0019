package types;

import java.util.Arrays;
import java.util.Random;

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
        int highest_idx = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] != 0) {
                if (array[highest_idx] <= array[i]) {
                    highest_idx = i;
                }
            }
        }
        return (char) highest_idx;
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

        for (int row_idx = 0; row_idx < table.length; row_idx++) {
          for (int col_idx = 0; col_idx < table[0].length; col_idx++) {
            if (isIsolated(row_idx, col_idx)) {
              count++;
            }
          }
        } 
 
        return count;
    }

    public static boolean isIsolated(int row, int col) {
        boolean[][] table = getSampleTable();


        int max_row = table.length - 1;
        int max_col = table[0].length - 1;

        if ((row % max_row == 0) || (col % max_col == 0)) {
          return false;
        }

        for (int rel_row = -1; rel_row < 2; rel_row++) {
          for (int rel_col = -1; rel_col < 2; rel_col++) {
            if (rel_col == 0 && rel_row == 0) {
              continue;
            }
            if (!table[rel_row + row][rel_col + col]) {
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
