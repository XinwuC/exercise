package medium;

import java.util.Arrays;

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
        return searchPeak(nums, 0, nums.length-1);
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