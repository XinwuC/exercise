package easy;

import java.util.Arrays;

/**
 * Given a sorted array, remove the duplicates in place such that each element appear only once and return the new length.
 * <p>
 * Do not allocate extra space for another array, you must do this in place with constant memory.
 * <p>
 * For example,
 * Given input array nums = [1,1,2],
 * <p>
 * Your function should return length = 2, with the first two elements of nums being 1 and 2 respectively. It doesn't matter what you leave beyond the new length.
 */
public class e026_RemoveDuplicatesFromSortedArray {
    public int removeDuplicates(int[] nums) {
        int newEnd = 1;
        while (newEnd < nums.length) {
            if (nums[newEnd] == nums[newEnd - 1])
                break;
            else
                ++newEnd;
        }
        for (int index = newEnd + 1; index < nums.length; ++index) {
            if (nums[index] != nums[newEnd - 1]) {
                nums[newEnd] = nums[index];
                ++newEnd;
            }
        }
        return newEnd;
    }

    public static void main(String[] args) {
        e026_RemoveDuplicatesFromSortedArray solution = new e026_RemoveDuplicatesFromSortedArray();

        int[] input = new int[]{1, 1, 2};
        System.out.println(solution.removeDuplicates(input) + " " + Arrays.toString(input));
        input = new int[]{1, 1, 2, 2};
        System.out.println(solution.removeDuplicates(input) + " " + Arrays.toString(input));
        input = new int[]{1, 1, 2, 2, 3};
        System.out.println(solution.removeDuplicates(input) + " " + Arrays.toString(input));
        input = new int[]{-1, 0, 0, 0, 0, 3, 3};
        System.out.println(solution.removeDuplicates(input) + " " + Arrays.toString(input));
    }
}
