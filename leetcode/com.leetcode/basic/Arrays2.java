package basic;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class Arrays2<E> {

    //动态数组关键点一：自动扩缩容
    //动态数组关键点一：索引越界检查
    //动态数组关键点一：删除元素会引起内存泄露

    //真正存储数据的底层数组
    private E[] data;
    //记录当前元素的个数
    private int size;
    //默认初始容量
    private static final int INIT_CAP = 1;

    public Arrays2() {
        this(INIT_CAP);
    }

    public Arrays2(int initCapacity) {
        data = (E[]) new Object[initCapacity];
        size = 0;
    }

    //新增最后索引位置
    public void addLast(E e) {
        int cap = data.length;
        //看data数组容量不够
        if (size == cap) {
            resize(2 * cap);
        }

        //在尾部插入元素
        data[size] = e;
        size++;
    }

    // 插入指定索引位置
    public void add(int index, E e) {
        //检查索引越界
        checkPositionIndex(index);

        int cap = data.length;
        //看data数组元素不够
        if (size == cap) {
            resize(2 * cap);
        }

        //搬运数据 data[index] -> data[index+1]
        //给元素腾出位置
        for (int i = size - 1; i >= index; i--) {
            data[i + 1] = data[i];
        }

        //插入新元素
        data[index] = e;
        size++;
    }

    public void addFirst(E e) {
        add(0, e);
    }

    //删
    public E removeLast() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        int cap = data.length;
        //可以缩容，节约空间
        if (size == cap / 4) {
            resize(cap / 2);
        }
        E deleteVal = data[size - 1];
        //删除最后一个元素
        //必须给最后一个元素为null，否则内存泄露
        data[size - 1] = null;
        size--;
        return deleteVal;
    }

    public E removeFirst() {
        return remove(0);
    }

    //查
    public E get(int index) {
        checkElementIndex(index);
        return data[index];
    }

    //改
    public E set(int index, E element) {
        //检查索引越界
        checkElementIndex(index);
        //修改数据
        E oldVal = data[index];
        data[index] = element;
        return oldVal;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public E remove(int index) {
        //检查索引越界
        checkElementIndex(index);

        int cap = data.length;
        //可以缩容，节约空间
        if (size == cap / 4) {
            resize(cap / 2);
        }
        E deleteVal = data[index];
        //搬移数据
        for (int i = index + 1; i < size; i++) {
            data[i - 1] = data[i];
        }
        data[size - 1] = null;
        return deleteVal;
    }

    //将容量改成newcap
    private void resize(int newCap) {
        E[] temp = (E[]) new Object[newCap];
        for (int i = 0; i < size; i++) {
            temp[i] = data[i];
        }
        data = temp;
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
            throw new IndexOutOfBoundsException("Index:" + index + ", Size:" + size);
        }
    }

    /**
     * @Author hongjian.li
     * @Description 检查index位置是否可以添加元素
     **/
    private void checkPositionIndex(int index) {
        if (!isPositionIndex(index)) {
            throw new IndexOutOfBoundsException("Index:" + index + ", Size:" + size);
        }
    }

    private void disPlay() {
        System.out.println("Size = " + size + "cap = " + data.length);
        System.out.println(Arrays.toString(data));
    }

    public static void main(String[] args) {

        //初始容量
        Arrays2<Integer> arrays2 = new Arrays2<>(3);
        //添加元素
        for (int i = 0; i <= 5; i++) {
            arrays2.addLast(i);
        }
        arrays2.remove(3);
        arrays2.add(1, 9);
        arrays2.removeLast();
        for (int i = 0; i < arrays2.size(); i++) {
            System.out.println(arrays2.get(i));
        }
    }

}
