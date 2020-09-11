package Camp_03;
/**
 * 给定一个矩阵 matrix 先从左上角开始 每一步都只能往右 或 下 走
 * 走到右下角 然后 从右下角出发 每一步只能往上或左走
 * 走到左上角
 * 任何一个位置的数字，只能获得一遍 返回最大路径和
 *
 * 本题 支持 优化成 记忆化搜索
 * */
public class CherryPickup {

    //暴力递归
    //思路 AB两个点 同步 右下走 其中一条代表返回  过程中重复的点只计算一次
    public static int comeGoMaxPathSum(int[][] matrix) {
        return process(matrix,0,0,0);
    }

    public static int process(int[][] matrix, int Ar, int Ac, int Br) {
        int N = matrix.length;
        int M = matrix[0].length;

        //base case
        if (Ar == N - 1 && Ac == M - 1){ //到达最右下角点 AB一定是同步到达的
            return matrix[Ar][Ac]; //得到这个点的点数 仅一份
        }

        // 要产生四种情况
        // A 下   B 右
        // A 下   B 下
        // A 右   B 右
        // A 右   B 下
        int Bc = Ar + Ac - Br; //根据AB同时 步数肯定一样 省略了Bc这个参数 方便转为动态规划

        int ADownBRight = -1;
        if (Ar + 1 < N && Bc + 1 < M) { //不越界
           ADownBRight = process(matrix,Ar + 1, Ac,Br); //隐藏Bc + 1
        }

        int ADownBDown = -1;
        if (Ar + 1 < N && Br + 1 < N) { //不越界
            ADownBDown = process(matrix,Ar + 1, Ac,Br + 1);
        }

        int ARightBRight = -1;
        if (Ac + 1 < M && Bc + 1 < M) { //不越界
            ARightBRight = process(matrix,Ar, Ac + 1 ,Br); //隐藏Bc + 1
        }

        int ARightBDown = -1;
        if (Ac + 1 < M && Br + 1 < N) { //不越界
            ARightBDown = process(matrix,Ar, Ac + 1,Br + 1); //隐藏Bc + 1
        }

        int nextBest = Math.max(Math.max(ADownBRight,ADownBDown), Math.max(ARightBRight,ARightBDown));

        //如果在一个点 只能获取一份
        if (Ar == Br){
            return matrix[Ar][Ac] + nextBest;
        }
        //最终返回的是A和B的点数 + nextBest
        return matrix[Ar][Ac] + matrix[Br][Bc] + nextBest;
    }

    public static void main(String[] args) {
        int[][] matrix = {
                {1,1,1,1,1,0,0,0,0,0},
                {0,0,0,0,1,0,0,0,0,0},
                {0,0,0,0,1,0,0,0,0,1},
                {1,0,0,0,1,0,0,0,0,0},
                {0,0,0,0,1,1,1,1,1,1}
        };

        System.out.println(comeGoMaxPathSum(matrix));
    }

}
