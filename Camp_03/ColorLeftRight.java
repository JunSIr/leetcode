package Camp_03;
/*
*
* 牛牛有一些排成一行的正方形。每个正方形已经被染成红色或者绿色。
* 牛牛现在可以选择任意一个正方形然后用这两种颜色的任意一种进行染色,这个正方形的颜色将会被覆盖。
* 牛牛的目标是在完成染色之后,每个红色R都比每个绿色G距离最左侧近。
* 牛牛想知道他最少需要涂染几个正方形。
如样例所示: s = RGRGR
我们涂染之后变成RRRGG满足要求了,涂染的个数为2,没有比这个更好的涂染方案
* */
public class ColorLeftRight {

    /* 把问题抽象为:
    * 枚举分界线，统计左边几个G 右边几个R  （左边G改R，右边R改G）相加就是每个分界线的方案数
    * */
    public static int minPaint(String s){

        if (s == null || s.length() < 2){
            return 0 ;
        }
        char[] str = s.toCharArray();
        int N = str.length ;
        //统计整个数组几个R
        int rAll = 0  ;
        for (int i = 0; i < N; i++) {
            rAll += str[i] == 'R' ? 1 : 0 ;
        }
        //无左有右 ... ）（0...N-1） -->右边几个R改几次
        int ans = rAll ;
        int leftG = 0 ;
        //左（0...i） 右 (i+1...N-1)
        for (int i = 0; i < N - 1; i++) {
            //左边几个G
            leftG += str[i] == 'G' ? 1 : 0 ;
            //右边几个R
            rAll -= str[i] == 'R' ? 1 : 0 ;
            ans  = Math.min(ans , leftG + rAll) ;
        }
        //无右全左 统计左边几个G
        ans = Math.min(ans,leftG += str[N - 1] == 'G' ? 1 : 0) ;
        return ans ;
    }


    public static void main(String[] args) {
        String test = "GGGGGR";
        System.out.println(minPaint(test));
    }
}
