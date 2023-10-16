package medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 54. Spiral Matrix
 * <p>
 * Total Accepted: 83346
 * Total Submissions: 340510
 * Difficulty: Medium
 * Contributors: Admin
 * <p>
 * Given a matrix of m x n elements (m rows, n columns), return all elements of the matrix in spiral order.
 * <p>
 * For example,
 * Given the following matrix:
 * <p>
 * [
 * [ 1, 2, 3 ],
 * [ 4, 5, 6 ],
 * [ 7, 8, 9 ]
 * ]
 * <p>
 * You should return [1,2,3,6,9,8,7,4,5].
 * <p>
 * <p>
 * Leetcode: https://leetcode.com/problems/spiral-matrix/
 * <p>
 * Created by xinwu on 1/2/17.
 */
public class m054_SpiralMatrix {
    public List<Integer> spiralOrder(int[][] matrix) {
        if (null == matrix || 0 == matrix.length)
            return new ArrayList<>();
        else
            return spiralOrder(matrix, 0, matrix.length - 1, 0, matrix[0].length - 1);
    }

    private List<Integer> spiralOrder(int[][] matrix, int startRow, int endRow, int startCol, int endCol) {
        List<Integer> result = new ArrayList<>();

        // empty matrix
        if (startRow > endRow || startCol > endCol)
            return result;

        int rowCount = endRow - startRow + 1;
        int colCount = endCol - startCol + 1;

        for (int i = startCol; i <= endCol; ++i)
            result.add(Integer.valueOf(matrix[startRow][i]));
        if (rowCount > 1) {
            for (int i = startRow + 1; i <= endRow; ++i)
                result.add(Integer.valueOf(matrix[i][endCol]));
            if (colCount > 1) {
                for (int i = endCol - 1; i >= startCol; --i)
                    result.add(Integer.valueOf(matrix[endRow][i]));
                for (int i = endRow - 1; i > startRow; --i)
                    result.add(Integer.valueOf(matrix[i][startCol]));
            }
        }

        result.addAll(spiralOrder(matrix, startRow + 1, endRow - 1, startCol + 1, endCol - 1));

        return result;
    }

    public static void main(String[] args) {
        m054_SpiralMatrix solution = new m054_SpiralMatrix();

        int[][] matrix = new int[][]{
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        List<Integer> order = solution.spiralOrder(matrix);
        System.out.println(Arrays.toString(order.toArray()));

        matrix = new int[][]{ {2,3}};
        System.out.println(Arrays.toString(solution.spiralOrder(matrix).toArray()));
    }

}
