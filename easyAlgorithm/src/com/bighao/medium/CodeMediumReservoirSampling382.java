package com.bighao.medium;

import java.util.Random;

/**
 * @Author: bighao周启豪
 * @Date: 2020/4/20 3:54
 * @Version 1.0
 * <p>
 * https://leetcode-cn.com/problems/linked-list-random-node/
 *
 * 蓄水池抽样算法
 * https://blog.csdn.net/zm714981790/article/details/51407552
 */
public class CodeMediumReservoirSampling382 {

    public static void main(String[] args) {
        // 初始化一个单链表 [1,2,3].
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        CodeMediumReservoirSampling382 solution = new CodeMediumReservoirSampling382(head);
        // getRandom()方法应随机返回1,2,3中的一个，保证每个元素被返回的概率相等。
        System.out.println(solution.getRandom2());
    }

    private ListNode head;

    public CodeMediumReservoirSampling382(ListNode head) {
        this.head = head;
    }

    /**
     * Returns a random node's value.
     */
    public int getRandom2() {
        // 先选取k个元素放入蓄水池，此题就只需要1个，因此就不需要数组来保存了
        ListNode reservoir = head;
        // 从k+1开始取，替换蓄水池中的元素
        ListNode cur = head.next;
        int count = 1;
        // 遍历链表
        while (cur != null) {
            // 换入时随机的选取一个作为替换项（１到ｋ的随机数）
            int r = (int) (Math.random() * (count + 1));
            // 如果r==0，就替换蓄水池里的元素
            if (r == 0) {
                reservoir = cur;
            }
            // 遍历链表下一个节点
            cur = cur.next;
            count++;
        }
        return reservoir.val;
    }

    public int getRandom4() {
        //以1的概率选择该元素head
        ListNode current = head;
        ListNode temp = head.next;
        int len = 2;
        while (temp != null){
            //以1/len的概率选择元素temp
            //从1，2，3，4，5...len 中随机选择一个元素
            //random()产生的是[0,1)的double型的数
            int target = 1 + (int) (Math.random() * len);
            if (target == len)
                current = temp;
            temp = temp.next;
            len++;
        }
        return current.val;
    }

    public int getRandom3() {
        int res = head.val;
        ListNode no = head.next;
        int i = 2;
        Random random = new Random();
        while(no!=null){
            if(random.nextInt(i) == 0){
                res = no.val;
            }
            i++;
            no = no.next;
        }
        return res;

    }


    public int getRandom() {
        int count = 0;
        ListNode reservoir = head;
        ListNode cur = head;
        while (cur != null) {
            int rand = (int) (Math.random() * (count + 1));
            if (rand == 0) {
                reservoir = cur;
            }
            cur = cur.next;
            count++;
        }
        return reservoir.val;
    }
}

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}
