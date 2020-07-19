package excercise;
/*
 * File: B.java
 * Date: 2020-07-19 22:16
 * Author: msw
 * PS ...
 */


public class B extends A {
    public static String a = printStr("子类的 静态 变量");
    public String b = printStr("子类的 非静态 变量");

    static {
        printStr("子类的 静态 代码块");
    }

    {
        printStr("子类的 非静态 代码块");
    }

    public B() {
        printStr("子类的 构造方法");
    }

    public static void main(String[] args) {
        new B();
    }
}

