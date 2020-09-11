package MyDataTest_08;
/*
* 给定一棵二叉树的头节点head，返回这颗二叉树中是不是完全二叉树
* 完全二叉树：
* 一棵深度为k的有n个结点的二叉树，对树中的结点按从上至下、从左到右的顺序进行编号，
* 如果编号为i（1≤i≤n）的结点与满二叉树中编号为i的结点在二叉树中的位置相同，
* 则这棵二叉树称为完全二叉树。
 * */
public class IsCBT {

    public static class Info{
        public int height ;
        public boolean isFull ; //是不是满二叉树
        public boolean isCBT ; //是否完全二叉

        public Info(int height, boolean isFull, boolean isCBT) {
            this.height = height;
            this.isFull = isFull;
            this.isCBT = isCBT;
        }
    }

    public static boolean isCBT(Node head){
        return  process(head).isCBT ;
    }

    public static Info process(Node head){

        if (head == null) return new Info(0,true,true) ;

        Info leftInfo = process(head.left);
        Info rightInfo = process(head.right);

        int height = Math.max(leftInfo.height , rightInfo.height) + 1  ;
        boolean isFull =
                (leftInfo.isFull && rightInfo.isFull && leftInfo.height == rightInfo.height ) ;

        boolean isCBT = false ;
        if (isFull){
            isCBT = true ;
        }else {
            /*
            * 核心决策
            * */
            if (leftInfo.isCBT && rightInfo.isCBT){
                /*
                * 三种情况模型
                * */
                if (rightInfo.isFull && leftInfo.height - rightInfo.height == 1) isCBT = true ;
                if (leftInfo.isFull && rightInfo.isFull && leftInfo.height - rightInfo.height == 1) isCBT = true ;
                if (leftInfo.isFull && rightInfo.height == leftInfo.height) isCBT = true ;
            }
        }

        return new Info(height,isFull,isCBT) ;
    }


    public static void main(String[] args) {
        Node head = new Node(2);
        head.left = new Node(1);
        head.right = new Node(3);
        head.left.right = new Node(4) ;



//        head.left.right = new Node(5);
//        head.right.left = new Node(6);
//        head.right.right = new Node(7);

        boolean is = isCBT(head);
        System.out.println(is);
    }
}
