package medium;

import java.util.Arrays;

/**
 * 1073. Adding Two Negabinary Numbers
 * https://leetcode.com/problems/adding-two-negabinary-numbers/
 * 
 * Given two numbers arr1 and arr2 in base -2, return the result of adding them
 * together.
 * 
 * Each number is given in array format: as an array of 0s and 1s, from most
 * significant bit to least significant bit. For example, arr = [1,1,0,1]
 * represents the number (-2)^3 + (-2)^2 + (-2)^0 = -3. A number arr in array,
 * format is also guaranteed to have no leading zeros: either arr == [0] or
 * arr[0] == 1.
 * 
 * Return the result of adding arr1 and arr2 in the same format: as an array of
 * 0s and 1s with no leading zeros.
 * 
 * Constraints:
 * 
 * 1 <= arr1.length, arr2.length <= 1000
 * arr1[i] and arr2[i] are 0 or 1
 * arr1 and arr2 have no leading zeros
 * 
 */
public class m1073 {
    public int[] addNegabinary(int[] arr1, int[] arr2) {
        int max = Math.max(arr1.length, arr2.length);
        int[] result = new int[max + 2];
        int resultLength = 0;
        int carry = 0;
        while (resultLength < max || carry != 0) {
            // for (int i = 0, carry = 0; i < min; ++i) {
            int idx1 = arr1.length - 1 - resultLength;
            int idx2 = arr2.length - 1 - resultLength;
            int bit1 = idx1 < 0 ? 0 : arr1[idx1];
            int bit2 = idx2 < 0 ? 0 : arr2[idx2];
            int bit = bit1 + bit2 - carry;
            switch (bit) {
                case 0:
                case 1:
                    result[max + 1 - resultLength] = bit;
                    carry = 0;
                    break;
                case -1:
                    result[max + 1 - resultLength] = 1;
                    carry = -1;
                    break;
                case 2:
                    result[max + 1 - resultLength] = 0;
                    carry = 1;
                    break;
                case 3:
                    result[max + 1 - resultLength] = 1;
                    carry = 1;
                    break;
            }
            ++resultLength;
        }
        // remove leading 0s
        int start = max + 2 - resultLength;
        for (; result[start] == 0 && start < max + 1; ++start)
            ;
        return Arrays.copyOfRange(result, start, max + 2);
    }

    public static void test(int[] arr1, int[] arr2, int[] expected) {
        m1073 solution = new m1073();
        int[] result = solution.addNegabinary(arr1, arr2);
        System.out.format("Length: %d vs %d (expected):\t%s\n", result.length, expected.length,
                result.length == expected.length);
        for (int i = 0; i < Math.min(result.length, expected.length); ++i) {
            if (result[i] != expected[i])
                System.out.format("\t[%d] %d vs %d (expected)\n", i, result[i], expected[i]);
        }
    }

    public static void main(String[] args) {
        test(new int[] { 1, 0, 1 }, new int[] { 1, 0, 1 }, new int[] { 1, 1, 1, 1, 0 });
        test(new int[] { 0 }, new int[] { 0 }, new int[] { 0 });
        test(new int[] { 0 }, new int[] { 1 }, new int[] { 1 });
        test(new int[] { 1, 1, 1, 1, 1 }, new int[] { 1, 0, 1 }, new int[] { 1, 0, 0, 0, 0 });
        test(new int[] { 1, 1, 0, 1, 1 }, new int[] { 1, 0 }, new int[] { 1, 0, 1 });
        test(new int[] { 1, 1 }, new int[] { 1, 0 }, new int[] { 1, 1, 0, 1 });
    }

}
