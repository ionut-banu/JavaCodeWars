/**
 * Created by Ionut on Dec, 2019
 */
public class Matrix {

    public static int determinant(int[][] matrix) {
        int det = 0;
        if (matrix.length == 1) {
            return matrix[0][0];
        } else if (matrix.length == 2) {
            return matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0];
        } else if (matrix.length > 2) {
            for (int i = 0; i < matrix.length; i++) {
                    int s = i % 2 == 0 ? 1 : -1;
                    det += s * matrix[i][0] * determinant(getMinor(i, 0, matrix));

            }
        }
        return det;
    }

    public static int[][] getMinor(int row, int col, int[][] matrix) {
        int[][] minor = new int[matrix.length - 1][matrix.length - 1];
        int p = 0;
        for (int i = 0; i < matrix.length; i++) {
            if (i == row)
                continue;
            int q = 0;
            for (int j = 0; j < matrix.length; j++) {
                if (j == col)
                    continue;
                minor[p][q] = matrix[i][j];
                q++;
            }
            p++;
        }
        return minor;
    }
}
