package Camp_03;
/**
 * 给定两个字符串S和T 返回S子序列等于T的不同子序列个数有多少个
 * 例如S = "rabbbit" T = "rabbit"
 * 返回3 （3个b乱删）
 * 行列尝试模型的  挺简单一dp
 * */
public class DistinctSubsequences {

    public static int numDistinct3(String S, String T) {
        char[] s = S.toCharArray();
        char[] t = T.toCharArray();
        int[] dp = new int[t.length + 1];
        dp[0] = 1;
        for (int j = 1; j <= t.length; j++) {
            dp[j] = 0;
        }
        for (int i = 1; i <= s.length; i++) {
            for (int j = t.length; j >= 1; j--) {
                dp[j] += s[i - 1] == t[j - 1] ? dp[j - 1] : 0;
            }
        }
        return dp[t.length];
    }

}
