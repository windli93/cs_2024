package Linkedlist;

/**
 * @Author hongjian.li
 * @Description 给你一个链表的头节点 head 和一个特定值 x ，请你对链表进行分隔，使得所有 小于 x 的节点都出现在 大于或等于 x 的节点之前。
 * <p>
 * 你应当 保留 两个分区中每个节点的初始相对位置。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：head = [1,4,3,2,5,2], x = 3
 * 输出：[1,2,2,4,3,5]
 * 示例 2：
 * <p>
 * 输入：head = [2,1], x = 2
 * 输出：[1,2]
 * @Date 2/11/2025 8:29 PM
 **/
public class Partitionlist_86 {

    private static class ListNode {
        int val;
        ListNode next;

        public ListNode(int val) {
            this.val = val;
        }
    }

    public ListNode partition(ListNode head, int x) {
        //虚节点1
        ListNode dummy1 = new ListNode(-1);
        //虚节点2
        ListNode dummy2 = new ListNode(-1);

        ListNode p1 = dummy1, p2 = dummy2;

        ListNode p = head;
        //递归循环
        while (p != null) {
            if (p.val < x) {
                p1.next = p;
                p1 = p1.next;
            } else {
                p2.next = p;
                p2 = p2.next;
            }

            //断开原链表中的每个节点的指针
            ListNode temp = p.next;
            p.next = null;
            p = temp;
        }
        //连接两个链表
        p1.next = dummy2.next;
        return dummy1.next;
    }

    public static void main(String[] args) {
        // 初始化链表1
        int[] values = {1, 4, 3, 2, 5, 2};
        Partitionlist_86.ListNode head = new Partitionlist_86.ListNode(values[0]);
        Partitionlist_86.ListNode nodeA = head;

        for (int i = 1; i < values.length; i++) {
            nodeA.next = new Partitionlist_86.ListNode(values[i]);
            nodeA = nodeA.next;
        }

        Partitionlist_86 partitionlist86 = new Partitionlist_86();
        ListNode listNode = partitionlist86.partition(head, 3);

        while (listNode != null) {
            System.out.println(listNode.val);
            listNode = listNode.next;
        }
    }
}
