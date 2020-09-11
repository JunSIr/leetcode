package MyDataTest_05;
/*
* 前缀树
* 1）单个字符串中，字符从前到后的加到一棵多叉树上
* 2）字符放在路上，节点上有专属的数据项（常见的是pass和end值）
* 3）所有样本都这样添加，如果没有路就新建，如有路就复用
* 4）沿途节点的pass值增加1，每个字符串结束时来到的节点end值增加1
* */

import java.util.HashMap;

/*定义前缀树节点数据结构*/
class Node_1{

    Node_1 [] nexts ; //节点后续节点
    int pass ; //pass指针
    int end ; //end指针

    public Node_1() {
        this.pass = 0;
        this.end = 0;
        //26代表这个节点后续最多有26条路，分别对应26个小写字母
        nexts = new Node_1[26] ;
    }
}

/*定义前缀树结构及其操作方法*/
class TrieTree_1{
    private Node_1 root ;  //树的根节点

    public TrieTree_1() {
        root = new Node_1();
    }

    //前缀树字符插入 O(n)
    public void insert(String word){
        //判空
        if (word==null) throw new RuntimeException("args is null");
        //字符串转字符数组
        char[] chars = word.toCharArray();
        //node指针指向这棵树根节点
        Node_1 node = this.root ;
        node.pass++;  //头结点不加元素，只记录字符个数
        //后一个节点下标
        int index = 0 ;

        for (int i = 0; i < chars.length; i++) {
            //确定下标
            index =  chars[i] - 'a' ;
            if (node.nexts[index]==null){
                //如果还未有，创建一个新的_node
                node.nexts[index] = new Node_1() ;
            }
            //node指针指到下一个节点
            node = node.nexts[index] ;
            //pass+1
            node.pass++ ;
        }
        node.end++ ;
    }

    //前缀树字符删除  O(n)
    public void delete(String word){
        //如果存在，则代码Insert代码可复用
        if (search(word)!=0) {
            //字符串转字符数组
            char[] chars = word.toCharArray();
            //node指针指向这棵树根节点
            Node_1 node = this.root ;
            node.pass--;  //头结点不加元素，只记录字符个数
            //后一个节点下标
            int index = 0 ;

            for (int i = 0; i < chars.length; i++) {
                //确定下标
                index =  chars[i] - 'a' ;
                //从树的结构看，如果最终到达的是叶子节点，置空返回即可，不懂画图
                if (--node.nexts[index].pass==0){
                    node.nexts[index] = null ;
                    return;
                }
                node = node.nexts[index] ;
                //node.pass-- ;在if里已经优化了，可以省略
            }
            node.end-- ;
        }
    }

    //前缀树字符搜索(word这个单词之前加入过几次) O(n)
    public int search(String word){
        //判空
        if (word==null) throw new RuntimeException("args is null");
        //字符串转字符数组
        char[] chars = word.toCharArray();
        //node指针指向这棵树根节点
        Node_1 node = this.root ;
        //后一个节点下标
        int index = 0 ;

        for (int i = 0; i < chars.length; i++) {
            //确定下标
            index =  chars[i] - 'a' ;
            if (node.nexts[index]==null) return 0 ;
            //node指针指到下一个节点
            node = node.nexts[index] ;
        }
        return node.end ;
    }

    // 所有加入的字符串中，有几个是以pre这个字符串作为前缀的
    //此代码与serach代码基本一致，只不过search返回的是node.end，此代码返回node.pass
    public int prefixNumbers(String word){
        //判空
        if (word==null) throw new RuntimeException("args is null");
        //字符串转字符数组
        char[] chars = word.toCharArray();
        //node指针指向这棵树根节点
        Node_1 node = this.root ;
        //后一个节点下标
        int index = 0 ;

        for (int i = 0; i < chars.length; i++) {
            //确定下标
            index =  chars[i] - 'a' ;
            if (node.nexts[index]==null) return 0 ;
            //node指针指到下一个节点
            node = node.nexts[index] ;
        }
        return node.pass ;

    }
}

