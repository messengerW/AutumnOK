package offer;

/*
 * File: P12.java
 * Date: 2020-05-12 19:55
 * Author: msw
 * P12: 给一个链表，若其中包含环，请找出该链表的环的入口结点，否则，输出null。
*/
public class P12 {
    //  能过
    public ListNode EntryNodeOfLoop(ListNode pHead) {
        ListNode p1 = pHead;
        ListNode p2 = pHead;

        while (p1 != null && p2.next != null) {
            p1 = p1.next;
            p2 = p2.next.next;
            if (p1 == p2) {
                p2 = pHead;
                while (p1 != p2) {
                    p1 = p1.next;
                    p2 = p2.next;
                }
                return p1;
            }
        }
        return null;
    }


    //  不能过
    public ListNode EntryNodeOfLoop1(ListNode pHead) {

        while (pHead != null) {
            if (pHead.val == -1)
                return pHead;
            pHead.val = -1;
            pHead = pHead.next;
        }

        return null;
    }

}
