package com.basic.jvm;

/**
 * @Author hongjian.li
 * @Description 模拟栈内存溢出
 * @Date 9/5/2024 4:27 PM
 **/
public class Demo1_2 {

    /**
      *@Author hongjian.li
      *@Description 1. 栈溢出主要是两个原因，一个是栈帧分配过多，一个是栈帧分配过大
      **/

    private static int count;

    public static void main(String[] args) {
        try {
            method1();
        } catch (Throwable e) {
            e.printStackTrace();
            System.out.println(count);
        }
    }

    private static void method1() {
        count++;
        method1();
    }
}
