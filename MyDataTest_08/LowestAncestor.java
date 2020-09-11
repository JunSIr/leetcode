package MyDataTest_08;


/*
* 给定一棵二叉树的头节点head，和另外两个节点a和b。
* 返回a和b的最低公共祖先
* 此题请想想  你所作的分类  是否包含所有可能性
* */
public class LowestAncestor {

    public static class Info{
        public boolean findO1 ; //是否找到O1
        public boolean findO2 ; //是否找到O2
        public Node ans ; // 最小公共祖先

        public Info(boolean findO1, boolean findO2, Node ans) {
            this.findO1 = findO1;
            this.findO2 = findO2;
            this.ans = ans;
        }
    }

    public static Node getAncestor(Node head,Node o1,Node o2){
        if (head==null) return null ;
        return process(head, o1, o2).ans ;
    }

    public static Info process(Node head,Node o1,Node o2){

        if (head==null) return new Info(false,false,null) ;

        Info leftInfo = process(head.left,o1,o2);
        Info rightInfo = process(head.right, o1, o2);


        boolean findO1 = o1 == head || leftInfo.findO1 || rightInfo.findO1 ;
        boolean findO2 = o2 == head || leftInfo.findO2 || rightInfo.findO2;


        Node ans = null ;
        /*
        *  如果左子树或右子树已产生ans 以子树的ans为准
        * */
        if (leftInfo.ans!=null) {
            ans = leftInfo.ans ;
        }
        if (rightInfo.ans!=null){
            ans = rightInfo.ans ;
        }
        //左右子树都无解
        if (ans==null){
            if (findO1 && findO2)
                ans = head ;
        }
        return new Info(findO1,findO2,ans) ;
    }

    public static void main(String[] args) {
        Node head = new Node(2);
        head.left = new Node(1);
        head.right = new Node(3);
        head.left.right = new Node(4) ;

        Node ancestor = getAncestor(head, head.left.right, head.right);
        System.out.println(ancestor.value);
    }
}
