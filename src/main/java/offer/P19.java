package offer;
/*
 * File: P19.java
 * Date: 2020-06-06 16:49
 * Author: msw
 * P19: 对于一个给定的字符序列S，请你把其循环左移K位后的序列输出。
 *      例如，字符序列S=”abcXYZdef”,要求输出循环左移3位后的结果，即“XYZdefabc”。
 */


public class P19 {
    public static String LeftRotateString(String str,int n) {

        long startTime = System.nanoTime();    //获取开始时间

        if (n < 0 || n > str.length())
            return "";

        StringBuilder sb = new StringBuilder();

        // 先将n以后的字符保存进SB
        for (int i = n; i < str.length(); i++)
            sb.append(str.charAt(i));

        // 再把n以前的字符追加进SB
        for (int i = 0; i < n; i++)
            sb.append(str.charAt(i));

        long endTime = System.nanoTime();    //获取结束时间
        System.out.println("useTime:\t" + (endTime - startTime) + "ns");    //输出程序运行时间

        return sb.toString();

    }

    public static String L2(String str, int n) {
        long startTime = System.nanoTime();    //获取开始时间

        // wocao。。好聪明的方法
        int len = str.length();
        if (n < 0 || n > len)
            return "";
        str += str;

        long endTime = System.nanoTime();    //获取结束时间
        System.out.println("useTime:\t" + (endTime - startTime) + "ns");    //输出程序运行时间

        return str.substring(n, len+n);

    }

    public static void main(String[] args) {

        String s = "abcXYZdef";

        System.out.println(s);
        System.out.println(LeftRotateString(s,3));
        System.out.println(L2(s,3));
    }
}
