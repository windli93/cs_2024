package Linkedlist;

/**
 * @Author hongjian.li
 * @Description
 * @Date 2/11/2025 9:53 PM
 **/
public class RemoveNthFromEnd_19 {

    private static class ListNode {
        int val;
        ListNode next;

        public ListNode(int val) {
            this.val = val;
        }
    }

    //使用快慢指针来解决
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0);  // 创建一个虚拟头节点
        dummy.next = head;
        ListNode p1 = dummy;
        //先往前走K步
        for (int i = 0; i < n; i++) {
            if (p1 == null) {
                return null;
            }
            p1 = p1.next;
        }

        ListNode p2 = dummy;
        while (p1.next != null) {
            p1 = p1.next;
            p2 = p2.next;
        }
        //删掉倒数第N个节点
        p2.next = p2.next.next;
        return dummy.next;
    }

    public static void main(String[] args) {
        // 初始化链表1
        int[] values = {1, 2, 3, 4, 5};
        RemoveNthFromEnd_19.ListNode head = new RemoveNthFromEnd_19.ListNode(values[0]);
        RemoveNthFromEnd_19.ListNode nodeA = head;

        for (int i = 1; i < values.length; i++) {
            nodeA.next = new RemoveNthFromEnd_19.ListNode(values[i]);
            nodeA = nodeA.next;
        }

        RemoveNthFromEnd_19 fromEnd19 = new RemoveNthFromEnd_19();
        ListNode result = fromEnd19.removeNthFromEnd(head, 2);

        while (result != null) {
            System.out.println(result.val);
            result = result.next;
        }
    }
}
