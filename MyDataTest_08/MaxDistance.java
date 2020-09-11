package MyDataTest_08;
/*
* 给定一棵二叉树的头节点head，任何两个节点之间都存在距离，
* 返回整棵二叉树的最大距离
*
* 单节点分类讨论
* 1) 最大距离不经过本点 返回左右子树最大距离
* 2）最大距离经过本点，返回经过本点的最大距离，左右高度差+1
* 关键点：你需要考虑的是 ”本点“ 就是满足条件或不满足条件的”头“结点 -- 不好理解
* */


public class MaxDistance {

    public static class Info{
        public int height ; //高度
        public int maxDistance ; //最大距离

        public Info(int height, int maxDistance) {
            this.height = height;
            this.maxDistance = maxDistance;
        }
    }

    public static int getMaxDistance(Node head){
        return process(head).maxDistance ;
    }

    public static Info process(Node head){
        //base case
        if (head==null) return new Info(0,0) ;
        //左右子树所有信息
        Info leftInfo = process(head.left);
        Info rightInfo = process(head.right);


        //需要的信息
        int height = Math.max(leftInfo.height,rightInfo.height) + 1  ; //本节点高度
        int sonMaxDistance = Math.max(leftInfo.maxDistance,rightInfo.maxDistance) ; //左右子树的最大距离
        int maxDistance = Math.max(sonMaxDistance,leftInfo.height + rightInfo.height + 1 ) ; //x+y+1是绕过本节点的最大距离

        return new Info(height,maxDistance) ;
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(3);
        head.left.left = new Node(4);
        head.left.left.left = new Node(4);
//
//
        head.left.right = new Node(5);
//        head.right.left = new Node(6);
//        head.right.right = new Node(7);

        int maxDistance = getMaxDistance(head);
        System.out.println(maxDistance);
    }

}
