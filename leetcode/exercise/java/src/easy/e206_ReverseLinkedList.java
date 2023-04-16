package easy;

import basic.ListNode;

/**
 * Reverse a singly linked list.
 * https://leetcode.com/problems/reverse-linked-list/description/
 * 
 */
public class e206_ReverseLinkedList {
    public ListNode reverseList(ListNode head) {
        if (null == head || null == head.next)
            return head;

        ListNode cur = head;
        ListNode prev = null;
        while (null != cur) {
            ListNode temp = cur;
            cur = temp.next;
            temp.next = prev;
            prev = temp;
        }

        return prev;
    }
}
