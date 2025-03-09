package com.basic.thread;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author hongjian.li
 * @Description 都是可重入锁
 * @Date 3/9/2025 3:19 PM
 **/
@Slf4j
public class Test13 {

    public static ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) {
        try {
            lock.lock();
            log.info("获取可重入锁");
            m1();
        } finally {
            lock.unlock();
        }
    }

    public static void m1() {
        lock.lock();
        try {
            log.debug("enter m1");
            m2();
        } finally {
            lock.unlock();
        }
    }


    public static void m2() {
        lock.lock();
        try {
            log.debug("enter m2");
        } finally {
            lock.unlock();
        }
    }
}
