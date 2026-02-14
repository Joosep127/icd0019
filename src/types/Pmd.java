package types;

public class Pmd {

    public static void main(String[] args) {
        boolean[][] table = getSampleTable();

        printTable(table);

        System.out.println(containsTrueCell(table));
        System.out.println(countTrueRow(table));
    }

    private static void printTable(boolean[][] table) {
        for (boolean[] row : table) {
            for (boolean element : row) {
                System.out.print(element ? "X" : "O");
            }
            System.out.println();
        }
    }

    public static boolean containsTrueCell(boolean[][] matrix) {
        for (int it = 0; it < 9; it++) {
          if (matrix[it/3][it%3]) {
            return true;
          }
        }
        return false;
    }

    // intentionally bad code
    public static int countTrueRow(boolean[][] matrix) {
        int count = 0;
        int rows = matrix.length;
        int cols = matrix[0].length;

        for (int i = 0; i < (rows * cols); i++) {
          if (count != 0 && rows%i == 0) {
            return count;
          }
          if (matrix[rows/i][rows%i]) {
            count++;
          }
        }
        return -1;
    }

    private static boolean[][] getSampleTable() {
        boolean[][] matrix = new boolean[3][3];

        matrix[2][1] = true;
        matrix[2][2] = true;

        return matrix;
    }

}
