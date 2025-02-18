package Linkedlist;

/**
 * @Author hongjian.li
 * @Description
 * @Date 9/15/2024 5:18 PM
 **/

/*
       Given the head of a linked list, return the node where the cycle begins. If there is no cycle, return null.

        There is a cycle in a linked list if there is some node in the list that can be reached again by continuously following the next pointer. Internally, pos is used to denote the index of the node that tail's next pointer is connected to (0-indexed). It is -1 if there is no cycle. Note that pos is not passed as a parameter.

        Do not modify the linked list.
*/
public class LinkedListCycleII_142 {

    private static class ListNode {
        int val;
        ListNode next;

        public ListNode(int val) {
            this.val = val;
        }
    }

    public ListNode detectCycle(ListNode head) {

        ListNode fast = head;
        ListNode slow = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast) {
                break;
            }
        }

        //没有成环的条件
        if (fast == null || fast.next == null) {
            return null;
        }

        //重新指向头节点
        slow = head;

        // 快慢指针同步前进，相交点就是环起点
        while (slow != fast) {
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }

    public static void main(String[] args) {
        // 初始化链表
        int[] values = {3, 2, 0, -4};
        LinkedListCycleII_142.ListNode head = new LinkedListCycleII_142.ListNode(values[0]);
        LinkedListCycleII_142.ListNode nodeA = head;
        LinkedListCycleII_142.ListNode cycleNode = null;  // 用于存储环起点的节点

        for (int i = 1; i < values.length; i++) {
            nodeA.next = new LinkedListCycleII_142.ListNode(values[i]);
            nodeA = nodeA.next;

            // 将节点 2 作为环的起点
            if (i == 1) {
                cycleNode = nodeA;  // 保存成环的起点节点
            }
        }

        // 形成环，最后一个节点的 next 指向节点 2
        nodeA.next = cycleNode;

        LinkedListCycleII_142 cycleII142 = new LinkedListCycleII_142();
        LinkedListCycleII_142.ListNode listNode = cycleII142.detectCycle(head);

        if (listNode != null) {
            System.out.println("环的起始节点值为: " + listNode.val);
        } else {
            System.out.println("没有环");
        }
    }
}
