package com.ziroom.aquarius.jvmdemo;

/**
 * @author yuanpeng
 * @version 1.0
 * @Date Created in 2020年05月10日 20:28
 * @since 1.0
 */
public class Demo1 {
    static int a=10;
    static{
        System.out.println("demo1被初始化了");
    }
    int b=100;

}

class Demo3 extends Demo1{
    static{
        System.out.println("demo3被初始化了");
    }
}

class Demo2{
    public static void main(String[] args) {
        System.out.println(Demo3.a);
    }
}


