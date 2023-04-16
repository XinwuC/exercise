package easy;

import java.util.List;
import java.util.ArrayList;

/**
 * 155. Min Stack
 * <p>
 * Total Accepted: 102927
 * Total Submissions: 397614
 * Difficulty: Easy
 * Contributors: Admin
 * <p>
 * Design a stack that supports push, pop, top, and retrieving the minimum
 * element in constant time.
 * <p>
 * push(x) -- Push element x onto stack.
 * pop() -- Removes the element on top of the stack.
 * top() -- Get the top element.
 * getMin() -- Retrieve the minimum element in the stack.
 * <p>
 * Example:
 * <code>
 * MinStack minStack = new MinStack();
 * minStack.push(-2);
 * minStack.push(0);
 * minStack.push(-3);
 * minStack.getMin();   --> Returns -3.
 * minStack.pop();
 * minStack.top();      --> Returns 0.
 * minStack.getMin();   --> Returns -2.
 * </code>
 * <p>
 * Leetcode: https://leetcode.com/problems/min-stack/
 * <p>
 * Created by xinwu on 12/13/16.
 */
public class e155_MiniStack {
    public e155_MiniStack() {

    }

    /**
     * initialize your data structure here.
     */
    private List<Integer> list = new ArrayList<Integer>();
    private int minIndex = -1;

    public void push(int x) {
        list.add(x);
        if (-1 == minIndex) {
            minIndex = 0;
        } else if (x < list.get(minIndex)) {
            minIndex = list.size() - 1;
        }
    }

    public void pop() {
        if (!list.isEmpty()) {
            minIndex = minIndex == list.size() - 1 ? -1 : minIndex;
            list.remove(list.size() - 1);
        }
    }

    public int top() {
        return list.isEmpty() ? -1 : list.get(list.size() - 1);
    }

    public int getMin() {
        if (list.isEmpty())
            return -1;
        if (-1 != minIndex)
            return list.get(minIndex);

        // find new min element
        minIndex = 0;
        int minValue = list.get(minIndex);
        for (int i = 1; i < list.size(); ++i) {
            int val = list.get(i);
            if (val < minValue) {
                minValue = val;
                minIndex = i;
            }
        }

        return minValue;
    }

    public static void main(String[] args) {
        e155_MiniStack stack = new e155_MiniStack();

        stack.push(1);
        stack.push(2);
        System.out.println(stack.top());
        System.out.println(stack.getMin());
        stack.pop();
        System.out.println(stack.getMin());
        System.out.println(stack.top());
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */
