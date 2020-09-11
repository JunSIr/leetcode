package Camp_03;

/**
 * 二叉树转换为双向链表
 * 思想 : left做pre right做last 二叉树递归套路
 * */
public class BSTtoDoubleLinkedList {

    //二叉树节点
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    public static Node convert(Node head){
        if (head == null){
            return null ;
        }
        return process(head).start ;
    }
    // 整棵树，串成双向链表，返回头、尾
    // 返回头尾是为了契合子递归的含义
    public static class Info{
        public Node start ;
        public Node end ;

        public Info(Node start, Node end) {
            this.start = start;
            this.end = end;
        }
    }

    public static Info process(Node X){
        //base case
        if (X == null){
            return new Info(null,null) ;
        }

        Info leftInfo = process(X.left);
        Info rightInfo = process(X.right);

        if (leftInfo.end != null){
            leftInfo.end.right = X ;
        }

        X.left = leftInfo.end ;
        X.right = rightInfo.start ; //允许空

        if (rightInfo.start!=null){
            rightInfo.start.left = X ;
        }

        return  new Info(
                leftInfo.start != null ? leftInfo.start : X ,
                rightInfo.end != null ? rightInfo.end : X
        ) ;
    }

}
