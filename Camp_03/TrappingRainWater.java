package Camp_03;
/*
 * 水池蓄水问题
 * 给定一个正整数数组arr，把arr想象成一个直方图。返回这个直方图如果装水，能装下几格水？
 */
public class TrappingRainWater {

    // O(n) 双指针
    public static int water1(int[] arr){

        if (arr == null || arr.length < 2){
            return  0  ;
        }

        int N = arr.length ;
        int L = 1 ;
        int R = N - 2;
        int leftMax = arr[0] ;
        int rightMax = arr[N - 1]  ;
        int water = 0  ;

        while (L <= R) {
            if (leftMax <= rightMax){ //左指针动
                water += Math.max(0, leftMax - arr[L]) ;
                //更新leftMax
                leftMax = Math.max(leftMax, arr[L++]) ;
            }else {  //右指针动
                water += Math.max(0 , rightMax - arr[R]) ;
                rightMax = Math.max(rightMax, arr[R--]) ;
            }

        }
        return water ;
    }

    public static void main(String[] args) {
        int[] arr = {0,1,0,2,1,0,1,3,2,1,2,1 } ;
        System.out.println(water1(arr));
    }
}
