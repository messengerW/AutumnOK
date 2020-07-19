package offer;

import java.util.Comparator;
import java.util.PriorityQueue;

/*
 * File: P15.java
 * Date: 2020-05-19 16:08
 * Author: msw
 * P15: 如何得到一个数据流中的中位数？
 * 如果从数据流中读出奇数个数值，那么中位数就是所有数值排序之后位于中间的数值。
 * 如果从数据流中读出偶数个数值，那么中位数就是所有数值排序之后中间两个数的平均值。
 * 我们使用Insert()方法读取数据流，使用GetMedian()方法获取当前读取数据的中位数。
 *
 * 这题没有思路，流式数据，我理解就是一个长度动态变化的数组，求这个数组的中位数
*/
public class P15 {

    private int cnt = 0;
    private PriorityQueue<Integer> low = new PriorityQueue<Integer>();
    // 默认维护小顶堆

    private PriorityQueue<Integer> high = new PriorityQueue<Integer>(15,new Comparator<Integer>() {
        public int compare(Integer o1, Integer o2) {
            return o2 - o1;
        }
    });

    public void Insert(Integer num) {
        // 数量++
        cnt++;
        // 如果为奇数的话
        if ((cnt & 1) == 1) {
            // 由于奇数，需要存放在大顶堆上
            // 但是呢，现在你不知道num与小顶堆的情况
            // 小顶堆存放的是后半段大的数
            // 如果当前值比小顶堆上的那个数更大
            if (!low.isEmpty() && num > low.peek()) {
                // 存进去
                low.offer(num);
                // 然后在将那个最小的吐出来
                num = low.poll();
            } // 最小的就放到大顶堆，因为它存放前半段
            high.offer(num);
        } else {
            // 偶数的话，此时需要存放的是小的数
            // 注意无论是大顶堆还是小顶堆，吐出数的前提是得有数
            if (!high.isEmpty() && num < high.peek()) {
                high.offer(num);
                num = high.poll();
            } // 大数被吐出，小顶堆插入
            low.offer(num);
        }

    }

    public Double GetMedian() {// 表明是偶数
        double res = 0;
        // 奇数
        if ((cnt & 1) == 1) {
            res = high.peek();
        } else {
            res = (high.peek() + low.peek()) / 2.0;
        }
        return res;
    }
}
