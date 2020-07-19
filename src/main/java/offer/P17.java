package offer;
/*
 * File: P17.java
 * Date: 2020-06-05 15:01
 * Author: msw
 * P17: 有编号从 0 - n-1 的 n 个小朋友围成一个圈从 0 - m-1 报数，每次报到 m-1 的小朋友出圈，
 *      请问编号是多少的小朋友会是最后一个出圈的呢?
 */


import java.util.ArrayList;

public class P17 {
    // 解法-1 巧妙难理解             index = (index + m) % list.size();
    public static int LastRemaining_Solution(int n, int m) {

        if (n == 0 || m ==0)
            return -1;

        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < n; i++)
            list.add(i);

        int index = -1;
        while (list.size() > 1) {
            index = (index + m) % list.size();
            list.remove(index);
            index--;
        }
        return list.get(0);
    }

    // 解法-2 方法笨 慢 多次循环
    public static int LastRemaining_Solution1(int n, int m) {

        if (n == 0 || m == 0 )
            return -1;

        int[] arr = new int[n];
        int i = -1;
        int step = 0;
        int count = n;
        while (count > 0) {
            i++;
            // 始终保证 i ∈ [0,n-1]
            if (i >= n)
                i = 0;
            // 因为是静态数组长度固定，无法删除元素，所以遇到已经出圈的直接跳过
            if (arr[i] == -1)
                continue;
            // 记录走了几步
            step++;
            // step = m 说明找到了下一个倒霉蛋
            if (step == m) {
                // 标记
                arr[i] = -1;
                // 重置计步器
                step = 0;
                // 每找到一个出圈一个，但是由于定长数组大小固定，无法依据 arr.length() 来决定停止
                // 所以借助 count 来计数，count = 0 结束循环，此时 i 对应的就是幸运儿的编号
                count--;
            }
        }

        return i;
    }

    public static void main(String[] args) {
        System.out.println(LastRemaining_Solution(10,3));
        System.out.println(LastRemaining_Solution1(10,3));

    }
}
