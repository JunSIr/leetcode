package MyDataTest_11;

import java.util.ArrayList;
import java.util.List;


public class PrintAllPermutations {
    /*
     * 打印一个字符串的全部排列
     * */
    public static void printPermutation(String str){
        if (str==null || str.length() < 2 )
            return;
        char[] chars = str.toCharArray();
        List<String> list = new ArrayList<>();
        process(chars,0,list);
        for(String r : list){
            System.out.println(r);
        }

    }

    public static void process(char[] str, int index, List<String> res){

        if (index == str.length){
            res.add(String.valueOf(str)) ;
            return;
        }

        for (int i = index; i < str.length; i++) {
            swap(str,i,index);
            process(str,index + 1,res);
            /*
            * 恢复现场
            * */
            swap(str,i,index);
        }

    }



    /*
     * 打印一个字符串的全部排列，要求不要出现重复的排列
     * 考虑分支限界
     * */
    public static void noRepeatPermutation(String str){
        if (str==null || str.length() < 2 )
            return;
        char[] chars = str.toCharArray();
        List<String> list = new ArrayList<>();
        process(chars,0,list);
        for(String r : list){
            System.out.println(r);
        }

    }

    public static void process_1(char[] str, int index, List<String> res){

        if (index == str.length){
            res.add(String.valueOf(str)) ;
            return;
        }
        boolean[] visited = new boolean[26] ; //经典套路了,此处注意朱永范围

        /*
        * index 之前的位置已经决定，不再关心
        * */
        for (int i = index; i < str.length; i++) {
            if (!visited[str[i] - 'a']){ //还没访问过
                visited[str[i] - 'a'] = true ;
                swap(str,i,index);
                process(str,index + 1,res);
                /*
                 * 恢复现场
                 * */
                swap(str,i,index);
            }


        }

    }


    /*
    * 数组 给定下标 交换值
    * */
    public static void swap(char[] chs, int i, int j) {
        char tmp = chs[i];
        chs[i] = chs[j];
        chs[j] = tmp;
    }


    public static void main(String[] args) {
        printPermutation("ab");
    }
}
