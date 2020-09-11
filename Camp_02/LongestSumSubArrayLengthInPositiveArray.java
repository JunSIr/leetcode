package Camp_02;
/*
* 数组累加和问题第一连
* 正数数组 求最长子数组 == k的长度
* 关键点 正数 --> 分析出其含有严格单调性-->滑动窗口可破
*
* */
public class LongestSumSubArrayLengthInPositiveArray {


    public static int getMaxLength(int[] arr ,int K){

        if (arr == null || arr.length ==0 || K < 0 ){
            return 0 ;
        }

        int L = 0 ;//滑动窗口左
        int R = 0 ;
        int sum = arr[0] ; //窗口左闭右闭
        int len = 0  ; //记录最大长度

        while (R < arr.length){
            if (sum == K){ //
                len = Math.max(len,R - L + 1 ) ;//更新len
                sum -= arr[L++] ;
            }else if (sum < K){
                R++ ;
                if (R == arr.length){
                    break; //越界，后续没有讨论的必要了
                }
                sum += arr[R] ;
            }else {
                sum -= arr[L++] ;
            }
        }
        return len ;
    }

    // for test
    public static int right(int[] arr, int K) {
        int max = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length; j++) {
                if (valid(arr, i, j, K)) {
                    max = Math.max(max, j - i + 1);
                }
            }
        }
        return max;
    }

    // for test
    public static boolean valid(int[] arr, int L, int R, int K) {
        int sum = 0;
        for (int i = L; i <= R; i++) {
            sum += arr[i];
        }
        return sum == K;
    }

    // for test
    public static int[] generatePositiveArray(int size, int value) {
        int[] ans = new int[size];
        for (int i = 0; i != size; i++) {
            ans[i] = (int) (Math.random() * value) + 1;
        }
        return ans;
    }

    // for test
    public static void printArray(int[] arr) {
        for (int i = 0; i != arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int len = 50;
        int value = 100;
        int testTime = 500000;
        System.out.println("test begin");
        for (int i = 0; i < testTime; i++) {
            int[] arr = generatePositiveArray(len, value);
            int K = (int) (Math.random() * value) + 1;
            int ans1 = getMaxLength(arr, K);
            int ans2 = right(arr, K);
            if (ans1 != ans2) {
                System.out.println("Oops!");
                printArray(arr);
                System.out.println("K : " + K);
                System.out.println(ans1);
                System.out.println(ans2);
                break;
            }
        }
        System.out.println("test end");
    }
}
