package Camp_01;
/*
* KMP算法实现
* */
public class KMP {
    /*
    * s-原串 m-目标串
    * 返回值代表 原串中符合m串的起始下标 长度自然就是m的值
    * */
    public static int getIndexOf(String s, String m){

        if (s==null || m == null || m.length() < 1 || s.length() < m.length()){
            return  -1 ;
        }

        char[] str = s.toCharArray();
        char[] match = m.toCharArray();
        int x = 0 ;  //str当前比对位置
        int y = 0 ;  //match当前比对位置
        //next数组
        int[] next = getNextArray(match);

        while (x < s.length() && y < m.length()){
            //如果能匹配得上
            if (str[x] == match[y]){
                x++ ;
                y++ ;
            }else if (next[y] == -1 ){ //匹配不上，也没有最长公共前缀
                x++  ; //直接跳到下一个位置
            }else {  //匹配不上，但有前缀
                y  = next[y] ;  //跳到第一个前缀末尾的后一个位置
            }
        }

        return y == match.length? x - y : -1  ;
    }

    /*
    * 生成Next数组
    * */
    public static int[] getNextArray(char[] match){

        if (match.length == 1 ){
            return new int[] {-1} ;
        }

        int[] next = new int[match.length] ;
        //人为规定
        next[0] = -1 ;
        next[1] = 0 ;
        int i = 2 ; //从第三个位置开始 生成next

        // cn位置的字符，是当前和i-1位置比较的字符
        int cn = 0;
        /*
        * 得知道 next[i]的信息是match[0-i-1]前后公共前缀信息
        * */
        while (i < next.length){
            //如果
            if (match[i-1] == match[cn]){
                next[i++] = ++cn ;
            }else if (cn > 0){
                cn = next[cn] ; //cn跳到上一个前缀的下一个位置
            }else {
                next[i++] = 0  ;
            }
            //打完收工
        }

        return next ;
    }

    public static void main(String[] args) {
        String str = "abbcd" ;
        String match = "bc" ;
        System.out.println(getIndexOf(str, match));
    }

}
