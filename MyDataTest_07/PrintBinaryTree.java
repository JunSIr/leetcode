package MyDataTest_07;
/*
* 如何设计一个打印整棵树的打印函数
* 要点:本题设计合理即可
 * */
public class PrintBinaryTree {

    public static void printBT(Node head){
        System.out.println("begin print Binary Tree");
        print(head,0,"H",17);
    }
    /*
     * height:某节点在树中是哪个高度
     * to:某树节点属于上个节点的左还是右，根节点用"H"表示 右v左^
     * len:初始指定 单个树节点的值的总长度不超过len
     * */
    public static void print(Node head,int height,String to ,int len){
        if (head==null) return;
        /*
        * 先右后左，结果顺时针转90度即可
        * */
        print(head.right,height+1, "v",17);

        String val = to + head.value + to ; //节点值+指示符
        int lenV = val.length() ; //（节点值+指示符）长度
        int lenL = (len - lenV) / 2 ; //字符左边要补的空格  这么理解：（17-lenv）->左右总共空格--> /2 -->左边或右边
        int lenR = len - lenL - lenV ; //右边要补的空格
        val = getSpace(lenL) + val + getSpace(lenR); //左空+val+右空
        String preSpace = getSpace(height * len); //层数决定的前置空格
        System.out.println(preSpace + val);

        print(head.left,height+1,"^",17);
    }
    /*
    * 根据长度获取相应长度的空格
    * */
    public static String getSpace(int len){
        StringBuffer sb = new StringBuffer("") ;
        for (int i = 0; i < len; i++) {
            sb.append(" ");
        }
        return sb.toString() ;
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.left = new Node(-222222222);
        head.right = new Node(3);
        head.left.left = new Node(Integer.MIN_VALUE);
        head.right.left = new Node(55555555);
        head.right.right = new Node(66);
        head.left.left.right = new Node(777);
        printBT(head);

        head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(3);
        head.left.left = new Node(4);
        head.right.left = new Node(5);
        head.right.right = new Node(6);
        head.left.left.right = new Node(7);
        printBT(head);

        head = new Node(1);
        head.left = new Node(1);
        head.right = new Node(1);
        head.left.left = new Node(1);
        head.right.left = new Node(1);
        head.right.right = new Node(1);
        head.left.left.right = new Node(1);
        printBT(head);

    }
}
