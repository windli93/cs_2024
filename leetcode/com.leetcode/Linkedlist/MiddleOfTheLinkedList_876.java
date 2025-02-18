package Linkedlist;

/**
 * @Author hongjian.li
 * @Description
 * @Date 9/22/2024 11:34 PM
 **/

//        Given the head of a singly linked list, return the middle node of the linked list.
//        If there are two middle nodes, return the second middle node.

public class MiddleOfTheLinkedList_876 {

    private static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public ListNode middleNode(ListNode head) {
        if (head == null) {
            return null; //如果链表为空，直接返回null
        }
        ListNode fast = head;
        ListNode slow = head;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        //当指针到达链表末尾的时候，慢指针处于中间位置
        return slow;
    }

    public static void main(String[] args) {
        // 初始化链表1
        int[] values = {1, 2, 3, 4, 5};
        MiddleOfTheLinkedList_876.ListNode head = new MiddleOfTheLinkedList_876.ListNode(values[0]);
        MiddleOfTheLinkedList_876.ListNode nodeA = head;

        for (int i = 1; i < values.length; i++) {
            nodeA.next = new MiddleOfTheLinkedList_876.ListNode(values[i]);
            nodeA = nodeA.next;
        }

        MiddleOfTheLinkedList_876 linkedList876 = new MiddleOfTheLinkedList_876();
        ListNode middle = linkedList876.middleNode(head);
        while (middle != null) {
            System.out.println(middle.val);
            middle = middle.next;
        }
    }
}
