package offer;
/*
 * File: P18.java
 * Date: 2020-06-06 14:48
 * Author: msw
 * PS ...
 */


import java.util.HashSet;
import java.util.Set;

public class P18 {
    public static boolean isContinuous(int [] numbers) {

        if (numbers.length != 5)
            return false;

        Set<Object> temp = new HashSet<Object>();

        //  首先遍历数组,找出所有非零数字,添加到set数组中,每添加一个 count++
        int count = 0;
        for (int number : numbers)
        {
            if (number != 0)
            {
                count++;
                temp.add(number);
            }
        }

        // 条件一: 是否有0以外的重复数字
        if (count == temp.size()) {
            // 条件二: max - min <= 4
            if ((int)temp.toArray()[count-1] - (int)temp.toArray()[0] <= 4)
                return true;
        }

        return false;

    }

    public static boolean isC(int[] numbers) {

        // 因为一副扑克中 0~13 + 大小王 共 14 个数字
        // 声明一个长度为 14 的int数组用来记录每个数字出现的次数，判断是否重复
        int[] time = new int[14];
        time[0] = -5;

        int max = -1;
        int min = 14;
        // 遍历数组
        for (int number : numbers) {
            // 如果是0直接跳过,因为0重复没有关系
            if (number == 0)
                continue;
            // 如果其他非0数字出现重复，则不可能顺子
            if (time[number] > 1)
                return false;
            // 动态更新 max/min
            if (number > max)
                max = number;
            if (number < min)
                min = number;
            // 统计个数
            time[number]++;
        }

        if (max - min < 5)
            return true;

        return false;
    }

    public static void main(String[] args) {

        int[] arr = new int[]{1,3,5,0,8};
        System.out.println(isC(arr));
    }
}
