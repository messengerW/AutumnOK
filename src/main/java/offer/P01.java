package offer;

/*
 * File: P01.java
 * Date: 2020-04-09 15:07
 * Author: msw
 * 剑指 Offer 01:在一个二维数组中（每个一维数组的长度相同），每一行都按照从左到右递增的顺序排序，
 * 每一列都按照从上到下递增的顺序排序。请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
 *
*/
public class P01 {
    public static boolean Find(int target, int [][] array) {
        /**
         * @Function: 判断二维数组中是否含有指定的整数
         * @param target-指定的整数
         * @param array-二维数组
         * @Return: true-有, false-无
        */
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                if( target == array[i][j] )
                    return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int arr[][] = {{1,2,3}, {4,5,6}, {7,8,9},{10,11,12}};

        System.out.println("一维长度:" + arr.length + "\t二维长度:" + arr[0].length);
        boolean r = Find(9,arr);
        System.out.println(r);
    }
}
