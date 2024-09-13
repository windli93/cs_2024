package node;

/**
 * @Author hongjian.li
 * @Description
 * @Date 9/13/2024 4:51 PM
 **/

/*      Given head, the head of a linked list, determine if the linked list has a cycle in it.

        There is a cycle in a linked list if there is some node in the list that can be reached again by continuously following the next pointer. Internally, pos is used to denote the index of the node that tail's next pointer is connected to. Note that pos is not passed as a parameter.

        Return true if there is a cycle in the linked list. Otherwise, return false.
        */
public class LinkedListCycle_141 {

    private static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }

        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next; //慢指针走一步
            fast = fast.next.next; //快指针走两步

            if (slow == fast) {
                return true; //如果快慢相遇，则存在环
            }
        }
        return false; //快指针到达链表末尾，无环
    }


    public static void main(String[] args) {
        // 初始化链表1
        int[] values = {3, 2, 0, -4};
        LinkedListCycle_141.ListNode head = new LinkedListCycle_141.ListNode(values[0]);
        LinkedListCycle_141.ListNode nodeA = head;

        for (int i = 1; i < values.length; i++) {
            nodeA.next = new LinkedListCycle_141.ListNode(values[i]);
            nodeA = nodeA.next;
        }

        LinkedListCycle_141 cycle141 = new LinkedListCycle_141();
        boolean result = cycle141.hasCycle(head);
        System.out.println(result);
    }
}
