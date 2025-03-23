package com.basic.thread;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Semaphore;

/**
 * @Author hongjian.li
 * @Description
 * @Date 3/8/2025 9:06 PM
 **/
@Slf4j(topic = "c.Test11")
public class Test16 {
    static int r = 0;

    public static void main(String[] args) throws InterruptedException {
        //信号量 可以让线程顺序执行
        Semaphore semaphore = new Semaphore(1);
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                try {
                    semaphore.acquire();
                    log.debug("running............");
                    Thread.sleep(1000);
                    log.debug("end............");
                } catch (InterruptedException exception) {
                    exception.printStackTrace();
                }finally {
                    semaphore.release();
                }
            }).start();
        }
    }
}
