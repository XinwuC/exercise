/**
 * 
 */
package hard;

import java.util.Arrays;

/**
 * 33. Search in Rotated Sorted Array
 * 
 * Total Accepted: 110601 Total Submissions: 359407 Difficulty: Hard
 * 
 * Suppose a sorted array is rotated at some pivot unknown to you beforehand.
 * 
 * (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
 * 
 * You are given a target value to search. If found in the array return its
 * index, otherwise return -1.
 * 
 * You may assume no duplicate exists in the array.
 * 
 * @author xinwu
 *
 */
public class h033_SearchInRotatedSortedArray {

	public int search(int[] nums, int target) {
		if (null == nums || 0 == nums.length)
			return -1;

		int length = nums.length;
		int pivotIndex = 0;
		while (pivotIndex + 1 < length && nums[pivotIndex] < nums[pivotIndex + 1])
			++pivotIndex;

		// reset pivotIndex to 0 when the array is not rotated
		if (pivotIndex == length)
			pivotIndex = 0;
		else
			++pivotIndex;

		// binary search with pivoted index translation
		int start = 0;
		int end = length - 1;
		int mid = (start + end) / 2;
		while (start <= end) {
			int pivotedMid = pivotedIndex(length, pivotIndex, mid);
			if (target == nums[pivotedMid]) {
				return pivotedMid;
			} else if (target > nums[pivotedMid]) {
				start = mid + 1;
				mid = (start + end) / 2;
			} else {
				end = mid - 1;
				mid = (start + end) / 2;
			}
		}

		return -1;
	}

	private int pivotedIndex(int arrayLength, int pivotIndex, int index) {
		return (pivotIndex + index) % arrayLength;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		h033_SearchInRotatedSortedArray solution = new h033_SearchInRotatedSortedArray();

		int[] nums = new int[] { 4, 5, 6, 7, 0, 1, 2 };
		System.out.println(Arrays.toString(nums));
		System.out.println("Searching for 7: " + solution.search(nums, 7));
		System.out.println("Searching for 0: " + solution.search(nums, 0));
		System.out.println("Searching for 4: " + solution.search(nums, 4));
		System.out.println("Searching for 2: " + solution.search(nums, 2));
		System.out.println("Searching for -1: " + solution.search(nums, -1));
		System.out.println("Searching for 3: " + solution.search(nums, 3));
		System.out.println("Searching for 8: " + solution.search(nums, 8));

		nums = new int[] { 0, 1, 2, 4, 5, 6, 7 };
		System.out.println(Arrays.toString(nums));
		System.out.println("Searching for 7: " + solution.search(nums, 7));
		System.out.println("Searching for 0: " + solution.search(nums, 0));
		System.out.println("Searching for 4: " + solution.search(nums, 4));
		System.out.println("Searching for 2: " + solution.search(nums, 2));
		System.out.println("Searching for -1: " + solution.search(nums, -1));
		System.out.println("Searching for 3: " + solution.search(nums, 3));
		System.out.println("Searching for 8: " + solution.search(nums, 8));

		nums = new int[] { 7, 0, 1, 2, 4, 5, 6 };
		System.out.println(Arrays.toString(nums));
		System.out.println("Searching for 7: " + solution.search(nums, 7));
		System.out.println("Searching for 0: " + solution.search(nums, 0));
		System.out.println("Searching for 4: " + solution.search(nums, 4));
		System.out.println("Searching for 2: " + solution.search(nums, 2));
		System.out.println("Searching for -1: " + solution.search(nums, -1));
		System.out.println("Searching for 3: " + solution.search(nums, 3));
		System.out.println("Searching for 8: " + solution.search(nums, 8));

		nums = new int[] { 1, 2, 4, 5, 6, 7, 0 };
		System.out.println(Arrays.toString(nums));
		System.out.println("Searching for 7: " + solution.search(nums, 7));
		System.out.println("Searching for 0: " + solution.search(nums, 0));
		System.out.println("Searching for 4: " + solution.search(nums, 4));
		System.out.println("Searching for 2: " + solution.search(nums, 2));
		System.out.println("Searching for -1: " + solution.search(nums, -1));
		System.out.println("Searching for 3: " + solution.search(nums, 3));
		System.out.println("Searching for 8: " + solution.search(nums, 8));
	}

}
