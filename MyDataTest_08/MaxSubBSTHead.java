package MyDataTest_08;
/*
 * 给定一棵二叉树的头节点head，
 * 返回这颗二叉树中最大的二叉搜索子树的头节点
 * 二叉搜索树定义:左子树都比根小，右子树都比根大
 * */
public class MaxSubBSTHead {

    public static class Info{
        public Node maxSubBSTHead ;//最大子二搜头
        public int max  ; //最大值
        public int min ; //最小值
        public int maxSubBSTSize ; //用于决策最大子搜头

        public Info(Node maxSubBSTHead, int max, int min ,int MaxSubBSTSize) {
            this.maxSubBSTHead = maxSubBSTHead;
            this.max = max;
            this.min = min;
            this.maxSubBSTSize = MaxSubBSTSize ;
        }
    }

    public static Node getMaxSubBSTHead(Node head){

        if (head==null) return null ;
        return process(head).maxSubBSTHead ;
    }

    public static Info process(Node head){

        if (head==null) return null ; //此处如果返回空，在处理的时候就需要判空，之前做的都是返回Info

        /*
         * 左右子树信息
         * */
        Info leftInfo = process(head.left);
        Info rightInfo = process(head.right) ;

        /*
         * 决策过程
         * */
        int maxSubBSTSize = 0 ;
        Node maxSubBSTHead = null ;
        int max = head.value  ; //最大值
        int min = head.value; //最小值

                /*
                * 暂时决策
                * */
        if (leftInfo!=null){
            max = Math.max(max,leftInfo.max) ;
            min = Math.min(min,leftInfo.min) ;
            maxSubBSTHead = leftInfo.maxSubBSTHead ;
            maxSubBSTSize = leftInfo.maxSubBSTSize ;
        }
        if (rightInfo!=null ){
            max = Math.max(max,rightInfo.max) ;
            min = Math.min(min,rightInfo.min) ;
            if (rightInfo.maxSubBSTSize > maxSubBSTSize){
                maxSubBSTSize = rightInfo.maxSubBSTSize ;
                maxSubBSTHead = rightInfo.maxSubBSTHead ;
            }
        }

        /*
         * 核心决策
         * 判断是否二叉搜索树，是的话将本搜索树信息向上返回
         * */
        if ((leftInfo == null || (leftInfo.maxSubBSTHead == head.left && leftInfo.max < head.value))
                && (rightInfo == null || (rightInfo.maxSubBSTHead == head.right && rightInfo.min > head.value))) {
            maxSubBSTHead = head ;
            maxSubBSTSize =
                    (leftInfo ==null ? 0 : leftInfo.maxSubBSTSize)
                            + (rightInfo == null ? 0 : rightInfo.maxSubBSTSize)
                            + 1 ;
        }
        return new Info(maxSubBSTHead,max,min,maxSubBSTSize) ;
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(3);
        head.right.left = new Node(2);
        head.right.right = new Node(4) ;
        head.left.left = new Node(4);
        head.left.left.left = new Node(4);

        Node maxSubBSTHead = getMaxSubBSTHead(head);
        System.out.println(maxSubBSTHead.value);
    }
}
