package medium;

import java.util.Arrays;

/**
 * 162. Find Peak Element
 * https://leetcode.com/problems/find-peak-element/description/
 * 
 * A peak element is an element that is strictly greater than its neighbors.
 * 
 * Given a 0-indexed integer array nums, find a peak element, and return its
 * index. If the array contains multiple peaks, return the index to any of the
 * peaks.
 * 
 * You may imagine that nums[-1] = nums[n] = -∞. In other words, an element is
 * always considered to be strictly greater than a neighbor that is outside the
 * array.
 * 
 * You must write an algorithm that runs in O(log n) time.
 * 
 * 
 * 
 * Example 1:
 * 
 * Input: nums = [1,2,3,1]
 * Output: 2
 * Explanation: 3 is a peak element and your function should return the index
 * number 2.
 * Example 2:
 * 
 * Input: nums = [1,2,1,3,5,6,4]
 * Output: 5
 * Explanation: Your function can return either index number 1 where the peak
 * element is 2, or index number 5 where the peak element is 6.
 * 
 * 
 * Constraints:
 * 
 * 1 <= nums.length <= 1000
 * -231 <= nums[i] <= 231 - 1
 * nums[i] != nums[i + 1] for all valid i.
 */
class m0162_FindPeakElement {
    private boolean isPeak(int[] nums, int peak) {
        if (nums.length == 1)
            return peak == 0;
        else if (peak == 0)
            return nums[peak] > nums[peak + 1];
        else if (peak == nums.length - 1)
            return nums[peak] > nums[peak - 1];
        else
            return nums[peak] > nums[peak + 1] && nums[peak] > nums[peak - 1];
    }

    private int searchPeak(int[] nums, int start, int end) {
        if (start >= end)
            return start;

        int mid = (start + end) / 2;
        if (isPeak(nums, mid))
            return mid;
        else if (nums[mid + 1] > nums[mid])
            return searchPeak(nums, mid + 1, end);
        else
            return searchPeak(nums, start, mid - 1);
    }

    public int findPeakElement(int[] nums) {
        return searchPeak(nums, 0, nums.length - 1);
    }

    private static void test(int[] nums) {
        m0162_FindPeakElement solution = new m0162_FindPeakElement();
        int peak = solution.findPeakElement(nums);
        System.out.format("%s: %s => %d, %d\n", solution.isPeak(nums, peak) ? "Pass" : "Fail",
                Arrays.toString(nums), peak, nums[peak]);
    }

    public static void main(String[] args) {
        test(new int[] { 1 });
        test(new int[] { 1, 2, 3, 1 });
        test(new int[] { 1, 2, 1, 3, 5, 6, 4 });

    }
}