package Linkedlist;

/**
 * @Author hongjian.li
 * @Description
 * @Date 9/15/2024 4:21 PM
 **/

/*
        You are given the heads of two sorted linked lists list1 and list2.

        Merge the two lists into one sorted list. The list should be made by splicing together the nodes of the first two lists.

        Return the head of the merged linked list.
*/
public class MergeTwoSortedLists_21 {

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

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        //创建一个虚节点
        ListNode dummy = new ListNode(-1);
        ListNode current = dummy;

        //双指针逐步比较List1和List2
        ListNode p1 = list1, p2 = list2;
        while (p1 != null && p2 != null) {
            //比较P1和p2的两个指针
            //将值较小的节点接到P节点
            if (p1.val <= p2.val) {
                current.next = p1;
                p1 = p1.next;
            } else {
                current.next = p2;
                p2 = p2.next;
            }
            //当前指针不断前进
            current = current.next;
        }

        //处理剩余节点
        if (p1 != null) {
            current.next = p1;
        } else if (p2 != null) {
            current.next = p2;
        }
        //返回合并后的链表的头节点
        return dummy.next;
    }

    public static void main(String[] args) {
        MergeTwoSortedLists_21 solution = new MergeTwoSortedLists_21();
        // 初始化链表1 [1,2,4]
        ListNode list1 = new ListNode(1, new ListNode(2, new ListNode(4)));

        // 初始化链表2 [1,3,4]
        ListNode list2 = new ListNode(1, new ListNode(3, new ListNode(4)));

        // 合并链表
        ListNode result = solution.mergeTwoLists(list1, list2);

        // 输出合并后的链表
        while (result != null) {
            System.out.print(result.val + " ");
            result = result.next;
        }
    }
}
