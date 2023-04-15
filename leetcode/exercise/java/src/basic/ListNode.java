package basic;

public class ListNode {
    public int val;
    public ListNode next;

    public ListNode(int x) {
        val = x;
    }

    public static ListNode deserialize(int[] arr) {
        if (arr == null || arr.length == 0)
            return null;

        ListNode head = new ListNode(arr[0]);
        ListNode node = head;
        for (int i = 1; i < arr.length; ++i) {
            node.next = new ListNode(arr[i]);
            node = node.next;
        }
        return head;
    }
}
