package offer;

/*
 * File: P09.java
 * Date: 2020-05-11 21:17
 * Author: msw
 * 在一个长度为n的数组里的所有数字都在0到n-1的范围内。 数组中某些数字是重复的，但不知道有几个数字是重复的。
 * 也不知道每个数字重复几次。请找出数组中任意一个重复的数字。 例如，如果输入长度为7的数组{2,3,1,0,2,5,3}，
 * 那么对应的输出是第一个重复的数字2。
 * // Parameters:
    //    numbers:     an array of integers
    //    length:      the length of array numbers
    //    duplication: (Output) the duplicated number in the array number,length of duplication array is 1,so using duplication[0] = ? in implementation;
    //                  Here duplication like pointor in C/C++, duplication[0] equal *duplication in C/C++
    //    这里要特别注意~返回任意重复的一个，赋值duplication[0]
    // Return value:       true if the input is valid, and there are some duplications in the array number
    //                     otherwise false

    题目描述有问题，“返回第一个重复的数字”，其实随意返回一个重复的数字即可
*/

import java.util.Arrays;

public class P09 {
    public static boolean duplicate(int numbers[], int length, int[] duplication) {

        Integer temp[] = new Integer[length];  // 这里一定要是length，不能是numbers.length

        if (numbers == null || length == 0)
            return false;

        for (int i = 0; i < length; i++) {

            // 设置一个变量flag, 表示当前元素是否存在于 temp 数组中
            boolean flag = Arrays.asList(temp).contains(numbers[i]);
            // index 用于记录第一个发生重复的元素的坐标
            int index = -1;

            // 如果 flag = false 说明temp数组还没有这个元素,那么把它添加进去
            if (!flag)
                temp[i] = numbers[i];
                // 反之 说明出现重复
            else {
//                index = find(temp,numbers[i]);
                duplication[0] = numbers[i];
//                System.out.println("最先出现重复的元素:" + duplication[0] + ", 它的下标:" + index);
                return true;
            }
        }
        return false;
    }

    public static boolean duplicate_1(int numbers[], int length, int[] duplication) {
        // 上面一开始想麻烦了。因为这道题只要求判断是否有重复、记录重复的元素，所以与元素下标无关。
        // 因此更简单的解法是：先将数组排序，然后只需判断相邻两元素是否相等即可判断是否存在重复
        if (numbers == null || length == 0)
            return false;

        Arrays.sort(numbers);

        for ( int i = 0; i < length-1; i++) {
            if (numbers[i] == numbers[i+1]){
                duplication[0] = numbers[i];
                return true;
            }
        }

        return false;
    }

    public static boolean d2(int numbers[], int length, int[] duplication) {

        boolean flag[] = new boolean[length];

        for( int i = 0; i < length; i++) {
            if (flag[numbers[i]] == true){
                duplication[0] = numbers[i];
                return true;
            }
            flag[numbers[i]] = true;
        }

        return false;
    }

    static int find(Integer arr[], int n) {
        int index = -1;

        for (int i = 0; i < arr.length; i++) {
            if (n == arr[i]) {
                index = i;
                break;
            }
        }

        return index;
    }

    public static void main(String[] args) {

        int nums[] = {1, 2, 3, 4, 5, 5, 7};
        int[] n = {-1};

        d2(nums, nums.length, n);


    }
}
