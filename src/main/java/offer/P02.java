package offer;

/*
 * File: P02.java
 * Date: 2020-04-10 16:28
 * Author: msw
 * 剑指 Offer 02:请实现一个函数，将一个字符串中的每个空格替换成“%20”。例如，
 *               当字符串为We Are Happy.则经过替换之后的字符串为We%20Are%20Happy。
*/
public class P02 {
    public static String replaceSpace(StringBuffer str) {
        String s = str.toString();
        s = s.replace(" ","%20");
        return s;
    }

    public static void main(String[] args) {
        StringBuffer sb = new StringBuffer();
        sb.append("We Are Happy.");

        String r = replaceSpace(sb);
        System.out.println(r);
    }
}
