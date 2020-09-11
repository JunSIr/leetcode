package MyDataTest_08;

public class IsBinarySearchTree {

    public static class Info{
        public boolean isBST ;
        public int max ;
        public int min ;

        public Info(boolean isBST, int max, int min) {
            this.isBST = isBST;
            this.max = max;
            this.min = min;
        }
    }

    public static boolean isBST(Node head){
        if (head==null) return true ;
        return  process(head).isBST ;
    }

    public static Info process(Node head){
        //base case
        if (head==null) return null ;

        Info leftInfo = process(head.left);
        Info rightInfo = process(head.right);

        /*
        * 开始决策
        * */
        int max = head.value ;
        int min = head.value ;

        if (leftInfo!=null){
            max = Math.max(max,leftInfo.max) ;
            min = Math.min(min,leftInfo.min);
        }
        if (rightInfo!=null){
            max = Math.max(max,rightInfo.max) ;
            min = Math.min(min,rightInfo.min);
        }
        boolean isBST = false;

        if ((leftInfo == null || leftInfo.max < head.value)
                &&  (rightInfo == null || rightInfo.min > head.value ))
        {
            isBST = true ;
        }

        return new Info(isBST,max,min) ;
    }

    public static void main(String[] args) {
        Node head = new Node(2);
        head.left = new Node(1);
        head.right = new Node(3);



//        head.left.right = new Node(5);
//        head.right.left = new Node(6);
//        head.right.right = new Node(7);

        boolean is = isBST(head);
        System.out.println(is);
    }
}
