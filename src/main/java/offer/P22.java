package offer;
/*
 * File: P22.java
 * Date: 2020-06-07 17:32
 * Author: msw
 * P22: 一个整型数组里除了两个数字之外，其他的数字都出现了两次。请写程序找出这两个只出现一次的数字。
 *  //num1,num2分别为长度为1的数组。传出参数
    //将num1[0],num2[0]设置为返回结果
 */


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class P22 {

    // 笨方法
    public static void FindNumsAppearOnce(int[] array, int[] num1, int[] num2) {

        if (array == null || array.length < 2)
            return;

        int[] times = new int[10000000];

        // 统计次数
        for (int n : array) {
            times[n]++;
        }

        int flag = 0;
        for (int i = 0; i < array.length; i++) {

            // flag=0 且 time=1 说明找到第一个
            if (times[array[i]] == 1 && flag == 0) {
                num1[0] = array[i];
                flag++;
                continue;
            }
            // flag=1 且 time=1 说明找到第二个
            if (times[array[i]] == 1 && flag == 1) {
                num2[0] = array[i];
                break;
            }
        }

        System.out.println("num1[0]: " + num1[0] + "\tnum2[0]: " + num2[0]);

    }

    // 借助 HashMap
    public static void FindNumsAppearOnce1(int[] array, int[] num1, int[] num2) {

        if (array == null || array.length < 2)
            return;

        HashMap<Integer, Integer> map = new HashMap<>();

        // 遍历数组
        for (int a : array) {
            // 先判断 a 是否已存在
            if (map.containsKey(a))
                map.replace(a, 2);
            else
                map.put(a, 1);
        }

        int flag = 0;
        for (int k : map.keySet()) {
            if (map.get(k) == 1 && flag == 0) {
                num1[0] = k;
                flag++;
                continue;
            }
            if (map.get(k) == 1 && flag == 1) {
                num2[0] = k;
                break;
            }
        }
        System.out.println("num1[0]: " + num1[0] + "\tnum2[0]: " + num2[0]);

    }

    // 别人的方法
    public static void FindNumsAppearOnce2(int[] array, int[] num1, int[] num2) {

        int length = array.length;
        if (length == 2) {
            num1[0] = array[0];
            num2[0] = array[1];
            return;
        }
        int bitResult = 0;
        for (int i = 0; i < length; ++i) {
            bitResult ^= array[i];
        }
        int index = findFirst1(bitResult);
        for (int i = 0; i < length; ++i) {
            if (isBit1(array[i], index)) {
                num1[0] ^= array[i];
            } else {
                num2[0] ^= array[i];
            }
        }

        System.out.println("num1[0]: " + num1[0] + "\tnum2[0]: " + num2[0]);

    }


    private static int findFirst1(int bitResult) {
        int index = 0;
        while (((bitResult & 1) == 0) && index < 32) {
            bitResult >>= 1;
            index++;
        }
        return index;
    }

    private static boolean isBit1(int target, int index) {
        return ((target >> index) & 1) == 1;
    }


    public static void main(String[] args) {

        int[] arr = new int[]{1, 1, 2, 2, 3, 4, 5, 5, 6, 6, 7, 7};

        int[] num1 = new int[1];
        int[] num2 = new int[1];

        System.out.println(Arrays.toString(arr));

        long start = System.nanoTime();
        FindNumsAppearOnce2(arr, num1, num2);
        long stop = System.nanoTime();
        System.out.println("Use time:\t" + (stop - start) + "ns\n\n");

    }
}
