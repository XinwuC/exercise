package medium;

import java.util.Arrays;

/**
 * 1690. Stone Game VII
 * https://leetcode.com/problems/stone-game-vii/
 * 
 * Alice and Bob take turns playing a game, with Alice starting first.
 * 
 * There are n stones arranged in a row. On each player's turn, they can remove
 * either the leftmost stone or the rightmost stone from the row and receive
 * points equal to the sum of the remaining stones' values in the row. The
 * winner is the one with the higher score when there are no stones left to
 * remove.
 * 
 * Bob found that he will always lose this game (poor Bob, he always loses), so
 * he decided to minimize the score's difference. Alice's goal is to maximize
 * the difference in the score.
 * 
 * Given an array of integers stones where stones[i] represents the value of the
 * ith stone from the left, return the difference in Alice and Bob's score if
 * they both play optimally.
 * 
 * Constraints:
 * 
 * n == stones.length
 * 2 <= n <= 1000
 * 1 <= stones[i] <= 1000
 */
public class m1690 {

    private int solve(int[] stones, int i, int j, int[][] dp, int sum) {
        if (i > j)
            return 0;
        if (dp[i][j] != 0)
            return dp[i][j];

        int left = (sum - stones[i]) - solve(stones, i + 1, j, dp, sum - stones[i]);
        int right = (sum - stones[j]) - solve(stones, i, j - 1, dp, sum - stones[j]);

        dp[i][j] = Math.max(left, right);
        return dp[i][j];
    }

    public int stoneGameVII(int[] stones) {
        int sum = 0;
        for (int s : stones)
            sum += s;
        return solve(stones, 0, stones.length - 1, new int[stones.length][stones.length], sum);
    }

    public static void test(int[] stones, int expected) {
        m1690 solution = new m1690();
        System.out.format("%s => %d, vs %d\n", Arrays.toString(stones), solution.stoneGameVII(stones), expected);
    }

    public static void main(String[] args) {
        test(new int[] { 481, 905, 202, 250, 371, 628, 92, 604, 836, 338, 676, 734 }, 3459);
        test(new int[] { 5, 3, 1, 4, 2 }, 6);
        test(new int[] { 7, 90, 5, 1, 100, 10, 10, 2 }, 122);
        test(new int[] { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 }, 8);
    }
}
