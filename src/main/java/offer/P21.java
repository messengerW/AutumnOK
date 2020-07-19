package offer;
/*
 * File: P21.java
 * Date: 2020-06-07 15:00
 * Author: msw
 * P21: 小明很喜欢数学,有一天他在做数学作业时,要求计算出9~16的和,他马上就写出了正确答案是100。
 * 但是他并不满足于此,他在想究竟有多少种连续的正数序列的和为100(至少包括两个数)。
 * 没多久,他就得到另一组连续正数和为100的序列:18,19,20,21,22。
 * 现在把问题交给你,你能不能也很快的找出所有和为S的连续正数序列? Good Luck!
 *
 * 输出所有和为S的连续正数序列。序列内按照从小至大的顺序，序列间按照开始数字从小到大的顺序
 */


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class P21 {
    public static ArrayList<ArrayList<Integer>> FindContinuousSequence(int sum) {

        // md 我觉得我的笨方法没问题啊。。怎么通不过

        ArrayList<ArrayList<Integer>> result = new ArrayList<>();

        if (sum < 3)
            return result;

        for (int n = 2; (int) n / 2 < (int) sum / n; n++) {
            float mid = (float) sum / (float) n;
            // 分情况讨论 n 为奇数/偶数
            if (n % 2 == 0) {
                // ① sum-偶，n-偶
                if (mid-(int)mid == 0.5) {
                    ArrayList<Integer> list = new ArrayList<>();
                    int start = (int)Math.ceil(mid) - n/2;
                    for (int i = 0; i < n; i++)
                        list.add(start+i);
                    result.add(list);
                }
            } else {
                // ② sum-偶，n-奇
                if (mid == (int)mid) {
                    ArrayList<Integer> list = new ArrayList<>();
                    int start = (int)mid - n/2;
                    for (int i = 0; i < n; i++)
                        list.add(start+i);
                    result.add(list);
                }
            }
        }

        // 对结果数组进行排序
        Collections.sort(result, new Comparator<ArrayList<Integer>>() {
            @Override
            public int compare(ArrayList<Integer> o1, ArrayList<Integer> o2) {
                if (o1.get(0) > o2.get(0))
                    return 0;
                else
                    return -1;
            }
        });
        return result;

    }

    public static ArrayList<ArrayList<Integer>> FindContinuousSequence2(int sum) {

//存放结果
        ArrayList<ArrayList<Integer> > result = new ArrayList<>();
        //两个起点，相当于动态窗口的两边，根据其窗口内的值的和来确定窗口的位置和大小
        int plow = 1,phigh = 2;
        while(phigh > plow){
            //由于是连续的，差为1的一个序列，那么求和公式是(a0+an)*n/2
            int cur = (phigh + plow) * (phigh - plow + 1) / 2;
            //相等，那么就将窗口范围的所有数添加进结果集
            if(cur == sum){
                ArrayList<Integer> list = new ArrayList<>();
                for(int i=plow;i<=phigh;i++){
                    list.add(i);
                }
                result.add(list);
                plow++;
                //如果当前窗口内的值之和小于sum，那么右边窗口右移一下
            }else if(cur < sum){
                phigh++;
            }else{
                //如果当前窗口内的值之和大于sum，那么左边窗口右移一下
                plow++;
            }
        }
        return result;

    }

    public static void main(String[] args) {

//        double a=12.5;
//        double b=25;
//        double c = 1;
//
//        System.out.println(a-(int)a);
//        System.out.println("a\t:" + a);
//        System.out.println("(int)a:\t" + (int)a);
//        System.out.println((Math.floor(a) + Math.ceil(a))/2);
//        System.out.println(Math.ceil(a));
//        System.out.println(Math.floor(a));
//        System.out.println("c===>" + c);   //1.5
//        System.out.println("c===>" + Math.ceil(c)); //2.0
//        System.out.println("c===>" + Math.floor(c));  //1.0

        System.out.println(FindContinuousSequence(500));
    }
}
