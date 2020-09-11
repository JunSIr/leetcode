package MyDataTest_10;

public class Graph_Edge {
    public int weight ; //权重
    public Graph_Node from ; //来的点
    public Graph_Node to ; //去的点

    public Graph_Edge(int weight, Graph_Node from, Graph_Node to) {
        this.weight = weight;
        this.from = from;
        this.to = to;
    }
}
