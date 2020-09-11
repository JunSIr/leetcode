package Camp_03;

import java.util.Arrays;

/*
* 题目描述：
* 数轴上从左到右有n个点，a[0] ,a[1],…,a[n-1]，给定一根长度为L绳子，求绳子最多覆盖其中几个点？
* 以下面的数组为例子，如果绳子长度为4，那么最多覆盖的点数应该是568这三个点
* int a[5] = {1,5,6,8,10};
* */
public class CordCoverMaxPoint {

    /*
    * 暴力解法 O(nlogn)
    * 以绳子末端 向前试探 枚举
    * */
    public static int maxPoint1(int[] arr,int len){
        if (arr == null || arr.length == 0 ){
            return 0 ;
        }
        int res = 1  ;
        //枚举每个尾节点
        for (int i = 0; i < arr.length; i++) {
            int nearestIndex = nearestIndex(arr, i, arr[i] - len);
            res = Math.max(res,i - nearestIndex + 1) ;
        }
        return res ;
    }

    // 在R位置左边找>=value 最左的位置  使用二分查找
    public static int nearestIndex(int[] arr,int R ,int value){
        int L = 0 ;
        int index = R ;
        while (L <= R ){
            int mid = L + ( (R - L)>>2) ; //二分中点
            if (arr[mid] >= value){ //命中
                index = mid ;
                R  = mid - 1;
            }else {
                L = mid + 1 ;
            }
        }
        return index;
    }

    /*
    * 滑动窗口 O(n)
    * */
    public static int maxPoint2(int[] arr, int len){
        if (arr == null || arr.length == 0 ){
            return 0  ;
        }

        int L = 0 ;
        int R =  0 ;
        int max = 0  ;
        int N = arr.length ;
        while (L < N){
            //不超过K R-->
            while (R < N && arr[R] - arr[L] <= len){
                R ++ ;
            }
            max = Math.max(max,R - L++) ;
        }
        return max ;
    }


    // for test
    public static int test(int[] arr, int L) {
        int max = 0;
        for (int i = 0; i < arr.length; i++) {
            int pre = i - 1;
            while (pre >= 0 && arr[i] - arr[pre] <= L) {
                pre--;
            }
            max = Math.max(max, i - pre);
        }
        return max;
    }

    // for test
    public static int[] generateArray(int len, int max) {
        int[] ans = new int[(int) (Math.random() * len) + 1];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = (int) (Math.random() * max);
        }
        Arrays.sort(ans);
        return ans;
    }

    public static void main(String[] args) {
        int len = 100;
        int max = 1000;
        int testTime = 100000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int L = (int) (Math.random() * max);
            int[] arr = generateArray(len, max);
            int ans1 = maxPoint1(arr, L);
            int ans2 = maxPoint2(arr, L);
            int ans3 = test(arr, L);
            if (ans1 != ans2 || ans2 != ans3) {
                System.out.println("oops!");
                break;
            }
        }

    }

}
