package MyDataTest_11;
/*
* 暴力递归就是尝试
* 1，把问题转化为规模缩小了的同类问题的子问题
* 2，有明确的不需要继续进行递归的条件(base case)
* 3，有当得到了子问题的结果之后的决策过程
* 4，不记录每一个子问题的解

* */

/*
* n层汉诺塔问题
* 打印n层汉诺塔从最左边移动到最右边的全部过程
* 关键在于理解这个问题是怎么拆分成子问题的
* */
public class Hanoi {

    public static void hanoi(int N){
        //将n层圆盘，从最左移动到最右
        process(N,"left","right","mid");
    }
    /*
     * N N层汉诺塔问题
     * 圆盘从 from ——-> to
     * other : 另一个圆盘
     * */
    public static void process(int N, String from, String to,String other){

        //base case
        //最上面一层的移动
        if (N == 1 ){
            System.out.println("Move 1 from " + from + " to " + to);
        }else {
            process(N-1,from,other,to);
            /*
            * 理解为什么sout在这个位置
            * 因为从 N == 1往下开始打印 递归到N==1往回正是我们直观的上到下移动过程
            * */
            System.out.println("Move " + N + " from " + from + " to " + to);
            process(N-1,from,to,other);
        }
    }
    public static void main(String[] args) {
        hanoi(3);
    }
}
