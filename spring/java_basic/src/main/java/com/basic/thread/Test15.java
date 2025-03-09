package com.basic.thread;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author hongjian.li
 * @Description 都是可重入锁
 * @Date 3/9/2025 3:19 PM
 **/
@Slf4j
public class Test15 {

    public static ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) {
       Thread t1 = new Thread(()->{
            try {
                //如果没有竞争，那么此方法就会获取lock对象锁
                //如果有竞争，就进入阻塞队列，可以被其他线程的Interruptibly打断
                if (lock.tryLock()){

                }
                lock.lockInterruptibly();
                log.info("获取可重入锁");
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            } finally {
                lock.unlock();
            }
        });

       t1.start();
       log.debug("我打断了");
       t1.interrupt();
    }
}
