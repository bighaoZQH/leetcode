package com.bighao.linkedlist;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * @version 1.0
 * @author: bighao周启豪
 * @date 2020/12/7 13:40
 * 
 * https://leetcode-cn.com/problems/linked-list-cycle-ii/
 *
 * 判断一个单链表是否有环，有环则返回第一个入环的节点
 * 1.哈希表
 * 2.快慢指针(弗洛伊德判圈法)  https://blog.csdn.net/Javasus/article/details/50015687
 *
 *
 * 算法描述
 * 如果有限状态机、迭代函数或者链表存在环，那么一定存在一个起点可以到达某个环的某处(这个起点也可以在某个环上)。
 *
 * 初始状态下，假设已知某个起点节点为节点S。现设两个指针t和h，将它们均指向S。
 *
 * 接着，同时让t和h往前推进，但是二者的速度不同：t每前进1步，h前进2步。只要二者都可以前进而且没有相遇，就如此保持二者的推进。当h无法前进，即到达某个没有后继的节点时，就可以确定从S出发不会遇到环。反之当t与h再次相遇时，就可以确定从S出发一定会进入某个环，设其为环C。
 *
 * 如果确定了存在某个环，就可以求此环的起点与长度。
 *
 * 计算环长度
 *
 * 上述算法刚判断出存在环C时，显然t和h位于同一节点，设其为节点M。显然，仅需令h不动，而t不断推进，最终又会返回节点M，统计这一次t推进的步数，显然这就是环C的长度。
 *
 * 为了求出环C的起点，只要令h仍位于节点M，而令t返回起点节点S。随后，同时让t和h往前推进，且保持二者的速度相同：t每前进1步，h前进1步。持续该过程直至t与h再一次相遇，设此次相遇时位于同一节点P，则节点P即为从节点S出发所到达的环C的第一个节点，即环C的一个起点。
 *
 * 链表起点为节点S，环起点为节点P，t和h相遇时位于同一节点M，S和P之间的距离为p，P和M之间的距离为m，环长为C，这里两点之间的距离是指从一点走多少步可以到点另外一点。
 *
 * 当t和h相遇时，
 *
 * t走的步数，step = p + m + a * C，a表示相遇时t走的圈数
 *
 * h走的步数，2 * step = p + m + b * C，b表示相遇时h走的圈数
 *
 * 两者相减：step = (b - a) * C = p + m + a * C，由此可知t走的步数是环C的倍数，即 p + m 刚好是环长度C的倍数。
 *
 * t和h在M处相遇，为了计算环C的起点，令h仍位于节点M，而令t返回起点S，随后，同时让t和h往前推进，且保持两者的速度相同：t每前进1步，h前进1步。持续该过程直至t与h再一次相遇，则它们此次相遇时一定位于环的起始节点P。为什么它们此次相遇时一定在环起始节点呢？
 *
 * t走了p步到达P，h在环C上p步在哪呢？h从M处出发走了p步，相对于环起始位置，h走过的距离是 m + p，而m + p刚好是环长度C的倍数，即h此时也位于环起始节点处，即t和h在P处相遇。据此就可以计算出环起始节点的位置。
 *
 *
 * 2.计算环的长度
 * 这一步比较简单，让其中一个指针停在B不动，另一个一步一步向前走并记录步数，再次相遇时步数即为环的长度。
 *
 * 3.寻找环的起点
 * 其中一个指针在B不动，另一个放到起点S，两个指针同时一步一步移动，则两指针将会在循环节的起点相遇。
 */
public class 环形链表IIMedium142 {

    /**
     * 快慢指针
     */
    public static ListNode hasCycle2(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }
        ListNode l1 = head;
        ListNode l2 = head.next;
        while (l2 != null && l2.next != null) {
            if (l1 == l2) {
                // 进入该if表示该链表有环，此时其中一个节点回到head节点，然后两个节点同时向后推进1步，如果l2.next与l1相等，说明l1是环的起始节点
                l1 = head;
                while (true) {
                    if (l2.next == l1) {
                        return l1;
                    }
                    l1 = l1.next;
                    l2 = l2.next;
                }
            }
            l1 = l1.next;
            l2 = l2.next.next;
        }
        return null;
    }


    /**
     * 哈希表 用hashSet改进
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

        ListNode result = hasCycle2(l1);
        System.out.println(result == null ? null : result.val);
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
