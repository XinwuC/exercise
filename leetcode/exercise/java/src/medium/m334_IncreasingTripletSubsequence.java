package medium;

import java.util.Arrays;

/**
 * 334. Increasing Triplet Subsequence
 * 
 * Total Accepted: 18935 Total Submissions: 53587 Difficulty: Medium
 * 
 * Given an unsorted array return whether an increasing subsequence of length 3
 * exists or not in the array.
 * 
 * Formally the function should:
 * 
 * Return true if there exists i, j, k such that arr[i] < arr[j] < arr[k] given
 * 0 ≤ i < j < k ≤ n-1 else return false.
 * 
 * Your algorithm should run in O(n) time complexity and O(1) space complexity.
 * 
 * Examples: Given [1, 2, 3, 4, 5], return true.
 * 
 * Given [5, 4, 3, 2, 1], return false.
 * 
 * LeetCode: https://leetcode.com/problems/increasing-triplet-subsequence/
 * 
 * @author xinwu
 *
 */
public class m334_IncreasingTripletSubsequence {

	public boolean increasingTriplet(int[] nums) {
		if (null == nums || 0 == nums.length)
			return false;

		int i = 0, j = -1, min = 0;

		for (int k = 1; k < nums.length; ++k) {
			if (-1 != j) {
				// check current minimal subsequence of 2
				if (nums[k] > nums[j]) {
					return true;
				}
				//
				if (nums[k] > nums[min]) {
					j = k;
					i = min;
				} else
					min = k;
			} else {
				if (nums[k] > nums[i])
					j = k;
				else if (nums[k] < nums[i]) {
					i = k;
					min = k;
				}
			}
		}

		return false;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		m334_IncreasingTripletSubsequence solution = new m334_IncreasingTripletSubsequence();

		int[] nums = { 3, 5, 4, 2, 6 };
		System.out.print(Arrays.toString(nums));
		System.out.println("\t" + solution.increasingTriplet(nums));

		nums = new int[] { 2, 5, 1, 4, 2 };
		System.out.print(Arrays.toString(nums));
		System.out.println("\t" + solution.increasingTriplet(nums));

		nums = new int[] { 1, 3, 2, 5, 4 };
		System.out.print(Arrays.toString(nums));
		System.out.println("\t" + solution.increasingTriplet(nums));

		nums = new int[] { 10, 12, 11, 3, 5, 8, 9, 10 };
		System.out.print(Arrays.toString(nums));
		System.out.println("\t" + solution.increasingTriplet(nums));

		nums = new int[] { 1, 1, 1, 1, 1, 1 };
		System.out.print(Arrays.toString(nums));
		System.out.println("\t" + solution.increasingTriplet(nums));
	}

}
