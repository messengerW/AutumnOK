package offer;
/*
 * File: P24.java
 * Date: 2020-06-08 17:52
 * Author: msw
 * P24: 统计一个数字在排序数组中出现的次数。
 */


import java.util.Arrays;
import java.util.HashMap;

public class P24 {

    // 先用笨方法，统计所有数字出现的次数
    public static int GetNumberOfK(int[] array, int k) {

        if (array == null)
            return 0;

        HashMap<Integer, Integer> map = new HashMap<>();

        for (int n : array) {
            if (!map.containsKey(n))
                map.put(n, 1);
            else {
                Integer value = map.get(n);
                value += 1;
                map.put(n, value);
            }
        }

        if (!map.containsKey(k))
            return 0;

        return map.get(k);
    }


    public static int GetNumberOfK1(int [] array , int k) {
        if(array == null || array.length == 0)
            return 0;
        int count = 0;
        int low = 0, high = array.length - 1, mid = 0;
        while(low <= high){
            mid = low + (high -low) / 2;
            if(array[mid] == k)
                break;
            else if(array[mid] > k)
                high = mid - 1;
            else
                low = mid + 1;
        }
        if(low <= high){
            for(int i = mid;i >= 0;i--){
                if(array[i] == k)
                    count++;
                else
                    break;
            }
            for(int i = mid + 1;i < array.length;i++){
                if(array[i] == k)
                    count++;
                else
                    break;
            }
        }
        return count;
    }

    public static void main(String[] args) {

        int num = 9;
        int[] arr = new int[]{1, 2, 3, 4, 5, 6, 6, 6, 7, 8, 9, 9, 11};

        long time1 = System.nanoTime();
        System.out.println("number:\t" + num + "\ttimes:\t" + GetNumberOfK1(arr, num));
        long time2 = System.nanoTime();
        System.out.println("use time:\t" + (time2-time1) + "ns");

    }
}
