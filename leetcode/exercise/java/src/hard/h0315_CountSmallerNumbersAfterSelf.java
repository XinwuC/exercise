package hard;

import java.util.Arrays;
import java.util.List;

/**
 * 315. Count of Smaller Numbers After Self
 * https://leetcode.com/problems/count-of-smaller-numbers-after-self/description/
 * 
 * Given an integer array nums, return an integer array counts where counts[i]
 * is the number of smaller elements to the right of nums[i].
 * 
 * Example 1:
 * 
 * Input: nums = [5,2,6,1]
 * Output: [2,1,1,0]
 * Explanation:
 * To the right of 5 there are 2 smaller elements (2 and 1).
 * To the right of 2 there is only 1 smaller element (1).
 * To the right of 6 there is 1 smaller element (1).
 * To the right of 1 there is 0 smaller element.
 * Example 2:
 * 
 * Input: nums = [-1]
 * Output: [0]
 * Example 3:
 * 
 * Input: nums = [-1,-1]
 * Output: [0,0]
 * 
 * Constraints:
 * 
 * 1 <= nums.length <= 105
 * -104 <= nums[i] <= 104
 */
public class h0315_CountSmallerNumbersAfterSelf {

    private void mergeSort(int[] nums, int[] origIndex, int[] count, int start, int end) {
        if (start == end)
            return;

        int mid = (start + end) / 2;
        mergeSort(nums, origIndex, count, start, mid);
        mergeSort(nums, origIndex, count, mid + 1, end);

        int inversions = 0;
        int i = start, j = mid + 1, k = 0;
        int[] merged = new int[end - start + 1];
        int[] mergedIndex = new int[end - start + 1];
        while (i <= mid && j <= end) {
            if (nums[i] > nums[j]) {
                inversions++;
                merged[k] = nums[j];
                mergedIndex[k] = origIndex[j];
                k++;
                j++;
            } else {
                count[origIndex[i]] += inversions;
                merged[k] = nums[i];
                mergedIndex[k] = origIndex[i];
                k++;
                i++;
            }
        }
        if (i <= mid) {
            // copy left
            for (; i <= mid; i++) {
                count[origIndex[i]] += inversions;
                merged[k] = nums[i];
                mergedIndex[k] = origIndex[i];
                k++;
             }
        } else if (j <= end) {
            for (; j <= end; ++j) {
                merged[k] = nums[j];
                mergedIndex[k] = origIndex[j];
                k++;
             }
        }
        for (int m = 0; m < k; ++m) {
            nums[start + m] = merged[m];
            origIndex[start + m] = mergedIndex[m];
        }

    }

    public List<Integer> countSmaller(int[] nums) {
        int[] count = new int[nums.length];
        int[] origIndex = new int[nums.length];
        for (int i = 0; i < nums.length; ++i) {
            origIndex[i] = i;
        }
        mergeSort(nums, origIndex, count, 0, nums.length - 1);
        return Arrays.stream(count).boxed().toList();
    }

    private static void test(int[] nums, int[] expected) {
        h0315_CountSmallerNumbersAfterSelf solution = new h0315_CountSmallerNumbersAfterSelf();
        List<Integer> result = solution.countSmaller(nums);
        boolean pass = result.size() == expected.length;
        for (int i = 0; pass && i < expected.length; ++i)
            pass = (result.get(i) == expected[i]);
        System.out.format("%s: %s => %s\n", pass, Arrays.toString(nums), result.toString());
    }

    public static void main(String[] args) {
        test(new int[] { 1, 2, 0 }, new int[] { 1, 1, 0 });
        test(new int[] { 5, 2, 6, 1 }, new int[] { 2, 1, 1, 0 });
        test(new int[] { -1 }, new int[] { 0 });
        test(new int[] { -1, -1 }, new int[] { 0, 0 });
    }
}
