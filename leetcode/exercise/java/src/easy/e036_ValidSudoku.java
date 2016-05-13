package easy;

/**
 * 36. Valid Sudoku
 * 
 * Difficulty: Easy
 * 
 * Determine if a Sudoku is valid, according to:
 * http://sudoku.com.au/TheRules.aspx
 * 
 * The Sudoku board could be partially filled, where empty cells are filled with
 * the character '.'.
 * 
 * Note: A valid Sudoku board (partially filled) is not necessarily solvable.
 * Only the filled cells need to be validated.
 * 
 * @author xinwu
 *
 */
public class e036_ValidSudoku {

	public boolean isValidSudoku(char[][] board) {
		if (null == board || 9 != board.length || 9 != board[0].length)
			return false;

		int[] bitMask = { 1, 1 << 1, 1 << 2, 1 << 3, 1 << 4, 1 << 5, 1 << 6, 1 << 7, 1 << 8 };

		int[] rowMask = { 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		int[] colMask = { 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		int[][] boxMask = { { 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 0 } };

		for (int row = 0; row < 9; ++row) {
			if (9 != board[row].length)
				return false;
			for (int col = 0; col < 9; ++col) {
				if ('.' == board[row][col])
					continue;

				int num = board[row][col] - '0';
				if (num < 1 || num > 9)
					return false;

				// check row
				if (0 != (rowMask[row] & bitMask[num - 1]))
					return false;
				else
					rowMask[row] |= bitMask[num - 1];
				// check column
				if (0 != (colMask[col] & bitMask[num - 1]))
					return false;
				else
					colMask[col] |= bitMask[num - 1];
				// check subbox
				int boxRow = row / 3;
				int boxCol = col / 3;
				if (0 != (boxMask[boxRow][boxCol] & bitMask[num - 1]))
					return false;
				else
					boxMask[boxRow][boxCol] |= bitMask[num - 1];
			}
		}

		return true;
	}

	public static void main(String[] args) {
		char[][] board = { 
				".87654321".toCharArray(), 
				"2........".toCharArray(), 
				"3........".toCharArray(),
				"4........".toCharArray(), 
				"5........".toCharArray(), 
				"6........".toCharArray(),
				"7........".toCharArray(), 
				"8........".toCharArray(), 
				"9........".toCharArray() 
				};
		
		e036_ValidSudoku target = new e036_ValidSudoku();
		System.out.println(target.isValidSudoku(board));
	}

}
