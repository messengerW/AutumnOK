package offer;

/*
 * File: P06.java
 * Date: 2020-05-11 16:45
 * Author: msw
 * P06: 操作给定的二叉树，将其变换为源二叉树的镜像。
*/
class P06 {
    public void Mirror(TreeNode root) {
        TreeNode temp = null;

        if (root != null){

            temp = root.left;
            root.left = root.right;
            root.right = temp;

            if (root.left != null)
                Mirror(root.left);
            if (root.right != null)
                Mirror(root.right);
        }
    }
}
