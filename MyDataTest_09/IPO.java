package MyDataTest_09;
/*
* 输入: 正数数组costs、正数数组profits、正数K、正数M
costs[i]表示i号项目的花费
profits[i]表示i号项目在扣除花费之后还能挣到的钱(利润)
K表示你只能串行的最多做k个项目
M表示你初始的资金
说明: 每做完一个项目，马上获得的收益，可以支持你去做下一个项目。不能并行的做项目。
输出：你最后获得的最大钱数。
* */


import java.util.Comparator;
import java.util.PriorityQueue;

public class IPO {
    /*
    * Project Info
    * */
    public static class Project{
        public int cost ; //需要的资金
        public int profix; //利润

        public Project(int cost, int profix) {
            this.cost = cost;
            this.profix = profix;
        }
    }

    /*
    * 比较器1，用于小根堆，以初始资金排名 小->大
    * */
    public static class myComparator_1 implements Comparator<Project>{
        @Override
        public int compare(Project o1, Project o2) {
            return o1.cost - o2.cost; //从小到大排
        }
    }

    /*
     * 比较器2，用于大根堆，以利润排名 大->小
     * */
    public static class myComparator_2 implements Comparator<Project>{
        @Override
        public int compare(Project o1, Project o2) {
            return o2.profix - o1.profix; //从小到大排
        }
    }

    /*
    * k 能接的项目
    * w 初始资金
    * profix 利润
    * cost 投入资金
    * */
    public static int MaxProfix(int k ,int w ,int[] profix ,int[] cost){
        /*
        * 建堆
        * */
        PriorityQueue<Project> costMinHeap = new PriorityQueue<>(new myComparator_1()) ;
        PriorityQueue<Project> profixMaxHeap = new PriorityQueue<>(new myComparator_2()) ;

        /*
        * 按花费从小到大入 小根堆
        * */
        for (int i = 0; i < cost.length; i++) {
            costMinHeap.add(new Project(cost[i],profix[i])) ;
        }

        for (int i = 0; i < k; i++) {
            /*
            * 如果启动资金够得着小根堆头节点，拉出来搞
            * */
            if (!costMinHeap.isEmpty() && costMinHeap.poll().cost <= w){
                //加入大根堆
                profixMaxHeap.add(costMinHeap.poll()) ;
            }

            //如果买不起了，直接返回
            if (profixMaxHeap.isEmpty())
                return w ;

            w += profixMaxHeap.poll().profix;
        }
        return w ;
    }

}
