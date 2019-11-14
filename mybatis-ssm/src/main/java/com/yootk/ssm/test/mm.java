package com.yootk.ssm.test;

/**
 * ******Created by—— IntelliJ IDEA******
 * User: halfhoney2018@gmail.com
 * Time:2019/11/13 20:37
 * DESC:
 **/
public class mm {
    public static void main(String[] args) {
        String s="hello world!";
        byte[] bytes = s.getBytes();
        for (byte b :bytes){
            System.out.println(new String(new byte [] {b
            }));
        }
        System.out.println(s.getBytes());
    }
}
