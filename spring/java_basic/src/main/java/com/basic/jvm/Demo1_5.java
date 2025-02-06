package com.basic.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author hongjian.li
 * @Description 演示堆内存溢出，Java heap space 每次分配1M内存
 * @Date 9/5/2024 4:06 PM
 **/
public class Demo1_5 {

    public static void main(String[] args) {
        int i = 0;
        try {
            List<byte[]> list = new ArrayList<>();
            while (true) {
                // 每次分配一个 1 MB 大小的字节数组
                list.add(new byte[1024 * 1024]);
                System.out.println("Allocated 1 MB");
                i++;
            }
        } catch (Throwable a) {
            a.printStackTrace();
            System.out.println(i);
        }
    }
}
