/**
 * @Author hongjian.li
 * @Description 单链表实现
 * @Date 2024/7/9 16:08
 **/
public class SingleLinkedList {

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    //输入一个数组，转为一个单链表
    ListNode createLinkedList(int[] arr) {
        if (arr == null || arr.length == 0) {
            return null;
        }
        ListNode head = new ListNode(arr[0]);
        ListNode curr = head;
        for (int i = 1; i < arr.length; i++) {
            curr.next = new ListNode(arr[i]);
            curr = curr.next;
        }
        return head;
    }

    //查
//    public static void main(String[] args) {
//        SingleLinkedList singleLinkedList = new SingleLinkedList();
//        //创建一个单链表
//        ListNode head = singleLinkedList.createLinkedList(new int[]{1, 2, 3, 4, 5});
//        for (ListNode p = head; p != null; p = p.next) {
//            System.out.println(p.val);
//        }
//    }

    //改
//    public static void main(String[] args) {
//        SingleLinkedList singleLinkedList = new SingleLinkedList();
//        ListNode head = singleLinkedList.createLinkedList(new int[]{1, 2, 3, 4, 5});
//
//        SingleLinkedList.ListNode newHead = new SingleLinkedList.ListNode(0);
//        newHead.next = head;
//        head = newHead;
//    }

    //改
//    public static void main(String[] args) {
//        SingleLinkedList singleLinkedList = new SingleLinkedList();
//        ListNode head = singleLinkedList.createLinkedList(new int[]{1, 2, 3, 4, 5});
//        //单链表插入新节点 6
//        ListNode p = head;
//        //先找到最后一个节点
//        while (p.next != null) {
//            p = p.next;
//        }
//        //现在p就是链表的最后一个节点
//        //在p后面插入新节点
//        p.next = new ListNode(6);
//        //现在链表变成了 1->2->3->4->5
//    }

    //增
//    public static void main(String[] args) {
//        SingleLinkedList singleLinkedList = new SingleLinkedList();
//        ListNode head = singleLinkedList.createLinkedList(new int[]{1, 2, 3, 4, 5});
//
//        ListNode p = head;
//        for (int i = 0; i < 2; i++) {
//            p = p.next;
//        }
//        //此时P指向第三个节点
//        ListNode newNode = new ListNode(66);
//        newNode.next = p.next;
//        //插入新节点
//        p.next = newNode;
//
//        // 现在链表变成了 1 -> 2 -> 3 -> 66 -> 4 -> 5
//    }

    //删
//    public static void main(String[] args) {
//        SingleLinkedList singleLinkedList = new SingleLinkedList();
//        ListNode head = singleLinkedList.createLinkedList(new int[]{1, 2, 3, 4, 5});
//
//        ListNode p = head;
//        for (int i = 0; i < 2; i++) {
//             p = p.next;
//        }
//        //此时p指向第三个节点，即要删除节点的前驱节点
//        //把第4个节点从链表里面摘除
//        p.next = p.next.next;
//        // 现在链表变成了 1 -> 2 -> 3 -> 5
//    }
}
