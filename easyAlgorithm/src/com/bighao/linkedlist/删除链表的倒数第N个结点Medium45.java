package com.bighao.linkedlist;

/**
 * @version 1.0
 * @author: bighao周启豪
 * @date 2021/8/7 13:51
 */
public class 删除链表的倒数第N个结点Medium45 {


    //
    //

    /**
     * 快慢指针
     * n = 2
     * 1.快的先走n步，然后再一起走，快的走到最后一个元素，慢的next就是要剔除的节点
     * 循环终止条件fast.next != null
     *          f   s
     *          ↓   ↓
     *  s 1 2 3 4 5 6
     *
     * 2.快的先走n+1步，然后再一起走, 这样s和f中间就隔了n个元素
     * 然后快的走到最后一个元素的next，慢的next就是要剔除的节点
     * 循环终止条件fast != null
     *          f     s
     *          ↓     ↓
     *  s 1 2 3 4 5 6
     *
     * 为了解决[1] 1这种情况，可以引入一个哨兵来解决
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode sentinel = new ListNode();
        sentinel.next = head;
        ListNode slow = sentinel;
        ListNode fast = sentinel;


        /**
         * 这样也行，fast先走n+1步，最后fast走到最后一个节点的next的时候，slow就停在了要删除节点的前面
         * 但我的方式更好点，可以少走一步，fast先走n步，fast走到后一个节点时停下，slow就停在了要删除节点的前面
         * while (n >= 0) {
         *             fast = fast.next;
         *             n--;
         *         }
         *
         * while (fast != null) {
         *     slow = slow.next;
         *     fast = fast.next;
         * }
         */
        while (n > 0) {
            fast = fast.next;
            n--;
        }

        while (fast.next != null) {
            slow = slow.next;
            fast = fast.next;
        }

        ListNode delNode = slow.next;
        slow.next = delNode.next;
        delNode.next = null;
        return sentinel.next;
    }


    static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }


}
