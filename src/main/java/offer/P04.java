package offer;

import java.util.Scanner;

/*
 * File: P04.java
 * Date: 2020-05-10 20:51
 * Author: msw
 * 剑指 Offer 04: 写一个函数，求两个整数之和，要求在函数体内不得使用+、-、*、/四则运算符号。
*/
public class P04 {

    public static int Add(int num1,int num2) {
        int c;
        while (num2 != 0){
            c = (num1 & num2) << 1;
            num1 = num1 ^ num2;
            num2 = c;
        }

        return num1;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n1 = scanner.nextInt();
        int n2 = scanner.nextInt();

        System.out.println(Add(n1,n2));
    }
}
