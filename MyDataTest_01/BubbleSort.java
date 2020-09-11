package MyDataTest_01;

public class BubbleSort {
/*
    在arr[0～N-1]范围上：
    arr[0]和arr[1]，谁大谁来到1位置；arr[1]和arr[2]，谁大谁来到2位置…arr[N-2]和arr[N-1]，谁大谁来到N-1位置

    在arr[0～N-2]范围上，重复上面的过程，但最后一步是arr[N-3]和arr[N-2]，谁大谁来到N-2位置
    在arr[0～N-3]范围上，重复上面的过程，但最后一步是arr[N-4]和arr[N-3]，谁大谁来到N-3位置
…
    最后在arr[0～1]范围上，重复上面的过程，但最后一步是arr[0]和arr[1]，谁大谁来到1位置
*/


    public static void main(String[] args) {
        int[] arr = {3,4,1,4,3};
        bubbleSort(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }


    public static void bubbleSort(int [] arr) {

        for (int i = arr.length - 1; i >= 0 ; i--) {
            for (int j = 1; j <= i ; j++) {
                if (arr[j-1]<arr[j]){
                    swap(arr,j-1,j);
                }
            }
        }
    }

    public static void swap(int[] arr, int i, int j) {
        /*很骚的写法，不用深究，一个位运算而已*/
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }
}
