package MyDataTest_02;

/*
Master公式
* 形如
T(N) = a * T(N/b) + O(N^d)(其中的a、b、d都是常数)
的递归函数，可以直接通过Master公式来确定时间复杂度
如果 log(b,a) < d，复杂度为O(N^d)
如果 log(b,a) > d，复杂度为O(N^log(b,a))
如果 log(b,a) == d，复杂度为O(N^d  * logN)
* */

/*
* 对于新手来说，把调用的过程画出结构图是必须的，这有利于分析递归
* 递归并不是玄学，递归底层是利用系统栈来实现的
* 任何递归函数都一定可以改成非递归
*/

public class TestRecursive {



    /*
            求数组arr[L..R]中的最大值，怎么用递归方法实现。
            主要还是二分思想+递归
            1）将[L..R]范围分成左右两半。左：[L..Mid]  右[Mid+1..R]
            2）左部分求最大值，右部分求最大值
            3） [L..R]范围上的最大值，是max{左部分最大值，右部分最大值}

            注意：2）是个递归过程，当范围上只有一个数，就可以不用再递归了
            */
    public static int getMax(int[] arr,int left,int right){

        //只有一个数了，返回自己
        if (left>=right) return arr[left] ;
        //中点
        int mid =  left + ((right-left)>>1) ;
        //求两边最大值，数组不会分割，边界条件要注意
        int leftMax = getMax(arr,left,mid-1) ;
        int rightMax = getMax(arr,mid+1,right) ;


        return Math.max(Math.max(leftMax, rightMax), arr[mid]);
    }


    public static void main(String[] args) {
        int [] arr = {1,2,3,5,6,8,7} ;
        int max = getMax(arr, 0, arr.length - 1);
        System.out.println(max);
    }
}
