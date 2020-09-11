package MyDataTest_09;

import java.util.Arrays;
import java.util.Comparator;
/*
* 贪心算法
* 1）最自然智慧的算法
* 2）用一种局部最功利的标准，总是做出在当前看来是最好的选择
* 3）难点在于证明局部最功利的标准可以得到全局最优解
* 4）对于贪心算法的学习主要以增加阅历和经验为主

* */
/*
* 贪心算法求解的标准过程
* 1，分析业务
* 2，根据业务逻辑找到不同的贪心策略
* 3，对于能举出反例的策略直接跳过，不能举出反例的策略要证明有效性，
*   这往往是特别困难的，要求数学能力很高且不具有统一的技巧性

 * */

/*
* 贪心算法的解题套路
* 1，实现一个不依靠贪心策略的解法X，可以用最暴力的尝试
* 2，脑补出贪心策略A、贪心策略B、贪心策略C...
* 3，用解法X和对数器，用实验的方式得知哪个贪心策略正确
* 4，不要去纠结贪心策略的证明

* */



/*
* 给定一个由字符串组成的数组strs，必须把所有的字符串拼接起来，
* 返回所有可能的拼接结果中，字典序最小的结果

* */

//Lexicography : 词典编纂
public class LowestLexicography {
    public static class MyComparator implements Comparator<String> {
        @Override
        public int compare(String a, String b) {
            return (a + b).compareTo(b + a); //原则上，要证明，事实上，证个屁
        }
    }

    public static String lowestString2(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        Arrays.sort(strs, new MyComparator());
        String res = "";
        for (int i = 0; i < strs.length; i++) {
            res += strs[i];
        }
        return res;
    }
}
