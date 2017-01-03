package medium;

import java.util.stream.IntStream;

/**
 * 268. Missing Number
 * <p>
 * Total Accepted: 85051
 * Total Submissions: 195750
 * Difficulty: Medium
 * Contributors: Admin
 * <p>
 * Given an array containing n distinct numbers taken from 0, 1, 2, ..., n, find the one that is missing from the array.
 * <p>
 * For example,
 * Given nums = [0, 1, 3] return 2.
 * <p>
 * Note:
 * Your algorithm should run in linear runtime complexity. Could you implement it using only constant extra space
 * complexity?
 *
 * Leetcode: https://leetcode.com/problems/missing-number/
 *
 * Created by xinwu on 1/2/17.
 */
public class m268_MissingNumber {

    public int missingNumber(int[] nums) {
        int n = nums.length;
        long expected = n*(1+n)/2;
        long sum = IntStream.of(nums).sum();
        return (int) (expected - sum);
    }

    public static void main(String[] args) {
        m268_MissingNumber solution = new m268_MissingNumber();

        System.out.println(solution.missingNumber(new int[]{3,1,0}));
        System.out.println(solution.missingNumber(new int[]{3,1,2,4}));
    }
}
