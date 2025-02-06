package com.basic.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @Author hongjian.li
 * @Description 验证java线程的创建方式，三种
 * @Date 2/6/2025 9:29 PM
 **/
public class ThreadExample {

    private static class MyThread extends Thread {
        @Override
        public void run() {
            System.out.println("Thread running" + Thread.currentThread().getName());
        }
    }

    private static class MyRunnable implements Runnable {
        @Override
        public void run() {
            System.out.println("Runnable running" + Thread.currentThread().getName());
        }
    }

    private static class MyCallable implements Callable<Integer> {

        @Override
        public Integer call() throws Exception {
            System.out.println("MyCallable running" + Thread.currentThread().getName());
            return 1;
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //方式一
        MyThread myThread = new ThreadExample.MyThread();
        myThread.start();

        //方式二
        Thread thread = new Thread(new ThreadExample.MyRunnable());
        thread.start();

        //方式三
        MyCallable callable = new ThreadExample.MyCallable();
        // 使用 FutureTask 包装 Callable 任务
        FutureTask<Integer> futureTask = new FutureTask<>(callable);
        // 创建一个线程来执行该任务
        Thread thread1 = new Thread(futureTask);
        thread1.start();

        // 获取执行结果，get() 会阻塞直到任务执行完毕
        Integer result = futureTask.get();
        System.out.println("计算结果是：" + result);
    }
}
