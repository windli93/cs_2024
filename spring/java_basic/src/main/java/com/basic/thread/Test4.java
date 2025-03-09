package com.basic.thread;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author hongjian.li
 * @Description
 * @Date 3/8/2025 8:39 PM
 **/
@Slf4j(topic = "c.Test4")
public class Test4 {

    public static void main(String[] args) {
        Thread t1 = new Thread("t1") {
            @Override
            public void run() {
                log.debug("running");
            }
        };
        System.out.println(t1.getState());
        t1.start();
//        System.out.println(t1.getState());
        log.debug("do other things");
    }
}
