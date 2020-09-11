package MyDataTest_06;
/*
*给定两个可能有环也可能无环的单链表，头节点head1和head2。请实现一个函数，如果两个链表相交，请返回相交的 第一个节点。如果不相交，返回null
【要求】
如果两个链表长度之和为N，时间复杂度请达到O(N)，额外空间复杂度 请达到O(1)。
*
* */

/* 分析：本题是一道三道题的杂交
* 之前在LeetCode提交的代码是利用了容器，本篇不利用容器，难度会相当高
* */
public class FindFirstIntersectNode {

    /*判断一个链表是否有环，如有，返回入环第一个节点，如无，返回null
    * 是个骚操作，记住就行
    * */
    public static Node getLoopNode(Node head){
        //排除铁定无环的情况: null/单节点/双节点 都不可能有环
        if (head==null||head.next==null||head.next.next==null) return null ;

            //以下，保证初始化不会空指针

        //快慢指针初始化
        Node slow = head.next ;
        Node fast = head.next.next ;

        //判断是否有无环
        while (slow!=fast){
            //如果有空，绝对不可能成环， .next是为了防止空指针
            if (fast.next==null||fast.next.next==null) {
                return null ;
            }
            slow = slow.next ;
            fast = fast.next.next ;
        }     //走完没返回null的话，有环，以下考虑返回第一个入环节点

        //骚操作，记住就好没必要证明
        fast = head ; //1.快指针来到头部
        //2.都走一步，相撞就是第一个入环节点
        while (slow!=fast){
            slow = slow.next ;
            fast = fast.next ;
        }
        return slow;
    }

    /*
    * 两个链表都无环的情况下，如有，返回相交第一个节点，如无，返回null
    * 需要知道，两个无环链表相交，必会共用相交之后的节点
    * */
    public static Node getnoLoopNode(Node head1,Node head2){

        if (head1==null||head2==null) return null  ;

        //判断是否有环，已知前提是两个链表都无环的，那么各自到最后节点，判断两个最终节点内存地址是否一致即可
        Node cur1 = head1 ;
        Node cur2 = head2 ;
        int n = 0 ; //计算两链表非公共部分差值
        while (cur1.next!=null){
            n++ ;
            cur1 = cur1.next ;
        }
        while (cur2.next!=null){
            n-- ;
            cur2 = cur2.next ;
        }

        if (cur1!=cur2) return null ;

        //以下，必相交，可以转化成单环问题
        cur1 = n < 0 ? head1 : head2 ; //cur1 -> 短链表头
        cur2 = cur1==head1? head2 :head1 ; //cur2 -> 长链表头

        //cur2与cur1同步
        n = Math.abs(n);
        while (n!=0){
            n-- ;
            cur2 = cur2.next ;
        }

        while (cur1!=cur2){
            cur1 = cur1.next ;
            cur2 = cur2.next ;
        }
        return cur1 ;
    }


    /*
     * 两个链表都有环的情况下，如有，返回相交第一个节点，如无，返回null
     * 需要知道，两个有环链表相交，必会共用相交之后的节点
     * 分三类，各自成环不相交/共用入环点/非共用入环点
     * */
    public static Node getallLoopNode(Node head1,Node loop1,Node head2,Node loop2){
        //不相交
        Node cur1 = head1 ;
        Node cur2 = head2 ;
       if(loop1==loop2){ //共用一个入环点
           //算差值
           int n = 0 ;//差值


           while (cur1!=loop1){
               n++ ;
               cur1 = cur1.next ;
           }
           while (cur2!=loop2){
               n-- ;
               cur2 = cur2.next ;
           }
           //以下，必相交，可以转化成单环问题
           cur1 = n < 0 ? head1 : head2 ; //cur1 -> 短链表头
           cur2 = cur1==head1? head2 :head1 ; //cur2 -> 长链表头
           //cur2与cur1同步
           n = Math.abs(n);
           while (n!=0){
               n-- ;
               cur2 = cur2.next ;
           }

           while (cur1!=cur2){
               cur1 = cur1.next ;
               cur2 = cur2.next ;
           }
           return cur1 ;
       }else {
           //如果非共用点相交
           cur1 = loop1.next ;
           while (cur1!=loop1){
               //如果相交
               if (cur1==loop2) return loop1 ;
               cur1 = cur1.next ;
           }
       }
        //最后就是不相交
        return null ;
    }

    public static Node findFirstIntersectNode(Node head1,Node head2){
        /*分类讨论*/

        if (head1 == null || head2 == null) {
            return null;
        }
        Node loop1 = getLoopNode(head1);
        Node loop2 = getLoopNode(head2);
        //都无环
        if (loop1 == null && loop2 == null) {
            return getnoLoopNode(head1, head2);
        }
        //都有环
        if (loop1 != null && loop2 != null) {
            return getallLoopNode(head1, loop1, head2, loop2);
        }
        return null;
    }

    public static void main(String[] args) {
        // 1->2->3->4->5->6->7->null
        Node head1 = new Node(1);
        head1.next = new Node(2);
        head1.next.next = new Node(3);
        head1.next.next.next = new Node(4);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(6);
        head1.next.next.next.next.next.next = new Node(7);

        // 0->9->8->6->7->null
        Node head2 = new Node(0);
        head2.next = new Node(9);
        head2.next.next = new Node(8);
        head2.next.next.next = head1.next.next.next.next.next; // 8->6
        System.out.println(findFirstIntersectNode(head1, head2).value);

        // 1->2->3->4->5->6->7->4...
        head1 = new Node(1);
        head1.next = new Node(2);
        head1.next.next = new Node(3);
        head1.next.next.next = new Node(4);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(6);
        head1.next.next.next.next.next.next = new Node(7);
        head1.next.next.next.next.next.next = head1.next.next.next; // 7->4

        // 0->9->8->2...
        head2 = new Node(0);
        head2.next = new Node(9);
        head2.next.next = new Node(8);
        head2.next.next.next = head1.next; // 8->2
        System.out.println(findFirstIntersectNode(head1, head2).value);

        // 0->9->8->6->4->5->6..
        head2 = new Node(0);
        head2.next = new Node(9);
        head2.next.next = new Node(8);
        head2.next.next.next = head1.next.next.next.next.next; // 8->6
        System.out.println(findFirstIntersectNode(head1, head2).value);

    }
}
