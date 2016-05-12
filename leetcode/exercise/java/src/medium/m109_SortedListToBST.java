package medium;

import java.util.ArrayList;
import java.util.List;

/**
 * 109. Convert Sorted List to Binary Search Tree
 * 
 * Total Accepted: 69718 Total Submissions: 228496
 * 
 * Difficulty: Medium
 * 
 * Given a singly linked list where elements are sorted in ascending order,
 * convert it to a height balanced BST.
 * 
 */
public class m109_SortedListToBST {
	public class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
		}
	}

	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}

	public TreeNode sortedListToBST(ListNode head) {
		if (null == head) return null;
		
		List<Integer> list = new ArrayList<Integer>();
		do {
			list.add(head.val);
			head = head.next;
		} while (null != head);
		
		return insert(list, 0, list.size()-1);
	}
	
	private TreeNode insert(List<Integer> list, int start, int end) {
		if (null == list || list.isEmpty()) return null;
		if (end < start) return null;
		if (end == start) return new TreeNode(list.get(start));
		
		int mid = (end + 1 - start) / 2;
		TreeNode root = new TreeNode(list.get(start + mid));
		root.left = insert(list, start, start + mid-1);
		root.right = insert(list, start + mid+1, end);
		
		return root;
	}
}
