package easy;

/**
 * 1957. Delete Characters to Make Fancy String
 * https://leetcode.com/problems/delete-characters-to-make-fancy-string/description/
 * 
 * A fancy string is a string where no three consecutive characters are equal.
 * 
 * Given a string s, delete the minimum possible number of characters from s to
 * make it fancy.
 * 
 * Return the final string after the deletion. It can be shown that the answer
 * will always be unique.
 * 
 */
public class e1957_DeleteCharactersToMakeFancyString {
    public String makeFancyString(String s) {
        StringBuilder builder = new StringBuilder();
        int currentCount = 0;
        int currentChar = '.';
        for (char c : s.toCharArray()) {
            if (c == currentChar) {
                if (currentCount < 2) {
                    builder.append(c);
                    ++currentCount;
                } // else continue
            } else {
                currentChar = c;
                currentCount = 1;
                builder.append(c);
            }
        }
        return builder.toString();
    }

    public static void test(e1957_DeleteCharactersToMakeFancyString s, String str) {
        System.out.println(str + " -> " + s.makeFancyString(str));

    }

    public static void main(String[] args) {
        e1957_DeleteCharactersToMakeFancyString s = new e1957_DeleteCharactersToMakeFancyString();
        test(s, "leeetcode");
        test(s, "aaabaaaa");
        test(s, "aab");
    }
}