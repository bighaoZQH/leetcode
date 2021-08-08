package com.bighao.linkedlist;

/**
 * @version 1.0
 * @author: bighao周启豪
 * @date 2021/8/8 14:25
 */
public class 排序链表Medium148 {

    /**
     * 147题是插入排序链表，但时间复杂度是O(n)
     * 这题要求要O(logn)
     * 所以需要用归并排序
     *
     * 1.递归方式 自顶向下进行归并
     *
     * 为什么fast要从head.next开始？ 这里和LeetCode876不同（如果有两个中间节点，返回后一个）
     * 1 2 3 4 5
     * 基数个时没有异议
     * 1 2 3 4 5 6
     * 偶数个时，3和4都是中间节点，但对于链表来说 应该选择3，否则会死递归
     * 即如果有两个中间节点，返回前一个。
     * 1 2找中间节点 应该是1，如果是2的话，就死递归了(一直满足head->mid)
     * head ->mid mid+1->end
     */
    public static ListNode sortList(ListNode head) {
        // 递归终止条件
        if (head == null || head.next == null) {
            return head;
        }
        // 找到mid节点，将当前链表一分为二，进行递归
        ListNode slow = head;
        ListNode fast = head.next;
        // 1 2 3 4 5 6
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        // 找到mid后断开链表
        ListNode rightHead = slow.next;
        slow.next = null;
        // 递归
        ListNode l1 = sortList(head);
        ListNode l2 = sortList(rightHead);
        // 合并
        return mergeTwoList(l1, l2);
    }

    private static ListNode mergeTwoList(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode();
        ListNode prev = dummyHead;

        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                prev.next = l1;
                l1 = l1.next;
            } else {
                prev.next = l2;
                l2 = l2.next;
            }
            prev = prev.next;
        }
        prev.next = l1 == null ? l2 : l1;
        return dummyHead.next;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6};
        ListNode list = ListTool.getList(arr);
        sortList(list);
    }

}
