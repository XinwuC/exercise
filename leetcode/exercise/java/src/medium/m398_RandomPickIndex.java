package medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

/**
 * 398. Random Pick Index
 * <p>
 * Total Accepted: 11621
 * Total Submissions: 29186
 * Difficulty: Medium
 * Contributors: Admin
 * <p>
 * Given an array of integers with possible duplicates, randomly output the index of a given target number. You can
 * assume that the given target number must exist in the array.
 * <p>
 * Note:
 * The array size can be very large. Solution that uses too much extra space will not pass the judge.
 * <p>
 * Example:
 * <p>
 * int[] nums = new int[] {1,2,3,3,3};
 * Solution solution = new Solution(nums);
 * <p>
 * // pick(3) should return either index 2, 3, or 4 randomly. Each index should have equal probability of returning.
 * solution.pick(3);
 * <p>
 * // pick(1) should return 0. Since in the array only nums[0] is equal to 1.
 * solution.pick(1);
 * <p>
 * <p>
 * Leetcode: https://leetcode.com/problems/random-pick-index/
 * <p>
 * Created by xinwu on 1/8/17.
 */
public class m398_RandomPickIndex {
    private Random random = new Random(System.currentTimeMillis());

    private HashMap<Integer, List<Integer>> index = new HashMap<>();

    public m398_RandomPickIndex(int[] nums) {
        for (int idx = 0; idx < nums.length; ++idx) {
            Integer key = new Integer(nums[idx]);
            List<Integer> indexList = index.containsKey(key) ? index.get(key) : new ArrayList<>();
            indexList.add(new Integer(idx));
            index.putIfAbsent(key, indexList);
        }
    }

    public int pick(int target) {
        Integer key = new Integer(target);
        if (index.containsKey(key)) {
            List<Integer> indexList = index.get(key);
            return indexList.get(random.nextInt(indexList.size())).intValue();
        } else {
            return -1;
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3, 3, 3};
        m398_RandomPickIndex solution = new m398_RandomPickIndex(nums);
        System.out.println(solution.pick(nums[solution.random.nextInt(nums.length)]));
    }
}
