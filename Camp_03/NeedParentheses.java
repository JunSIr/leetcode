package Camp_03;
/*    括号配对问题四连
* 给定一个只包括 '('，')'，的字符串
* (1)判断字符串是否有效。
* (2)如果一个括号字符串无效，返回需要再添几个括号让其整体有效
* (3)返回括号串的最大嵌套层数
* (4)返回一个括号串中，最长的有效字串长度
* 有效字符串需满足：
* 左括号必须用相同类型的右括号闭合。
* 左括号必须以正确的顺序闭合。
* 注意空字符串可被认为是有效字符串。
* */
public class NeedParentheses {

    public static boolean valid(String str){
        if (str == null || str.length() == 0 ){
            return true ;
        }

        char[] s = str.toCharArray();
        int count = 0 ;
        for (int i = 0; i < s.length; i++) {
                count += s[i] == '('? 1 : -1  ;
                if (count < 0 ){
                    return false ;
                }
        }
        if (count!=0){
            return false ;
        }
        return true ;
    }

    public static int needParentheses(String s) {

        if (s == null || s.length() == 0 ){
            return 0 ;
        }

        char[] str = s.toCharArray();
        int count = 0 ;
        int need = 0  ;

        for (int i = 0; i < str.length; i++) {
            if (str[i] == '('){
                count ++ ;
            }else {
                if (count == 0 ){ //再减就是-1 ，故提前处理
                    need ++ ;
                }else {
                    count --  ;
                }
            }
        }
        return count + need ;
    }

    public static int deep(String s){
        if (!valid(s)){
            return  0  ;
        }
        char[] str = s.toCharArray();
        int max = Integer.MIN_VALUE ;
        int count =  0  ;
        for (int i = 0; i < str.length; i++) {
            if (str[i] == '('){
                max = Math.max(max,count ++ ) ;
            }else {
                count -- ;
            }
        }
        return max ;
    }



    public static int maxLength(String s){

        if (s == null || s.length() < 2){
            return 0  ;
        }

        char[] str = s.toCharArray();
        int[] dp = new int[str.length] ;
        int pre  = 0; // 前探的下标
        int ans = 0;  //结果每次选最大，返回

        //默认dp[0] = 0 ; '('也是0 ; 只有')'才有必要讨论
        for (int i = 1; i < str.length; i++) {
            if (str[i] == ')'){
                pre = i - dp[i -1] - 1  ; //康康之前有效段的前一个位置的字符
                //pre>0 表示位置有效 str[pre] == '('表示配对成功 +2
                if (pre > 0 &&  str[pre] == '('){
                    //更新dp
                    dp[i]  = dp[i -1 ] + 2 + pre>0 ? dp[pre -1] : 0  ;
                }

            }
            ans = Math.max(ans,dp[i]) ;
        }

        return  ans ;
    }

}


