package easy;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

/**
 * Given an array of integers where 1 ≤ a[i] ≤ n (n = size of array), some elements appear twice and others appear once.
 * <p>
 * Find all the elements of [1, n] inclusive that do not appear in this array.
 * <p>
 * Could you do it without extra space and in O(n) runtime? You may assume the returned list does not count as extra space.
 * <p>
 * Example:
 * <p>
 * Input:
 * [4,3,2,7,8,2,3,1]
 * <p>
 * Output:
 * [5,6]
 */
public class e448_FindAllNumbersDisappeared {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> res = new ArrayList<>();

        int n = nums.length;
        for (int i = 0; i < n; ++i)
            nums[(nums[i] - 1) % n] += n;
        for (int i = 0; i < n; ++i)
            if (nums[i] <= n)
                res.add(i + 1);


        return res;
    }

    public static void main(String[] args) {
        e448_FindAllNumbersDisappeared solution = new e448_FindAllNumbersDisappeared();
        System.out.println(Arrays.toString(solution.findDisappearedNumbers(new int[]{4, 3, 2, 7, 8, 2, 3, 1}).toArray()));
    }
}
