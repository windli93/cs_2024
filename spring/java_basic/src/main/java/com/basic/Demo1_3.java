package com.basic;

/**
 * @Author hongjian.li
 * @Description
 * @Date 9/5/2024 4:51 PM
 **/
class A {
};

class B {
};

public class Demo1_3 {

    static A a = new A();
    static B b = new B();

    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            synchronized (a) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (b) {
                    System.out.println("我获得了a和b");
                }
            }
        }).start();
        Thread.sleep(1000L);
        new Thread(() -> {
            synchronized (b) {
                synchronized (a) {
                    System.out.println("我获得了a 和 b");
                }
            }
        }).start();
    }
}
