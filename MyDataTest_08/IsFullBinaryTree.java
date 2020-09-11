package MyDataTest_08;
/*
* 给定一棵二叉树的头节点head，返回这颗二叉树是不是满二叉树
*   满二叉树:一棵深度为k且有2^k个结点的二叉树称为满二叉树 --> (2^H) - 1 = N
 * */
public class IsFullBinaryTree {
// (1 << all.height) - 1 == all.nodes

    public static class Info{
        public int height ;//高度
        public int nodes ; //节点数

        public Info(int height, int nodes) {
            this.height = height;
            this.nodes = nodes;
        }
    }

    public static boolean isFullBinaryTree(Node head){
        Info all = process(head);
        return (1 << all.height) - 1 == all.nodes ;
    }

    public static Info process(Node head){
        //base case
        if (head==null) return new Info(0,0);

        Info leftInfo = process(head.left);
        Info rightInfo  = process(head.right);

        /*
        * 开始决策
        * */
        int height = Math.max(leftInfo.height,rightInfo.height) + 1 ;
        int nodes = leftInfo.nodes + rightInfo.nodes + 1  ;

        return new Info(height,nodes);
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(3);



//        head.left.right = new Node(5);
//        head.right.left = new Node(6);
//        head.right.right = new Node(7);

        boolean is = isFullBinaryTree(head);
        System.out.println(is);
    }
}
