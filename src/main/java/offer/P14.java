package offer;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/*
 * File: P14.java
 * Date: 2020-05-19 13:26
 * Author: msw
 * P14: 从上到下按层打印二叉树，同一层结点从左至右输出。每一层输出一行。
*/
public class P14 {
    ArrayList<ArrayList<Integer>> Print(TreeNode pRoot) {
        /**
         * @Function: 按照题目要求，每一层输出一行，所以要创建一个二维动态数组，一维是
         *            ArrayList<Integer> = new ArrayList<Integer>()
         * @param pRoot: 树的根结点
         * @Return: java.util.ArrayList<java.util.ArrayList<java.lang.Integer>>
         */

        ArrayList<ArrayList<Integer>> resultList = new ArrayList<ArrayList<Integer>>();
        //  老规矩，先判断是否空树
        if (pRoot == null)
            return resultList;

        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        //  根结点先入队
        queue.offer(pRoot);

        while (!queue.isEmpty()) {
            //  声明一个一维动态数组，用来存储一层的结点
            ArrayList<Integer> list = new ArrayList<Integer>();
            //  因为要求按层输出，所以这里记录一下上一层有多少结点
            int preSize = queue.size();
            //  for循环遍历上一层的所有结点，将它们的子结点依次添加到列表
            for (int i = 0; i < preSize; i++) {
                TreeNode p = queue.poll();
                list.add(p.val);
                //  每出队一个结点，需要在队尾入队这个节点的两个子结点
                if (p.left != null)
                    queue.offer(p.left);
                if (p.right != null)
                    queue.offer(p.right);
            }
            //  将存储一层结点的数组放进二维数组
            resultList.add(list);
        }
        return resultList;
    }

    ArrayList<ArrayList<Integer>> Print_Digui(TreeNode pRoot) {
        /**
         * @Function: 这是怎么想到用递归的啊。。。nb
         * @param pRoot
         * @Return: java.util.ArrayList<java.util.ArrayList<java.lang.Integer>>
        */

        ArrayList<ArrayList<Integer>> resultList  = new ArrayList<ArrayList<Integer>>();
        //  调用递归函数
        depth(pRoot, 1, resultList);
        return resultList;

    }

    private void depth(TreeNode root, int depth, ArrayList<ArrayList<Integer>> resultList) {
        if ( root == null )
            return;
        //  每一层单独存储在一个一维动态数组，根据树的层数动态调整一维数组的个数
        if ( depth > resultList.size() )
        {
            ArrayList<Integer> list = new ArrayList<Integer>();
            resultList.add(list);
        }
        resultList.get(depth-1).add(root.val);

        depth(root.left, depth+1, resultList);
        depth(root.right, depth+1, resultList);
    }
}
