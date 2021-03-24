package com.bighao.linkedlist;

/**
 * @version 1.0
 * @author: bighao周启豪
 * @date 2021/1/10 0:10
 */
public class K个一组翻转链表Hard25 {

    public static ListNode reverseKGroup2(ListNode head, int k) {
        ListNode hair = new ListNode(0);
        hair.next = head;
        ListNode pre = hair;

        while (head != null) {
            ListNode tail = pre;
            for (int i = 0; i < k; ++i) {
                tail = tail.next;
                if (tail == null) {
                    return hair.next;
                }
            }
            ListNode nex = tail.next;
            ListNode[] reverse = myReverse(head, tail);
            head = reverse[0];
            tail = reverse[1];
            pre.next = head;
            tail.next = nex;
            pre = tail;
            head = tail.next;
        }

        return hair.next;
    }

    public static ListNode[] myReverse(ListNode head, ListNode tail) {
        ListNode prev = tail.next;
        ListNode p = head;
        while (prev != tail) {
            ListNode nex = p.next;
            p.next = prev;
            prev = p;
            p = nex;
        }
        return new ListNode[]{tail, head};
    }


    public static ListNode reverseKGroup(ListNode head, int k) {
        ListNode newHead = new ListNode(0);
        newHead.next = head;

        // kGroup链表尾头的前置节点
        ListNode prev = newHead;
        while (head != null) {
            // kGroup链表尾节点
            ListNode tail = prev;
            // find k group
            for (int i = 0; i < k; i++) {
                tail = tail.next;
                if (tail == null) {
                    return newHead.next;
                }
            }
            // 下一个kGroup链表头
            ListNode nexHead = tail.next;

            // reverse the local linkedlist
            ListNode pre = nexHead;
            ListNode cur = head;
            while (cur != nexHead) {
                ListNode nex = cur.next;
                cur.next = pre;
                pre = cur;
                cur = nex;
            }

            // 将反转后的子链表的头连接到前置节点
            prev.next = pre;
            // 将prev更新为原来的子链表头
            prev = head;
            // 更新head节点
            head = nexHead;
        }
        return newHead.next;
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(2);
        ListNode l3 = new ListNode(3);
        ListNode l4 = new ListNode(4);
        ListNode l5 = new ListNode(5);

        l1.next = l2;
        /*l2.next = l3;
        l3.next = l4;
        l4.next = l5;*/

        long l = System.currentTimeMillis();
        ListNode listNode = reverseKGroup(l1, 2);
        System.out.println(System.currentTimeMillis() - l);

        ListNode temp = listNode;
        while (temp != null) {
            System.out.println(temp.val);
            temp = temp.next;
        }
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
