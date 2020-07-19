package offer;
/*
 * File: P26.java
 * Date: 2020-07-18 12:04
 * Author: msw
 * PS 给你一根长度为n的绳子，请把绳子剪成整数长的m段（m、n都是整数，n>1并且m>1，m<=n），
 * 每段绳子的长度记为k[1],...,k[m]。请问k[1]x...xk[m]可能的最大乘积是多少？
 * 例如，当绳子的长度是8时，我们把它剪成长度分别为2、3、3的三段，此时得到的最大乘积是18
 */

public class P26 {
    // 方法1 - 暴力递归
//    public static int cutRope(int target) {
//
//        if (target < 2 || target >60)
//            return -1;
//        if (target == 2)
//            return 1;
//        if (target == 3)
//            return 2;
//
//        return f(target);
//    }
//
//    public static int f(int n) {
//        if ( n <= 4 )
//            return n;
//
//        int max = n - 1;
//
//        for (int i = 1; i <= n-1; i++)
//            max = Math.max(i * f(n - i), max);
//
//        return max;
//    }

    public static int cutRope(int target) {

        if ( target < 2 || target > 60 )
            return -1;
        // target=2、3和递归中遇到2、3处理方式不同
        if ( target == 2 )
            return 1;
        if ( target == 3 )
            return 2;

        int[] mark = new int[61];
        for (int i = 0; i <= target; i++)
            mark[i] = -1;

        return f(target, mark);

    }

    public static int f(int n, int[] mark) {

        // 注意这里的判断和cutRope方法里的判断意味不同
        if ( n <= 4 )
            return n;
        // 如果mark[n] != -1,说明之前有计算过
        if (mark[n] != -1)
            return mark[n];

        for (int i = 1; i < n; i++)
            mark[n] = Math.max(mark[n], i*f(n-i, mark));

        return mark[n];
    }

    public static void main(String[] args) {
        int val = 8;
        System.out.println("===> " + cutRope(val));
    }
}
