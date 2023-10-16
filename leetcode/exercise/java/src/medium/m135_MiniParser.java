package medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * 385. Mini Parser
 * <p>
 * Total Accepted: 9756
 * Total Submissions: 32985
 * Difficulty: Medium
 * Contributors: Admin
 * <p>
 * Given a nested list of integers represented as a string, implement a parser to deserialize it.
 * <p>
 * Each element is either an integer, or a list -- whose elements may also be integers or other lists.
 * <p>
 * Note: You may assume that the string is well-formed:
 * <p>
 * String is non-empty.
 * String does not contain white spaces.
 * String contains only digits 0-9, [, - ,, ].
 * <p>
 * Example 1:
 * <p>
 * Given s = "324",
 * <p>
 * You should return a NestedInteger object which contains a single integer 324.
 * <p>
 * Example 2:
 * <p>
 * Given s = "[123,[456,[789]]]",
 * <p>
 * Return a NestedInteger object containing a nested list with 2 elements:
 * <p>
 * 1. An integer containing value 123.
 * 2. A nested list containing two elements:
 * i.  An integer containing value 456.
 * ii. A nested list with one element:
 * a. An integer containing value 789.
 * <p>
 * Leetcode: https://leetcode.com/problems/mini-parser/
 * <p>
 * Created by xinwu on 1/4/17.
 */


// This is the interface that allows for creating nested lists.
// You should not implement it, or speculate about its implementation
class NestedInteger {
    private Integer value = null;
    private List<NestedInteger> list = null;

    // Constructor initializes an empty nested list.
    public NestedInteger() {
        list = new ArrayList<>();
    }

    // Constructor initializes a single integer.
    public NestedInteger(int value) {
        this.value = Integer.valueOf(value);
    }

    // @return true if this NestedInteger holds a single integer, rather than a nested list.
    public boolean isInteger() {
        return null != value;
    }

    // @return the single integer that this NestedInteger holds, if it holds a single integer
    // Return null if this NestedInteger holds a nested list
    public Integer getInteger() {
        return value;
    }

    // Set this NestedInteger to hold a single integer.
    public void setInteger(int value) {
        this.value = Integer.valueOf(value);
    }

    // Set this NestedInteger to hold a nested list and adds a nested integer to it.
    public void add(NestedInteger ni) {
        list.add(ni);
    }

    // @return the nested list that this NestedInteger holds, if it holds a nested list
    // Return null if this NestedInteger holds a single integer
    public List<NestedInteger> getList() {
        return list;
    }

    @Override
    public String toString() {
        if (isInteger()) return value.toString();
        return Arrays.toString(list.toArray());
    }
}

public class m135_MiniParser {

    public NestedInteger deserialize(String s) {
        Stack<NestedInteger> stack = new Stack<>();
        NestedInteger parent = null;
        char[] c = s.toCharArray();
        for (int i = 0; i < c.length; ++i) {
            if ('[' == c[i]) {
                NestedInteger newIntList= new NestedInteger();
                if (null != parent) {
                    parent.add(newIntList);
                    stack.push(parent);
                }
                parent = newIntList;
            } else if (']' == c[i]) {
                if (stack.isEmpty()) break;
                else parent = stack.pop();
            } else if (',' == c[i]) {
                continue;
            } else { // 0-9, -
                boolean negative = false;
                if ('-' == c[i]) {
                    negative = true;
                    ++i;
                }
                int ivalue = 0;
                while (i < c.length && c[i] >= '0' && c[i] <= '9') {
                    ivalue = ivalue * 10 + (c[i] - '0');
                    ++i;
                }
                --i;
                NestedInteger ni = new NestedInteger(negative ? -ivalue : ivalue);
                if (null == parent) {
                    parent = ni;
                    break;
                } else {
                    parent.add(ni);
                }
            }
        }
        return parent;
    }

    public static void main(String[] args) {
        m135_MiniParser solution = new m135_MiniParser();

        System.out.println(solution.deserialize("324"));
        System.out.println(solution.deserialize("-324"));
        System.out.println(solution.deserialize("[123,[456,[789]]]"));
        System.out.println(solution.deserialize("[123,456,[788,799,833],[[]],10,[]]"));
    }
}
