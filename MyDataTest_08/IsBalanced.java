package MyDataTest_08;
/* 二叉树的递归套路

* 1）假设以X节点为头，假设可以向X左树和X右树要任何信息
* 2）在上一步的假设下，讨论以X为头节点的树，得到答案的可能性（最重要）常见- 与x有关，与x无关
* 3）列出所有可能性后，确定到底需要向左树和右树要什么样的信息
* 4）把左树信息和右树信息求全集，就是任何一棵子树都需要返回的信息S
* 5）递归函数都返回S，每一棵子树都这么要求
* 6）写代码，在代码中考虑如何把左树的信息和右树信息整合出整棵树的信息

* */


/*二叉树数据结构*/
class Node {
    public int value;
    public Node left;
    public Node right;

    public Node(int data) {
        this.value = data;
    }
}

/*
 * 给定一棵二叉树的头节点head，返回这颗二叉树是不是平衡二叉树
 * 平衡二叉树定义： 任何节点，左右子树高度差不大于1
 */

public class IsBalanced {
    /*
    * 要向左右子树要的信息
    * */
    public static class Info{
        public boolean isBalanced ; //是否平衡
        public int height ; //高度

        public Info(boolean isBalanced, int height) {
            this.isBalanced = isBalanced;
            this.height = height;
        }
    }

    public static boolean isBalance(Node head){
        return process(head).isBalanced ;
    }

    public static Info process(Node head){

        //base case
        if (head==null) return new Info(true,0) ;

        //需要的信息
        Info leftInfo = process(head.left);
        Info rightInfo = process(head.right);

        //我自己的判断逻辑
        int height = Math.max(leftInfo.height , rightInfo.height) + 1  ; //我的高度

        boolean isBalance = false ; //我的平衡性:左右子树平衡且左右子树高度差不大于1

        if (leftInfo.isBalanced && rightInfo.isBalanced && Math.abs(rightInfo.height - leftInfo.height) <=1){
            isBalance = true ;
        }

        return new Info(isBalance,height) ;
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(3);
        head.left.left = new Node(4);


//        head.left.right = new Node(5);
//        head.right.left = new Node(6);
//        head.right.right = new Node(7);

        boolean balance = isBalance(head);
        System.out.println(balance);
    }

}
