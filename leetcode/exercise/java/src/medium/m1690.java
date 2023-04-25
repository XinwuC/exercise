package medium;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

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
    // private int getSum(int[] stones, int[][] sums, int i, int j) {
    // if (j >= stones.length || i >= stones.length || i < 0 || j < 0)
    // return 0;
    // if (i <= j && sums[i][j] == 0)
    // sums[i][j] = stones[i] + getSum(stones, sums, i + 1, j);
    // return sums[i][j];
    // }

    private int solve(int[] stones, int i, int j, int[][] dp, int sum) {
        if (i > j)
            return 0;
        if (dp[i][j] != 0)
            return dp[i][j];

        // if (j - i == 1) {
        // dp[i][j] = Math.max(stones[i], stones[j]);
        // return dp[i][j];
        // }
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

    // private int solveVII(int[] stones, int[][] sums, int m, int n,
    // Queue<Integer> alice, Queue<Integer> bob) {
    // int i = m;
    // int j = n;
    // if (i == j) {
    // alice.add(i);
    // return 0;
    // }

    // int alice_score = 0;
    // int bob_score = 0;

    // // alice's turn
    // if (stones[i] < stones[j]) {
    // alice.add(i);
    // alice_score = getSum(stones, sums, ++i, j);
    // } else {
    // alice.add(j);
    // alice_score = getSum(stones, sums, i, --j);
    // }
    // if (i == j) {
    // bob.add(i);
    // return alice_score;
    // }

    // // bob's turn
    // Queue<Integer> left_alice = new LinkedList<>();
    // Queue<Integer> left_bob = new LinkedList<>();
    // left_bob.add(i);
    // int left_diff = solveVII(stones, sums, i + 1, j, left_alice, left_bob);
    // int left_sum = getSum(stones, sums, i + 1, j);

    // Queue<Integer> right_alice = new LinkedList<>();
    // Queue<Integer> right_bob = new LinkedList<>();
    // right_bob.add(j);
    // int right_diff = solveVII(stones, sums, i, j - 1, right_alice,
    // right_bob);
    // int right_sum = getSum(stones, sums, i, j - 1);
    // int diff = 0;
    // if (left_diff - left_sum < right_diff - right_sum) {
    // alice.addAll(left_alice);
    // bob.addAll(left_bob);
    // bob_score = left_sum;
    // diff = left_diff + alice_score - bob_score;
    // } else {
    // alice.addAll(right_alice);
    // bob.addAll(right_bob);
    // bob_score = right_sum;
    // diff = right_diff + alice_score - bob_score;
    // }

    // // pop alice
    // return diff;
    // }

    // public int solveVII(int[] stones) {
    // int[][] sums = new int[stones.length][stones.length];
    // for (int i = 0; i < stones.length; ++i) {
    // sums[0][stones.length - 1] += stones[i];
    // sums[i][i] = stones[i];
    // }

    // // debug
    // alice = new LinkedList<>();
    // bob = new LinkedList<>();
    // int result = solveVII(stones, sums, 0, stones.length - 1, alice, bob);
    // return result;
    // }

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
