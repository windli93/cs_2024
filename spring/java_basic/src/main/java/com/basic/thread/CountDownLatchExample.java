package com.basic.thread;

import java.util.concurrent.CountDownLatch;

/**
 * @Author hongjian.li
 * @Description
 * @Date 2/6/2025 10:48 PM
 **/
public class CountDownLatchExample {

    public static void main(String[] args) throws InterruptedException {
        int threadCount = 3;
        CountDownLatch latch = new CountDownLatch(threadCount);

        Runnable task = () -> {
            System.out.println("Task running:" + Thread.currentThread().getName());
            latch.countDown();
        };

        for (int i = 0; i < threadCount; i++) {
            new Thread(task).start();
        }

        latch.await();
        System.out.println("All tasks completed");
    }
}
