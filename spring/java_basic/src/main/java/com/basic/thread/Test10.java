package com.basic.thread;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author hongjian.li
 * @Description
 * @Date 3/8/2025 9:06 PM
 **/
@Slf4j(topic = "c.Test7")
public class Test10 {
    static int r = 0;

    public static void main(String[] args) throws InterruptedException {
        log.debug("开始");
        Thread t1 = new Thread("t1") {
            @SneakyThrows
            @Override
            public void run() {
                log.debug("Start...........");
                sleep(1);
                log.debug("End................");
                r = 10;
            }
        };
        t1.start();
        t1.join();

        log.debug("End111................");
        log.debug("End222................");
    }
}
