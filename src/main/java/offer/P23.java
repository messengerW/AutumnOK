package offer;
/*
 * File: P23.java
 * Date: 2020-06-08 16:45
 * Author: msw
 * P23: 输入一棵二叉树，判断该二叉树是否是平衡二叉树。只需要考虑其平衡性，不需要考虑其是不是排序二叉树
 *      AVL : 任一节点对应的两棵子树的最大高度差为1
 */


public class P23 {

    // 设置一个bool值用来在遍历树的时候标记是否满足条件，预设为满足，一旦发现某个节点不符合则置为false
    private boolean isBalanced = true;

    public boolean IsBalanced_Solution(TreeNode root) {

        getDepth(root);
        return isBalanced;
    }

    // 之前有一道题是求树的深度，只需要在其基础上稍微改动
    // 想想，求树深是怎么操作的？不就是遍历树吗
    // 从根节点往下一层层遍历，从最底层的叶子节点向上逐层返回树身
    // 最先返回的是最底层的深度，也就是 return max(0,0)+1 = 1
    // 然后逐层比较、+1返回到根节点，得到的就是树的最大深度也就是树深
    // 所以这道题只需要稍微改进，在遍历每一个结点的时候加一个判断就可以了
    public int getDepth(TreeNode root) {
        if (root == null)
            return 0;
        int left = getDepth(root.left);
        int right = getDepth(root.right);

        // 每次遍历一个结点的时候判断这个结点是否满足 AVL 的条件
        // 如果不满足则将标志设为 false
        if (Math.abs(left - right) > 1) {
            isBalanced = false;
        }

        return Math.max(left, right) + 1;

    }

}
