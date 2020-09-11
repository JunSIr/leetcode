package MyDataTest_03;

/*
在一个数组中，一个数左边比它小的数的总和，叫数的小和，所有数的小和累加起来，叫数组小和。求数组小和。
        例子： [1,3,4,2,5]
        1左边比1小的数：没有
        3左边比3小的数：1
        4左边比4小的数：1、3
        2左边比2小的数：1
        5左边比5小的数：1、3、4、 2
        所以数组的小和为1+1+3+1+1+3+4+2=16
        */

public class MergeTest {
    //排序入口.不变
    public static int getSmallSum(int[] arr){
        if (arr==null||arr.length<2) return 0 ;
        //让数组在0~N-1范围有序
        return process(arr,0,arr.length-1);
    }


    //让一个数组在限定范围内有序,并累加范围内所有小和
    public static int process(int[] arr,int L,int R){
        //base case 只有一个数了，天然有序
        if (L==R) return 0;
        //确定中点
        int M = L + ((R-L)>>1) ;

        return
        process(arr,L,M) //左边小和来
                +process(arr,M+1,R) //右边小和来
                    +merge(arr,L,M,R) ; //左右merge产生小和来


    }

    //合并两边有序的范围，让整体有序
    public static int merge(int[] arr,int L ,int M,int R){
        int pl = L  ;//左边界头指针
        int pr = M+1 ;   //右边界头指针
        int[] help = new int[R-L+1] ;
        int i = 0 ; //help数组的下标
        int res = 0 ; //小和
        //双边不越界的情况下
        while (pl <= M && pr <= R){
            //同时产生小和
            res += arr[pl] < arr[pr] ? arr[pl]*( R - pr + 1) :0 ;
            help[i++] = arr[pl] < arr[pr] ? arr[pl++] : arr[pr++];
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
        return res ;
    }

    public static void main(String[] args) {
        int[] arr = {1,3,4,2,5} ;
        System.out.println(getSmallSum(arr));
    }
}
