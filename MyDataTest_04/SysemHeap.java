package MyDataTest_04;



import java.util.PriorityQueue;
//系统堆
/*
* 语言提供的堆结构 vs 手写的堆结构
取决于，你有没有动态改信息的需求！
语言提供的堆结构，如果你动态改数据，不保证依然有序
手写堆结构，因为增加了对象的位置表，所以能够满足动态改信息的需求

 * */
public class SysemHeap {
    /* 应用:已知一个几乎有序的数组。
     *几乎有序是指，如果把数组排好顺序的话，每个元素移动的距离一定不超过k，
     * 并且k相对于数组长度来说是比较小的。
     * 请选择一个合适的排序策略，对这个数组进行排序。*/
    public void sortedArrDistanceLessK(int[] arr, int k) {
        //优先级队列的底层实现为堆，默认是小根堆
        PriorityQueue<Integer> heap = new PriorityQueue<Integer>() ;

        int index = 0 ;
        //把前K+1个数放入小根堆
        for (; index <=Math.min(arr.length-1,k)  ; index++) {
            heap.add(arr[index]) ;
        }
        int i =0 ;
        for ( ; index <arr.length ; i++,index++) {
            arr[i] = heap.poll();
            heap.add(arr[index]);
        }

        //剩余数批量入
        while (!heap.isEmpty()){
            arr[i++]  =  heap.poll() ;
        }

    }

    public static void main(String[] args) {
        PriorityQueue<Integer> heap = new PriorityQueue<>();

        heap.add(8);
        heap.add(4);
        heap.add(4);
        heap.add(9);
        heap.add(10);
        heap.add(3);

        while (!heap.isEmpty()) {
            System.out.println(heap.poll());
        }
    }



}
