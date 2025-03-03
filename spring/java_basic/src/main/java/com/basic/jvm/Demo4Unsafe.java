package com.basic.jvm;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @Author hongjian.li
 * @Description 工作内存中的数据还没同步回主内存，下一个线程就已经开始对共享变量进行修改，时间片轮换造成了数据一次
 * @Date 3/2/2025 2:58 PM
 **/
public class Demo4Unsafe {

    private volatile int data;
    static final Unsafe unsafe;
    static final long DATA_OFFSET = 0L;

    static {
        try {
            //Unsafe 对象不能直接调用，只能通过反射获得
            Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
            theUnsafe.setAccessible(true);
            unsafe = (Unsafe) theUnsafe.get(null);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new Error(e);
        }
    }

    public void increase() {
        int oldValue;
        while (true) {
            //获取共享变量旧值，可以在这一行加入断点，修改data调用来加深理解
            oldValue = data;
            //cas 尝试修改data为旧值+1，如果期间旧值被别的线程改了，返回false
            if (unsafe.compareAndSwapLong(this, DATA_OFFSET, oldValue, oldValue + 1)) {
                return;
            }
        }
    }


    public void decrease() {
        int oldValue;
        while (true) {
            //获取共享变量旧值，可以在这一行加入断点，修改data调用来加深理解
            oldValue = data;
            //cas 尝试修改data为旧值+1，如果期间旧值被别的线程改了，返回false
            if (unsafe.compareAndSwapLong(this, DATA_OFFSET, oldValue, oldValue - 1)) {
                return;
            }
        }
    }

    public int getData() {
        return data;
    }

    public static void main(String[] args) throws InterruptedException {

        Demo4Unsafe unsafe = new Demo4Unsafe();
        int count = 5;

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < count; i++) {
                unsafe.increase();
                ;
            }
        });

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < count; i++) {
                unsafe.decrease();
            }
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(unsafe.getData());
    }
}

