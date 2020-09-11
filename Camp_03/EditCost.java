package Camp_03;
/**
 *     给定两个字符串str1和str2，再给定三个整数ic，dc，rc，
 *    分别代表插入、删除、替换一个字符的代价，返回将str1编辑成str2的最小代价。
 * 举例：
 * str1="abc"   str2="adc"  ic=5    dc=3   rc=2，从"abc"编辑到"adc" 把b替换成d代价最小，为2；
 * str1="abc"   str2="adc"  ic=5    dc=3   rc=10，从"abc"编辑到"adc"，先删除b再插入d代价最小，为8；
 * */
public class EditCost {

    //样本行列的dp模型
    //dp含义 dp[i][j] str[i-1] str[j-1]讨论 dp代表长度 需要+1
    public static int minCost(String s1, String s2 , int ic ,int dc ,int rc){
        if (s1 == null || s2 == null) {
            return 0;
        }

        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();

        int N = str1.length + 1;
        int M = str2.length + 1;

        int [][] dp = new int[N][M] ;

        // dp[0][0]  = 0
        //填好行列
        for (int i = 1; i < dp.length; i++) {
            dp[i][0] = dc * i ;
        }

        for (int i = 1; i < dp[0].length; i++) {
            dp[0][i] = ic * i ;
        }

        //从上到下 从左往右 要最后一行最后一个点的数据
        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                if (str1[i - 1] == str2[j - 1]){ //最后一位相等
                   dp[i][j] = dp[i - 1][j - 1] ;// 代价为之前的代价
                }else { //最后一位不相等
                    dp[i][j] = dp[i - 1][j - 1] + rc ; //付出一个替换代价
                }
                //与其他可能性比较，最终决策出代价最小的
                //最后一位保留 str1所有位与str2最后一位之前同步 付出插入代价
                dp[i][j] = Math.min(dp[i][j],dp[i][j - 1] + ic) ;
                //最后一位不保留 付出删除代价
                dp[i][j] = Math.min(dp[i][j],dp[i - 1][j] + dc) ;

                /*
                * 以上综合考虑了 rc ic dc 的发生可能性 不用想那么复杂
                * */
            }
        }

        return dp[N - 1][M - 1];
    }

    public static void main(String[] args) {
        String str1 = "ab12cd3";
        String str2 = "abcdf";
        System.out.println(minCost(str1, str2, 5, 3, 2));

        str1 = "abcdf";
        str2 = "ab12cd3";
        System.out.println(minCost(str1, str2, 3, 2, 4));

        str1 = "";
        str2 = "ab12cd3";
        System.out.println(minCost(str1, str2, 1, 7, 5));
    }

}
