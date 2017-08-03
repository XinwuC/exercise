package easy;

import java.util.Arrays;

/**
 * Given a non-negative integer represented as a non-empty array of digits, plus one to the integer.
 * <p>
 * You may assume the integer do not contain any leading zero, except the number 0 itself.
 * <p>
 * The digits are stored such that the most significant digit is at the head of the list.
 */
public class e066_PlusOne {
    public int[] plusOne(int[] digits) {
        boolean addingDigit = false;
        for (int i = digits.length - 1; i >= 0; --i) {
            ++digits[i];
            if (10 == digits[i]) {
                digits[i] = 0;
                addingDigit = 0 == i;
            } else {
                break;
            }
        }
        if (addingDigit) {
            int[] res = new int[digits.length + 1];
            res[0] = 1;
            for (int i = 0; i < digits.length; ++i)
                res[i + 1] = digits[i];
            digits = res;
        }
        return digits;
    }

    public static void main(String[] args) {
        e066_PlusOne solution = new e066_PlusOne();
        System.out.println(Arrays.toString(solution.plusOne(new int[]{0})));
        System.out.println(Arrays.toString(solution.plusOne(new int[]{1})));
        System.out.println(Arrays.toString(solution.plusOne(new int[]{9})));
        System.out.println(Arrays.toString(solution.plusOne(new int[]{1, 9})));
        System.out.println(Arrays.toString(solution.plusOne(new int[]{9, 9})));
    }
}
