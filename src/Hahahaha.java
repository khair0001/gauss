import java.util.Random;

public class Hahahaha {
    private static final int N = 10;
    private static final int M = 11;
    private static double[][] matrix = new double[N][M];
    private static Random random = new Random();

    private static double randomMatrix() {
        return random.nextInt(9) + 1; // Generates numbers 1-9
    }

    private static void printMatrix(String title) {
        System.out.println("\n" + title);
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                System.out.printf("%7.2f", matrix[i][j]);
                if (j == 9) {
                    System.out.print(" | ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    private static void swapRows(int from, int to) {
        double[] temp = matrix[from];
        matrix[from] = matrix[to];
        matrix[to] = temp;
    }

    private static int findMaxRow(int col, int startRow) {
        int maxRow = startRow;
        for (int i = startRow + 1; i < N; i++) {
            if (Math.abs(matrix[i][col]) > Math.abs(matrix[maxRow][col])) {
                maxRow = i;
            }
        }
        return maxRow;
    }

    private static void gaussianElimination() {
        for (int i = 0; i < N - 1; i++) {
            int maxRow = findMaxRow(i, i);
            if (maxRow != i) {
                swapRows(i, maxRow);
                printMatrix("After row swap (step " + (i + 1) + ")");
            }

            for (int j = i + 1; j < N; j++) {
                double factor = matrix[j][i] / matrix[i][i];
                for (int k = i; k < M; k++) {
                    matrix[j][k] -= factor * matrix[i][k];
                }
            }
            printMatrix("After elimination (step " + (i + 1) + ")");
        }
    }

    private static void backSubstitution() {
        double[] x = new double[N];
        for (int i = N - 1; i >= 0; i--) {
            x[i] = matrix[i][M - 1];
            for (int j = i + 1; j < N; j++) {
                x[i] -= matrix[i][j] * x[j];
            }
            x[i] /= matrix[i][i];
            
            // Update the last column of the matrix with the solved variable
            matrix[i][M - 1] = x[i];
            
            printMatrix("After solving for x" + (i + 1));
        }
    }

    private static void printResult() {
        System.out.println("Final Result:");
        for (int i = 0; i < N; i++) {
            System.out.printf("x%d = %.5f%n", i + 1, matrix[i][M - 1]);
        }
    }

    public static void main(String[] args) {
        double[] b = {1, 0, 0, 0, 1, 0, 0, 0, 0, -1};
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M - 1; j++) {
                matrix[i][j] = randomMatrix();
            }
            matrix[i][M - 1] = b[i];
        }

        printMatrix("Original Matrix");

        gaussianElimination();

        printMatrix("Matrix after Gaussian Elimination");

        backSubstitution();

        printResult();
    }
}