package MyDataTest_10;
/*  图的基本概念
* 1）由点的集合和边的集合构成
* 2）虽然存在有向图和无向图的概念，但实际上都可以用有向图来表达
* 3）边上可能带有权值
* */

/*
* 图结构的表达
* 1）邻接表法
* 2）邻接矩阵法
* 3）除此之外还有其他众多的方式
* */

/*
* 图的面试题如何搞定
* 图的算法都不算难，只不过coding的代价比较高
* 1）先用自己最熟练的方式，实现图结构的表达
* 2）在自己熟悉的结构上，实现所有常用的图算法作为模板
 * */

import java.util.HashMap;
import java.util.HashSet;

/*
* 自定义图数据结构，很直观，很灵性
* 一个图由点与边组成
* */
public class Graph {
    //点
    public HashMap<Integer,Graph_Node> nodes ;
    //边
    public HashSet<Graph_Edge> edges  ;

    public Graph() {
        nodes = new HashMap<>();
        edges = new HashSet<>();
    }
}
