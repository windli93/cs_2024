package com.basic.thread;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author hongjian.li
 * @Description
 * @Date 3/8/2025 9:06 PM
 **/
@Slf4j(topic = "c.Test7")
public class Test7 {

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread() {
            @Override
            public void run() {
                log.debug("Enter Sleep...........");
                try {
                    Thread.sleep(2000);
                } catch (Exception e) {

                }
            }
        };

        t1.start();
        Thread.sleep(1000);
    }
}
