package Camp_01;
/*
* 找出一个数组中第K小（大）的数
* */
public class TopK {
    /*
    * 利用堆 时间复杂度O(N*logK)
    */

    /*
    * 改写快排 时间复杂度O(N)
    * 原理 : 快排 partition后  中间等于区的数  位置不变
    * */
    public static int minKth(int[] array , int k ){
        if (array.length < k || k < 0  ){
            return  Integer.MAX_VALUE;
        }
        int[] arr = copyArray(array);
        //k-1 比如 第一小  那就是排序后的第0个数
        return process(arr,0,arr.length -1 , k -1) ;
     }
    /*
    *  复制数组
    * */
    public static int[] copyArray(int[] arr) {
        int[] ans = new int[arr.length];
        for (int i = 0; i != ans.length; i++) {
            ans[i] = arr[i];
        }
        return ans;
    }

    public static int process(int[] arr ,int L ,int R ,int index){
        //base case
        if (L == R ){
            return  arr[R] ;
        }

        //随机pivot 这一步保证整个算法O（N）
        int pivot = arr[L + (int) (Math.random() * (R - L + 1))];

        int[] range = partition(arr, L, R, pivot);
        if (index >= range[0] && index <= range[1]) {   //在范围内
            return arr[index]  ;
        }else if (index < range[0]){
            return process(arr,L,range[0] -1 ,index) ;
        }else {
            return process(arr,range[1]+1 , R , index) ;
        }

    }

    /*
    *  以pivot做分割，分成左边小于区 中间等于区 右边大于区
    *
    * */
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

    /*
    * 交换
    * */
    public static void swap(int[] arr, int i1, int i2) {
        int tmp = arr[i1];
        arr[i1] = arr[i2];
        arr[i2] = tmp;
    }


    public static void main(String[] args) {
        int[] arr = {3,2,3,4} ;
        System.out.println(minKth(arr, 1));
    }

}
