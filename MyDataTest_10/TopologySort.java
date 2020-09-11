package MyDataTest_10;

import java.util.*;

/*
*  拓扑排序
* 1）在图中找到所有入度为0的点输出
* 2）把所有入度为0的点在图中删掉，继续找入度为0的点输出，周而复始
* 3）图的所有点都被删除后，依次输出的顺序就是拓扑排序
要求：有向图且其中没有环(如果有环，就是经典的循环依赖问题)
应用：事件安排、编译顺序
* */
public class TopologySort {
    /*
    * graph : 有向无环图
    * */
    public static List<Graph_Node> topologySort(Graph graph){

        //节点剩余入度表
        HashMap<Graph_Node,Integer> inMap = new HashMap<>() ;
        //零入度表
        Queue<Graph_Node> zeroMap = new LinkedList<>();

        /*
        * 图节点遍历，做初始化工作
        * graph.nodes.values():所有节点拉出来做集合
        * */
        for (Graph_Node node: graph.nodes.values()) {
            inMap.put(node,node.in);
            if (node.in == 0 )
                zeroMap.add(node) ;
        }

        /*
        *
        * */
        List<Graph_Node> results = new ArrayList<>();  //返回结果

        while (!zeroMap.isEmpty()){
            Graph_Node poll = zeroMap.poll();
            results.add(poll);
            /*
            * 消除影响
            * */
            for (Graph_Node next : poll.nexts) {
                inMap.put(next,inMap.get(next)-1) ;
                if (inMap.get(next) == 0 )
                    zeroMap.add(next);
            }

        }

        return results ;
    }
}
