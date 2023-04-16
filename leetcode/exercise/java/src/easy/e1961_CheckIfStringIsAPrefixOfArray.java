package easy;

/**
 * 1961. Check If String Is a Prefix of Array
 * https://leetcode.com/problems/check-if-string-is-a-prefix-of-array/
 * 
 * Given a string s and an array of strings words, determine whether s is a
 * prefix string of words.
 * 
 * A string s is a prefix string of words if s can be made by concatenating the
 * first k strings in words for some positive k no larger than words.length.
 * 
 * Return true if s is a prefix string of words, or false otherwise.
 * 
 * Constraints:
 * 
 * 1 <= words.length <= 100
 * 1 <= words[i].length <= 20
 * 1 <= s.length <= 1000
 * words[i] and s consist of only lowercase English letters.
 * 
 */

public class e1961_CheckIfStringIsAPrefixOfArray {
    public boolean isPrefixString(String s, String[] words) {
        int i = 0;
        for (int k = 0; i < s.length() && k < words.length; ++k) {
            // remaining s is shorter then next word k
            if (s.length() - i < words[k].length())
                return false;
            for (int j = 0; j < words[k].length(); ++i, ++j) {
                if (s.charAt(i) != words[k].charAt(j))
                    return false;
            }
        }
        return i == s.length();
    }

    public static void test(String s, String[] words) {
        e1961_CheckIfStringIsAPrefixOfArray target = new e1961_CheckIfStringIsAPrefixOfArray();
        System.out.println(s + " vs. [" + String.join(",", words) + "] => "
                + target.isPrefixString(s, words));
    }

    public static void main(String[] args) {
        test("iloveleetcode", new String[] { "i", "love", "leetcode", "apples" });
        test("iloveleetcode", new String[] { "apples", "i", "love", "leetcode", "apples" });
        test("iloveleetcod", new String[] { "i", "love", "leetcode", "apples" });
    }
}