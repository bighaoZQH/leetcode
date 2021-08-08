package com.bighao.linkedlist;

/**
 * @version 1.0
 * @author: bighao周启豪
 * @date 2021/8/7 12:14
 */
public class ListTool {

    public static ListNode getList(int[] arr) {
        ListNode head = new ListNode(arr[0]);
        ListNode prev = head;
        for (int i = 1; i < arr.length; i++) {
            ListNode node = new ListNode(arr[i]);
            prev.next = node;
            prev = node;
        }
        return head;
    }

}
