/**
 * @Author hongjian.li
 * @Description 双链表
 * @Date 2024/7/9 16:47
 **/
public class DoubleLinkedList {

    static class DoubleListNode {
        int val;
        DoubleListNode next, prev;

        DoubleListNode(int x) {
            val = x;
        }
    }

    DoubleListNode createDoubleList(int[] arr) {
        if (arr == null || arr.length == 0) {
            return null;
        }

        DoubleListNode head = new DoubleListNode(arr[0]);
        DoubleListNode curr = head;
        for (int i = 1; i < arr.length; i++) {
            DoubleListNode newNode = new DoubleListNode(arr[i]);
            curr.next = newNode;
            newNode.prev = curr;
            curr = curr.next;
        }
        return head;
    }

//    public static void main(String[] args) {
//        DoubleLinkedList linkedList = new DoubleLinkedList();
//        DoubleListNode head = linkedList.createDoubleList(new int[]{1, 2, 3, 4, 5});
//
//        //从头遍历数据
//        for (DoubleListNode p = head; p != null; p = p.next) {
//            System.out.println(p.val);
//        }
//
//        //从尾遍历双链表
//        for (DoubleListNode p = head; p != null; p = p.prev) {
//            System.out.println(p.val);
//        }
//    }

//    public static void main(String[] args) {
//        DoubleLinkedList linkedList = new DoubleLinkedList();
//        DoubleListNode head = linkedList.createDoubleList(new int[]{1, 2, 3, 4, 5});
//
//        // 在双链表头部插入新节点 0
//        DoubleListNode newHead = new DoubleListNode(0);
//        newHead.next = head;
//        head.prev = newHead;
//        head = newHead;
//        // 现在链表变成了 0 -> 1 -> 2 -> 3 -> 4 -> 5
//    }

//    public static void main(String[] args) {
//        DoubleLinkedList linkedList = new DoubleLinkedList();
//        DoubleListNode head = linkedList.createDoubleList(new int[]{1, 2, 3, 4, 5});
//        DoubleListNode tail = head;
//        // 先走到链表的最后一个节点
//        while (tail.next != null) {
//            tail = tail.next;
//        }
//
//        // 在双链表尾部插入新节点 6
//        DoubleListNode newNode = new DoubleListNode(6);
//        tail.next = newNode;
//        newNode.prev = tail;
//        // 更新尾节点引用
//        tail = newNode;
//        // 现在链表变成了 1 -> 2 -> 3 -> 4 -> 5 -> 6
//    }

//    public static void main(String[] args) {
//        DoubleLinkedList linkedList = new DoubleLinkedList();
//        DoubleListNode head = linkedList.createDoubleList(new int[]{1, 2, 3, 4, 5});
//
//        // 在第 3 个节点后面插入新节点 66
//        // 找到第 3 个节点
//        DoubleListNode p = head;
//        for (int i = 0; i < 2; i++) {
//            p = p.next;
//        }
//
//        // 组装新节点
//        DoubleListNode newNode = new DoubleListNode(66);
//        newNode.next = p.next;
//        newNode.prev = p;
//
//        // 插入新节点
//        p.next.prev = newNode;
//        p.next = newNode;
//    }
}
