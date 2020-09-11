package Camp_03;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 给定两个字符串s1和s2，问s2最少删除多少个字符可以成为s1的子串
 * 比如 s1 = "abcde" s2 = "axbc"
 * 返回1 删掉'x‘就是s1的子串
 * 本题边界控制比较飞
 * */
public class DeleteMinCost {

/*
* 解法一，来自群里的解法：求出str2所有的子序列，然后按照长度排序，长度大的排在前面。
* 然后考察哪个子序列字符串和s1的某个子串相等(KMP)，答案就出来了。
* 分析：
* 因为题目原本的样本数据中，有特别说明s2的长度很小。所以这么做也没有太大问题，也几乎不会超时。
* 但是如果某一次考试给定的s2长度远大于s1，这么做就不合适了。
* */
    public static int minCost1(String s1, String s2){
        if (s1.length() == 0 || s2.length() == 0) {
            return s2.length();
        }
        //s2所有子序列
        List<String> s2Subs = new ArrayList<>();
        process(s2.toCharArray(),0,"",s2Subs);
        //子序列按长度排序
        s2Subs.sort(new LenComp());
        for (String str : s2Subs){
            if (s1.contains(str)){
                return s2.length() - str.length() ; //s2 - 子序列长度 = 多余长度
            }
        }
        return s2.length() ; //代表全删 空串也是子串
    }

    //求所有子序列 不懂画树图了解一下 就是要与不要模型
    public static void process(char[] str2, int index, String path, List<String> list){
        //base case 到末尾 入list
        if (index == str2.length){
            list.add(path) ;
            return;
        }
        //本位置不考虑
        process(str2, index + 1, path ,list);
        //本位置考虑
        process(str2, index + 1, path + str2[index], list);
    }

    //按长度比较的
    public static class LenComp implements Comparator<String> {
        @Override
        public int compare(String o1, String o2) {
            return o2.length() - o1.length();
        }
    }

    // 解法二
    // 生成所有s1的子串
    // 然后考察每个子串和s2的编辑距离(假设编辑距离只有删除动作且删除一个字符的代价为1)
    // 如果s1的长度较小，s2长度较大，这个方法比较合适
    public static int minCost2(String s1, String s2){
        if (s1.length() == 0 || s2.length() == 0) {
            return s2.length();
        }

        int ans = Integer.MAX_VALUE ;
        char[] str2 = s2.toCharArray();
        //枚举s1所有字串
        for (int start = 0; start < s1.length(); start++) {
            // 左闭右开 所以<=s1.length
            for (int end = start + 1; end <= s1.length() ; end++) {
                ans = Math.min(ans, distance(str2,s1.substring(start, end).toCharArray())) ;
            }
        }
        return ans == Integer.MAX_VALUE ? s2.length() : ans;
    }
    /*
    * 求s2到s1子串的编辑距离 限定删除代价
    * */
    public static int distance(char[] str2, char[] s1sub){
        int row = str2.length;
        int col = s1sub.length;
        int[][] dp = new int[row][col];
        //处理第一个格子，无需编辑则0代价，否则max代价代表不行
        dp[0][0] = str2[0] == s1sub[0] ? 0 : Integer.MAX_VALUE;

        //填好行列
        for (int j = 1 ; j < col; j++) { //第一行数据
            dp[0][j] = Integer.MAX_VALUE ;
        }
        for (int i = 1; i < row; i++) { //第一列数据
            dp[i][0] = (dp[i - 1][0] != Integer.MAX_VALUE || str2[i] == s1sub[0]) ? i : Integer.MAX_VALUE;
        }

        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                dp[i][j] = Integer.MAX_VALUE;
                if (dp[i - 1][j] != Integer.MAX_VALUE){ //前面的代价有效
                    dp[i][j] = dp[i - 1][j] + 1; //付出一个删除代价
                }
                if (str2[i] == s1sub[j] && dp[i - 1][j - 1] != Integer.MAX_VALUE) {
                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][j - 1]);
                }
            }
        }

        return dp[row - 1][col - 1];
    }

    public static String generateRandomString(int l, int v) {
        int len = (int) (Math.random() * l);
        char[] str = new char[len];
        for (int i = 0; i < len; i++) {
            str[i] = (char) ('a' + (int) (Math.random() * v));
        }
        return String.valueOf(str);
    }

    public static void main(String[] args) {
        int str1Len = 20;
        int str2Len = 10;
        int v = 5;
        int testTime = 10000;
        boolean pass = true;
        System.out.println("test begin");
        for (int i = 0; i < testTime; i++) {
            String str1 = generateRandomString(str1Len, v);
            String str2 = generateRandomString(str2Len, v);
            int ans1 = minCost1(str1, str2);
            int ans2 = minCost2(str1, str2);
            if (ans1 != ans2 ) {
                pass = false;
                System.out.println(str1);
                System.out.println(str2);
                System.out.println(ans1);
                System.out.println(ans2);
                break;
            }
        }
        System.out.println("test pass : " + pass);
    }

}
