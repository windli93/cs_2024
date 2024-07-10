import java.util.NoSuchElementException;

/**
 * @Author hongjian.li
 * @Description 双链表代码实现，高级版
 * @Date 2024/7/10 11:07
 **/
public class DoubleLinkedList2<E> {

    //虚拟头尾节点
    final private Node<E> head, tail;
    private int size;

    //双链表节点
    private static class Node<E> {
        E val;
        Node<E> next;
        Node<E> prev;

        Node(E val) {
            this.val = val;
        }
    }

    //构造器初始化
    public DoubleLinkedList2() {
        this.head = new Node<>(null);
        this.tail = new Node<>(null);
        head.next = tail;
        tail.next = head;
        this.size = 0;
    }

    //增
    public void addLast(E e) {
        Node<E> x = new Node<>(e);
        Node<E> temp = tail.prev;
        temp.next = x;
        x.prev = temp;
        x.next = tail;
        tail.prev = x;

        size++;
    }

    public void addFirst(E e) {
        Node<E> x = new Node<>(e);
        Node<E> temp = head.next;
        temp.prev = x;
        x.next = temp;

        head.next = x;
        x.prev = head;
        size++;
    }

    public void add(int index, E element) {
        checkPositionIndex(index);
        if (index == size) {
            addLast(element);
            return;
        }

        //找到index对应的Node
        Node<E> p = getNode(index);
        Node<E> temp = p.prev;

        //新插入的 Node
        Node<E> x = new Node<>(element);

        p.prev = x;
        temp.next = x;

        x.prev = temp;
        x.next = p;

        size++;
    }

    //删
    public E removeFirst() {
        if (size < 1) {
            throw new NoSuchElementException();
        }
        //虚拟节点的存在是我们不用考虑指针的问题
        Node<E> x = head.next;
        Node<E> temp = x.next;

        head.next = temp;
        temp.prev = head;

        x.prev = null;
        x.next = null;

        size--;
        return x.val;
    }

    public E removeLast() {
        if (size < 1) {
            throw new NoSuchElementException();
        }
        Node<E> x = tail.prev;
        Node<E> temp = tail.prev.prev;

        tail.prev = temp;
        temp.next = tail;

        x.prev = null;
        x.next = null;

        size--;
        return x.val;
    }

    public E remove(int index) {
        checkElementIndex(index);
        //找到index对应的node
        Node<E> x = getNode(index);
        Node<E> prev = x.prev;
        Node<E> next = x.next;

        prev.next = next;
        next.prev = prev;

        x.prev = x.next = null;
        size--;
        return x.val;
    }

    public E get(int index) {
        checkElementIndex(index);
        //找到index对应的node
        Node<E> p = getNode(index);
        return p.val;
    }

    public E getFirst() {
        if (size < 1) {
            throw new NoSuchElementException();
        }
        return head.next.val;
    }

    public E getLast() {
        if (size < 1) {
            throw new NoSuchElementException();
        }
        return tail.prev.val;
    }

    public E set(int index, E val) {
        checkElementIndex(index);
        //找到index对应的node
        Node<E> p = getNode(index);

        E oldVal = p.val;
        p.val = val;

        return oldVal;
    }


    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private Node<E> getNode(int index) {
        checkElementIndex(index);
        Node<E> p = head.next;
        //todo 可以优化，通过Index判断 head还是tail开始遍历
        for (int i = 0; i < index; i++) {
            p = p.next;
        }
        return p;
    }

    private boolean isElementIndex(int index) {
        return index >= 0 && index < size;
    }

    private boolean isPositionIndex(int index) {
        return index >= 0 && index <= size;
    }

    /**
     * @Author hongjian.li
     * @Description 检查index索引位置是否可以存在元素
     **/
    private void checkElementIndex(int index) {
        if (!isElementIndex(index)) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size:" + size);
        }
    }


    /**
     * @Author hongjian.li
     * @Description 检查index索引位置是否可以添加元素
     **/
    private void checkPositionIndex(int index) {
        if (!isPositionIndex(index)) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size:" + size);
        }
    }

    private void displaty() {
        System.out.println("Size = " + size);
        for (Node<E> p = head.next; p != tail; p = p.next) {
            System.out.println(p.val + " ->");
        }
        System.out.println("null");
        System.out.println();
    }

    public static void main(String[] args) {
        
    }
}
