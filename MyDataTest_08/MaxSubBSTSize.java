package MyDataTest_08;
/*
* 给定一棵二叉树的头节点head，
* 返回这颗二叉树中最大的二叉搜索子树的大小(节点个数)

 * 二叉搜索树定义:左子树都比根小，右子树都比根大
* */
public class MaxSubBSTSize {

    public static class Info{
        public boolean isAllBST ;//是否二叉搜索树
        public int maxSubBSTSize ; //最大二叉搜索子树大小(节点数) target
        public int max  ; //最大值
        public int min ; //最小值

        public Info(boolean isAllBST, int maxSubBSTSize, int max, int min) {
            this.isAllBST = isAllBST;
            this.maxSubBSTSize = maxSubBSTSize;
            this.max = max;
            this.min = min;
        }
    }

    public static int getMaxSubBSTSize(Node head){
        if (head==null) return 0 ;
        return process(head).maxSubBSTSize ;
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
        boolean isAllBST = false ;//是否二叉搜索树



            /*
            * 以本节点为头的子树最大值与最小值决策
            * 暂时决策最大搜索子树Size，在最后一步完善决策
            * */
        int max = head.value  ; //最大值
        int min = head.value; //最小值
        int maxSubBSTSize = 0; //最大二叉搜索子树节点数目
        if (leftInfo!=null){
            max = Math.max(max,leftInfo.max) ;
            min = Math.min(min,leftInfo.min) ;
            maxSubBSTSize = Math.max(leftInfo.maxSubBSTSize,maxSubBSTSize); //先决策出左右子树的最大二搜Size
        }
        if (rightInfo!=null){
            max = Math.max(max,rightInfo.max) ;
            min = Math.min(min,rightInfo.min) ;
            maxSubBSTSize = Math.max(rightInfo.maxSubBSTSize,maxSubBSTSize) ; //先决策出左右子树的最大二搜Size
        }

        /*
         * 核心决策
         * 判断是否二叉搜索树，是的话将本搜索树信息向上返回
         * */
        if ((leftInfo == null || (leftInfo.isAllBST && leftInfo.max < head.value))
                && (rightInfo == null || (rightInfo.isAllBST && rightInfo.min > head.value))) {
            isAllBST = true ;
            maxSubBSTSize =
                    (leftInfo ==null ? 0 : leftInfo.maxSubBSTSize)
                            + (rightInfo == null ? 0 : rightInfo.maxSubBSTSize)
                            + 1 ;
        }

        return new Info(isAllBST,maxSubBSTSize,max,min) ;
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(3);
        head.right.left = new Node(2);
        head.right.right = new Node(4) ;
        head.left.left = new Node(4);
        head.left.left.left = new Node(4);


//
//
//        head.left.right = new Node(5);
//        head.right.left = new Node(6);
//        head.right.right = new Node(7);

        int max = getMaxSubBSTSize(head);
        System.out.println(max);
    }
}
