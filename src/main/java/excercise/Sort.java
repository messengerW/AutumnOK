package excercise;
/*
 * File: Sort.java
 * Date: 2020-08-16 21:43
 * Author: msw
 * PS ...
 */


public class Sort {
    public static void main(String[] args) {
        int[] arr = {5,2,8,-3,7,9,6,11,3,8};

        System.out.println("Select Sort: ");
        for (int n : selectSort(arr))
            System.out.print(n + " ");
    }

    static int[] selectSort(int[] arr) {
        int len = arr.length;
        int minIndex, temp;
        for (int cnt = 0; cnt < len; cnt++) {
            minIndex = cnt;
            for (int i = cnt+1; i < len; i++) {
                if (arr[i] < arr[minIndex]) {
                    minIndex = i;
                }
            }
            temp = arr[cnt];
            arr[cnt] = arr[minIndex];
            arr[minIndex] = temp;
        }
        return arr;
    }
    static int[] insertSort(int[] arr) {
        int len =  arr.length;
        int preIndex = 0, current;
        for (int i = 1; i < len; i++) {
            preIndex = i-1;
            current = arr[i];
            while (preIndex >= 0) {
                // 如果比前一个小，则插入到前一个的前面
                if (current < arr[preIndex]) {
                    arr[preIndex+1] = arr[preIndex];
                    arr[preIndex] = current;
                    preIndex--;
                }
                // 反之，说明已找到位置，退出
                else
                    break;
            }
        }
        return arr;
    }
    static int[] shellsSort(int[] arr) {
        int len = arr.length;
        int temp;
        for (int k = len/2; k > 0; k --) {
            // 改变步长，再次进行直接插入排序
            System.out.println("k:" + k);
            for (int i = 0; i+k < len; i++) {
                if (arr[i+k] < arr[i]) {
                    temp = arr[i+k];
                    arr[i+k] = arr[i];
                    arr[i] = temp;
                }
            }
        }
        return arr;
    }
}
