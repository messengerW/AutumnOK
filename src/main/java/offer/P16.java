package offer;
/*
 * File: P16.java
 * Date: 2020-06-05 14:08
 * Author: msw
 * P16: 求1+2+3+...+n，要求不能使用乘除法、for、while、if、else、switch、case等关键字
 *      及条件判断语句（A?B:C）
 */


public class P16 {
    // 解法-1
    public static int Sum_Solution(int n) {
        // 短路原理,只有 && 坐标的表达式成立的时候才会执行右边
        boolean flag = n > 0 && (n += Sum_Solution(n-1)) > 0;
        return n;
    }

    // 解法-2
    public static int Sum_Solution1(int n) {
        //  等差数列 1+2+3+...+n = n(n+1)/2 = (n^2 + n)/2 = [pow(n,2)+n] / 2
        //  [pow(n,2)+2]/2 借助 >> 位运算
        int sum = (int) Math.pow(n,2) + n;
        return sum >> 1;
    }

    public static void main(String[] args) {
        int n = 5;
        System.out.println(Sum_Solution(n));
        System.out.println(Sum_Solution1(n));
    }
}
