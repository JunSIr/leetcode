package MyDataTest_01;

/*
认识二分法：核心->正确构建两边淘汰策略
1) 在一个有序数组中，找某个数是否存在
2) 在一个有序数组中，找>=某个数最左侧的位置
3) 在一个有序数组中，找<=某个数最右侧的位置
4) 局部最小值问题
*/
public class BinarySearch {
    public static void main(String[] args) {
//        int[] arr = {1,2,3,4,5} ;
        int[] arr = {0,1,2,1,3,-1} ;
        System.out.println("minindex="+regionMin(arr));
    }

    public static boolean bsExist(int[] arr,int num) {
        //判空
        if (arr==null||arr.length<1) return true ;

        //初始化边界条件
        int left = 0 ;
        int right = arr.length-1 ;
        int mid = 0 ;

        while (left<right){
            //确定中点，后续逻辑调整左右边界
            mid = left+((right-left)>>2) ;
            if (arr[mid]==num){
                return true ;
            }else if (arr[mid]<num){
                //重新调整左边界
                left = mid+1 ;
            }else {
                //重新调整有边界
                right = mid -1  ;
            }
        }
        //循环结束，left = Right
        return num==arr[left] ;
    }

    // 在arr上，找满足<=value的最右位置
    public static int nearestIndex(int[] arr, int value) {


        int L = 0;
        int R = arr.length - 1;
        int mid = 0  ;
        int index = -1; // 记录最右的下标

        //判空
        if (arr==null||arr.length<1) return index ;

        /*<=R是有说法的*/
        while (L<=R){
            //重新确定中点
            mid = L + ((R-L)>>2) ;
            if (arr[mid]<=value){
                //调整左边界
                L = mid + 1  ;
                index = mid ;
            }else {
                R = mid-1 ;
            }
        }
        return index;
    }


    /*局部最小问题，无需有序，相邻不等
    * 局部最小问题:
    * 1):第一个数小于第二个数，第一个数为局小
    * 2）:最后一个数小于倒数第二个数，最后一个数是局小
    * 3）:中间任意数，同时小于左右两边，中间数为局小
    * */
    public static int regionMin(int[] arr){
        //判空
        if (arr==null||arr.length<1) return -1 ;

        /*初始化边界条件与二分中点*/
        int left = 0 ;
        int right = arr.length-1 ;
        int mid = 0 ;
        //判断第一个是不是局小
        if (arr[0]<arr[1]) return 0 ;
        //判断第二个是不是局小
        if (arr[arr.length-1]<arr[arr.length-2]) return arr.length-1 ;
        //判断中点的两边，最终（极端情况下）中点会收敛于一点,此点必为局小
        while (left<right){
            //确定中点位置
            mid = left + ((right-left)>>2) ;
            //中点位置的值同时小于左右两边
            if (arr[mid]<arr[mid-1]&&arr[mid]<arr[mid+1]) return mid ;
            //中点位置小于左大于右
            if (arr[mid]<arr[mid-1]&&arr[mid]>arr[mid+1]){
                //调整左边界
                left = mid+1 ;
            }
            //中点位置大于左小于右
            if (arr[mid]>arr[mid-1]&&arr[mid]<arr[mid+1]) right = mid-1 ;

            //中点位置同时大于两边
            right = mid-1 ;
        }
        return left ;  //这个无所谓的
    }
}
