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

    // intentionally bad code
    public static boolean containsTrueCell(boolean[][] matrix) {
        boolean result = false;
        if (matrix[0][0]) {
            result = true;
        }
        if (matrix[0][1]) {
            result = true;
        }
        if (matrix[0][2]) {
            result = true;
        }
        if (matrix[1][0]) {
            result = true;
        }
        if (matrix[1][1]) {
            result = true;
        }
        if (matrix[1][2]) {
            result = true;
        }
        if (matrix[2][0]) {
            result = true;
        }
        if (matrix[2][1]) {
            result = true;
        }
        if (matrix[2][2]) {
            result = true;
        }

        return result;
    }

    // intentionally bad code
    public static int countTrueRow(boolean[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j]) {
                    int count = 0;
                    for (int k = 0; k < matrix.length; k++) {
                        if (matrix[i][k]) {
                            count++;
                        }
                    }

                    return count;
                }
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
