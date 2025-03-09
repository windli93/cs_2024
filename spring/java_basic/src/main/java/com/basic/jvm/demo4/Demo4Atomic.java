package com.basic.jvm.demo4;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author hongjian.li
 * @Description
 * @Date 3/2/2025 6:17 PM
 **/
public class Demo4Atomic {
    private static AtomicInteger i = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(()->{
            for (int j = 0; j < 5000; j++) {
                i.getAndIncrement();
            }
        });

        Thread t2 = new Thread(()->{
            for (int j = 0; j < 5000; j++) {
                i.getAndDecrement();
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println(i);
    }
}
