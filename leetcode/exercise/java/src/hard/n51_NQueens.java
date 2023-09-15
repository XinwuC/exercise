package hard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

import basic.Utils;

/**
 * https://leetcode.com/problems/n-queens/
 * 
 * The n-queens puzzle is the problem of placing n queens on an n x n chessboard
 * such that no two queens attack each other.
 * 
 * Given an integer n, return all distinct solutions to the n-queens puzzle. You
 * may return the answer in any order.
 * 
 * Each solution contains a distinct board configuration of the n-queens'
 * placement, where 'Q' and '.' both indicate a queen and an empty space,
 * respectively.
 * 
 * Example 1:
 * 
 * Input: n = 4
 * Output: [[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]]
 * Explanation: There exist two distinct solutions to the 4-queens puzzle as
 * shown above
 * Example 2:
 * 
 * Input: n = 1
 * Output: [["Q"]]
 * 
 * Constraints:
 * 1 <= n <= 9
 */
public class n51_NQueens {
    private String queenInRow(int n, int column) {
        char[] c = new char[n];
        Arrays.fill(c, '.');
        c[column] = 'Q';
        return new String(c);
    }

    /**
     * try to put a queue on row
     * 
     * @param chess nxn chess, 0 - empty, 1- queen, 2-attack by exisitng queens
     * @param row   current row to try put queen
     * @return column to put queen. -1: cannot put the queen
     */
    private void tryPut(int row, int[][] chess, List<List<String>> result, List<String> current) {
        for (int column = 0; column < chess.length; ++column) {
            assert (current.size() == row);
            if (chess[row][column] == 0) {
                // put queen on [row, i]
                current.add(queenInRow(chess.length, column));
                if (row == chess.length - 1) {
                    // the last row, add solution to result
                    result.add(new ArrayList<String>(current));
                    current.remove(current.size() - 1);
                    return;
                } else {
                    // not the last row, try put in remaining rows
                    chess[row][column] = 1;
                    mask(chess, row, column, false);
                    tryPut(row + 1, chess, result, current);
                    // reserve the result and try next column
                    mask(chess, row, column, true);
                    chess[row][column] = 0;
                    current.remove(current.size() - 1);
                }
            }
        }
    }

    /**
     * Mask the chess with attack positions
     * 
     * @param chess
     * @param row
     * @param column
     * @return
     */
    private void mask(int[][] chess, int row, int column, boolean reverse) {
        int target = reverse ? -2 : 2;
        for (int i = 0; i < chess.length; ++i) {
            if (chess[row][i] != 1)
                chess[row][i] += target;
            if (chess[i][column] != 1)
                chess[i][column] += target;
            if (row - i - 1 >= 0 && column - i - 1 >= 0 && chess[row - i - 1][column - i - 1] != 1)
                chess[row - i - 1][column - i - 1] += target;
            if (row - i - 1 >= 0 && column + i + 1 < chess.length && chess[row - i - 1][column + i + 1] != 1)
                chess[row - i - 1][column + i + 1] += target;
            if (row + i + 1 < chess.length && column + i + 1 < chess.length && chess[row + i + 1][column + i + 1] != 1)
                chess[row + i + 1][column + i + 1] += target;
            if (row + i + 1 < chess.length && column - i - 1 >= 0 && chess[row + i + 1][column - i - 1] != 1)
                chess[row + i + 1][column - i - 1] += target;
        }
    }

    public List<List<String>> solveNQueens(int n) {
        List<List<String>> result = new ArrayList<List<String>>();

        int[][] chess = new int[n][n];
        List<String> current = new ArrayList<String>();
        tryPut(0, chess, result, current);
        return result;
    }

    public static void test(int n, String[][] expected) {
        n51_NQueens queen = new n51_NQueens();
        List<List<String>> output = queen.solveNQueens(n);
        // String[][] os = Utils.twoDListToArray(output);
        // boolean pass = Arrays.deepEquals(output.toArray(), expected);
        // System.out.format("%s: n=%d, %s\n", pass, n, Arrays.deepToString(os));
    }

    public static void main(String[] args) {
        test(1, null);
        test(4, null);
    }
}
