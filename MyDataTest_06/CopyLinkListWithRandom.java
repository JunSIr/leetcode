package MyDataTest_06;

import java.util.HashMap;

/*
* 一种特殊的单链表节点类描述如下
class Node {
int value;
Node next;
Node rand;
Node(int val) { value = val; }
}
rand指针是单链表节点结构中新增的指针，rand可能指向链表中的任意一个节点，也可能指向null。
给定一个由Node节点类型组成的无环单链表的头节点 head，请实现一个函数完成这个链表的复制，并返回复制的新链表的头节点。
【要求】
时间复杂度O(N)，额外空间复杂度O(1)
*/

public class CopyLinkListWithRandom {
    /*带有random指针的节点*/
     static class Node {
        public int value;
        public Node next;
        public Node rand;

        public Node(int data) {
            this.value = data;
        }
    }


    /*
    * 笔试 使用容器HashMap 时间:O(n) 空间:O(n)
    * */
    public static Node copyLinkListWithRand_1(Node head) {

        //辅助容器
        HashMap<Node,Node> map = new HashMap<>();

        //复制并入哈希表
        Node cur = head ;
        while (cur!=null){
            //复制 --> map（Node,copyNode）
            map.put(cur,new Node(cur.value)) ;
            cur = cur.next ;
        }

        //连线
        cur = head ;
        while (cur!=null){
            map.get(cur).next = map.get(cur.next) ;
            map.get(cur).rand = map.get(cur.rand);
            cur = cur.next ;
        }

        return map.get(head) ;
    }

    /*
    * 面试用 时间O(n) 空间O(1)
    * */
    public static Node copyLinkListWithRand_2(Node head){

        //复制
        Node cur = head ;
        Node next = null  ;
        while (cur!=null){
            next = cur.next ;
            cur.next = new Node(cur.value) ;  //1->1'->2->2'
            cur.next.next= next ;
            cur = next ;
        }

        //set copyNode's random
        cur = head ;
        next = null ;
        Node nodeCp = null ; //用来指向复制节点的变量
        while (cur!=null){
            next = cur.next.next ; //保存cur下次要跳的节点
            nodeCp = cur.next ;
            //此处代码完美避免了空指针
            nodeCp.rand = cur.rand==null ? null : cur.rand.next ;
            cur = next ;
        }

        //spilt

        cur = head ;
        Node res = cur.next ; //返回结果为首个复制节点
        next = null ;
        nodeCp = null ;
        while (cur!=null){
            next = cur.next.next ;
            nodeCp = cur.next ;
            cur.next = next;
            //此处完美处理了空指针
            nodeCp.next = next==null? null : next.next ;
            cur = next ;
        }
        return  res ;
    }
    public static void main(String[] args) {
        Node head = null;
        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(5);
        head.next.next.next.next.next = new Node(6);

        head.rand = head.next.next.next.next.next; // 1 -> 6
        head.next.rand = head.next.next.next.next.next; // 2 -> 6
        head.next.next.rand = head.next.next.next.next; // 3 -> 5
        head.next.next.next.rand = head.next.next; // 4 -> 3
        head.next.next.next.next.rand = null; // 5 -> null
        head.next.next.next.next.next.rand = head.next.next.next; // 6 -> 4

        Node node = copyLinkListWithRand_2(head);
        while (node!=null){
            System.out.println("value:"+node.value);
            System.out.println("next:"+node.next.value);
            System.out.println("random:"+node.rand.value);
            System.out.println("===============");
            node = node.next ;
        }
    }
}
