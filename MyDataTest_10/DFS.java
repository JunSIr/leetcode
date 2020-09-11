package MyDataTest_10;

import java.util.HashSet;
import java.util.Queue;
import java.util.Stack;

/*
* 深度优先遍历
1，利用栈实现
2，从源节点开始把节点按照深度放入栈，然后弹出
3，每弹出一个点，把该节点下一个没有进过栈的邻接点放入栈
4，直到栈变空

* */
public class DFS {

    public static void dfs(Graph_Node node){
        if (node==null)
            return;
        /*
        * 本栈自底向上，表示当前序列，方便回退
        * */
        Stack<Graph_Node> stack = new Stack<>() ;
        //还是HashSet
        HashSet<Graph_Node> set = new HashSet<>();

        stack.add(node);
        System.out.println(node.value); //入栈就打印
        set.add(node);

        while (!stack.isEmpty()){

            Graph_Node pop = stack.pop();

            for (Graph_Node next: pop.nexts) {
                if (!set.contains(next)){
                    stack.push(next);
                    System.out.println(next.value);
                    stack.push(pop) ;
                    set.add(next);

                    break; //每次只搞一个相邻
                }
            }

        }
    }
}
