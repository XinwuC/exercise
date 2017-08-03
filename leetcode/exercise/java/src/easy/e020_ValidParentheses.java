package easy;

import java.util.Stack;

/**
 * Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
 * <p>
 * The brackets must close in the correct order, "()" and "()[]{}" are all valid but "(]" and "([)]" are not.
 */
public class e020_ValidParentheses {
    public boolean isValid(String s) {
        char[] cs = s.toCharArray();
        Stack<Character> brackets = new Stack<>();
        for (int i = 0; i < cs.length; ++i) {
            switch (cs[i]) {
                case '(':
                case '[':
                case '{':
                    brackets.push(cs[i]);
                    break;
                case ')':
                    if (brackets.empty()) return false;
                    Character c = brackets.pop();
                    if ('(' != c) return false;
                    break;
                case ']':
                    if (brackets.empty()) return false;
                    c = brackets.pop();
                    if ('[' != c) return false;
                    break;
                case '}':
                    if (brackets.empty()) return false;
                    c = brackets.pop();
                    if ('{' != c) return false;
                    break;
            }
        }

        return brackets.empty();
    }

    public static void main(String[] args) {
        e020_ValidParentheses solution = new e020_ValidParentheses();

        System.out.println(solution.isValid(""));
        System.out.println(solution.isValid("asdfasg"));
        System.out.println(solution.isValid("asdf(asg"));
        System.out.println(solution.isValid("()"));
        System.out.println(solution.isValid("()[]{}"));
        System.out.println(solution.isValid("([)]"));

        System.out.println(solution.isValid("([])"));
    }
}
