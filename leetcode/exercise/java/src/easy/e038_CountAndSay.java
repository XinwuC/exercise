package easy;

/**
 * The count-and-say sequence is the sequence of integers with the first five terms as following:
 * <p>
 * 1.     1
 * 2.     11
 * 3.     21
 * 4.     1211
 * 5.     111221
 * 1 is read off as "one 1" or 11.
 * 11 is read off as "two 1s" or 21.
 * 21 is read off as "one 2, then one 1" or 1211.
 * Given an integer n, generate the nth term of the count-and-say sequence.
 * <p>
 * Note: Each term of the sequence of integers will be represented as a string.
 * <p>
 * Example 1:
 * <p>
 * Input: 1
 * Output: "1"
 * Example 2:
 * <p>
 * Input: 4
 * Output: "1211"
 */
public class e038_CountAndSay {
    public String countAndSay(int n) {
        StringBuffer result = new StringBuffer("1");

        for (int iteration = 2; iteration <= n; ++iteration) {
            // count and say
            char[] current = result.toString().toCharArray();
            result.setLength(0);
            // set counter
            char countChar = current[0];
            int count = 0;
            for (int i = 0; i < current.length; ++i) {
                if (current[i] == countChar) {
                    // * count
                    ++count;
                } else {
                    // * say
                    result.append(String.valueOf(count));
                    result.append(countChar);
                    // reset counter
                    countChar = current[i];
                    count = 1;
                }
            }
            if (count > 0) {
                result.append(String.valueOf(count));
                result.append(countChar);
            }
        }

        return result.toString();
    }

    public static void main(String[] args) {
        e038_CountAndSay solution = new e038_CountAndSay();
        for (int i = 1; i < 30; ++i) {
            System.out.println(i + "\t" + solution.countAndSay(i));
        }
    }
}
