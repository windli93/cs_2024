package com.basic.thread;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author hongjian.li
 * @Description
 * @Date 3/8/2025 9:06 PM
 **/
@Slf4j(topic = "c.Test11")
public class Test11 {
    static int r = 0;

    public static void main(String[] args) throws InterruptedException {
        log.debug("开始");
        Thread t1 = new Thread(() -> {
            log.debug("sleep....");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }
        }, "t1");

        t1.start();
        log.debug("interrupt");
        t1.interrupt();
        log.debug("打断标志：{}", t1.isInterrupted());
    }
}
