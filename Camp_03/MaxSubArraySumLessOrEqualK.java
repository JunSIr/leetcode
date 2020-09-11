package Camp_03;

import java.util.TreeSet;

/**
 * 给定一个数组arr,再给定一个K值
 * 返回累加和小于等于K，但是离K最近（是<=K的，并且是最大的）的子数组累加和
 * 请返回arr中，求个子数组的累加和，是<=K的，并且是最大的。
 * 返回这个最大的累加和
 * */

public class MaxSubArraySumLessOrEqualK {
    // 逆向思维  枚举每个i结尾时的累加和 看前面有无前缀和 >= （当前累加和-K ）如果有 该位置最大累加和就是 j->i累加和
    public static int getMaxLessOrEqualK(int[] arr, int K) {
        //有序表 记录前面所有累加和
        TreeSet<Integer> set = new TreeSet<>();
        set.add(0); //初始前面有个0的累加和 固定套路
        int max = Integer.MIN_VALUE;

        //枚举每个位置作为结尾
        int sum = 0;
        for (int value : arr) {
            sum += value;
            if (set.ceiling(sum - K) != null) { //之前有
                max = Math.max(max, sum - set.ceiling(sum - K));
            }
            set.add(sum);
        }
        return max;
    }

}
