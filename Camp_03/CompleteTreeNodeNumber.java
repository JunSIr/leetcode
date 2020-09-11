package Camp_03;

/*
* 求完全二叉树节点数目 要求O(logN)
* 高度玩等差数列
* */
public class CompleteTreeNodeNumber {

    //二叉树节点
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    public static int nodeNum(Node head) {
        if (head == null) {
            return 0;
        }
        return bs(head, 1, mostLeftLevel(head, 1));
    }
    // node在第level层，h是总的深度（h永远不变，全局变量
    // 以node为头的完全二叉树，节点个数是多少
    public static int bs(Node node, int level, int h) {
        if (level == h) {
            return 1;  //到达最后一个节点
        }
        //如果右树深度与左树深度一样，左树可确定全满，右树再递归
        if (mostLeftLevel(node.right, level + 1) == h) {
            return (1 << (h - level)) + bs(node.right, level + 1, h);
        }else { //右树深度与左数不一样 右树可确定 左树再递归
            return (1 << (h - level - 1)) + bs(node.left,level + 1, h);
        }
    }
    /*
    * 求以node为头节点的树 总共有几层 完全二叉树的情况下
    * */
    public static int mostLeftLevel(Node node, int level){
        while (node != null){
            level++;
            node = node.left;
        }
        return level - 1;
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(3);
        head.left.left = new Node(4);
        head.left.right = new Node(5);
        head.right.left = new Node(6);
        System.out.println(nodeNum(head));
    }
}
