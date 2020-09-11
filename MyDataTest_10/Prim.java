package MyDataTest_10;


import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;
/*
* 最小生成树算法之Prim
1）可以从任意节点出发来寻找最小生成树
2）某个点加入到被选取的点中后，解锁这个点出发的所有新的边
3）在所有解锁的边中选最小的边，然后看看这个边会不会形成环
4）如果会，不要当前边，继续考察剩下解锁的边中最小的边，重复3）
5）如果不会，要当前边，将该边的指向点加入到被选取的点中，重复2）
6）当所有点都被选取，最小生成树就得到了
 */
public class Prim {

    public static class EdgeComparator implements Comparator<Graph_Edge> {
        @Override
        public int compare(Graph_Edge o1, Graph_Edge o2) {
            return o1.weight - o2.weight;
        }
    }

    public static Set<Graph_Edge> primMST(Graph graph){
        /*
        * 所需容器
        * */
        PriorityQueue<Graph_Edge> heap = new PriorityQueue<>(new EdgeComparator());  //小根堆
        HashSet<Graph_Edge> result = new HashSet<>();  //要选的边
        HashSet<Graph_Node> set = new HashSet<>();  //防止重复

        for (Graph_Node node : graph.nodes.values()) {  //防止森林
            set.add(node);
            if (!set.contains(node)){
                /*
                * 此点相关联的边进小根堆
                * */
                for (Graph_Edge edge :
                        node.edges) {
                    heap.add(edge);
                }
                /*
                * 开始跑小根堆
                * */
                while (!heap.isEmpty()){
                    Graph_Edge poll = heap.poll();
                    //最小边的下一个点
                    Graph_Node toNode = poll.to;
                    if (!set.contains(toNode)){
                        set.add(toNode);
                        result.add(poll);
                        for(Graph_Edge edge : toNode.edges){
                            heap.add(edge);
                        }

                    }
                }

            }
        }
        return result ;
    }

}
