package MyDataTest_10;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

/*
* 宽度优先遍历
1，利用队列实现
2，从源节点开始依次按照宽度进队列，然后弹出
3，每弹出一个点，把该节点所有没有进过队列的邻接点放入队列
4，直到队列变空

* */
public class BFS {
    /*
    * 从某节点开始，进行深搜
    * */
    public static void bfs(Graph_Node node){
        if (node==null)
            return;

        //与二叉树的层序一样，需要队列
        Queue<Graph_Node> queue =new LinkedList<>() ;
        //不要重复
        HashSet<Graph_Node> set = new HashSet<>();

        queue.add(node);
        set.add(node) ;

        while (!queue.isEmpty()){

            Graph_Node poll = queue.poll();
            System.out.println(poll.value); //以业务为准

            for (Graph_Node next: poll.nexts) {

                if(!set.contains(next)){
                    queue.add(next);
                    set.add(next) ;
                }
            }
        }

    }
}
