package com.basic.jvm;

/**
 * @Author hongjian.li
 * @Description 单例模式，双重检查锁
 * @Date 3/2/2025 2:58 PM
 **/
public class Demo4_singleton {

    private Demo4_singleton() {
    }

    //禁止指令重排序
    private static volatile Demo4_singleton INSTANCE = null;

    public static Demo4_singleton getInstance() {
        //示例没创建，才会进入内部的synchronized代码块
        if (INSTANCE == null) {
            synchronized (Demo4_singleton.class) {
                //也许有其他线程已经创建实例，所以再判断一下
                if (INSTANCE == null) {
                    INSTANCE = new Demo4_singleton();
                }
            }
        }
        return INSTANCE;
    }
}
