package offer;

import java.util.ArrayList;

/*
 * File: P13.java
 * Date: 2020-05-13 12:18
 * Author: msw
 * P13: 给定一个二叉树和其中的一个结点，请找出中序遍历顺序的下一个结点并且返回。
 *      注意，树中的结点不仅包含左右子结点，同时包含指向父结点的指针（用pNode.next表示）。
*/
public class P13 {
    static ArrayList<TreeLinkNode> list = new ArrayList<TreeLinkNode>();

    // way1
    public TreeLinkNode GetNext(TreeLinkNode pNode) {
        // 1.如果当前节点有“右子树”,则下一节点是右子树的最左节点
        if (pNode.right != null) {
            TreeLinkNode pRight = pNode.right;
            while (pRight.left != null)
                pRight = pRight.left;
            return pRight;
        }

        // 2.如果当前节点没有“右子树”,而且是其父节点的左节点,则下一节点是其父节点
        if (pNode.next != null && pNode == pNode.next.left) {
            return pNode.next;
        }

        // 3.如果当前节点没有“右子树”,而且是其父节点的右节点,那么就一直向上回溯,
        // 直到找到某个节点是其父节点的左子树,则这个节点的父节点就是中序遍历的下一个节点;
        // 如果没有这样的节点,说明这个节点就是中序遍历的最后一个节点,无下一个节点
        if (pNode.next != null && pNode == pNode.next.right) {

            TreeLinkNode pre = pNode.next;
            while (pre.next != null) {
                if (pre == pre.next.left)
                    return pre.next;
                pre = pre.next;
            }
        }
        return null;
    }

    // way2
    public TreeLinkNode GetNext1(TreeLinkNode pNode) {
        //  遍历之前需要先找到树的根节点
        TreeLinkNode pre = pNode;
        while (pre.next != null)
            pre = pre.next;

        //  找到根节点之后,开始进行中序遍历
        InOrder(pre);

        //  在保存了遍历结果的 list 中找到 pNode 的下一个节点
        for (int i = 0; i < list.size(); i++) {
            if (pNode == list.get(i)) {
                return i == list.size() - 1 ? null : list.get(i + 1);
            }
        }
        return null;
    }

    void InOrder(TreeLinkNode node) {
        if (node != null) {
            InOrder(node.left);
            list.add(node);
            InOrder(node.right);
        }
    }
}