package easy;

/**
 * 
 * 83. Remove Duplicates from Sorted List
 * 
 * Total Accepted: 128265 Total Submissions: 342484 Difficulty: Easy
 * 
 * Given a sorted linked list, delete all duplicates such that each element
 * appear only once.
 * 
 * For example, Given 1->1->2, return 1->2. Given 1->1->2->3->3, return 1->2->3.
 * 
 * LeetCode: https://leetcode.com/problems/remove-duplicates-from-sorted-list/
 * 
 * @author xinwu
 *
 */
public class e083_RemoveDuplicatesFromSortedList {

	public static class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
		}
	}

	public ListNode deleteDuplicates(ListNode head) {
		ListNode current = head;
		while (null != current && null != current.next) {
			ListNode next = current.next;
			if (current.val == next.val) {
				current.next = next.next;
			} else {
				current = current.next;
			}
		}

		return head;
	}

	private static ListNode createList(int[] list) {
		if (null == list || 0 == list.length)
			return null;

		ListNode head = new ListNode(list[0]);
		ListNode current = head;
		for (int i = 1; i < list.length; ++i) {
			current.next = new ListNode(list[i]);
			current = current.next;
		}

		return head;
	}

	private static void printList(ListNode list) {
		System.out.print("[");
		while (null != list) {
			System.out.print(list.val + ", ");
			list = list.next;
		}
		System.out.println("]");
	}

	public static void main(String[] args) {
		e083_RemoveDuplicatesFromSortedList solution = new e083_RemoveDuplicatesFromSortedList();

		ListNode test = createList(new int[] { 1, 1, 1 });
		printList(test);
		solution.deleteDuplicates(test);
		printList(test);
	}

}
