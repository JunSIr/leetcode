package Camp_03;

import java.util.Arrays;
import java.util.HashSet;

/**
 * 如果一个数组再排序之后，每相邻两个数差的绝对值都为1，则该数组为可整合数组。
 * 例如，[5,3,4,6,2]排序之后为[2,3,4,5,6],符合每相邻两个数差的绝对值为1，所以这个数组为可整合数组。
 * 给定一个整型数组，请返回其中最大可整合子数组的长度。
 * 例如，[5,5,3,2,6,4,3]的最大可整合子数组为[5,3,2,6,4]，所以返回5.
 * */
public class LongestIntegratedLength {
    /*
    * 暴力解 枚举所有子数组 排序 业务判定
    * 时间复杂度 O(N^3 * log N)
    * */
    public static int getLIL1(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        // 枚举所有子数组 返回答案
        int len = 0;
        for (int start = 0; start < arr.length; start++) {
            for (int end = start; end < arr.length; end++) {
                if (isIntegrated(arr,start,end)){
                    len = Math.max(len,end - start + 1);
                }
            }
        }
        return len;
    }
    // 业务判定逻辑
    public static boolean isIntegrated(int[] arr, int left, int right) {
        //为了干扰原始数组后续子数组的逻辑，得重新copy一个新的数组
        int[] newArr = Arrays.copyOfRange(arr, left, right + 1);
        Arrays.sort(newArr);
        for (int i = 1; i < newArr.length; i++) {
            if (newArr[i - 1] != newArr[i] - 1){
                return false;
            }
        }
        return true;
    }

    /*
    * 优化解
    * 原题意重新整合成
    * 1） 有重复直接pass
    * 2） max - min ！= R - L  pass
    * */
    public static int getLIL2(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }

        int len = 0; //记录结果
        int max = Integer.MIN_VALUE; //记录每个子数组最大值
        int min = Integer.MAX_VALUE; //记录每个子数组最大值
        HashSet<Integer> set = new HashSet<>(); //判定每个子数组有无重复值
        for (int L = 0; L < arr.length; L++) {
            set.clear(); //清空
            //重新赋值
            max = Integer.MIN_VALUE; //记录每个子数组最大值
            min = Integer.MAX_VALUE; //记录每个子数组最大值
            for (int R = L; R < arr.length; R++) {
                //判定重复
                if (set.contains(arr[R])){ //如果重复 此子数组失效
                    break;
                }
                //更新
                set.add(arr[R]);
                max = Math.max(max,arr[R]);
                min = Math.min(min,arr[R]);
                //判定逻辑
                if (max - min == R - L){
                    len = Math.max(len,R - L + 1);
                }

            }
        }

        return len;
    }

    public static void main(String[] args) {
        int[] arr = { 5, 5, 3, 2, 6, 4, 3 };
        System.out.println(getLIL1(arr));
        System.out.println(getLIL2(arr));
    }
}