/*定义更通用的前缀树节点数据结构*/
class Node_2{

//    Node_1 [] nexts ; //节点后续节点

    //integer代表asc码
    HashMap<Integer,Node_2> nexts ;
    int pass ; //pass指针
    int end ; //end指针

    public Node_2() {
        this.pass = 0;
        this.end = 0;
        //26代表这个节点后续最多有26条路，分别对应26个小写字母
        nexts = new HashMap<>() ;
    }
}

/*定义更通用的前缀树结构及其操作方法*/
class TrieTree_2{
    private Node_2 root ;  //树的根节点

    public TrieTree_2() {
        root = new Node_2();
    }

    //前缀树字符插入 O(n)
    public void insert(String word){
        //判空
        if (word==null) throw new RuntimeException("args is null");
        //字符串转字符数组
        char[] chars = word.toCharArray();
        //node指针指向这棵树根节点
        Node_2 node = this.root ;
        node.pass++;  //头结点不加元素，只记录字符个数
        //后一个节点下标
        int index = 0 ;

        for (int i = 0; i < chars.length; i++) {
            //确定下标,asc码
            index =  (int)chars[i]  ;

            if (!node.nexts.containsKey(index)){
                //如果还未有，创建一个新的_node
                node.nexts.put(index,new Node_2())  ;
            }
            //node指针指到下一个节点
            node = node.nexts.get(index) ;
            //pass+1
            node.pass++ ;
        }
        node.end++ ;
    }

    //前缀树字符删除  O(n)
    public void delete(String word){
        //如果存在，则代码Insert代码可复用
        if (search(word)!=0) {
            //字符串转字符数组
            char[] chars = word.toCharArray();
            //node指针指向这棵树根节点
            Node_2 node = this.root ;
            node.pass--;  //头结点不加元素，只记录字符个数
            //后一个节点下标
            int index = 0 ;

            for (int i = 0; i < chars.length; i++) {
                //确定下标
                index = (int) chars[i];
                //从树的结构看，如果最终到达的是叶子节点，置空返回即可，不懂画图
                if (--node.nexts.get(index).pass==0){
                    node.nexts.remove(index) ;
                    return;
                }
                node = node.nexts.get(index) ;
                //node.pass-- ;在if里已经优化了，可以省略
            }
            node.end-- ;
        }
    }

    //前缀树字符搜索(word这个单词之前加入过几次) O(n)
    public int search(String word){
        //判空
        if (word==null) throw new RuntimeException("args is null");
        //字符串转字符数组
        char[] chars = word.toCharArray();
        //node指针指向这棵树根节点
        Node_2 node = this.root ;
        //后一个节点下标
        int index = 0 ;

        for (int i = 0; i < chars.length; i++) {
            //确定下标
            index =  chars[i]  ;
            if (node.nexts.get(index)==null) return 0 ;
            //node指针指到下一个节点
            node = node.nexts.get(index) ;
        }
        return node.end ;
    }

    // 所有加入的字符串中，有几个是以pre这个字符串作为前缀的
    //此代码与serach代码基本一致，只不过search返回的是node.end，此代码返回node.pass
    public int prefixNumbers(String word){
        //判空
        if (word==null) throw new RuntimeException("args is null");
        //字符串转字符数组
        char[] chars = word.toCharArray();
        //node指针指向这棵树根节点
        Node_2 node = this.root ;
        //后一个节点下标
        int index = 0 ;

        for (int i = 0; i < chars.length; i++) {
            //确定下标
            index =  chars[i]  ;
            if (node.nexts.get(index)==null) return 0 ;
            //node指针指到下一个节点
            node = node.nexts.get(index) ;
        }
        return node.pass ;

    }
}

public class TrieTree {

    public static void main(String[] args) {
        TrieTree_2 tt = new TrieTree_2() ;
        tt.insert("abc");
        tt.insert("abd");
        tt.insert("abc");
        System.out.println(tt.search("abc"));
        int a = tt.prefixNumbers("ab");
        System.out.println(a);
        tt.delete("abd");
        int b = tt.prefixNumbers("ab");
        System.out.println(b);
    }

}