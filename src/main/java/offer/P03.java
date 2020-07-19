package offer;

/*
 * File: P03.java
 * Date: 2020-05-10 13:48
 * Author: msw
 * 剑指 Offer 03: 给定一个数组A[0,1,...,n-1],请构建一个数组B[0,1,...,n-1],
 * 其中B中的元素B[i]=A[0]*A[1]*...*A[i-1]*A[i+1]*...*A[n-1]。不能使用除法。
 * 注意：规定B[0] = A[1] * A[2] * ... * A[n-1]，B[n-1] = A[0] * A[1] * ... * A[n-2].
*/

import java.util.ArrayList;

public class P03 {

    // 第一个是我自己写的,for 套着 for,时间复杂度 O(n^2)
    public static int[] multiply1(int[] A) {

        int len = A.length;
        int c[] = new int[len];

        for (int i = 0; i < len; i++)
            c[i] = 1;

        for (int i = 0; i < len; i++) {
            if (i == 0) {
                for (int j = 1; j < len; j++)
                    c[i] *= A[j];
            } else if (i == len - 1) {
                for (int j = 0; j < len - 1; j++)
                    c[i] *= A[j];
            } else {
                for (int j = 0; j < i; j++)
                    c[i] *= A[j];
                for (int j = i + 1; j < len; j++)
                    c[i] *= A[j];
            }

        }

        return c;
    }

    // 这个是看了别人写的,自己理解后敲的,O(n)
    public static int[] multiply2(int[] A) {

        int len = A.length;
        int b[] = new int[len];

        b[0] = 1;
        for (int i = 1; i < len; i++)
            b[i] = b[i - 1] * A[i - 1];

        int temp = 1;
        for (int j = len - 2; j >= 0; j--) {
            temp *= A[j + 1];
            b[j] *= temp;
        }

        return b;

    }

    // 这个是对上面的解法稍微改变一下
    public static int[] multiply3(int[] A) {
        int len = A.length;
        int b[] = new int[len];

        b[0] = 1;
        b[1] = A[0];
        for (int i = 2; i < len; i++) {
            b[i] = b[i - 1] * A[i - 1];
        }

        int temp = 1;
        for (int j = len - 2; j >= 0; j--) {
            temp *= A[j + 1];
            b[j] *= temp;
        }
        return b;
    }

    // 类似前两种
    public static int[] multiply4(int[] A) {
        int len = A.length;
        int b[] = new int[len];

        int ret = 1;
        for (int i = 0; i < len; i++)
        {
            b[i] = ret;
            ret *= A[i];
        }

        ret = 1;
        for (int j = len-1; j >= 0; j--)
        {
            b[j] *= ret;
            ret *= A[j];
        }
        return b;
    }

    public static void main(String[] args) {

        int arr_A[] = {1, 2, 3, 4, 5};
        int arr_B[] = {};

        arr_B = multiply4(arr_A);
        for (int i = 0; i < arr_B.length; i++)
            System.out.println(arr_B[i]);
    }

}
