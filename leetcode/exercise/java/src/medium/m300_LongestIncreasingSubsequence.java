package medium;

import java.util.Arrays;

/**
 * 300. Longest Increasing Subsequence
 * 
 * Difficulty: Medium
 * 
 * Given an unsorted array of integers, find the length of longest increasing
 * subsequence.
 * 
 * For example, Given [10, 9, 2, 5, 3, 7, 101, 18], The longest increasing
 * subsequence is [2, 3, 7, 101], therefore the length is 4. Note that there may
 * be more than one LIS combination, it is only necessary for you to return the
 * length.x
 * 
 * Your algorithm should run in O(n2) complexity.
 * 
 * Follow up: Could you improve it to O(n log n) time complexity?
 * 
 * @author xinwu
 *
 */
public class m300_LongestIncreasingSubsequence {

	public int lengthOfLIS(int[] nums) {
		if (null == nums || 0 == nums.length)
			return 0;

		int[] maxLen = new int[nums.length];
		int[] minLast = new int[nums.length];
		int[] minSecondLast = new int[nums.length];

		maxLen[0] = 1;
		minLast[0] = nums[0];
		minSecondLast[0] = Integer.MIN_VALUE;
		for (int i = 1; i < nums.length; ++i) {
			if (minLast[i - 1] < nums[i]) {
				// length + 1
				maxLen[i] = maxLen[i - 1] + 1;
				minSecondLast[i] = minLast[i - 1];
				minLast[i] = nums[i];
			} else if (minLast[i - 1] > nums[i]) {
				maxLen[i] = maxLen[i - 1];
				if (minLast[i - 1] > nums[i] && minSecondLast[i - 1] < nums[i]) {
					minLast[i] = nums[i];
				} else {
					minLast[i] = minLast[i - 1];
				}
				minSecondLast[i] = minSecondLast[i - 1];
			} else {
				maxLen[i] = maxLen[i - 1];
				minLast[i] = minLast[i - 1];
				minSecondLast[i] = minSecondLast[i - 1];
			}
		}
		
		System.out.println("nums:\t" + Arrays.toString(nums));
		System.out.println("maxLen:\t" + Arrays.toString(maxLen));
		System.out.println("minLast:\t" + Arrays.toString(minLast));
		System.out.println("minSecondLast:\t" + Arrays.toString(minSecondLast));


		return maxLen[nums.length - 1];
	}

	public static void main(String[] args) {
		int[] nums = { 3, 5, 6, 2, 5, 4, 19, 5, 6, 7, 12 };

		m300_LongestIncreasingSubsequence target = new m300_LongestIncreasingSubsequence();
		System.out.println(target.lengthOfLIS(nums));
	}

}
