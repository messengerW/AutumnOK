package offer;
/*
 * File: P20.java
 * Date: 2020-06-06 17:41
 * Author: msw
 * P20: 输入一个递增排序的数组和一个数字 S，在数组中查找两个数 a 和 b，a+b=S，
 *      如果有多对数字的和等于 S，输出两个数的乘积最小的。
 */


import java.util.ArrayList;

public class P20 {
    public static ArrayList<Integer> FindNumbersWithSum(int[] array, int sum) {

        ArrayList<Integer> result = new ArrayList<>();

        if (array.length < 2)
            return result;

        int dim = 65536;
        for (int i = 0; i < array.length-1; i++) {
            for (int j = i+1; j < array.length; j++) {
                if ( array[i] + array[j] == sum ) {
                    if (array[i]*array[j] < dim) {
                        result.clear();
                        result.add(array[i]);
                        result.add(array[j]);
                        dim = array[i] * array[j];
                    }
                }
            }
        }

        return result;

    }

    public static void main(String[] args) {

        int[] arr = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        long time1 = System.nanoTime();

        System.out.println(FindNumbersWithSum(arr, 11));

        long time2 = System.nanoTime();
        System.out.println("useTime:\t" + (time2-time1));

    }

}
