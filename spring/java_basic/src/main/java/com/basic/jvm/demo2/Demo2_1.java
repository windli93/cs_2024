package com.basic.jvm.demo2;

import java.util.ArrayList;

/**
 * @Author hongjian.li
 * @Description
 * @Date 3/6/2025 9:54 PM
 **/
public class Demo2_1 {

    private static final int _512KB = 512 * 1024;
    private static final int _1MB = 1 * 1024 * 1024;
    private static final int _6MB = 1 * 1024 * 1024;
    private static final int _7MB = 10 * 1024 * 1024;
    private static final int _8MB = 1 * 1024 * 1024;

    public static void main(String[] args) {
        ArrayList<byte[]> list = new ArrayList<>();
        list.add(new byte[_7MB]);
        list.add(new byte[_8MB]);
        list.add(new byte[_6MB]);
    }
}
