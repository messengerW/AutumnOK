package offer;

import java.util.Scanner;

/*
 * File: P05.java
 * Date: 2020-05-10 22:17
 * Author: msw
 * 剑指05: 一只青蛙一次可以跳上1级台阶，也可以跳上2级……它也可以跳上n级。求该青蛙跳上一个n级的台阶总共有多少种跳法。
*/
public class P05 {
    public static int JumpFloorII(int target) {
        return (int)Math.pow(2,target-1);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        System.out.println(JumpFloorII(n));
    }
}
