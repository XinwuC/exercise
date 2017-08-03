package easy;

import java.util.List;
import java.util.ArrayList;

/**
 * Find the nth digit of the infinite integer sequence 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ...
 * <p>
 * Note:
 * n is positive and will fit within the range of a 32-bit signed integer (n < 231).
 * <p>
 * Example 1:
 * <p>
 * Input:
 * 3
 * <p>
 * Output:
 * 3
 * Example 2:
 * <p>
 * Input:
 * 11
 * <p>
 * Output:
 * 0
 * <p>
 * Explanation:
 * The 11th digit of the sequence 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ... is a 0, which is part of the number 10.
 */
public class e400_NthDigit {
    boolean init = false;
    int[] index = null;
    private int position;

    /**
     * build position index
     * index:     0,       1,         2, ...
     * range: [0,9], [10,99], [100,999], ...
     * length:    1,       2,         3, ...
     * value:   9*1,    90*2,     900*3, ...
     */
    private void buildIndex() {
        if (!init) {
            init = true;
            // build position index
            List<Integer> indexList = new ArrayList<Integer>();
            long totalDigits = 0;
            for (int length = 1; totalDigits < Integer.MAX_VALUE; ++length) {
                totalDigits += length * 9 * Math.pow(10, length - 1);
                if (totalDigits >= Integer.MAX_VALUE) {
                    indexList.add(Integer.MAX_VALUE);
                    break;
                } else {
                    indexList.add(new Integer((int) totalDigits));
                }
            }
            index = new int[indexList.size()];
            for (int i = 0; i < index.length; ++i) {
                index[i] = indexList.get(i).intValue();
            }
        }
    }

    /**
     * Look up from position index array
     * <p>
     * example: in range [100, 999]
     * int:                 1 0 0, 1 0 1, 1 0 2, ...
     * position:            1 2 3  4 5 6  7 8 9
     * ceil(x/length):      1 1 1  2 2 2  3 3 3
     * x%length - 1:        0 1 -1 0 1 -1 0 1 -1
     */
    public int findNthDigit(int n) {
        buildIndex();
        // binary search for the range
        int start = 0, end = index.length;
        int rangeIndex = 0;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (n <= index[mid]) {
                if (mid > 0) {
                    if (n > index[mid - 1]) {
                        rangeIndex = mid;
                        break;
                    } else {
                        end = mid - 1;
                    }
                } else {
                    rangeIndex = 0;
                    break;
                }
            } else {
                start = mid + 1;
            }
        }

        // find digit from the range
        int length = rangeIndex + 1;
        int range_start = (int) Math.pow(10, length - 1);
        int previous_range = 0;
        if (rangeIndex > 0)
            previous_range = index[rangeIndex - 1];
        int range_index = n - previous_range;
        int value = range_start + (int) Math.ceil((float) range_index / length) - 1;
        int position = range_index % length - 1;
        position = position == -1 ? length - 1 : position;
        String valueString = String.valueOf(value);
        char c = valueString.charAt(position);

        System.out.println(String.format("%dth digit is %s, [length: %d, range_start: %d, range_index: %d, int: %d, position: %d]", n, c, length, range_start, range_index, value, position));

        return c - '0';

    }

    public static void main(String[] args) {
        e400_NthDigit solution = new e400_NthDigit();

        solution.findNthDigit(1);
        solution.findNthDigit(3);
        solution.findNthDigit(9);
        solution.findNthDigit(10);
        solution.findNthDigit(11);
        solution.findNthDigit(189);
        solution.findNthDigit(190);
        solution.findNthDigit(198);
        solution.findNthDigit(2889);
        solution.findNthDigit(1000000000);
    }
}
