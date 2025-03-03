package com.basic.jvm;

/**
 * @Author hongjian.li
 * @Description  工作内存中的数据还没同步回主内存，下一个线程就已经开始对共享变量进行修改，时间片轮换造成了数据一次
 * @Date 3/2/2025 2:58 PM
 **/
public class Demo4_02 {

    static int i = 0;
    static Object obj = new Object();

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            for (int j = 0; j < 5000; j++) {
                i++;
                System.out.println("t1 " + i);
            }
        });

        Thread t2 = new Thread(() -> {
            for (int j = 0; j < 5000; j++) {
                i--;
                System.out.println("t2 " + i);
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();
        System.out.println(i);
    }
}
