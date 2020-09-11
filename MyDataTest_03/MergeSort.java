package MyDataTest_03;
/*
* ---- 归并排序：
1）整体是递归，左边排好序+右边排好序+merge让整体有序
2）让其整体有序的过程里用了排外序方法
3）利用master公式来求解时间复杂度
4）当然可以用非递归实现
*/

/*          T(N) = a * T(N/b) + O(N^d)
            递归行为发生的次数为2，a==>2;
            递归的规模为n/2，故N/b=n/2 b==>2;
            递归行为外的时间复杂度 merge（） == > O(n) d==>1
            T(N) = 2*T(N/2) + O(N^1)

            如果 log(b,a) < d，复杂度为O(N^d)
            如果 log(b,a) > d，复杂度为O(N^log(b,a))
            如果 log(b,a) == d，复杂度为O(N^d  * logN)
            可知时间复杂度为O(N*logN)
        merge过程需要辅助数组，所以额外空间复杂度为O(N)
        归并排序的实质是把比较行为变成了有序信息并传递，比O(N^2)的排序快
        */

public class MergeSort {
    //排序入口
    public static void mergeSort(int[] arr){
        if (arr==null||arr.length<2) return;
        //让数组在0~N-1范围有序
        process(arr,0,arr.length-1);
    }


    //让一个数组在限定范围内有序
    public static void process(int[] arr,int L,int R){
        //base case 只有一个数了，天然有序
        if (L==R) return;
        //确定中点
        int M = L + ((R-L)>>1) ;
        //左边有序
        process(arr,L,M);
        //右边有序
        process(arr,M+1,R);
        //合并
        merge(arr,L,M,R);
    }

    //合并两边有序的范围，让整体有序
    public static void merge(int[] arr,int L ,int M,int R){
        int pl = L  ;//左边界头指针
        int pr = M+1 ;   //右边界头指针
        int[] help = new int[R-L+1] ;
        int i = 0 ; //help数组的下标
        //双边不越界的情况下
        while (pl <= M && pr <= R){
            help[i++] = arr[pl] <= arr[pr] ? arr[pl++] : arr[pr++];
        }
        //到这里，就将help剩余的数据补全
        while (pl<=M){
            help[i++] = arr[pl++] ;
        }
        while (pr<=R){
            help[i++] = arr[pr++] ;
        }

        //打回去
        for (int j = 0; j < help.length; j++) {
            arr[L+j] = help[j] ;
        }
    }

    // Copy_非递归方法实现
    public static void mergeSort2(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int N = arr.length;
        int mergeSize = 1;
        while (mergeSize < N) {
            int L = 0;
            while (L < N) {
                int M = L + mergeSize - 1;
                if (M >= N) {
                    break;
                }
                int R = Math.min(M + mergeSize, N - 1);
                merge(arr, L, M, R);
                L = R + 1;
            }
            if (mergeSize > N / 2) {
                break;
            }
            mergeSize <<= 1;
        }
    }

    public static void main(String[] args) {
        int[] arr = {1,3,2,5,3} ;
        mergeSort(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }
}
