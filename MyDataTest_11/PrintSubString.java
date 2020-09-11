package MyDataTest_11;

import java.util.ArrayList;
import java.util.List;

/*
* 字符串子序列问题
* */
public class PrintSubString {

    /*
    * 打印一个字符串的全部子序
    * 注意 子串要求连续、子序列不要求连续
    * */

    public static void sub(String str){
        if (str==null || str.length() == 0 )
            return;
        List<String> res = new ArrayList<>() ;
        char[] chars = str.toCharArray();
        process(chars,0, res,"");
        for(String a : res){
            System.out.println(a);
        }
    }

      /*
      * str:要被分解成子序列的字符数组
      * index 左->右每一步做决策
      * res 决策最后决定要的结果
      * path 当前的字符序列
      * 一定要把决策图画一画！！
       */
    public static void process(char[] str , int index , List<String> res, String path){

        //base case
        if (index == str.length){
            res.add(path) ;
            return;
        }
        /*
        * 每次下去两条分支、yes and no
        * */
        String no = path ;  //无视当前位置，到下一个位置做决定
        process(str,index +1 ,res , no);

        String yes = path + String.valueOf(str[index]) ; //更新当前位置，到下一个位置做决定
        process(str,index +1 ,res , yes);
    }


    /*
    * 打印一个字符串的全部子序列，要求不要出现重复字面值的子序列
    * 这个最后在结果res在利用HashSet过滤就行了
    * 当然，这个时候应该想到分支限界，下面来
     * */



    public static void main(String[] args) {
        sub("abc");
    }
}
