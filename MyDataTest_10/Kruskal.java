package MyDataTest_10;

import java.util.*;


/*
* 最小生成树算法之Kruskal
1）总是从权值最小的边开始考虑，依次考察权值依次变大的边
2）当前的边要么进入最小生成树的集合，要么丢弃
3）如果当前的边进入最小生成树的集合中不会形成环，就要当前边
4）如果当前的边进入最小生成树的集合中会形成环，就不要当前边
5）考察完所有边之后，最小生成树的集合也得到了
 * */

public class Kruskal {




    /*
    * 定制 并查集
    * */
    public static class UnionFind {
        // key 某一个节点， value key节点往上的节点
        private HashMap<Graph_Node, Graph_Node> fatherMap;
        // key 某一个集合的代表节点, value key所在集合的节点个数
        private HashMap<Graph_Node, Integer> sizeMap;

        public UnionFind() {
            fatherMap = new HashMap<Graph_Node, Graph_Node>();
            sizeMap = new HashMap<Graph_Node, Integer>();
        }

        public void makeSets(Collection<Graph_Node> nodes) {
            fatherMap.clear();
            sizeMap.clear();
            for (Graph_Node node : nodes) {
                fatherMap.put(node, node);
                sizeMap.put(node, 1);
            }
        }

        private Graph_Node findFather(Graph_Node n) {
            Stack<Graph_Node> path = new Stack<>();
            while(n != fatherMap.get(n)) {
                path.add(n);
                n = fatherMap.get(n);
            }
            while(!path.isEmpty()) {
                fatherMap.put(path.pop(), n);
            }
            return n;
        }

        public boolean isSameSet(Graph_Node a, Graph_Node b) {
            return findFather(a) == findFather(b);
        }

        public void union(Graph_Node a, Graph_Node b) {
            if (a == null || b == null) {
                return;
            }
            Graph_Node aDai = findFather(a);
            Graph_Node bDai = findFather(b);
            if (aDai != bDai) {
                int aSetSize = sizeMap.get(aDai);
                int bSetSize = sizeMap.get(bDai);
                if (aSetSize <= bSetSize) {
                    fatherMap.put(aDai, bDai);
                    sizeMap.put(bDai, aSetSize + bSetSize);
                    sizeMap.remove(aDai);
                } else {
                    fatherMap.put(bDai, aDai);
                    sizeMap.put(aDai, aSetSize + bSetSize);
                    sizeMap.remove(bDai);
                }
            }
        }
    }
    /*
    * 定制 比较器
    * */
    public static class Graph_EdgeComparator implements Comparator<Graph_Edge> {

        @Override
        public int compare(Graph_Edge o1, Graph_Edge o2) {
            return o1.weight - o2.weight;
        }

    }

    /*
    *
    * */
    public static Set<Graph_Edge> kruskalMST(Graph graph){

        //并查集
        UnionFind uf = new UnionFind();
        //所有点，各自成单独集合
        uf.makeSets(graph.nodes.values());
        /*
        * 边都进小根堆
        * */
        PriorityQueue<Graph_Edge> heap = new PriorityQueue<>();
        for (Graph_Edge edge : graph.edges) {
            heap.add(edge);
        }
        /*
        * 决策
        * */
        Set<Graph_Edge> results = new HashSet<>() ;
        while (!heap.isEmpty()){

            Graph_Edge poll = heap.poll();
            if (!uf.isSameSet(poll.from,poll.to)){
                results.add(poll) ;
                uf.union(poll.from,poll.to);
            }

        }
        return results ;
    }
}
