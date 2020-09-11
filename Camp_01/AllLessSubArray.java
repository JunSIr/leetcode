package Camp_01;

import java.util.LinkedList;

/*
* 单调栈的应用
* 给定一个整型数组 和一个整数num
* 某个arr中的子数组sub，如果想达标，必须满足
* sub中的最大值- sub中的最小值 <=num
* 返回arr中达标子数组的数量
* */
/*
* 关键在于分析出单调性
*  即 大数组的特性 内的小数组也符合
* */
public class AllLessSubArray {

    public static int getNum(int[] arr ,int num){

        if (arr == null || arr.length == 0) {
            return 0;
        }

        //准备两个滑动窗口，一个保存 大优先级 一个小优先级
        LinkedList<Integer> qmax = new LinkedList<>();
        LinkedList<Integer> qmin = new LinkedList<>();
        int res = 0  ;

        int L = 0  ;
        int R = 0  ;

        //L代表0下标节点，从0开始，尝试每个位置
        while (L < arr.length){

            /*
            * 右滑R  R的终止位置是第一个不达标的位置
            * 由单调性 R之前到L位置达标
            * */
            while (R < arr.length){

                while (!qmax.isEmpty() && arr[qmax.peekLast()] <= arr[R]) {
                    qmax.pollLast();
                }
                qmax.addLast(R);

                while (!qmin.isEmpty() && arr[qmin.peekLast()] >= arr[R]) {
                    qmin.pollLast();
                }
                qmin.addLast(R);

                if (arr[qmax.getFirst()] - arr[qmin.getFirst()] > num)
                    break;
                R ++  ;
            }

            //此时R是第一个不达标的位置
            res += R - L  ;

            //把L位置设置为过期
            if (qmax.peekFirst() == L )
                qmax.pollFirst() ;
            if (qmin.peekFirst() == L )
                qmin.pollFirst() ;
            L ++  ;
        }

        return res  ;
    }

    public static void main(String[] args) {
        int[] arr = {1,2} ;
        System.out.println(getNum(arr, 0));
    }

}
