/**
 * 
 */
package medium;

import java.util.Arrays;

/**
 * 
 * 221. Maximal Square
 * 
 * Total Accepted: 34469 Total Submissions: 140751 Difficulty: Medium
 * 
 * Given a 2D binary matrix filled with 0's and 1's, find the largest square
 * containing all 1's and return its area.
 * 
 * For example, given the following matrix:
 * 
 * <code>
 *  1 0 1 0 0
 *  1 0 1 1 1
 *  1 1 1 1 1 
 *  1 0 0 1 0
 * </code>
 * 
 * Return 4.
 *
 * Leetcode: https://leetcode.com/problems/maximal-square/
 * 
 * @author xinwu
 *
 */
public class m221_MaximalSquare {

	public int maximalSquare(char[][] matrix) {
		if (null == matrix || 0 == matrix.length || null == matrix[0] || 0 == matrix[0].length)
			return 0;

		int rowSize = matrix.length;
		int colSize = matrix[0].length;
		int maxSize = Math.min(rowSize, colSize);
		boolean[][][] flags = new boolean[rowSize][colSize][maxSize];

		int result = 0;
		// initialize n==1
		for (int i = 0; i < rowSize; ++i)
			for (int j = 0; j < colSize; ++j) {
				if ('1' == matrix[i][j]) {
					flags[i][j][0] = true;
					result = 1;
				} else {
					flags[i][j][0] = false;
				}
			}

		// matrix is all 0
		if (0 == result)
			return result;

		for (int k = 1; k < maxSize; ++k) {
			for (int i = 0; i + k < rowSize; ++i) {
				for (int j = 0; j + k < colSize; ++j) {
					if (flags[i][j][k - 1] && flags[i + 1][j][k - 1] && flags[i][j + 1][k - 1]
							&& flags[i + 1][j + 1][k - 1]) {
						flags[i][j][k] = true;
						result = k + 1;
					}
				}
			}
			// result does not increase, no need to try anymore
			if (result != k + 1)
				break;
		}

		return result * result;
	}

	private static void printMatrix(char[][] matrix) {
		for (char[] row : matrix)
			System.out.println(Arrays.toString(row));
	}

	public static void main(String[] args) {
		m221_MaximalSquare solution = new m221_MaximalSquare();

		char[][] matrix = new char[][] { "10100".toCharArray(), "10111".toCharArray(), "11111".toCharArray(),
				"10010".toCharArray() };
		printMatrix(matrix);
		System.out.println("largest square: " + solution.maximalSquare(matrix) + "\n");

		matrix = new char[][] { "11111".toCharArray(), "11111".toCharArray(), "11111".toCharArray(),
				"11111".toCharArray() };
		printMatrix(matrix);
		System.out.println("largest square: " + solution.maximalSquare(matrix) + "\n");

	}

}
