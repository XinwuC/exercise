package medium;

import java.util.concurrent.TimeUnit;

/**
 * 240. Search a 2D Matrix II
 * 
 * Accepted: 36006 Total Submissions: 104307
 * 
 * Difficulty: Medium
 * 
 * Write an efficient algorithm that searches for a value in an m x n matrix.
 * This matrix has the following properties: - Integers in each row are sorted
 * in ascending from left to right. Integers in - each column are sorted in
 * ascending from top to bottom. For example,
 * 
 * Consider the following matrix:
 * 
 * [ [1, 4, 7, 11, 15], [2, 5, 8, 12, 19], [3, 6, 9, 16, 22], [10, 13, 14, 17,
 * 24], [18, 21, 23, 26, 30] ]
 * 
 * Given target = 5, return true.
 * 
 * Given target = 20, return false.
 * 
 */
public class m240_MetricsSearch {

	public boolean searchMatrix(int[][] matrix, int target) {
		if (null == matrix || 0 == matrix.length)
			return false;

		return searchMatrix(matrix, target, 0, matrix.length - 1, 0, matrix[0].length - 1);
	}

	private boolean searchMatrix(int[][] matrix, int target, int startRow, int endRow, int startCol, int endCol) {
		if (null == matrix || startRow<0 || startCol<0 || 
				endRow < startRow || endCol < startCol || 
				endRow >= matrix.length || endCol >= matrix[0].length) return false;
		// 1x1
		else if (endRow == startRow && endCol == startCol) return matrix[startRow][startCol] == target;
		// 1x2
		else if (endRow == startRow && endCol == startCol+1) return matrix[startRow][startCol] == target || matrix[startRow][endCol] == target;
		// 2x1 
		else if (endRow == startRow+1 && endCol == startCol) return matrix[startRow][startCol] == target || matrix[endRow][startCol] == target;
		// 2x2
		else if (startRow + 1 == endRow && startCol + 1 == endCol)
			return matrix[startRow][startCol] == target ? true
					: matrix[startRow + 1][startCol] == target ? true
							: matrix[startRow][startCol + 1] == target ? true
									: matrix[startRow + 1][startCol + 1] == target ? true : false;

		// large matrix
		int midRow = startRow + (endRow - startRow) / 2;
		int midCol = startCol + (endCol - startCol) / 2;
		int median = matrix[midRow][midCol];
		boolean found = false;

		if (median == target)
			return true;
		else if (target > median) {
			found = searchMatrix(matrix, target, midRow, endRow, midCol, endCol);
		} else
			found = searchMatrix(matrix, target, startRow, midRow, startCol, midCol);
		
		return found ? true : searchMatrix(matrix, target, midRow+1, endRow, startCol, midCol-1) ||
				searchMatrix(matrix, target, startRow, midRow-1, midCol+1, endCol);
	}

	public static void main(String[] args) {
		int[][] matrix = new int[][] { {-1}, {-1}};
//			{ 1, 4, 7, 11, 15, 17 }, 
//			{ 2, 5, 8, 12, 19, 23 }, 
//			{ 3, 6, 9, 16, 22, 34 },
//			{ 10, 13, 14, 17, 24, 47 }, 
//			{ 18, 21, 23, 26, 30, 58 }, 
//		};

		m240_MetricsSearch search = new m240_MetricsSearch();

		int target = 34;
		long start = System.currentTimeMillis();
		boolean result = search.searchMatrix(matrix, target);
		long end = System.currentTimeMillis();
		System.out.println(
				"Search " + target + " is " + result + "\t - " + TimeUnit.MILLISECONDS.toSeconds(end - start) + "ms");

		target = 3;
		start = System.currentTimeMillis();
		result = search.searchMatrix(matrix, target);
		end = System.currentTimeMillis();
		System.out.println(
				"Search " + target + " is " + result + "\t - " + TimeUnit.MILLISECONDS.toSeconds(end - start) + "ms");
		
		System.out.println("Search 58 is " + search.searchMatrix(matrix, 58));
		System.out.println("Search 17 is " + search.searchMatrix(matrix, 17));
		System.out.println("Search 18 is " + search.searchMatrix(matrix, 18));
		System.out.println("Search 1 is " + search.searchMatrix(matrix, 1));
		System.out.println("Search 3 is " + search.searchMatrix(matrix, 3));
		
		System.out.println("Search -1 is " + search.searchMatrix(matrix, -1));
		System.out.println("Search 20 is " + search.searchMatrix(matrix, 20));
		System.out.println("Search 100 is " + search.searchMatrix(matrix, 100));




	}

}
