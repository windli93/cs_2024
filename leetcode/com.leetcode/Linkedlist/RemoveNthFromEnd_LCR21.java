package Linkedlist;

/**
 * @Author hongjian.li
 * @Description
 * @Date 10/3/2024 9:04 PM
 **/
public class RemoveNthFromEnd_LCR21 {

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


    public ListNode removeNthFromEnd(ListNode head, int cnt) {

        ListNode fast = head;
        ListNode slow = head;

        //让fast指针先走，然后返回slow，就可以得到倒数前几部
        for (int i = 0; i < cnt; i++) {
            if (fast == null) {
                return null;
            }
            fast = fast.next;
        }

        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }

        while (head != null) {
            if (head.next == slow) {
                head.next = head.next.next;
                return head;
            }
            head = head.next;
        }

        return head;
    }

    public static void main(String[] args) {
        // 初始化链表1
        int[] values = {1, 2, 3, 4, 5};

        RemoveNthFromEnd_LCR21.ListNode head = new RemoveNthFromEnd_LCR21.ListNode(values[0]);
        RemoveNthFromEnd_LCR21.ListNode nodeA = head;

        for (int i = 1; i < values.length; i++) {
            nodeA.next = new RemoveNthFromEnd_LCR21.ListNode(values[i]);
            nodeA = nodeA.next;
        }

        RemoveNthFromEnd_LCR21 lcr21 = new RemoveNthFromEnd_LCR21();
        RemoveNthFromEnd_LCR21.ListNode listNode = lcr21.removeNthFromEnd(head, 2);
        System.out.println(listNode.val);
    }
}
