package easy;

/**
 * Write a function that takes a string as input and reverse only the vowels of a string.
 * <p>
 * Example 1:
 * Given s = "hello", return "holle".
 * <p>
 * Example 2:
 * Given s = "leetcode", return "leotcede".
 * <p>
 * Note:
 * The vowels does not include the letter "y".
 */
public class e345_ReverseVowels {
    private boolean isVowel(char c) {
        return 'a' == c || 'e' == c || 'i' == c || 'o' == c || 'u' == c ||
                'A' == c || 'E' == c || 'I' == c || 'O' == c || 'U' == c;
    }

    public String reverseVowels(String s) {
        if (null == s || s.length() < 2)
            return s;

        int start = 0, end = s.length() - 1;
        char[] result = s.toCharArray();
        while (start < end) {
            // find vowl from beginning
            while (start < end) {
                if (!isVowel(result[start])) ++start;
                else break;
            }
            // find vowl from the end
            while (start < end) {
                if (!isVowel(result[end])) --end;
                else break;
            }
            // swap
            if (start < end) {
                char temp = result[end];
                result[end] = result[start];
                result[start] = temp;
            }
            ++start;
            --end;
        }
        return new String(result);
    }

    public static void main(String[] args) {
        e345_ReverseVowels solution = new e345_ReverseVowels();
        System.out.println(solution.reverseVowels("hello"));
        System.out.println(solution.reverseVowels("leetcode"));
    }
}
