package excercise;
/*
 * File: A.java
 * Date: 2020-07-19 22:12
 * Author: msw
 * PS ...
 */


public class A {

    public static String a = printStr("父类的 静态 变量");
    public String b = printStr("父类的 非静态 变量");

    static {
        printStr("父类的 静态 代码块");
    }

    {
        printStr("父类的 非静态 代码块");
    }

    public A() {
        printStr("父类的 构造方法");
    }

    public static String printStr(String str) {
        System.out.println(str);
        return str;
    }
}