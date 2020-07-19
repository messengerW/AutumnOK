package offer;

import java.util.Stack;

/*
 * File: P07.java
 * Date: 2020-05-11 14:35
 * Author: msw
 * P07: 用两个栈来实现一个队列，完成队列的Push和Pop操作。 队列中的元素为int类型。
 * 数据结构有关的这两道都有点没思路。这是借鉴评论区的思路：
   1.入队: 直接插入 stack1
   2.出队: 当 stack2 不为空，弹出 stack2 栈顶元素，如果 stack2 为空，将 stack1 中的全部数逐个出栈入栈 stack2，再弹出 stack2 栈顶元素
*/
public class P07 {

    Stack<Integer> stack1 = new Stack<Integer>();
    Stack<Integer> stack2 = new Stack<Integer>();

    public void push(int node) {
        stack1.push(node);
    }

    public int pop() {

        if (stack2.empty()) {
            while (!stack1.empty())
                stack2.push(stack1.pop());
        }

        return stack2.pop();
    }
}
