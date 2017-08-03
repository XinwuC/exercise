package easy;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

/**
 * Given an array of integers, 1 ≤ a[i] ≤ n (n = size of array), some elements appear twice and others appear once.
 * <p>
 * Find all the elements that appear twice in this array.
 * <p>
 * Could you do it without extra space and in O(n) runtime?
 * <p>
 * Example:
 * Input:
 * [4,3,2,7,8,2,3,1]
 * <p>
 * Output:
 * [2,3]
 */
public class e442_FindAllDuplicates {
    public List<Integer> findDuplicates(int[] nums) {
        List<Integer> res = new ArrayList<>();

        int n = nums.length;
        for (int i = 0; i < n; ++i)
            nums[(nums[i] - 1) % n] += n;
        for (int i = 0; i < n; ++i)
            if (nums[i] > 2 * n)
                res.add(new Integer(i + 1));


        return res;
    }


    public static void main(String[] args) {
        e442_FindAllDuplicates solution = new e442_FindAllDuplicates();
        System.out.println(Arrays.toString(solution.findDuplicates(new int[]{4, 3, 2, 7, 8, 2, 3, 1}).toArray()));
    }
}
