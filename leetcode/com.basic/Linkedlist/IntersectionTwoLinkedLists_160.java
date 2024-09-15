package Linkedlist;

/**
 * @Author hongjian.li
 * @Description 获取相交链表
 * @Date 9/12/2024 3:33 PM
 **/

/*
Given the heads of two singly linked-lists headA and headB, return the node at which the two lists intersect. If the two linked lists have no intersection at all, return null.

For example, the following two linked lists begin to intersect at node c1:
* */

public class IntersectionTwoLinkedLists_160 {

    private static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {

        //计算A链表的长度
        int lengthA = getLength(headA);
        //计算B链表的长度
        int lengthB = getLength(headB);

        //对齐两个链表的长度
        while (lengthA > lengthB) {
            headA = headA.next;
            lengthA--;
        }

        while (lengthA < lengthB) {
            headB = headB.next;
            lengthB--;
        }

        //同时遍历两个链表，寻找交点
        while (headA != headB) {
            headA = headA.next;
            headB = headB.next;
        }

        //返回交点或者Null
        return headA;
    }

    /**
     * @Author hongjian.li
     * @Description 获取长度
     **/
    private int getLength(ListNode head) {
        int length = 0;
        while (head != null) {
            length++;
            head = head.next;
        }
        return length;
    }


    public static void main(String[] args) {
        // 初始化链表1
        int[] values = {1,9,1,2,4};
        ListNode head = new ListNode(values[0]);
        ListNode nodeA = head;

        for (int i = 1; i < values.length; i++) {
            nodeA.next = new ListNode(values[i]);
            nodeA = nodeA.next;
        }

        // 初始化链表2
        int[] values1 = {3};
        ListNode head1 = new ListNode(values1[0]);
        ListNode nodeB = head1;

        // 设置链表1和链表2的交点：链表2连接到链表1的第3个节点处
        ListNode temp = head;
        for (int i = 0; i < 2; i++) {  // 跳到链表1的第3个节点
            temp = temp.next;
        }
        nodeB.next = temp;  // 设置交点

        // 调用查找交点的方法
        IntersectionTwoLinkedLists_160 linkedLists160 = new IntersectionTwoLinkedLists_160();
        ListNode node1 = linkedLists160.getIntersectionNode(head, head1);

        // 打印结果
        if (node1 != null) {
            System.out.println("交点值: " + node1.val);
        } else {
            System.out.println("没有交点");
        }
    }

}
