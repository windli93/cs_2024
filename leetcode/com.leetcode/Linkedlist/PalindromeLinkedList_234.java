package Linkedlist;

/**
 * @Author hongjian.li
 * @Description
 * @Date 9/13/2024 3:30 PM
 **/

/*Given the head of a singly linked list, return true if it is a palindrome or false otherwise.*/


public class PalindromeLinkedList_234 {

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

    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }

        //找到链表的中间节点
        ListNode fast = head;
        ListNode slow = head;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        //如果链表长度是奇数，则慢指针需要再往后面后移一位
        if (fast != null) {
            slow = slow.next;
        }
        //反转链表的后半部分
        slow = reverseNode(slow);
        ListNode secondHalf = slow;

        //比较前半部分和反转后的后半部分
        ListNode firstHalf = head;
        while (secondHalf != null) {
            if (firstHalf.val != secondHalf.val) {
                return false;
            }
            firstHalf = firstHalf.next;
            secondHalf = secondHalf.next;
        }
        return true;
    }

    //反转辅助函数
    private ListNode reverseNode(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;

        while (curr != null) {
            ListNode nextTemp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextTemp;
        }
        return prev;
    }


    public static void main(String[] args) {

        // 初始化链表1
        int[] values = {1, 2, 2, 1};
        PalindromeLinkedList_234.ListNode head = new PalindromeLinkedList_234.ListNode(values[0]);
        PalindromeLinkedList_234.ListNode nodeA = head;

        for (int i = 1; i < values.length; i++) {
            nodeA.next = new PalindromeLinkedList_234.ListNode(values[i]);
            nodeA = nodeA.next;
        }

        PalindromeLinkedList_234 linkedList234 = new PalindromeLinkedList_234();
        boolean result = linkedList234.isPalindrome(head);
        System.out.println(result);
    }
}
