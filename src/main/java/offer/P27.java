package offer;
/*
 * File: P27.java
 * Date: 2020-07-18 14:11
 * Author: msw
 * PS 1~13中包含1的数字有1、10、11、12、13因此共出现6次,但是对于后面问题他就没辙了。
 * 希望你帮帮他,并把问题更加普遍化,可以很快的求出任意非负整数区间中1出现的次数（从1 到 n 中1出现的次数）。
 */


public class P27 {
    public static int NumberOf1Between1AndN_Solution(int n) {

        if(n <= 0)return 0;
        int count = 0;
        for(int i=1; i <= n; i*=10){
            //计算在第i位上总共有多少个1
            count = count + (n/(10*i))*i;
            //不足i的部分有可能存在1
            int mod = n%(10*i);
            //如果超出2*i -1，则多出的1的个数是固定的
            if(mod > 2*i -1)count+=i;
            else{
                //只有大于i的时候才能会存在1
                if(mod >= i)
                    count += (mod -i)+1;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int a = 153;

        System.out.println(NumberOf1Between1AndN_Solution(a));
    }
}
