package com.basic.jvm.demo4;

/**
 * @Author hongjian.li
 * @Description 单例模式，双重检查锁
 * @Date 3/2/2025 2:58 PM
 **/
public class Singleton {
    //禁止指令重排序
    private static volatile Singleton INSTANCE = null;

    public static Singleton getInstance() {
        //示例没创建，才会进入内部的synchronized代码块,用Volatile保障有序性
        if (INSTANCE == null) {
            //首次使用才会加锁
            synchronized (Singleton.class) {
                //也许有其他线程已经创建实例，所以再判断一下
                if (INSTANCE == null) {
                    INSTANCE = new Singleton();
                }
            }
        }
        return INSTANCE;
    }
    //hapendsBefore规定了共享变量的写操作对其他线程操作的读操作是否可见，
}
