package easy;

import basic.ListNode;

/**
 * Given a singly linked list, determine if it is a palindrome.
 * <p>
 * Follow up:
 * Could you do it in O(n) time and O(1) space?
 */
public class e234_PalindromeLinkedList {
    public boolean isPalindrome(ListNode head) {
        if (null == head || null == head.next)
            return true;

        ListNode slow = head;
        ListNode fast = head;
        // find mid
        while (null != fast && null != fast.next) {
            slow = slow.next;
            fast = fast.next.next;
        }
        // odd
        if (null != fast)
            slow = slow.next;
        slow = reverse(slow);

        // compare
        while (null != slow) {
            if (slow.val != head.val)
                return false;
            slow = slow.next;
            head = head.next;
        }
        return true;
    }

    public ListNode reverse(ListNode head) {
        ListNode reversed = null;
        while (null != head) {
            ListNode nextHead = head.next;
            head.next = reversed;
            reversed = head;
            head = nextHead;
        }
        return reversed;
    }

    public static void main(String[] args) {
        e234_PalindromeLinkedList solution = new e234_PalindromeLinkedList();

        System.out.println(solution.isPalindrome(ListNode.deserialize(new int[]{1, 1, 2, 1})));
        System.out.println(solution.isPalindrome(ListNode.deserialize(new int[]{1, 1, 2, 2, 1})));

        System.out.println(solution.isPalindrome(ListNode.deserialize(new int[]{1, 2, 3, 2, 1})));
        System.out.println(solution.isPalindrome(ListNode.deserialize(new int[]{1, 2, 2, 1})));
    }
}
