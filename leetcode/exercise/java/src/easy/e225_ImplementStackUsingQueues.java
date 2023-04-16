package easy;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 225. Implement Stack using Queues
 * 
 * Total Accepted: 45559 Total Submissions: 148012 Difficulty: Easy
 * 
 * Implement the following operations of a stack using queues.
 * 
 * <code>
 * push(x) -- Push element x onto stack. 
 * pop() -- Removes the element on top of the stack. 
 * top() -- Get the top element. 
 * empty() -- Return whether the stack is empty.
 * </code>
 * 
 * Notes:
 * 
 * You must use only standard operations of a queue -- which means only push to
 * back, peek/pop from front, size, and is empty operations are valid. Depending
 * on your language, queue may not be supported natively. You may simulate a
 * queue by using a list or deque (double-ended queue), as long as you use only
 * standard operations of a queue. You may assume that all operations are valid
 * (for example, no pop or top operations will be called on an empty stack).
 *
 * 
 * LeetCode: https://leetcode.com/problems/implement-stack-using-queues/
 * 
 * @author xinwu
 *
 */
public class e225_ImplementStackUsingQueues {
	// only push(), peek(), pop(), size(), isEmpty() allowed
	private Queue<Integer> _queue = new ArrayDeque<Integer>();

	// Push element x onto stack.
	public void push(int x) {
		_queue.offer(x);
		for (int i = 0; i < _queue.size() - 1; ++i) {
			_queue.offer(_queue.poll());
		}
	}

	// Removes the element on top of the stack.
	public void pop() {
		_queue.poll();
	}

	// Get the top element.
	public int top() {
		return _queue.peek().intValue();
	}

	// Return whether the stack is empty.
	public boolean empty() {
		return _queue.isEmpty();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
