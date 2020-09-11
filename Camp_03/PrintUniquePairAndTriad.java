package Camp_03;
/*
* 给定一个有序数组 给定一个正数aim
* 1）返回累加和为aim的所有不同二元组
* 2）返回累加和为aim的所有不同三元组
* */
public class PrintUniquePairAndTriad {

    /*
    * 二元组解法 双指针
    * */
    public static void printUniquePair(int[] arr, int K){

        if (arr == null || arr.length < 2){
            return;
        }
        int L = 0 ;
        int R = arr.length - 1;

        while (L < R){
            if (arr[L] + arr[R] <  K){
                L ++ ;
            }else if (arr[L] + arr[R] > K){
                R -- ;
            }else { // [L]==[R]
                if ( L == 0 || arr[L - 1] != arr[L]){
                    System.out.println(arr[L] + "," + arr[R]);
                }
                L ++ ;
                R -- ;
            }
        }
    }

    //三元组
    public static void printUniqueTriad(int[] arr , int K){
        if (arr == null || arr.length < 3){
            return;
        }
        int N = arr.length;
        for (int i = 0; i < N - 2; i++) { //最后面两个数不用枚举了
            printRest(arr,i,i + 1, N - 1, K - arr[i] );
        }

    }

    public static void printRest(int[] arr ,int F , int L , int R , int K){

        while (L < R){
            if (arr[L] + arr[R] <  K){
                L ++ ;
            }else if (arr[L] + arr[R] > K){
                R -- ;
            }else { // [L]==[R]
                if ( L == F + 1 || arr[L - 1] != arr[L]){
                    System.out.println(arr[F] + "," + arr[L] + "," + arr[R]);
                }
                L ++ ;
                R -- ;
            }
        }

    }
    public static void printArray(int[] arr) {
        for (int i = 0; i != arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int sum = 10;
        int[] arr1 = { -8, -4, -3, 0, 1, 2, 4, 5, 8, 9 };
        printArray(arr1);
        System.out.println("====");
        printUniquePair(arr1, sum);
        System.out.println("====");
        printUniqueTriad(arr1, sum);
        int[] arr = {1,2,3,4} ;
        printUniquePair(arr,5);

    }


}
