package com.basic.jvm.demo4;

/**
 * @Author hongjian.li
 * @Description 工作内存中的数据还没同步回主内存，下一个线程就已经开始对共享变量进行修改，时间片轮换造成了数据一次
 * @Date 3/2/2025 2:58 PM
 **/
public class Demo4_03 {

    static boolean run = true;


    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(() -> {
            while (run) {
                System.out.println(1);
            }
        });

        t.start();
        Thread.sleep(1000);
        run = false;
    }
}
