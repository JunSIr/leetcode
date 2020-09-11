package MyDataTest_01;

import java.util.Arrays;

public class SeletionSort {
    /*  选择排序
    arr[0～N-1]范围上，找到最小值所在的位置，然后把最小值交换到0位置。
    arr[1～N-1]范围上，找到最小值所在的位置，然后把最小值交换到1位置。
    arr[2～N-1]范围上，找到最小值所在的位置，然后把最小值交换到2位置。
    */

    public static void main(String[] args) {
        int [] arr = {1,4,3,2,5,7,6} ;
        //seletionSort(arr);
        standardSeletionSort(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }

    public static void seletionSort(int [] arr){
        //莫忘判空
        if (arr.length<2|| arr==null) return;
        //first foreach
        for (int i = 0; i < arr.length; i++) {

            int minValue = Integer.MAX_VALUE ;
            //second foreach
            for (int j = i; j < arr.length; j++) {
                if (arr[j]<minValue) {
                    minValue = arr[j] ; //reflush minValue
                    swap(arr,i,j); //swap j -> i
                }
            }
        }
    }

    public static void standardSeletionSort(int [] arr){
        //莫忘判空
        if (arr.length<2    || arr==null) return;

        //first foreach :不用跑到最后一个数，在倒数第二次选最小就可以完成这个操作，不过，即使跑，也没问题
        //0~n-1  1~n-1  ... 找最小
        for (int i = 0; i < arr.length-1; i++) {

            int minIndex = i ; //为了减少交换频度，提高效率,采用最小的下标做标记
            //second foreach
            for (int j = i+1; j < arr.length; j++) {
                //find the minIndex
                minIndex = arr[j]<arr[minIndex]?j:minIndex ;
            }
            swap(arr,i,minIndex);
        }
    }

    public static void swap(int[] arr,int i,int j){
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
