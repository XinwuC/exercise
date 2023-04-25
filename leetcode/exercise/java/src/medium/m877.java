package medium;

import java.util.Arrays;

/**
 * 877. Stone Game
 * https://leetcode.com/problems/stone-game/
 * 
 * Alice and Bob play a game with piles of stones. There are an even number of
 * piles arranged in a row, and each pile has a positive integer number of
 * stones piles[i].
 * 
 * The objective of the game is to end with the most stones. The total number of
 * stones across all the piles is odd, so there are no ties.
 * 
 * Alice and Bob take turns, with Alice starting first. Each turn, a player
 * takes the entire pile of stones either from the beginning or from the end of
 * the row. This continues until there are no more piles left, at which point
 * the person with the most stones wins.
 * 
 * Assuming Alice and Bob play optimally, return true if Alice wins the game, or
 * false if Bob wins.
 * 
 * Constraints:
 * 
 * 2 <= piles.length <= 500
 * piles.length is even.
 * 1 <= piles[i] <= 500
 * sum(piles[i]) is odd.
 * 
 */
public class m877 {
    private int stoneGame(int[] piles, int i, int j, int[][] dp) {
        if (i >= j)
            return 0;
        if (dp[i][j] != 0)
            return dp[i][j];

        if (j - i == 1) {
            dp[i][j] = Math.abs(piles[i] - piles[j]);
            return dp[i][j];
        }

        // alice leftmost, i -> bob leftmost, i+1 or rightmost j
        int bob = Math.min(stoneGame(piles, i + 2, j, dp) - piles[i + 1],
                stoneGame(piles, i + 1, j - 1, dp) - piles[j]);
        int alice = piles[i] + bob;
        // rightmost, j -> leftmost,i, i+1..j-1 or rightmsot j-1, i..j-2
        bob = Math.min(stoneGame(piles, i + 1, j - 1, dp) - piles[i], stoneGame(piles, i, j - 2, dp) - piles[j - 1]);
        alice = Math.max(alice, piles[j] + bob);

        dp[i][j] = alice;
        return alice;
    }

    private boolean solve(int[] piles, int i, int j, int alice, int bob, int[][] dp) {
        if (i >= j)
            return false;
        if (dp[i][j] != 0)
            return dp[i][j] > 0;

        if (j - i == 1) {
            dp[i][j] = Math.abs(piles[i] - piles[j]);
            return alice + dp[i][j] - bob > 0;
        }
        // l -> l
        boolean success = solve(piles, i + 2, j, alice + piles[i], bob + piles[i + 2], dp);
        if (!success) {
            // l - > r
            success = solve(piles, i + 1, j - 1, alice + piles[i], bob + piles[j], dp);
            if (!success) {
                // r->l
                success = solve(piles, i + 1, j - 1, alice + piles[j], bob + piles[i], dp);
                if (!success)
                    // r->r
                    success = solve(piles, i, j - 2, alice + piles[j], bob + piles[j - 1], dp);
            }
        }
        return success;
    }

    public boolean stoneGame(int[] piles) {
        int[][] dp = new int[piles.length][piles.length];
        // return stoneGame(piles, 0, piles.length - 1, dp) > 0;
        return solve(piles, 0, piles.length - 1, 0, 0, dp);
    }

    public static void test(int[] piles) {
        m877 solution = new m877();
        System.out.format("%s => %s\n", Arrays.toString(piles), solution.stoneGame(piles));
    }

    public static void main(String[] args) {
        test(new int[] { 5, 3, 4, 5 });
        test(new int[] { 3, 7, 2, 3 });
        test(new int[] { 7, 7, 12, 16, 41, 48, 41, 48, 11, 9, 34, 2, 44, 30, 27, 12, 11, 39, 31, 8, 23, 11, 47, 25, 15,
                23, 4, 17, 11, 50, 16, 50, 38, 34, 48, 27, 16, 24, 22, 48, 50, 10, 26, 27, 9, 43, 13, 42, 46, 24 });

    }
}
