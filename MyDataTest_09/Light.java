package MyDataTest_09;

import java.util.HashSet;

/*

*
给定一个字符串str，只由‘X’和‘.’两种字符构成。
‘X’表示墙，不能放灯，也不需要点亮
‘.’表示居民点，可以放灯，需要点亮
如果灯放在i位置，可以让i-1，i和i+1三个位置被点亮
返回如果点亮str中所有需要点亮的位置，至少需要几盏灯

* */
public class Light {
    /*
    * 暴力递归
    * */
    public static int minLight(String road){
        if (road == null || road.length()==0 )
            return 0  ;
        return process(road.toCharArray(),0,new HashSet<>());
    }
    /*
    * str[index->..]位置，自由选择放与不放灯
    * str[0->..index-1]位置已经做完决定，放了灯的位置存在lights里
    * 要求选出所有照亮.的方案，并在这些有效方案中，返回最少需要几个灯
    * */
    public static int process(char[] road, int index, HashSet<Integer> lights){
        /*
        * 检查是否有效解
        * 需要知道，本个检验需要进行很多次
        * */

        if (index ==road.length){   //if整条路放置完毕，验证
            for (int i = 0; i < road.length; i++) {
                //某个点 左中右都没灯，验算不通过
                if (!lights.contains(i-1) && !lights.contains(i) && !lights.contains(i+1)){
                    return Integer.MAX_VALUE ; //表示方案无效
                }
            }
            return lights.size() ; //验证通过，返回方案的数目
        }else {
            /*
            * 决策过程
            * */
            int no = process(road,index+1 ,lights) ; //本轮不放灯的方案数 (本轮为‘X’)
            int yes  = Integer.MAX_VALUE ;  //本轮放灯的方案数(本轮为‘.’)

            if (road[index] == '.'){
                lights.add(index);
                yes = process(road,index+1, lights);
                lights.remove(index) ; //清理现场
            }

            return Math.min(no,yes) ;
        }
    }

    /*
    * 贪心算法
    * 贪心策略:
    * */
    public static int minLight_1(String road){
        if (road == null || road.length()==0 )
            return 0  ;

        char[] str  = road.toCharArray();
        int index = 0 ;
        int light = 0 ; // 放置灯数

        //还未贪完
        while (index < str.length){
            /*
            * 分两类，一类是本轮‘X’，另一类本轮'.'
            * */

           if (str[index] == 'X'){
               //'X'不选 light不更新,到下一个位置做决策
               index ++  ;
           }else {
               //'.'必更新，讨论决定到第几步去决策
               light ++  ;
               if (index + 1 == str.length){
                   //决策完毕
                   break;
               }else {
                   if (str[index + 1] == 'X'){
                       index +=2 ;
                   }else {
                       index +=3;
                   }
               }
           }
        }
        return light ;
    }
    public static void main(String[] args) {
        String str = ".X.X...." ;
        System.out.println(minLight_1(str));
    }
}
