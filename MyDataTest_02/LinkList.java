package MyDataTest_02;

/*单链表数据结构*/
class Node {
        public int value;
        public Node next;
        public Node(int data) {
            value = data;
        }
}
/*双链表数据结构*/
class DoubleNode {
    public int value;
    public DoubleNode last;
    public DoubleNode next;
    public DoubleNode(int data) {
        value = data;
    }
}

public class LinkList {

    public static void main(String[] args) {
        Node head = new Node(1) ;
        head.next = new Node(2) ;
        head.next.next = new Node(3) ;

    }

    /*单链表非递归反转*/
    public static Node reverseLinkList(Node head){
        //准备两个辅助节点，初始化为null
        Node pre = null;
        Node next = null ;

        while (head!=null){
            next = head.next ; //记录/抓取头后面的节点
            head.next = pre ; //反转指向 --> 只有这一步可以画指向图
            pre = head ; //归位
            head = next ; //归位
        }
        return pre ;
    }

    /*双向链表非递归反转*/
    public static DoubleNode reverseDoubleLinkList(DoubleNode head){
        //准备两个辅助节点，初始化为null
        DoubleNode pre = null;
        DoubleNode next = null ;

        while (head!=null){
            next = head.next ; //记录/抓取头后面的节点
            head.next = pre ; //反转指向 --> 只有这一步可以画指向图
            head.last = next ;
            pre = head ; //归位
            head = next ; //归位
        }
        return pre ;
    }

    /*单向链表删除定值*/
    public static Node removeValue(Node head,int value){

        //找到第一个不等于value的node
        while (head!=null){
            if (head.value!=value) break;
            head = head.next  ;
        }

        //初始化快慢指针，快慢指针为相邻连体婴儿
        Node pre = null ;
        Node cur = null ;

        //
        while (cur!=null){
            if (cur.value==value){
                pre.next = cur.next ;
            }else {
                pre = cur ;
            }
            cur = cur.next ;
        }
        return  head ;
    }
}
