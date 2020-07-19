package offer;

/*
 * File: P08.java
 * Date: 2020-05-11 16:45
 * Author: msw
 * P08: 输入一棵二叉树，求该树的深度。
*/
public class P08 {
    public int TreeDepth(TreeNode root) {

        if ( root == null )
            return 0;

        int left = TreeDepth(root.left);
        int right = TreeDepth(root.right);

        return Math.max(left,right) + 1;
    }
}
