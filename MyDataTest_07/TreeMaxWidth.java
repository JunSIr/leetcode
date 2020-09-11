package MyDataTest_07;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/*
* 给一个头结点，确定确定二叉树的最大宽度(二叉树最宽的层有多少个节点)
* 核心:建立发现层结束机制
* * */
public class TreeMaxWidth {

    /*
    * 使用哈希表+层序遍历
    * 节点数最后结算，即当前层完毕，结算上一层的结点数
    * */
    public static int maxWidthUseHash(Node head){
        if (head==null) return 0 ;

        //key:节点;value:该节点所在层数
        HashMap<Node,Integer> map = new HashMap<>() ;
        map.put(head,1) ;

        int curLevel = 1 ; //正在统计的是哪一层
        int curLevelNodes = 0 ; //正在统计层的结点数
        int max = 0 ; // flag

        //套接层序遍历
        Queue<Node> queue = new LinkedList<>() ;
        queue.add(head) ;
        while (!queue.isEmpty()){
            Node cur = queue.poll();
            //拿到当前节点所在层
            int curNodeLevel = map.get(cur) ;
            //左右入队，并将左右节点的层数存入哈希表
            if (cur.left!=null){
                queue.add(cur.left);
                map.put(cur.left,curLevel+1) ;   //更新map当前节点及其所在层
            }
            if (cur.right!=null){
                queue.add(cur.right) ;
                map.put(cur.right,curLevel+1) ;  //更新map当前节点及其所在层
            }
            //当前层未结束
            if (curLevel == curNodeLevel){
                curLevelNodes++ ; //节点数+1
            }else {  //已经到下一层
                //与上一层做结算
                max = Math.max(max,curLevelNodes) ;
                curLevel++ ; //更新-当前层+1
                curLevelNodes = 1 ;//表示换层的第一个节点
            }
        }
        //别忘了最后一层的结算
        max = Math.max(max,curLevelNodes) ;
        return max;
    }

    /*
     * 不使用哈希表+层序遍历
     * 节点数完事就结算
     * */
    public static int maxWidthNoUseHash(Node head){
        if (head==null) return 0 ;

        Node curEnd = head ; //当前层最右点
        Node nextEnd = null ; //下一层最右点

        //int curLevel = 1 ;  不用再记录层数
        int curLevelNodes = 0 ; //正在统计层的结点数
        int max = 0 ; // flag

        //套接层序遍历
        Queue<Node> queue = new LinkedList<>() ;
        queue.add(head) ;
        while (!queue.isEmpty()){

            Node cur = queue.poll();

            //找到下一层最右节点，方便直接跳过去
            if (cur.left!=null){
                queue.add(cur.left) ;
                nextEnd = cur.left ;
            }
            if(cur.right!=null){
                queue.add(cur.right) ;
                nextEnd = cur.right ;
            }
            curLevelNodes++ ;
            //当前是最右了，马上进行结算
            if (curEnd==cur){
                max = Math.max(max,curLevelNodes) ;
                curLevelNodes = 0 ; //结算完，马上换层清空
                curEnd = nextEnd ; //更新
            }
        }
        /*  一层结束结算一层，最后一层结束就结算掉了
        max = Math.max(max,curLevelNodes) ;*/
        return max;
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(3);
        head.left.left = new Node(4);
        head.left.right = new Node(5);
        head.right.left = new Node(6);
        head.right.right = new Node(7);
        int i = maxWidthNoUseHash(head);
        System.out.println(i);
    }
}
