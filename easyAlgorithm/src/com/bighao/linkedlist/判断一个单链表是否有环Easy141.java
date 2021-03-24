package com.bighao.linkedlist;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * @version 1.0
 * @author: bighao周启豪
 * @date 2020/12/7 13:40
 *
 * https://leetcode-cn.com/problems/linked-list-cycle/
 *
 * 判断一个单链表是否有环
 * 1.哈希表
 * 2.快慢指针(弗洛伊德判圈法)
 */
public class 判断一个单链表是否有环Easy141 {

    /**
     * 快慢指针
     */
    public static boolean hasCycle2(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }
        ListNode l1 = head;
        ListNode l2 = head.next;
        while (l2 != null && l2.next != null) {
            if (l1 == l2) {
                return true;
            }
            l1 = l1.next;
            l2 = l2.next.next;
        }
        return false;
    }


    /**
     * 哈希表 用hashSet改进
     * 执行用时：3 ms, 在所有 Java 提交中击败了22.87%的用户
     * 内存消耗：38.8 MB, 在所有 Java 提交中击败了58.26%的用户
     */
    public static boolean hasCycle(ListNode head) {
        HashSet<Object> set = new HashSet<>();
        while (head != null) {
            if (!set.add(head.next)) {
                return true;
            }
            head = head.next;
        }
        return false;
    }

    /**
     * 哈希表
     * 5ms...
     */
    public static boolean hasCycleSource(ListNode head) {
        Map<ListNode, Integer> map = new HashMap<>();
        while (head != null) {
            Integer index = map.get(head.next);
            if (index != null) {
                return true;
            }
            map.put(head, 1);
            head = head.next;
        }
        return false;
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(3);
        ListNode l2 = new ListNode(2);
        ListNode l3 = new ListNode(0);
        ListNode l4 = new ListNode(-4);
        l1.next = l2;
        l2.next = l3;
        l3.next = l4;
        l4.next = l2;

        /*ListNode l1 = new ListNode(3);
        ListNode l2 = new ListNode(2);
        ListNode l3 = new ListNode(1);
        ListNode l4 = new ListNode(45);
        ListNode l5 = new ListNode(490);
        ListNode l6 = new ListNode(43);

        l1.next = l2;
        l2.next = l3;
        l3.next = l4;
        l4.next = l5;
        l5.next = l6;*/

        System.out.println(hasCycle2(l1));
    }


    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

}
