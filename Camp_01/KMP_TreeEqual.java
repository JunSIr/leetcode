package Camp_01;
/*
* 判断一个数是否是另一个数的任意子树
* 二叉树递归法 时间O(n*m)
* KMP O(n)
* */


import java.util.ArrayList;

public class KMP_TreeEqual {
    //标准二叉树结构
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int v) {
            value = v;
        }
    }

    /*
     * 二叉树递归法
     * big做头节点的树，其中是否有某棵子树的结构是和small为头的树完全一样的
     * */
    public static boolean containsTree_1(Node big ,Node small){
        /*
        * base case
        * */
        if (small == null){
            return true ;
        }
        if (big == null){
            return false ;
        }
        if (isSameValueStructure(big,small)){
            return  true ;
        }

        return  containsTree_1(big.left,small) || containsTree_1(big.right,small) ;
    }

    // 以head1为头的树，是否在结构对应上，完全和head2一样
    public static boolean isSameValueStructure(Node head1, Node head2) {
        /*
        * base case
        * */
        if (head1 == null && head2 != null){
            return false ;
        }
        if (head1 != null && head2 == null){
            return false ;
        }
        if (head1 == null && head2 == null){
            return true ;
        }
        if (head1.value != head2.value){
            return false ;
        }

        return  isSameValueStructure(head1.left,head2.left) &&
                isSameValueStructure(head1.right,head2.right);

    }


    /*
    *  KMP做法
    * */
    public static boolean containsTree_2(Node big, Node small){

        /*
        * 先把两个树序列化 再转化为String[]
        * */
        ArrayList<String> b = preSerial(big);
        ArrayList<String> s = preSerial(small);

        String[] str = new String[b.size()] ;
        String[] match = new String[s.size()] ;

        for (int i = 0; i < b.size(); i++) {
            str[i] = b.get(i) ;
        }
        for (int i = 0; i < s.size(); i++) {
            match[i] = s.get(i) ;
        }

        return getIndexOf(str,match) != -1;

    }

    /*
    * 先序 序列化
    * */
    public static ArrayList<String> preSerial(Node head){

        ArrayList<String > ans = new ArrayList<>() ;
        pres(head, ans);
        return  ans  ;
    }

    public static void pres(Node head, ArrayList<String> ans){
        if (head == null){
            ans.add(null) ;
        }else {
            ans.add(String.valueOf(head.value));
            pres(head.left, ans);
            pres(head.right, ans);
        }
    }

    public static int getIndexOf(String[] str1, String[] str2){

        if (str1 == null || str2 == null || str1.length < 1 || str1.length < str2.length) {
            return -1;
        }


        int x = 0 ;  //str当前比对位置
        int y = 0 ;  //match当前比对位置
        //next数组
        int[] next = getNextArray(str2);

        while (x < str1.length && y < str2.length){
            //如果能匹配得上
            if (isEqual(str1[x],str2[y])){
                x++ ;
                y++ ;
            }else if (next[y] == -1 ){ //匹配不上，也没有最长公共前缀
                x++  ; //直接跳到下一个位置
            }else {  //匹配不上，但有前缀
                y  = next[y] ;  //跳到第一个前缀末尾的后一个位置
            }
        }

        return y == str2.length? x - y : -1  ;
    }



    public static int[] getNextArray(String[] match){

        if (match.length == 1 ){
            return new int[] {-1} ;
        }

        int[] next = new int[match.length] ;
        //人为规定
        next[0] = -1 ;
        next[1] = 0 ;
        int i = 2 ; //从第三个位置开始 生成next

        // cn位置的字符，是当前和i-1位置比较的字符
        int cn = 0;
        /*
         * 得知道 next[i]的信息是match[0-i-1]前后公共前缀信息
         * */
        while (i < next.length){
            //如果
            if (isEqual(match[i-1],match[cn])){
                next[i++] = ++cn ;
            }else if (cn > 0){
                cn = next[cn] ; //cn跳到上一个前缀的下一个位置
            }else {
                next[i++] = 0  ;
            }
            //打完收工
        }

        return next ;
    }

    public static boolean isEqual(String a, String b) {
        if (a == null && b == null) {
            return true;
        } else {
            if (a == null || b == null) {
                return false;
            } else {
                return a.equals(b);
            }
        }
    }


    // for test
    public static Node generateRandomBST(int maxLevel, int maxValue) {
        return generate(1, maxLevel, maxValue);
    }

    // for test
    public static Node generate(int level, int maxLevel, int maxValue) {
        if (level > maxLevel || Math.random() < 0.5) {
            return null;
        }
        Node head = new Node((int) (Math.random() * maxValue));
        head.left = generate(level + 1, maxLevel, maxValue);
        head.right = generate(level + 1, maxLevel, maxValue);
        return head;
    }

    public static void main(String[] args) {
        int bigTreeLevel = 7;
        int smallTreeLevel = 4;
        int nodeMaxValue = 5;
        int testTimes = 100000;
        System.out.println("test begin");
        for (int i = 0; i < testTimes; i++) {
            Node big = generateRandomBST(bigTreeLevel, nodeMaxValue);
            Node small = generateRandomBST(smallTreeLevel, nodeMaxValue);
            boolean ans1 = containsTree_1(big, small);
            boolean ans2 = containsTree_2(big, small);
            if (ans1 != ans2) {
                System.out.println("Oops!");
            }
        }
        System.out.println("test finish!");

    }
}
