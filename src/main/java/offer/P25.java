package offer;
/*
 * File: P25.java
 * Date: 2020-06-20 10:46
 * Author: msw
 * PS ...
 */


public class P25 {
    public ListNode FindFirstCommonNode(ListNode pHead1, ListNode pHead2) {

        if (pHead1 == null || pHead2 == null)
            return null;

        boolean flag = true;
        while (flag) {

            while (pHead1 != null) {
                ListNode pt = pHead2;

                while (pt != null) {
                    if (pHead1 == pt) {
                        flag = false;
                        return pHead1;
                    }
                    pt = pt.next;
                }
                pHead1 = pHead1.next;
            }
            flag = false;
        }

        return null;
    }
}
