package MyDataTest_11;
/*
* 规定1和A对应、2和B对应、3和C对应...
* 那么一个数字字符串比如"111”就可以转化为:"AAA"、"KA"和"AK"
* 给定一个只有数字字符组成的字符串str，返回有多少种转化结果
* */

/*
* 本题属于从左往右的尝试模型
* */
public class ConvertLetterToString {

    public static int convert(String str){
        if (str==null || str.length() == 0 )
            return 0 ;

        char[] chars = str.toCharArray();
        return  process(chars,0) ;
    }

    // 从左往右 index之前的位置固定，不再考虑
    public static int  process(char[] str , int index ){
        //base case
        if (str.length == index)
            return 1 ;
        /*
        * 可以理解为分支限界
        * */

        // 0无对应字母。注意前面位置已固定，此0作为第一，后续无结果的
        if (str[index] == '0' )
            return 0 ;

        if (str[index] == '1'){
            /*
            * 两种选择，一种“1”单独成A，一种“1”与index+1位置组合
            * */
            int res = process(str,index + 1) ; //单独成A
            if (index + 1 < str.length) //不越界
                res += process(str,index + 2 ) ; //组合

            return res ;
        }

        if (str[index] == '2' ){
            int res = process(str,index+1 ) ; //单独成B
            // 20~ 26
            if (index+1 < str.length && (str[index+1] >= '0' && str[index +1 ] <= '6'))
                res += process(str,index+2 ) ;

            return res ;
        }

        // 3~9 下一个位置
        return process(str,index + 1 ) ;
    }

    /*
    * 暴力改动态规划
    * 结构 单可变参数 --> 一维数组
    * 边界控制  0-N
    *   if (str.length == index)
            return 1 ;          ---->从右往左
    * */
    public static int dpWays(String str){
        if (str==null || str.length() == 0 )
            return 0 ;
        char[] chars = str.toCharArray();

        int N = chars.length;
        int[] dp = new int[N+1] ;

/*        if (str.length == index)
            return 1 ;*/
        dp[N] = 1;

        for (int index = N -1; index >=0 ; index--) {

          if (chars[index] == '0' )
                dp[index] = 0 ;

            if (chars[index] == '1'){
                dp[index] = dp[index+1];
                if (index + 1 < chars.length) //不越界
                    dp[index] += dp[index+2];
            }
            if (chars[index] == '2' ){
                dp[index] = dp[index+1];
                // 20~ 26
                if (index+1 < chars.length && (chars[index+1] >= '0' && chars[index +1 ] <= '6'))
                    dp[index] += dp[index+2] ;

            }
        }
        //与递归顺序相反
        return dp[0] ;
    }


    public static void main(String[] args) {
        int convert = convert("111111");
        System.out.println(convert);
        convert = dpWays("111111");
        System.out.println(convert);
    }
}
