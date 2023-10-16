package medium;

/**
 * 443. String Compression
 * https://leetcode.com/problems/string-compression/description/
 * 
 * Given an array of characters chars, compress it using the following
 * algorithm:
 * 
 * Begin with an empty string s. For each group of consecutive repeating
 * characters in chars:
 * 
 * If the group's length is 1, append the character to s.
 * Otherwise, append the character followed by the group's length.
 * The compressed string s should not be returned separately, but instead, be
 * stored in the input character array chars. Note that group lengths that are
 * 10 or longer will be split into multiple characters in chars.
 * 
 * After you are done modifying the input array, return the new length of the
 * array.
 * 
 * You must write an algorithm that uses only constant extra space.
 * 
 * Example 1:
 * 
 * Input: chars = ["a","a","b","b","c","c","c"]
 * Output: Return 6, and the first 6 characters of the input array should be:
 * ["a","2","b","2","c","3"]
 * Explanation: The groups are "aa", "bb", and "ccc". This compresses to
 * "a2b2c3".
 * Example 2:
 * 
 * Input: chars = ["a"]
 * Output: Return 1, and the first character of the input array should be: ["a"]
 * Explanation: The only group is "a", which remains uncompressed since it's a
 * single character.
 * Example 3:
 * 
 * Input: chars = ["a","b","b","b","b","b","b","b","b","b","b","b","b"]
 * Output: Return 4, and the first 4 characters of the input array should be:
 * ["a","b","1","2"].
 * Explanation: The groups are "a" and "bbbbbbbbbbbb". This compresses to
 * "ab12".
 * 
 * Constraints:
 * 
 * 1 <= chars.length <= 2000
 * chars[i] is a lowercase English letter, uppercase English letter, digit, or
 * symbol.
 */
public class m0443_StringCompression {
    static class Solution {
        public int compress(char[] chars) {
            int count = 1;
            char currentChar = chars[0];
            int len = 0;
            for (int i = 1; i < chars.length; ++i) {
                if (currentChar == chars[i]) {
                    count++;
                } else {
                    chars[len++] = currentChar;
                    if (count > 1)
                        for (char c : String.valueOf(count).toCharArray())
                            chars[len++] = c;
                    count = 1;
                    currentChar = chars[i];
                }
            }
            chars[len++] = currentChar;
            if (count > 1)
                for (char c : String.valueOf(count).toCharArray())
                    chars[len++] = c;
            return len;
        }
    }

    private static void test(char[] chars, char[] expects) {
        Solution s = new Solution();
        String input = new String(chars);
        String expect = new String(expects);
        int len = s.compress(chars);
        String output = new String(chars, 0, len);
        System.out.format("%s: %s => %s (vs) %s\n", output.equals(expect), input, output, expect);

    }

    public static void main(String[] args) {
        test(new char[] { 'a', 'a', 'b', 'b', 'c', 'c', 'c' }, new char[] { 'a', '2', 'b', '2', 'c', '3' });
        test(new char[] { 'a' }, new char[] { 'a' });
        test(new char[] { 'a', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b' },
                new char[] { 'a', 'b', '1', '2' });
        test(new char[] { 'a', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'B', 'B' },
                new char[] { 'a', 'b', '1', '0', 'B', '2' });
    }
}
