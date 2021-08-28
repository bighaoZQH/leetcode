package com.bighao.linkedlist;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @version 1.0
 * @author: bighao周启豪
 * @date 2021/8/7 21:56
 *
 * 1.顺序合并 就是挨个去合并
 * 2.分治合并 就是归并排序的一个思想
 * 3.优先队列
 */
public class 合并K个升序链表Hard23 {

    /**
     * 时间复杂度: O(k*n*logK)
     * 空间复杂度: O(k)
     * @param lists
     * @return
     */
    public ListNode mergeKListsByQueue(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        // 优先队列，默认是小顶堆，但因为ListNode没有实现Comparator，所以这里要自定义一个Comparator
        /*PriorityQueue<ListNode> queue = new PriorityQueue<>(new Comparator<ListNode>() {
            @Override
            public int compare(ListNode o1, ListNode o2) {
                return o1.val - o2.val;
            }
        });*/
        PriorityQueue<ListNode> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o.val));
        // O(k*logK)
        for (ListNode list : lists) { //O(k)
            if (list != null) {
                queue.add(list); //O(logK)
            }
        }

        ListNode dummyHead = new ListNode();
        ListNode prev = dummyHead;
        // O(kn*logK)
        while (!queue.isEmpty()) { //O(k*n)
            ListNode min = queue.remove(); //O(logK)
            // 将min的next节点加入到优先队列中
            if (min.next != null) {
                queue.add(min.next); //O(logK)
            }
            prev.next = min;
            // 更新prev节点
            prev = prev.next;
        }
        return dummyHead.next;
    }

    /**
     * 分治合并 跟归并排序一个思想
     */
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        return merge(lists, 0, lists.length - 1);
    }

    public ListNode merge(ListNode[] lists, int left, int right) {
        if (left == right) {
            return lists[left];
        }
        if (left > right) {
            return null;
        }

        int mid = (right - left) / 2 + left;
        ListNode leftMergedList = merge(lists, left, mid);
        ListNode rightMergedList = merge(lists, mid + 1, right);
        return mergeTwoList(leftMergedList, rightMergedList);
    }

    /**
     * 顺序合并
     */
    public ListNode mergeKLists1(ListNode[] lists) {
        if (lists.length == 0) {
            return null;
        }
        ListNode head = lists[0];
        for (int i = 1; i < lists.length; i++) {
            head = mergeTwoList(head, lists[i]);
        }

        return head;
    }

    public ListNode mergeTwoList(ListNode l1, ListNode l2) {
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

    }

}
