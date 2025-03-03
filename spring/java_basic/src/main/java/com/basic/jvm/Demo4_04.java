package com.basic.jvm;

/**
 * @Author hongjian.li
 * @Description 工作内存中的数据还没同步回主内存，下一个线程就已经开始对共享变量进行修改，时间片轮换造成了数据一次
 * @Date 3/2/2025 2:58 PM
 **/
public class Demo4_04 {

    int num = 0;
    boolean ready = false;

    public void actor1(int i) {
        if (ready) {
            i = num + num;
        } else {
            i = 1;
        }
    }

    public void actor2(int i) {
        num = 2;
        ready = true;
    }
}
