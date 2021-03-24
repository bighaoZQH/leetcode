package com.bighao.queue.designCircularDeque;

/**
 * @version 1.0
 * @author: bighao周启豪
 * @date 2021/2/3 9:28
 * <p>
 * https://leetcode-cn.com/problems/design-circular-deque/
 * <p>
 * 设计循环双端队列
 *
 * 单链表实现
 *
 * 执行用时：7 ms, 在所有 Java 提交中击败了61.62%的用户
 * 内存消耗：39.1 MB, 在所有 Java 提交中击败了49.45%的用户
 */
public class MyCircularDeque1 {

    int max;

    int size;

    private ListNode head;

    private ListNode tail;

    /**
     * Initialize your data structure here. Set the size of the deque to be k.
     */
    public MyCircularDeque1(int k) {
        max = k;
    }

    /**
     * Adds an item at the front of Deque. Return true if the operation is successful.
     */
    public boolean insertFront(int value) {
        if (isFull()) {
            return false;
        }
        ListNode node = new ListNode(value);
        if (head == null) {
            head = node;
            tail = node;
            head.next = tail;
            size++;
            return true;
        }
        node.next = head;
        head = node;
        size++;
        return true;
    }

    /**
     * Adds an item at the rear of Deque. Return true if the operation is successful.
     */
    public boolean insertLast(int value) {
        if (isFull()) {
            return false;
        }
        ListNode node = new ListNode(value);
        if (head == null) {
            head = node;
            tail = node;
            head.next = tail;
            size++;
            return true;
        }
        tail.next = node;
        tail = node;
        size++;
        return true;
    }

    /**
     * Deletes an item from the front of Deque. Return true if the operation is successful.
     */
    public boolean deleteFront() {
        if (isEmpty()) {
            return false;
        }
        if (head == tail) {
            head = null;
            tail = null;
            size--;
            return true;
        }
        ListNode temp = head;
        head = head.next;
        temp.next = null;
        size--;
        return true;
    }

    /**
     * Deletes an item from the rear of Deque. Return true if the operation is successful.
     */
    public boolean deleteLast() {
        if (isEmpty()) {
            return false;
        }
        if (head == tail) {
            tail = null;
            head = null;
            size--;
            return true;
        }
        ListNode temp = head;
        while (true) {
            if (temp.next == tail) {
                break;
            }
            temp = temp.next;
        }
        temp.next = null;
        tail = temp;
        size--;
        return true;
    }

    /**
     * Get the front item from the deque.
     */
    public int getFront() {
        return isEmpty() ? -1 : head.val;
    }

    /**
     * Get the last item from the deque.
     */
    public int getRear() {
        return isEmpty() ? -1 : tail.val;
    }

    /**
     * Checks whether the circular deque is empty or not.
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Checks whether the circular deque is full or not.
     */
    public boolean isFull() {
        return size == max;
    }


    static class ListNode {
        ListNode next;
        int val;
        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }


    public static void main(String[] args) {
        MyCircularDeque1 circularDeque = new MyCircularDeque1(8);
        System.out.println(circularDeque.insertFront(8));
        System.out.println(circularDeque.getFront());
        System.out.println(circularDeque.isEmpty());
        System.out.println(circularDeque.deleteFront());
        System.out.println(circularDeque.insertLast(3));
        System.out.println(circularDeque.getRear());
        System.out.println(circularDeque.insertLast(7));
        System.out.println(circularDeque.insertFront(7));
        System.out.println(circularDeque.deleteLast());
        System.out.println(circularDeque.insertLast(4));
        System.out.println(circularDeque.isEmpty());
    }

    /**
     * ["MyCircularDeque","insertFront","deleteLast","getRear","getFront","getFront","deleteFront","insertFront","insertLast","insertFront","getFront","insertFront"]
     * [[4],[9],[],[],[],[],[],[6],[5],[9],[],[6]]
     *
     * ["MyCircularDeque","insertFront","getFront","isEmpty","deleteFront","insertLast","getRear","insertLast","insertFront","deleteLast","insertLast","isEmpty"]
     * [[8],[5],[],[],[],[3],[],[7],[7],[],[4],[]]
     *
     * ["MyCircularDeque","getFront","insertLast","insertLast","getRear","insertLast","isFull","deleteLast","insertFront","getFront"]
     * [[3],[],[2],[3],[],[4742],[],[],[4],[]]
     */

}
