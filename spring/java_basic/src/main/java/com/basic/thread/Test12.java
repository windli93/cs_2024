package com.basic.thread;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author hongjian.li
 * @Description
 * @Date 3/8/2025 9:06 PM
 **/
@Slf4j(topic = "c.Test12")
public class Test12 {

    public static void main(String[] args) throws InterruptedException {
        log.debug("开始");
        Thread t1 = new Thread(() -> {
            while (true) {

            }
        }, "t1");

        t1.start();
        Thread.sleep(1000);
        log.debug("interrupt");
        t1.interrupt();
        log.debug("打断标志：{}", t1.isInterrupted());
    }
}
