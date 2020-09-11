package Camp_03;
/**
 * 股票三连问题
 * */
public class BestTimeToBuyAndSellStock {
    /**
     * 给定一个数组arr，从左到右表示昨天从早到晚股票的价格
     * 作为一个事后诸葛亮，你想知道每次只交易一股
     * 且每次交易只买卖一股，返回能挣到的最大钱数
     * */
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0){
            return 0;
        }
        // 允许当点买当点卖 虽然没有意义 但是规则允许
        int min = prices[0]; //历史最低价
        int ans = 0;
        for (int i = 0; i < prices.length; i++) {
            min = Math.min(min,prices[i]); //更新历史最低价
            ans = Math.max(ans,prices[i] - min); //当前 - 之前最低价 == 目前收益
        }
        return ans;
    }

    /**
     * 给定一个数组arr，从左到右表示昨天从早到晚股票的价格
     * 作为一个事后诸葛亮，你想知道每次随意交易（买卖不限次）
     * 且每次交易只买卖一股，返回能挣到的最大钱数
     * */
    //解法方法，比前一个数大就算收益，本质上，想象成一个函数图 就是把所有k > 0的利益都赚到
    public static int maxProfit1(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        int ans = 0;
        for (int i = 1; i < prices.length; i++) {
            ans += Math.max(prices[i] - prices[i-1], 0);
        }
        return ans;
    }
    /**
     * 给定一个数组arr，从左到右表示昨天从早到晚股票的价格
     * 作为一个事后诸葛亮，交易次数不超过K次
     * 且每次交易只买卖一股，返回能挣到的最大钱数
     * */
    public static int maxProfit2(int K, int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        int N = prices.length;

        // 升降相等 升的不可能过半 故若交易次数过半 与不限次交易解法一致
        if (K >= N / 2){
            return maxProfit1(prices);
        }
        //col - 交易次数 row - 每个时间点的股价
        int[][] dp = new int[N][K + 1];
        int ans = 0;
        //从下到上 从左到右模型 经过优化后的结果
        //以下是经过省略枚举的结果 不要直观对应！
        for (int j = 1; j <= K; j++) {
            int t = dp[0][j - 1] - prices[0];
            for (int i = 1; i < N; i++) {
                t = Math.max(t,dp[i][j - 1] - prices[i]);
                dp[i][j] = Math.max(dp[i - 1][j], t + prices[i]);
                ans = Math.max(ans, dp[i][j]);
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        int[] test = { 4, 1, 231, 21, 12, 312, 312, 3, 5, 2, 423, 43, 146 };
        int K = 3;
        System.out.println(maxProfit2(K, test));


    }
}
