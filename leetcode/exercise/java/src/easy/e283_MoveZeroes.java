package easy;

import java.util.Arrays;

/**
 * Given an array nums, write a function to move all 0's to the end of it while maintaining the relative order of the non-zero elements.
 * <p>
 * For example, given nums = [0, 1, 0, 3, 12], after calling your function, nums should be [1, 3, 12, 0, 0].
 * <p>
 * Note:
 * You must do this in-place without making a copy of the array.
 * Minimize the total number of operations.
 */
public class e283_MoveZeroes {
    public void moveZeroes(int[] nums) {
        int start = -1;
        for (int i = 0; i < nums.length; ++i) {
            if (0 == nums[i]) {
                if (-1 == start) start = i;
            } else if (-1 != start) {
                nums[start] = nums[i];
                nums[i] = 0;
                ++start;
            }
        }
    }

    public static void test(int[] nums) {
        e283_MoveZeroes solution = new e283_MoveZeroes();
        solution.moveZeroes(nums);
        System.out.println(Arrays.toString(nums));
    }

    public static void main(String[] args) {
        test(new int[]{0, 0, 0, 0, 0});
        test(new int[]{0, 0, 0, 3, 12});
        test(new int[]{0, 1, 0, 3, 12});
        test(new int[]{0, 0, 0, 0, 1});
        test(new int[]{1, 0, 0, 0, 1});
        test(new int[]{1, 1, 0, 0, 1});
        test(new int[]{1, 1, 1, 1, 1});

    }
}
