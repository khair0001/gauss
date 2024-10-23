public class Hahahaha {
    private static final int N = 2; // Changed to 2 for compatibility with matrix size
    private static final int M = N + 1;
    private static double[][] matrix = new double[N][M];

    private static void matriks() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                System.out.printf("%7.2f", matrix[i][j]);
                if (j == M - 2) {
                    System.out.print(" | ");
                }
            }
            System.out.println();
        }
    }

    private static void tukarbaris(int from, int to) {
        double[] temp = matrix[from];
        matrix[from] = matrix[to];
        matrix[to] = temp;
    }

    private static int findMaxRow(int colom, int maxRowNow) {
        int maxRow = maxRowNow;
        for (int i = maxRowNow + 1; i < N; i++) {
            if (Math.abs(matrix[i][colom]) > Math.abs(matrix[maxRow][colom])) {
                maxRow = i;
            }
        }
        return maxRow;
    }

    private static void elimimasiGauss() {
        for (int i = 0; i < N - 1; i++) {
            int maxRow = findMaxRow(i, i);
            if (maxRow != i) {
                tukarbaris(i, maxRow);
            }

            for (int j = i + 1; j < N; j++) {
                double factor = matrix[j][i] / matrix[i][i];
                for (int k = i; k < M; k++) {
                    matrix[j][k] -= factor * matrix[i][k];
                }
            }
        }
    }

    private static void Hasil() {
        double[] x = new double[N];
        for (int i = N - 1; i >= 0; i--) {
            x[i] = matrix[i][M - 1];
            for (int j = i + 1; j < N; j++) {
                x[i] -= matrix[i][j] * x[j];
            }
            x[i] /= matrix[i][i];
        }
        for (int i = 0; i < N; i++) {
            System.out.printf("x%d = %.12f%n", i + 1, x[i]);
        }
    }

    public static void main(String[] args) {
        double[][] A = {
            {6, 9},
            {9, 31}
        };
        double[] b = {56, 154};

        // Copy values into the matrix
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M - 1; j++) {
                matrix[i][j] = A[i][j];
            }
            matrix[i][M - 1] = b[i];
        }

        System.out.println("\nMatriks Awal:");
        matriks();

        elimimasiGauss();

        System.out.println("\nMatriks Segitiga Atas:");
        matriks();

        System.out.println("\nNilai Xi:");
        Hasil();
    }
}