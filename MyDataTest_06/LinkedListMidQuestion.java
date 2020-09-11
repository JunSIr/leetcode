package MyDataTest_06;

/*链表中点问题
* 扣清楚奇偶问题是关键
* 方法论：
* 面试时链表解题的方法论
* 1)对于笔试，不用太在乎空间复杂度，一切为了时间复杂度
* 2)对于面试，时间复杂度依然放在第一位，但是一定要找到空间最省的方法
* 常用技巧
* 1）使用容器(哈希表、数组等)
* 2）快慢指针
* */


import com.class06.Code01_LinkedListMid;

import java.util.ArrayList;

/*
* 数据结构
* */
class Node{
    public int value;
    public Node next;
    public Node(int v) {
        value = v;
    }
}
/*
* 全程使用快慢指针，快指针移动速度为两倍慢指针速度，快指针走完时，满指针达到中点，高中v-t图原理
* 初始条件的设置决定了返回的是关于中点的哪个位置
* */
public class LinkedListMidQuestion {

    /*
     输入链表头节点，奇数长度返回中点，偶数长度返回上中点
    */

    public static Node midOrUpMidNode(Node head){

        //判空与预处理 此步走完保证节点数目>=3个 根据该情况可以判断出快慢指针初始条件
        if (head==null||head.next==null||head.next.next==null) {
            return head ;
        }
        //初始化快慢指针
        Node slow = head.next ;
        Node fast = head.next.next ;
        //固定的
        while (fast.next!=null&&fast.next.next!=null){
            slow = slow.next ;//慢走一步
            fast = fast.next.next; //快走两步
        }
        return slow ;
    }

    /*
    输入链表头节点，奇数长度返回中点，偶数长度返回下中点
    */

    public static Node midOrDownMidNode(Node head){
        //判空与预处理 此步走完保证节点数目>=2 根据该情况可以判断出快慢指针初始条件
        if (head==null||head.next==null) return head ;
        //初始化快慢指针
        Node slow = head.next ;
        Node fast = head.next ;
        while (fast.next!=null&&fast.next.next!=null){
            slow = slow.next ;//慢走一步
            fast = fast.next.next; //快走两步
        }
        return slow ;
    }

    /*
    输入链表头节点，奇数长度返回中点前一个，偶数长度返回上中点前一个
    */

    public static Node preMidOrUpMidPreNode(Node head){
        //判空与预处理 此步走完保证节点数目>=3 根据该情况可以判断出快慢指针初始条件
        if (head==null||head.next==null||head.next.next==null) return null ;
        //初始化快慢指针
        Node slow = head ;
        Node fast = head.next.next ;
        while (fast.next != null && fast.next.next!=null){
            slow = slow.next ;//慢走一步
            fast = fast.next.next; //快走两步
        }
        return slow ;
    }

        /*
    输入链表头节点，奇数长度返回中点前一个，偶数长度返回下中点前一个
    */
    public static Node preMidOrDownMidPreNode(Node head){
        //判空与预处理 此步走完保证节点数目>=2 根据该情况可以判断出快慢指针初始条件
        if (head==null||head.next==null) return null ;

        //初始化快慢指针
        Node slow = head ;
        Node fast = head.next ;
        while (fast.next != null&&fast.next.next!=null){
            slow = slow.next ;//慢走一步
            fast = fast.next.next; //快走两步
        }
        return slow ;
    }

                /*面试版_使用哈希表直接取下标*/

    public static Code01_LinkedListMid.Node right1(Code01_LinkedListMid.Node head) {
        if (head == null) {
            return null;
        }
        Code01_LinkedListMid.Node cur = head;
        ArrayList<Code01_LinkedListMid.Node> arr = new ArrayList<>();
        while (cur != null) {
            arr.add(cur);
            cur = cur.next;
        }
        return arr.get((arr.size() - 1) / 2);
    }

    public static Code01_LinkedListMid.Node right2(Code01_LinkedListMid.Node head) {
        if (head == null) {
            return null;
        }
        Code01_LinkedListMid.Node cur = head;
        ArrayList<Code01_LinkedListMid.Node> arr = new ArrayList<>();
        while (cur != null) {
            arr.add(cur);
            cur = cur.next;
        }
        return arr.get(arr.size() / 2);
    }

    public static Code01_LinkedListMid.Node right3(Code01_LinkedListMid.Node head) {
        if (head == null || head.next == null || head.next.next == null) {
            return null;
        }
        Code01_LinkedListMid.Node cur = head;
        ArrayList<Code01_LinkedListMid.Node> arr = new ArrayList<>();
        while (cur != null) {
            arr.add(cur);
            cur = cur.next;
        }
        return arr.get((arr.size() - 3) / 2);
    }

    public static Code01_LinkedListMid.Node right4(Code01_LinkedListMid.Node head) {
        if (head == null || head.next == null) {
            return null;
        }
        Code01_LinkedListMid.Node cur = head;
        ArrayList<Code01_LinkedListMid.Node> arr = new ArrayList<>();
        while (cur != null) {
            arr.add(cur);
            cur = cur.next;
        }
        return arr.get((arr.size() - 2) / 2);
    }

    //test
    public static void main(String[] args) {
        Node test = null;
        test = new Node(0);
        test.next = new Node(1);
        test.next.next = new Node(2);
        test.next.next.next = new Node(3);
        test.next.next.next.next = new Node(4);
        test.next.next.next.next.next = new Node(5);
        test.next.next.next.next.next.next = new Node(6);
        test.next.next.next.next.next.next.next = new Node(7);

        Node node = midOrUpMidNode(test) ;
        Node node1 = midOrDownMidNode(test);
        Node node2 = preMidOrUpMidPreNode(test);
        Node node3 = preMidOrDownMidPreNode(test);
        System.out.println(node.value);
        System.out.println(node1.value);
        System.out.println(node2.value);
        System.out.println(node3.value);
    }
}
