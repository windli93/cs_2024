package com.basic.jvm.load;

/**
 * @Author hongjian.li
 * @Description
 * @Date 3/8/2025 12:48 PM
 **/
public class Load5_3 {

    public static void main(String[] args) throws ClassNotFoundException {
        System.out.println(Load5_3.class.getClassLoader());
        Class<?> aClass = Load5_3.class.getClassLoader().loadClass("com.basic.jvm.demo4.Demo4_1");
        System.out.println(aClass.getClassLoader());
    }
}
