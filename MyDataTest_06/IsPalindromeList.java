package MyDataTest_06;


import java.util.Stack;

/*
*给定一个单链表的头节点head，请判断该链表是否为回文结构。
1）栈方法特别简单（笔试用）
2）改原链表的方法就需要注意边界了（面试用）
* */

public class IsPalindromeList {


    /*面试场景：使用辅助栈解决，简单粗暴 O(n) O(n)*/
    public static boolean  isPalindromeList_1(Node head){
        Stack<Node> stack = new Stack<>() ; //辅助栈
        //为了不改变head引用，全部使用辅助节点操作
        Node node1 = head ;
        Node node2 = head ;
        //链表节点全部入栈，
        while (node1!=null){
            stack.push(node1) ;
            node1 = node1.next ;
        }
        //从头开始与栈弹出元素做对比
        while (node2!=null){
            if (node2.value!=stack.pop().value) return false ;
            node2 = node2.next ;
        }
        return true ;
    }

    /*面试可谈：节省一半空间的辅助栈 O(n) O(n/2)*/
    public static boolean  isPalindromeList_2(Node head){
        //保证至少有两个节点
        if (head==null||head.next==null) return true ;

        Stack<Node> stack = new Stack<>() ; //辅助栈
        Node help = head ; //为了不改变head引用
        //偶数下中点模型
        Node fast = head.next ; //快指针
        Node slow = head.next ; //慢指针
        //让慢指针走到中点（奇数是正中点，偶数是上中点）
        while (fast.next!=null && fast.next.next!=null){
            slow = slow.next ;
            fast= fast.next.next ;
        }
        //中点以后的元素入栈
        while (slow!=null){
            stack.push(slow) ;
            slow = slow.next ;
        }
        //从头开始与栈弹出元素进行比对了
        while (!stack.isEmpty()){
            if (help.value!=stack.pop().value) return false ;
            help = help.next ;
        }
        return true ;
    }

    /*面试最优解 O(n) O(1)  纯快慢指针*/
    public static boolean  isPalindromeList_3(Node head){
        /*  常规定位中点 */
        if (head == null || head.next == null) {
            return true;
        }
        Node slow = head;
        Node fast = head;
        while (fast.next != null && fast.next.next != null) { // find mid node
            slow = slow.next; // slow -> mid
            fast = fast.next.next; // fast -> end
        }

        /* 后半部分逆序 */
        fast = slow.next ; //fast指向中点slow的下一个
        slow.next = null ; //slow中点的下一个指向空
        Node help = null ; //辅助节点，用来指向需要保存的节点
        while (fast!=null){
            /*此部分不懂画图*/
            help = fast.next ;
            fast.next = slow ;
            slow = fast ;
            fast = help ;
        }

        /*两边向中间靠，逐个节点比对*/
        fast = head ;  //fast在头，slow在尾部
        help = slow ; //保存末尾节点
        boolean res = true ;
        //此处不必担心空指针问题，偶数情况下，slow到null就进不来了，&不要搞混了
        while (fast!=null&&slow!=null) {
            if (fast.value!=slow.value) res =false ;   //不能直接return false ; 因为要还原链表
            fast = fast.next ;
            slow = slow.next ;
        }

        /*还原链表*/
        slow = help.next ;
        help.next = null ;
        while (fast!=null){
            fast = slow.next ;
            slow.next = help ;
            help = slow  ;
            slow = fast  ;
        }
        return res ;
    }

    public static void main(String[] args) {
        Node test = new Node(1) ;

        test.next = new Node(2);
        test.next.next = new Node(21) ;
        test.next.next.next = new Node(1);
        System.out.println(isPalindromeList_3(test));

    }
}


