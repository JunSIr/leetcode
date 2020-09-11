package Camp_03;
/**
 * 背包容量为w
 * 一共有n袋零食，第i袋零食体积为v[i]
 * 总体积不超过背包容量的情况下
 * 一共有多少钟零食放法(总体积为0也算一种放法)
 * */
public class SnacksWays {

    /*
    * 暴力递归尝试
    * arr 零食体积 w 背包容量
    * */
    public static int ways1(int[] arr, int w){

        return process(arr, 0 , w) ;
    }

    public static int process(int[] arr ,int index ,int rest){
        // base case
        if (rest < 0 ){ //容量不够了
            return  -1 ; //代表无效
        }
        // rest >= 0
        if (index == arr.length){
            return 1 ; //有效方案数1
        }
        /*
        * 决策  要与不要
        * */
        int next1 = process(arr,index + 1,rest) ;
        int next2 = process(arr, index + 1, rest - arr[index]) ;

        return next1 + (next2 != -1 ? next2 : 0) ;
    }
    /*
    * 暴力改动态规划
    * */
    public static int ways2(int[] arr, int w) {
        int N = arr.length;
        int[][] dp = new int[N + 1][w + 1];

        for (int j = 0; j <= w; j++) {
            dp[N][j] = 1;
        }

        for (int i = N - 1; i >= 0; i--) {
            for (int j = 0; j <= w; j++) {
                dp[i][j] = dp[i + 1][j] + ((j - arr[i] >= 0) ? dp[i + 1][j - arr[i]] : 0);
            }
        }
        return dp[0][w];
    }




    public static void main(String[] args) {
        int[] arr = { 4, 3, 2, 9 };
        int w = 8;
        System.out.println(ways1(arr, w));
        System.out.println(ways2(arr, w));

    }
}
