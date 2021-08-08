package com.bighao.linkedlist;

/**
 * @version 1.0
 * @author: bighao周启豪
 * @date 2021/8/7 12:04
 *
 * 三种方法：
 * 1.链表转数组（动态数组，但这题限定了最大100个节点，所以不需要动态数组）
 *
 * 2.单指针，先count，然后直接遍历到count/2返回
 *
 * 3.快慢指针，一个走1步，一个走2步，2步走到头，1步肯定在中间
 *  1 2 3 4 5 null
 *  对于节点个数为奇数的链表来说，此时链表的中间节点是节点3。
 *  1 2 3 4 5 6 null
 *  对于节点个数为偶数的链表来说，此时链表的中间节点是节点3，即在2和3这两个中间节点中，找到是第二个中间节点。
 *
 * 题目要求的是如果有两个中间节点，则返回第二个中间节点。
 * 因此，对于该题目而言，快指针fast向前移动的条件是：fast!=null && fast.next != null
 */
public class 链表的中间结点Easy876 {

    // 快慢指针
    public ListNode middleNode3(ListNode head) {
        ListNode slowNode = head;
        ListNode fastNode = head;
        while (fastNode != null && fastNode.next != null) {
            slowNode = slowNode.next;
            fastNode = fastNode.next.next;
        }
        return slowNode;
    }

    // 链表转数组
    public ListNode middleNode1(ListNode head) {
        ListNode[] arr = new ListNode[100];
        int size = 0;
        while (head != null) {
            arr[size++] = head;
            head = head.next;
        }
        return arr[size / 2];
    }

    // 先count，后直接遍历到count/2返回
    public static ListNode middleNode2(ListNode head) {
        // count
        int count = 0;
        ListNode temp = head;
        while (temp != null) {
            count++;
            temp = temp.next;
        }

        temp = head;
        int i = 0;
        while (i < count / 2) {
            i++;
            temp = temp.next;
        }

        return temp;
    }


    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6};
        ListNode list = getList(arr);
        middleNode2(list);
    }

    private static ListNode getList(int[] arr) {
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
