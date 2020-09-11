package MyDataTest_06;

import com.class06.Code03_SmallerEqualBigger;

import java.sql.SQLClientInfoException;

/*
* 将单向链表按某值划分成左边小、中间相等、右边大的形式
1）把链表放入数组里，在数组上做partition（笔试用）
2）分成小、中、大三部分，再把各个部分之间串起来（面试用
* */
public class SmallerEqualBigger {

    /*笔试使用_使用辅助数组空间，在数组上做partition
    * 时间O(n) 空间(n)
    * */
    public static Node listPartition_1(Node head ,int pivot){

        if (head==null||head.next==null) return head ;

        /*开辟一个能容纳链表节点的数组*/

        //算出链表长度i
        Node cur = head ;
        int i = 0 ;
        while (cur!=null){
            i++ ;
            cur = cur.next ;
        }

        //开辟数组
        Node[] arr = new Node[i] ;

        //把链表元素转移到数组
        cur =  head ;
        i = 0  ;
        while (cur!=null){
            arr[i++] = cur ;
            cur = cur.next ;
        }

        //把数组分区
        arrParttion_2(arr,pivot);

        //数组元素转回链表 注意此处只需要连n-1次
        for ( i = 1; i < arr.length; i++) {
            arr[i-1].next = arr[i];
        }
        arr[i-1].next= null ;

        return arr[0] ;
    }
    //在数组上做分区
    public static void arrParttion_2(Node[] arr , int pivot){

        int small = -1 ; //小于区初始下标
        int big = arr.length ; //大于区初始下标
        int index = 0  ; //数组起始遍历下标

        while (index!=big){
            if (arr[index].value<pivot){
                swap(arr,++small,index++);
            }else if (arr[index].value>pivot){
                swap(arr,--big,index);
            }else {
                index++ ;
            }

        }
    }

    public static void swap(Node[] nodeArr, int a, int b) {
        Node tmp = nodeArr[a];
        nodeArr[a] = nodeArr[b];
        nodeArr[b] = tmp;
    }

    /*
    * 面试使用_使用有限变量完成
    * 时间O（n） 空间(1)
    * */
    public static Node listPartition_2(Node head,int privot){

        //需要六个变量，分别是小于区、等于区、大于区的头尾指针
        Node sH = null ;//小于区头指针
        Node sT = null ;//小于区尾指针
        Node eH = null ;//等于区头指针
        Node eT = null ;//等于区尾指针
        Node bH = null ;//大于区头指针
        Node bT = null ;//大于区尾指针

        Node next = null ;//做记录的节点
        //头节点往下走，依次比较，把小中大区的头尾指针串好
        while (head!=null){

            next = head.next ; //记录head的next节点
            head.next =null ;
            //当前节点小于privot
            if (head.value<privot){
                //如果是空，头尾指针全部指向当前节点
                if (sH==null){
                    sH = head;
                    sT = head ;
                }else{
                    sT.next = head ; //尾指针.next指向当前节点
                    sT = head ;  // 尾指针变成当前节点
                }
            }else if (head.value==privot){
                //如果是空，头尾指针全部指向当前节点
                if (eH==null){
                    eH = head;
                    eT = head ;
                }else{
                    eT.next = head ; //尾指针.next指向当前节点
                    eT = head ;  // 尾指针变成当前节点
                }
            }else {
                //如果是空，头尾指针全部指向当前节点
                if (bH==null){
                    bH = head;
                    bT = head ;
                }else{
                    bT.next = head ; //尾指针.next指向当前节点
                    bT = head ;  // 尾指针变成当前节点
                }
            }
            head = next ; //当前节点来到下一个节点
        }

        /*（小中大头尾相接，需要考虑小中大分区各自是否空的情况）*/

        //小于区和等于区连好，所有情况都考虑上
        if (sT!=null){
            sT.next = eH;  //头尾接
            //判断等于区是否空
            eT = eT==null? sT:eT  ;//考虑了等于区是否空，不空不变，空的话上一步被覆盖
        }

        //等于区和大于区连上，小于区和等于区自适应了，不用管
        if (eT!=null){
            eT.next = bH ;
        }

        return sH != null? sH :(eH != null ? eH : bH) ;

    }
    public static void main(String[] args) {
        Node test = null;
        test = new Node(0);
        test.next = new Node(5);
        test.next.next = new Node(7);
        test.next.next.next = new Node(7);
        test.next.next.next.next = new Node(6);
        test.next.next.next.next.next = new Node(6);
        test.next.next.next.next.next.next = new Node(8);
        test.next.next.next.next.next.next.next = new Node(6);

        Node node = listPartition_2(test, 7);
        while (node!=null){
            System.out.println(node.value);
            node= node.next ;
        }

    }
}
