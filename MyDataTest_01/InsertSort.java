package MyDataTest_01;

public class InsertSort {
/*
    想让arr[0~0]上有序，这个范围只有一个数，当然是有序的。
    想让arr[0~1]上有序，所以从arr[1]开始往前看，如果arr[1]<arr[0]，就交换。否则什么也不做。
            …
    想让arr[0~i]上有序，所以从arr[i]开始往前看，arr[i]这个数不停向左移动，一直移动到左边的数字不再比自己大，停止移动。
    最后一步，想让arr[0~N-1]上有序， arr[N-1]这个数不停向左移动，一直移动到左边的数字不再比自己大，停止移动。
*/

    public static void main(String[] args) {
        int[] arr = {2,1,4,3,5} ;
        insertSort(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }
    public static void insertSort(int[] arr){
        if (arr == null || arr.length < 2) {
            return;
        }

        for (int i = 1; i <= arr.length-1; i++) {
            for (int j = i; j > 0 ; j--) {
                if (arr[j]<arr[j-1]) swap(arr,j,j-1);
            }
        }
    }

    // i和j是一个位置的话，会出错
    public static void swap(int[] arr, int i, int j) {
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }
}
