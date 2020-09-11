package MyDataTest_10;

/*
* 转换器接口
* 负责将其他数据结构表达的图转化为自己专属的图结构
* */
public class GraphGenerator {


    /*          matrix
    *       [from to weight]
    * edge1   ...  ...  ...
    * edge2   ...  ...  ...
    * edge3   ...  ...  ...
    * */
    /*
    * 结构化为专属图结构
    * */
    public static Graph crateGraph(Integer[][] matrix){

        Graph graph = new Graph() ;


        for (int i = 0; i < matrix.length; i++) {
            /*
            * 信息提取
            * */
            Integer from = matrix[i][0];
            Integer to = matrix[i][1];
            Integer weight = matrix[i][2];
            /*
            * 点入图
            * */
            if (!graph.nodes.containsKey(from)){
                graph.nodes.put(from,new Graph_Node(from));
            }
            if (!graph.nodes.containsKey(to)){
                graph.nodes.put(to,new Graph_Node(to));
            }
            /*
            * new 边对象
            * */
            Graph_Edge edge = null ;
            Graph_Node fromNode = graph.nodes.get(from);
            Graph_Node toNode = graph.nodes.get(to);
            edge = new Graph_Edge(weight,fromNode,toNode) ;

            /*
            * 点处理
            * */
            fromNode.nexts.add(toNode);
            fromNode.out++ ;
            toNode.in ++ ;
            fromNode.edges.add(edge) ;
            /*
            * 边入图
            * */
            graph.edges.add(edge) ;

        }

        return graph ;
    }


    public static void main(String[] args) {
        //此边，从1来，到2去，权重是3
        Integer[][] matrix = new Integer[1][3];
        matrix[0][0] = 1 ;
        matrix[0][1] = 2 ;
        matrix[0][2] = 3 ;
        Graph graph = crateGraph(matrix);
        System.out.println(graph.nodes.size()); //is 2?
        System.out.println(graph.edges.size()); //is 1?

    }
}
