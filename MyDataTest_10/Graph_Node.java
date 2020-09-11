package MyDataTest_10;

import java.util.ArrayList;

/*
* 图的 点封装
* */
public class Graph_Node {
    // 点的值
    public int value ;
    //入度
    public int in ;
    //出度
    public int out ;
    //由此点出发，相关联的下一个点
    public ArrayList<Graph_Node> nexts ;
    //相关(可以是出去)的边
    public ArrayList<Graph_Edge>  edges ;
    /*
    * 构造器
    * */
    public Graph_Node(int value) {
        this.value = value;
        this.in = 0;
        this.out = 0;
        this.nexts = new ArrayList<>();
        this.edges = new ArrayList<>();
    }
}
