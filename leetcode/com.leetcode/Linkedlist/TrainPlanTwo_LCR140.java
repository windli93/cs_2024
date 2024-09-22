package Linkedlist;

/**
 * @Author hongjian.li
 * @Description LCR 140. 训练计划 II
 * @Date 9/22/2024 11:45 PM
 **/
public class TrainPlanTwo_LCR140 {

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


    public ListNode trainingPlan(ListNode head, int cnt) {
        if (head == null) {
            return null;
        }

        ListNode fast = head;
        ListNode slow = head;

        //要让fast指针先走，然后返回slow，就可以得到倒数前几步
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

        return slow;
    }

    public static void main(String[] args) {
        // 初始化链表1
        int[] values = {1, 2, 3, 4, 5};
        TrainPlanTwo_LCR140.ListNode head = new TrainPlanTwo_LCR140.ListNode(values[0]);
        TrainPlanTwo_LCR140.ListNode nodeA = head;

        for (int i = 1; i < values.length; i++) {
            nodeA.next = new TrainPlanTwo_LCR140.ListNode(values[i]);
            nodeA = nodeA.next;
        }

        TrainPlanTwo_LCR140 lcr140 = new TrainPlanTwo_LCR140();
        ListNode listNode = lcr140.trainingPlan(head, 2);
        System.out.println(listNode.val);
    }
}
