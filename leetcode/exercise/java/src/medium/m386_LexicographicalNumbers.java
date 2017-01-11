package medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 386. Lexicographical Numbers
 * <p>
 * Total Accepted: 12540
 * Total Submissions: 31739
 * Difficulty: Medium
 * Contributors: Admin
 * <p>
 * Given an integer n, return 1 - n in lexicographical order.
 * <p>
 * For example, given 13, return: [1,10,11,12,13,2,3,4,5,6,7,8,9].
 * <p>
 * Please optimize your algorithm to use less time and space. The input size may be as large as 5,000,000.
 * <p>
 * Leetcode: https://leetcode.com/problems/lexicographical-numbers/
 * <p>
 * Created by xinwu on 1/10/17.
 */
public class m386_LexicographicalNumbers {
    public List<Integer> lexicalOrder(int n) {
        List<Integer> result = new ArrayList<>();

        long current = 1;
        result.add(new Integer((int) current));
        while (result.size() < n) {
            // add much 0 as possible
            for (long next = current * 10; next <= n && result.size() < n; next = current * 10) {
                current = next;
                result.add(new Integer((int) current));
            }
            // adjust if current == n
            if (current == n) current = current / 10;
            // incremental by 1 until ends with 0
            for (long next = current + 1; next <= n && result.size() < n; next = current + 1) {
                if (0 != next % 10) {
                    current = next;
                    result.add(new Integer((int) current));
                } else {
                    while (0 == next % 10) next /= 10;
                    current = next;
                    result.add(new Integer((int) current));
                    break;
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        m386_LexicographicalNumbers solution = new m386_LexicographicalNumbers();

        System.out.println(Arrays.toString(solution.lexicalOrder(100).toArray()));
        System.out.println(Arrays.toString(solution.lexicalOrder(30).toArray()));
        System.out.println(Arrays.toString(solution.lexicalOrder(9).toArray()));
        System.out.println(Arrays.toString(solution.lexicalOrder(19).toArray()));
        System.out.println(Arrays.toString(solution.lexicalOrder(91).toArray()));
        System.out.println(Arrays.toString(solution.lexicalOrder(99).toArray()));
    }
}
