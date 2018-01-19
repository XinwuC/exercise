package easy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * Given an array of integers, return indices of the two numbers such that they add up to a specific target.
 * <p>
 * You may assume that each input would have exactly one solution, and you may not use the same element twice.
 * <p>
 * Example:
 * Given nums = [2, 7, 11, 15], target = 9,
 * <p>
 * Because nums[0] + nums[1] = 2 + 7 = 9,
 * return [0, 1].
 */
public class e001_TwoSum {
    public int[] twoSum(int[] nums, int target) {
        int[] result = new int[]{0, 0};
        // Key: nums[i], Value: index
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < nums.length; ++i)
            hashMap.put(nums[i], i);
        for (int i = 0; i < nums.length; ++i) {
            int remaining = target - nums[i];
            if (hashMap.containsKey(remaining)) {
                int j = hashMap.get(remaining);
                if (i != j) {
                    result[0] = i;
                    result[1] = j;
                    break;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        e001_TwoSum solution = new e001_TwoSum();
        System.out.println(Arrays.toString(solution.twoSum(new int[]{2, 7, 11, 15}, 4)));
        System.out.println(Arrays.toString(solution.twoSum(new int[]{2, 7, 11, 15}, 9)));
        System.out.println(Arrays.toString(solution.twoSum(new int[]{3, 2, 4}, 6)));
    }
}
