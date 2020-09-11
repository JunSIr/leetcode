package MyDataTest_11;
/*
* 背包问题
* 给定两个长度都为N的数组weights和values，
* weights[i]和values[i]分别代表 i号物品的重量和价值。
* 给定一个正数bag，表示一个载重bag的袋子，
* 你装的物品不能超过这个重量。
* 返回你能装下最多的价值是多少?

* */
public class KnapsackQuestion {

    public static int maxValue(int[] w,int [] v, int bag){
        if (w==null||w.length<1)
            return 0 ;
        return process(w,v,0,0,bag) ;
    }
    /*
    * w v 数组代表重量与价值
    * index 跟之前一样，当前来到的位置
    * alreadyW 背包已经装下的重量
    * bag 总重量
    * */
    public static int process(int[] w, int[] v, int index, int alreadyW ,int bag){
        // base case
        if (alreadyW > bag)
            return -1;
        // 装完，本方案收工
        if (index == w.length)
            return 0  ;
        //本物品不放进背包
        int p1 = process(w, v, index + 1, alreadyW, bag);

        //本物品放进背包
        //p2Next代表如果放进去，之后产生的最大价值
        int p2Next = process(w, v, index + 1, (alreadyW + w[index]), bag);

        int p2 = -1 ;
        if (p2Next!=-1 ){
            p2 = p2Next + v[index] ;
        }
        return Math.max(p1,p2) ;

    }





    /*
    * Rest版本写法
    * */
    public static int maxValueOfRest(int[] w, int[] v, int bag) {
        return process(w, v, 0, bag);
    }
    // 只剩下rest的空间了，
    // index...货物自由选择，但是不要超过rest的空间
    // 返回能够获得的最大价值
    public static int process(int[] w, int[] v, int index, int rest) {
        if (rest <= 0) { // base case 1
            return 0;
        }
        // rest >=0
        if (index == w.length) { // base case 2
            return 0;
        }
        // 有货也有空间
        int p1 = process(w, v, index + 1, rest);
        int p2 = Integer.MIN_VALUE;
        if (rest >= w[index]) {
            p2 = v[index] + process(w, v, index + 1, rest - w[index]);
        }
        return Math.max(p1, p2);
    }


    /*
    * 动态规划版
    * 可变参数为index和rest 结构化之后就是 二维数组
    * 边界控制: rest 0->bag   index 0->N(if index == w.length)
    * bag:背包容量
    * */

    public static int dpWays(int[] w, int[] v, int bag) {

        int N = w.length ;
        int[][] dp = new int[N+1][bag+1] ;
        /*
        *  index从结构的倒数第二行开始，最后一行根据
        *  if (index == w.length) return 0 判断出最后一行都是0
        *  而int初始化就是0 不用管了
        * */
        for (int index = N-1; index >= 0; index--) {
            /*
            *  if (rest <= 0) --> 0列都是0 -->从第一列开始
            * */
            for (int rest = 1; rest <= bag ; rest++) {
                /*
                 *dp[index][rest] = ? ;
                 * 此处参考暴力递归的决策过程
                */

                //int p1 = process(w, v, index + 1, rest);
                 dp[index][rest] = dp[index+1][rest] ;

                 if (rest>=w[index])
                     dp[index][rest] =  Math.max(dp[index][rest],
                                        v[index] + dp[index+1][rest-w[index]]
                             )  ;
                /*
                        int p2 = Integer.MIN_VALUE;
                        if (rest >= w[index]) {
                            p2 = v[index] + process(w, v, index + 1, rest - w[index]);
                        }
                        return Math.max(p1, p2);*/


            }
        }
        //看主函数  return process(w, v, 0, bag) -->要的是dp结构的[0][bag]位置
        return dp[0][bag] ;

    }





    public static void main(String[] args) {
        int[] weights = { 3, 2, 4, 7 };
        int[] values = { 5, 6, 3, 19 };
        int bag = 11;
        System.out.println(maxValue(weights, values, bag));
        System.out.println(dpWays(weights, values, bag));
    }

}
