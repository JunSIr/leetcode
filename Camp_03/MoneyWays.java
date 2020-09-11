package Camp_03;
/**
* 现有n1 + n2两种面值的硬币，其中n1钟为普通币，可以取任意枚，后n2钟为纪念币
 * 每种最多只能取一枚，每种硬币有一个面值
 * 问能用多少种方法拼出m的面值？
* */
/*
* 思想:
* 对 普通币和纪念币分别建立dp表
* */
public class MoneyWays {

    public static int moneyWays(int[] arbitrary, int[] onlyone, int money){
        if (money < 0) {
            return 0;
        }
        //啥币也没有的特殊情况
        if ((arbitrary == null || arbitrary.length == 0) && (onlyone == null || onlyone.length == 0)) {
            return money == 0 ? 1 : 0;
        }
        //拿到两张dp表
        int[][] dpArb = getDpArb(arbitrary, money);
        int[][] dpOne = getDpOne(onlyone, money);

        //如果没有普通币
        if (dpArb == null){
            return dpOne[dpOne.length - 1][money];
        }
        //如果没有纪念币
        if (dpOne == null){
            return dpArb[dpArb.length - 1][money];
        }

        //枚举所有方法
        int res = 0;
        for (int i = 0; i <= money ; i++) {
            res += dpArb[dpArb.length - 1][i] * dpOne[dpOne.length - 1][money - i];
        }
        return res;
    }

    //建立普通币dp表
    public static int[][] getDpArb(int[] arr, int money) {
        if (arr == null || arr.length == 0) {
            return null;
        }
        // 含义 row:普通币面额 col:搞定的钱数递增 0~Money
        //0..i券 自由选择张数， 搞定j元， 有多少方法？
        int[][] dp = new int[arr.length][money + 1];

        //第0列都是1  代表 “一张也不用” 搞定j元
        for (int i = 0; i < arr.length; i++) {
            dp[i][0] = 1;
        }

        //第一行 代表 用arr[0]种货币 搞定j元面试  一定整数倍
        for (int i = 1; arr[0] * i <= money; i++) {
            dp[0][arr[0] * i] = 1;
        }

        //省略了枚举行为后的dp方程  上到下 左到右
        for (int i = 1; i < arr.length; i++) {
            for (int j = 1; j <= money ; j++) {
                dp[i][j] = dp[i - 1][j];
                dp[i][j] += j - arr[i] >= 0 ? dp[i][j - arr[i]] : 0;
            }
        }
        return dp;
    }

    //建立纪念币dp表  普通背包问题
    public static int[][] getDpOne(int[] arr, int money) {
        // =================与getArb一样 =================
        if (arr == null || arr.length == 0) {
            return null;
        }
        int[][] dp = new int[arr.length][money + 1];
        for (int i = 0; i < arr.length; i++) {
            dp[i][0] = 1;
        }
        // =================与getArb一样 =================
        if (arr[0] <= money) {
            dp[0][arr[0]] = 1; //初始化dp
        }
        for (int i = 1; i < arr.length; i++) {
            for (int j = 1; j <= money; j++) {
                dp[i][j] = dp[i - 1][j];
                dp[i][j] += j - arr[i] >= 0 ? dp[i - 1][j - arr[i]] : 0;
            }
        }
        return dp;
    }
}
