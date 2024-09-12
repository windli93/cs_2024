package node;

/**
 * @Author hongjian.li
 * @Description 反转链表
 * @Date 9/12/2024 5:12 PM
 **/

/*
 * Given the head of a singly linked list, reverse the list, and return the reversed list.
 * */
public class ReverseLinkedList_206 {

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

    public ListNode reverseList(ListNode head) {
        ListNode prev = null; //定义为前一个节点，初始化为Null
        ListNode current = head;  //当前节点

        while (current != null) {
            ListNode next = current.next; //保存下一个节点，防止链表断裂
            current.next = prev;       //反转当前节点的指针
            prev = current;            //前一个节点向前移动
            current = next;            //当前节点向前移动
        }
        return prev; //返回新的头节点
    }


    public static void main(String[] args) {

        // 初始化链表1
        int[] values = {1, 2, 3, 4, 5};
        ReverseLinkedList_206.ListNode head = new ReverseLinkedList_206.ListNode(values[0]);
        ReverseLinkedList_206.ListNode nodeA = head;

        for (int i = 1; i < values.length; i++) {
            nodeA.next = new ReverseLinkedList_206.ListNode(values[i]);
            nodeA = nodeA.next;
        }

        ReverseLinkedList_206 linkedList206 = new ReverseLinkedList_206();
        ReverseLinkedList_206.ListNode node1 = linkedList206.reverseList(head);
        System.out.println(node1.val);
    }
}
