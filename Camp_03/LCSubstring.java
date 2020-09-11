package Camp_03;

/** 给定两个字符串str1和str2，返回两个字符串的最长公共子串
* 本题将动态空间压缩到极致
* */

public class LCSubstring {

    /*
    * 正常dp表
    * */
    public static String lcst1(String str1,String str2){

        if (str1 == null || str2 == null || str1.equals("") || str2.equals("")){
            return "" ;
        }

        char[] chs1 = str1.toCharArray();
        char[] chs2 = str2.toCharArray();
        int[][] dp = getdp(chs1, chs2);
        int end = 0 ; //记录字串结尾位置
        int max = 0 ; //记录最大值


        //遍历dp
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                if (dp[i][j] > max){
                    end = i ;
                    max = dp[i][j] ;
                }
            }
        }
        return str1.substring(end - max + 1 , end + 1) ;
    }

    //拿到dp数组 含义: 以str[r]与str[c]结尾的字串长度
    public static int[][] getdp(char[] str1, char[] str2){

        int[][] dp = new int[str1.length][str2.length] ;
        //填好第一列
        for (int i = 0; i < str1.length; i++) {
            if (str1[i] == str2[0]){
                dp[i][0] = 1;
            }
            //其余都是0，系统默认实现
        }
        //填好第一行
        for (int i = 1; i < str2.length; i++) {
            if (str2[i] == str1[0]){
                dp[0][i] = 1 ;
            }
        }

        //状态转移方程: 看左上角
        for (int i = 1; i < str1.length; i++) {
            for (int j = 1; j < str2.length; j++) {
                if (str1[i] == str2[j]){
                    dp[i][j] = dp[i - 1][j - 1] + 1 ;
                }
            }
        }
        return dp ;
    }

    /*
    * 压缩dp表，用有限几个变量完成,dp表只存在于逻辑意义上
    * */
    public static String lcst2(String s1 , String s2){
        if (s1 == null || s2 == null || s1.equals("") || s2.equals("")){
            return "" ;
        }

        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();

        // 初始点在最右上
        int row = 0 ;
        int col = str2.length - 1 ;
        int max = 0 ;//记录最大值
        int end = 0 ; //记录点位 方便回串
        while (row < str1.length){

            //向右下方移动 构成斜线
            int i = row;
            int j = col;
            int len = 0 ;
            while (i < str1.length && j < str2.length){

                if (str1[i] != str2[j]){
                    len = 0 ;
                }else {
                    len ++ ;
                }
                if (len > max){
                    end= i ;
                    max = len ;
                }
                i ++ ;
                j ++ ;
            }

            if (col > 0){
                col -- ;
            }else { // col==0
                row ++ ;
            }

        }
        return s1.substring(end - max + 1 , end + 1) ;
    }

    public static void main(String[] args) {
        String str1 = "ABC1234567DEFG";
        String str2 = "HIJKL1234567MNOP";
        System.out.println(lcst1(str1, str2));
        System.out.println(lcst2(str1, str2));
    }
}
