package Camp_03;

import java.util.Arrays;

/**
 *题目描述： 数对是数学中一个重要的概念，类似于计算机中的pair，数对的性质如下：
 *每个数对(x,y)包含两个实数元素x,y，描述一对数之间的关系。两个数对比大小将先比较第一个数的大小，如果相同再比较第二个数的大小
 *现在，有n个数（两两可能相同），他们之间两两将会形成n^2个数对（自己和自己也会形成数对）。
 * 我们希望知道，第k小的数对是哪一对数，并输出这一对。
 * 样例输入 3 4 3 1 2 样例输出 (2,1)
 * 样例解释 数对一共有9个，分别是： (3,3)(3,1)(3,2)(1,3)(1,1)(1,2)(2,3)(2,1)(2,2)
 * 按从小到大的排序后：(1,1)(1,2)(1,3)(2,1)(2,2)(2,3)(3,1)(3,2)(3,3) 第4个为(2,1)
 *
 *               本题核心  剪枝 --> 根据规律推出公式
 *
 * */
public class KthMinPair {

    // O(N*logN)
    public static int[] kthMinPair(int[] arr, int K){
        int N = arr.length ;
        if (K > N * N){
            return null ;
        }
        //排序 O(N*logN)
        Arrays.sort(arr);

        //第K小的数值对，第一维数字
        int firstNum = arr[(K - 1) / N] ;
        // 数出比firstNum小的数有几个
        int lessFirstNumSize = 0  ;
        // 数出等于firstNum的数有几个
        int firstNumSize = 0  ;

        for (int i = 0; i < N && arr[i] <= firstNum; i++) {
            if (arr[i] < firstNum){
                lessFirstNumSize ++ ;
            }else { // ==
                firstNumSize ++ ;
            }
        }

        // 第k小 - 剪枝排除
        int rest = K - lessFirstNumSize * N ;
        return new int[] { firstNum, arr[(rest - 1) / firstNumSize] };
    }

    // O(N*logN) --> O（N） 排序优化成bfprt的前置partition
    public static int[] KthMinPair2(int[] arr , int K){
        int N = arr.length ;
        if (K > N * N){
            return null ;
        }
        //排序 O(N*logN)
        Arrays.sort(arr);

        //第K小的数值对，第一维数字
        int firstNum = arr[(K - 1) / N] ;
        // 数出比firstNum小的数有几个
        int lessFirstNumSize = 0  ;
        // 数出等于firstNum的数有几个
        int firstNumSize = 0  ;

        for (int i = 0; i < N && arr[i] <= firstNum; i++) {
            if (arr[i] < firstNum){
                lessFirstNumSize ++ ;
            }else { // ==
                firstNumSize ++ ;
            }
        }

        // 第k小 - 剪枝排除
        int rest = K - lessFirstNumSize * N ;

        return new int[] { firstNum, getMinKth(arr, (rest - 1) / firstNumSize)};//在有序情况下的index
    }


    // 改写快排模板，时间复杂度O(N)
    // 在无序数组arr中，找到，如果排序的话，arr[index]的数是什么？
    public static int getMinKth(int[] arr, int index) {
        int L = 0;
        int R = arr.length - 1;
        int pivot = 0;
        int[] range = null;
        while (L < R) {
            pivot = arr[L + (int) (Math.random() * (R - L + 1))];
            range = partition(arr, L, R, pivot);
            if (index < range[0]) {
                R = range[0] - 1;
            } else if (index > range[1]) {
                L = range[1] + 1;
            } else {
                return pivot;
            }
        }
        return arr[L];
    }
    public static int[] partition(int[] arr, int L, int R, int pivot) {
        int less = L - 1;
        int more = R + 1;
        int cur = L;
        while (cur < more) {
            if (arr[cur] < pivot) {
                swap(arr, ++less, cur++);
            } else if (arr[cur] > pivot) {
                swap(arr, cur, --more);
            } else {
                cur++;
            }
        }
        return new int[] { less + 1, more - 1 };
    }
    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }


    // 为了测试，生成值也随机，长度也随机的随机数组
    public static int[] getRandomArray(int max, int len) {
        int[] arr = new int[(int) (Math.random() * len) + 1];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * max) - (int) (Math.random() * max);
        }
        return arr;
    }

    // 为了测试
    public static int[] copyArray(int[] arr) {
        if (arr == null) {
            return null;
        }
        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = arr[i];
        }
        return res;
    }

    // 随机测试了百万组，保证三种方法都是对的
    public static void main(String[] args) {
        int max = 100;
        int len = 30;
        int testTimes = 100000;
        System.out.println("test bagin, test times : " + testTimes);
        for (int i = 0; i < testTimes; i++) {
            int[] arr = getRandomArray(max, len);
            int[] arr1 = copyArray(arr);
            int[] arr2 = copyArray(arr);
            int[] arr3 = copyArray(arr);
            int N = arr.length * arr.length;
            int k = (int) (Math.random() * N) + 1;
            int[] ans1 = kthMinPair(arr1, k);
            int[] ans2 = KthMinPair2(arr,k);

            if (ans1[0] != ans2[0] || ans1[1] != ans2[1] ) {
                System.out.println("Oops!");
            }
        }
        System.out.println("test finish");
    }
}
