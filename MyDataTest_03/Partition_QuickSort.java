package MyDataTest_03;

public class Partition_QuickSort {

    /*
    给定一个数组arr，和一个整数num。请把小于等于num的数放在数组的左边，大于num的数放在数组的右边。
    返回等于元素的下标
    要求额外空间复杂度O(1)，时间复杂度O(N) */

    public static int partition(int[] arr,int L,int R ){
        if (arr==null||arr.length==0) return -1 ;
        if (arr.length<2) return 0 ;

        int lessEqual = L-1 ; //初始左边界 <=
//        int more = R ;  //初始右边界
        int index = L ; //遍历_下标

       //Index走到越界，退出循环
        while(index<R){

            //只要小于等于，扩充左边界，直至越界
            if (arr[index]<=arr[R]){
                swap(arr,index,++lessEqual);
            }//其余情况，index往后走
            index++ ;
            }
        //把R放到它应该在的位置
        swap(arr,R,++lessEqual);

            return lessEqual ;
        }


    /*荷兰国旗子问题_划分
    * 以arr[R]为划分值，在限定范围内，<arr[R]的在左边，=arr[R]的在中间，>arr[R]的在右边
    * 以数组形式返回等于区左右边界
    * */
    public static int[] netherlandsFlag(int[] arr,int L ,int R){

        if (arr==null||arr.length==0) return new int[]{-1,-1} ;
        if (arr.length<2) return new int[]{L,L} ;

        int less = L-1 ; //初始左边界
        int more = R ;  //初始右边界
        int index = L ; //遍历_下标

        /*tips:index与more不管中哪个分支，其中一个必移动，最终相遇 */
        while(index<more){

            if (arr[index]>arr[R]){
                swap(arr,--more,index);//与大于区左边开始第一个数交换,i不变
            }else if (arr[index]<arr[R]){
                swap(arr,++less,index++);//小于的数与左边界右第一个数呼唤，并扩大左边界
            }else {
                //等于的情况
                index++ ; //不对边界做任何调整，使得==的值永远夹在中间
            }
        }
        //把R位置的数，与大于区边界位置交换即可
        swap(arr,R,more);
        //返回最终结果
        return new int[] {less+1,more};
    }

    //通用_根据下标交换数组位置
    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    /*
    * 快速排序，基于一次partition可以确定一个数在有序数组中的位置
    * 所以递归让每个数有序即可
    * */
    public static void QuickSort_01(int[] arr) {
        if (arr.length==0||arr==null) return;
        process_01(arr,0,arr.length-1); //让0~N有序
    }

    public static void process_01(int[] arr,int L,int R) {
        //base case
        if (L>=R) return;

        int last = partition(arr, L, R); //让arr[R]的位置固定,返回下标

        process_01(arr,L,last-1); //递归确定左边所有数的有序位置
        process_01(arr,last+1,R); //递归确定右边所有数的有序位置

    }
    /*快速排序2.0
    * 一次确定一批数，利用荷兰问题
    * */
    public static void QuickSort_02(int[] arr) {
        if (arr.length==0||arr==null) return;
        process_02(arr,0,arr.length-1); //让0~N有序
    }

    public static void process_02(int[] arr,int L,int R) {
        //base case
        if (L>=R) return;

        int[] equal_indexs = netherlandsFlag(arr, L, R);//找到相等的一批数的固定位置

        process_01(arr,L,equal_indexs[0]-1); //递归确定左边所有数的有序位置
        process_01(arr,equal_indexs[1]+1,R); //递归确定右边所有数的有序位置

    }

    /*快速排序3.0
     * 随机快排，任选一个数作为arr[R]
     * 理想条件下，选中的数为中点位置，此时根据masters公式，O（N）= NlogN，尽管这是个概率问题，但对所有可能
     * 出现的位置的概率求期望，得到的就是NlogN
     * */
    public static void QuickSort_03(int[] arr) {
        if (arr.length==0||arr==null) return;
        process_03(arr,0,arr.length-1); //让0~N有序
    }

    public static void process_03(int[] arr,int L,int R) {
        //base case
        if (L>=R) return;
        swap(arr, L + (int) (Math.random() * (R - L + 1)), R);//随机选一个扔到R位置
        int[] equal_indexs = netherlandsFlag(arr, L, R);//找到相等的一批数的固定位置

        process_01(arr,L,equal_indexs[0]-1); //递归确定左边所有数的有序位置
        process_01(arr,equal_indexs[1]+1,R); //递归确定右边所有数的有序位置

    }


    public static void main(String[] args) {
        int[] arr = {4,5,3,1,3} ;
//        int[] flag = netherlandsFlag(arr, 0, arr.length - 1);

/*        for (int i = 0; i < flag.length; i++) {
            System.out.println(flag[i]);
        }*/


/*        int partition = partition(arr, 0, arr.length - 1);

                for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);*/
        QuickSort_02(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
        }
    }

