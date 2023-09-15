package medium;

/**
 * 1156. Swap For Longest Repeated Character Substring
 * https://leetcode.com/problems/swap-for-longest-repeated-character-substring/
 * 
 * You are given a string text. You can swap two of the characters in the text.
 * 
 * Return the length of the longest substring with repeated characters.
 * 
 * Constraints:
 * 
 * 1 <= text.length <= 2 * 104
 * text consist of lowercase English characters only.
 */
public class m1156 {

    public int maxRepOpt1(String text) {
        int max = 1;
        // preprocess: count swappables
        int[] swappable = new int[26];
        for (int i = 0; i < text.length();) {
            char c = text.charAt(i);
            ++swappable[c - 'a'];
            for (++i; i < text.length() && c == text.charAt(i); ++i)
                ;
        }
        // process the count
        int preCount = 0;
        for (int i = 0; i < text.length();) {
            // loop invariant:
            // i: index of "curr" char, should be the first char of current repeat substring
            // preCount: length of previous substring that can connect to current one
            char curr = text.charAt(i);
            char repeatChar = curr;
            int currCount = 1;
            for (++i; i < text.length(); ++i) {
                curr = text.charAt(i);
                if (curr == repeatChar)
                    ++currCount;
                else
                    break;
            }
            max = Math.max(max, preCount == 0 ? (swappable[repeatChar - 'a'] > 1 ? currCount + 1 : currCount)
                    : preCount + currCount);

            if (i < text.length()) {
                // i pointed to a different char
                if (i + 1 < text.length() && repeatChar == text.charAt(i + 1)) {
                    // can connect to next substring
                    // ignore charAt(i) as its length is 1, always smaller than two substrings
                    // reset loop to process next substring directly
                    ++i; // reset i to the start of next substring
                    preCount = swappable[repeatChar - 'a'] > 2 ? currCount + 1 : currCount;
                } else {
                    // cannot connect to next substring, reset loop to process curr char
                    if (i - 2 >= 0 && text.charAt(i - 2) == text.charAt(i))
                        preCount = swappable[text.charAt(i - 2) - 'a'] > 2 ? 2 : 1;
                    else
                        preCount = 0;
                }
            }
        }

        return max;
    }

    public static void test(String text, int expected) {
        int result = new m1156().maxRepOpt1(text);
        System.out.format("[%s] %s => %d, vs %d (expected)\n", result == expected, text, result, expected);
    }

    public static void main(String[] args) {
        test("a", 1);
        test("bbababaaaa", 6);
        test("aaabbaa", 4);
        test("ababa", 3);
        test("aaabaaa", 6);
        test("aaaaa", 5);
        test("aaabbaaacaaa", 7);
        test("bbcaaaxy", 3);
    }
}
