package com.basic.thread;

/**
 * @Author hongjian.li
 * @Description 线程状态与同步
 * @Date 2/6/2025 10:26 PM
 **/
public class SynchronizedExample {

    private static class Counter {
        private int counter = 0;

        public synchronized void increment() {
            counter++;
            System.out.println("Thread name" + Thread.currentThread().getName() + "Counter num" + counter);
        }

        public int getCounter() {
            return counter;
        }
    }


    public static void main(String[] args) throws InterruptedException {
        Counter counter = new SynchronizedExample.Counter();
        Runnable task = () -> {
            for (int i = 0; i < 1000; i++) {
                counter.increment();
            }
        };

        Thread thread1 = new Thread(task);
        Thread thread2 = new Thread(task);

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println("Final count" + counter.getCounter());
    }

}
