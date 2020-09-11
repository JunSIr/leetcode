package Camp_03;

/*
* 二叉树递归套路
* 路径和问题三连
* 给定一个二叉树的头节点head,路径的规定有以下三种不同规定
* 1.路径必须是头节点触发，到叶节点为止，返回最大路径和
* 2.路径可以从任何节点出发，但必须往下走到任何节点，返回最大路径和
* 3.路径可以从任何节点出发，到任何节点，返回最大路径和
*
* */
public class MaxSumInTree {

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int val) {
            value = val;
        }
    }

    //类属性
    public static int maxSum = Integer.MIN_VALUE ;

    /*
     * 第一题，头节点出发到叶子节点，暴力做法
     * */
    public static int maxPath(Node head){
        maxSum = Integer.MIN_VALUE ;
        p(head, 0);
        return maxSum ;
    }

    /*
    * 从上到下，到下一个节点时带着本节点目前路径和
    * */
    public static void p(Node x, int pre){
        //到达叶子节点，结算当前路径
        if (x.left==null && x.right==null){
            maxSum = Math.max(maxSum, pre + x.value) ;
        }

        if (x.left!=null){
            p(x.left,pre + x.value);
        }

        if (x.right!=null){
            p(x.right, pre + x.value);
        }

    }

    /*
    * 第一题的暴力递归做法
    * */
    public static int maxPath_lowToUp(Node head){
        if (head == null){
            return  0  ;
        }
        return process(head) ;
    }

    /*
     * 从上到下，到下一个节点时带着本节点目前路径和
     * */
    public static int process(Node x){
        //到达叶子节点，结算当前路径
        if (x.left==null && x.right==null){
            return x.value ;
        }

        int next  = Integer.MIN_VALUE ;

        if (x.left != null){
            next = process(x.left) ;
        }
        if (x.right != null){
            next = Math.max(next,process(x.right));  //左右子路径做决策，选最大
        }

        return  next + x.value ;

    }


    /*
    * 第二题 任何节点 从上到下
    * 需要讨论 :
    * 1）X无关的时候， 1， 左树上的整体最大路径和 2， 右树上的整体最大路径和
    * 2) X有关的时候 3， x自己 4， x往左走 5，x往右走
    * */
    public static int maxPath_2(Node head){
        if (head == null){
            return  0  ;
        }
        return 0  ;
    }

    public static class Info{
        public int allTreeMaxSum  ;
        public int fromHeadMaxSum ;

        public Info(int allTreeMaxSum, int fromHeadMaxSum) {
            this.allTreeMaxSum = allTreeMaxSum;
            this.fromHeadMaxSum = fromHeadMaxSum;
        }
    }

    public static Info process_2(Node x){
        if (x == null){
            return  null ;
        }

        Info leftInfo = process_2(x.left);
        Info rightInfo = process_2(x.right);

        int p1 = Integer.MIN_VALUE ;
        int p4 = Integer.MIN_VALUE ;
        if (leftInfo!=null){
            p1 = leftInfo.allTreeMaxSum ;
            p4 = leftInfo.fromHeadMaxSum + x.value;
        }

        int p3 = x.value ;

        int p2 = Integer.MIN_VALUE ;
        int p5 = Integer.MIN_VALUE ;
        if (rightInfo!=null){
            p2 =  rightInfo.allTreeMaxSum ;
            p5 = rightInfo.fromHeadMaxSum + x.value ;
        }

        int allTreeMaxSum  = Math.max(
                Math.max(Math.max(p1,p2),p3),Math.max(p4,p5));
        int fromHeadMaxSum = Math.max(
                Math.max(p3,p4),p5
        ) ;
        return new Info(allTreeMaxSum,fromHeadMaxSum);
    }


    /*
    * 第三题，任何节点，上下任意
    * 1）X无关的时候， 1， 左树上的整体最大路径和 2， 右树上的整体最大路径和
    * 2) X有关的时候 3， x自己 4， x往左走 5，x往右走 6, 既往左，又往右（多加了这个分类）
    * */

    /*      int p6 = Integer.MIN_VALUE;
		    if (leftInfo != null && rightInfo != null) {
            p6 = x.value + leftInfo.fromHeadMaxSum + rightInfo.fromHeadMaxSum;
    }*/


}
