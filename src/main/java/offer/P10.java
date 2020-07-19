package offer;

/*
 * File: P10.java
 * Date: 2020-05-12 12:26
 * Author: msw
 * P10: 请实现一个函数用来判断字符串是否表示数值（包括整数和小数）。例如，
 * 字符串"+100","5e2","-123","3.1416"和"-1E-16"都表示数值。
 * 但是"12e","1a3.14","1.2.3","+-5"和"12e+4.3"都不是。
*/

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class P10 {
    /* 这道题主要考java正则表达式，另外一个小考点是 字符数组(char[] s) 转 字符串(String s) */
    public static boolean isNumeric(char[] str) {

        String pattern = "^[-+]?\\d*(?:\\.\\d*)?(?:[eE][+\\-]?\\d+)?$";
        String s = new String(str);     // 字符数组 转 字符串
        return Pattern.matches(pattern,s);
    }

    public static void main(String[] args) {

        char s[] = new char[]{'f','o','o','o'};

        boolean flag = isNumeric(s);

    }
}
