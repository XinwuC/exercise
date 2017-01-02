package medium;

/**
 * 62. Unique Paths
 * <p>
 * Total Accepted: 117769
 * Total Submissions: 301059
 * Difficulty: Medium
 * Contributors: Admin
 * <p>
 * A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).
 * <p>
 * The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right
 * corner of the grid (marked 'Finish' in the diagram below).
 * <p>
 * How many possible unique paths are there?
 * <p>
 * <p>
 * Above is a 3 x 7 grid. How many possible unique paths are there?
 * <p>
 * Note: m and n will be at most 100.
 * <p>
 * <p>
 * LeetCode: https://leetcode.com/problems/unique-paths/
 * <p>
 * Created by xinwu on 1/2/17.
 */
public class m062_UniquePaths {

    public int uniquePaths(int m, int n) {
        int[][] count = new int[m][n];
        count[m - 1][n - 1] = 1;

        for (int i = m - 1; i >= 0; --i) {
            for (int j = n - 1; j >= 0; --j) {
                if (0 == count[i][j]) {
                    int downCount = (i + 1 < m) ? count[i + 1][j] : 0;
                    int rightCount = (j + 1 < n) ? count[i][j + 1] : 0;
                    count[i][j] = downCount + rightCount;
                }
            }
        }

        return count[0][0];
    }

    public static void main(String[] args) {
        m062_UniquePaths solution = new m062_UniquePaths();
        System.out.println(solution.uniquePaths(1,1));
        System.out.println(solution.uniquePaths(1,2));
        System.out.println(solution.uniquePaths(2,1));
        System.out.println(solution.uniquePaths(2,2));
        System.out.println(solution.uniquePaths(3,7));
    }
}
