package MyDataTest_04;
/*
*
1）堆结构就是用数组实现的完全二叉树结构
2）完全二叉树中如果每棵子树的最大值都在顶部就是大根堆(大顶堆)
3）完全二叉树中如果每棵子树的最小值都在顶部就是小根堆（小顶堆）
4）堆结构的heapInsert与heapify操作
5）堆结构的增大和减少
6）优先级队列结构，就是堆结构
*/

/*
* 语言提供的堆结构 vs 手写的堆结构
* 取决于，你有没有动态改信息的需求！
* 语言提供的堆结构，如果你动态改数据，不保证依然有序
* 手写堆结构，因为增加了对象的位置表，所以能够满足动态改信息的需求
 */

/*
* 堆中任意节点，其父（index-1）/2   其左子 index*2+1  其右子 index*2+2
**/
public class HeapAndHeapSort {


}
/*大根堆*/
class MaxRootHeap{
    private int[] heap ; //底层数据结构
    private int heapSize ; //堆大小
    private int limit; //限制堆的大小

    public MaxRootHeap(int limit) {
        heap = new int[limit] ;
        heapSize = 0  ;
        this.limit = limit ;
    }

    //将元素添加到大根堆
    public void push(int value) {
        if (heapSize>=limit) throw new RuntimeException("堆满了");
        heap[heapSize] = value ;
        heapInsert(heap,heapSize++);//调整到符合大顶堆的数组位置中去 本条执行完后才会++

    }
    //弹出大根堆最大元素(头结点元素)
    public  int pop(){
        int ans = heap[0];
        swap(heap,0,--heapSize); //--heapSize等于把顶元素交换过去后，不再考虑进堆结构范围内
        heapify(heap,0,heapSize);
        return ans ;
    }

    /*---内部方法---*/

/*    插入某元素到大根堆合适的位置
    index 代表插入位置的下标*/
    private void heapInsert(int[] arr,int index){
        //只要插入元素比父节点大，那就一直往上
      while (arr[index] > arr[(index-1)/2]){
          swap(arr,index,(index-1)/2);//与父节点调换位置
          index= (index-1)/2 ;
      }
    }

    //堆化，用于取出元素保持堆结构
    private void heapify(int[] arr,int index,int heapSize){
        //确定左孩子下标
        int left = index * 2 + 1 ;

        //左孩子不越界的情况下（有左孩子）
        while (left < heapSize){
            /*分两种情况:
            *
            * 1）[index]大
            * 2）左和右任一个比[index]大
            *   1# 存在右子，且右子比左子大
            *   2# 不存在、右子小
            * */
            //先比较左右，谁胜出谁来跟[index比]
            int largest = left + 1 <heapSize && arr[left] < arr[left + 1]? left+1 :left ;
            largest = arr[index]>arr[largest]? index : largest ;

            //如果自己胜出
            if (largest==index) break;

            swap(arr,index,largest);
            left = index * 2 + 1 ;
        }

    }
    private void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }


    /*--------大名鼎鼎堆排序-------
    * 额外空间复杂度为O（1）
    *   1，先让整个数组都变成大根堆结构，建立堆的过程:
        1)从上到下的方法，时间复杂度为O(N*logN)
        2)从下到上的方法，时间复杂度为O(N)
        2，把堆的最大值和堆末尾的值交换，然后减少堆的大小之后，再去调整堆，一直周而复始，时间复杂度为O(N*logN)
        3，堆的大小减小成0之后，排序完成

    * */
    public void heapSort(int[] arr){
        if (arr == null || arr.length < 2) {
            return;
        }

        // 直接把数组转化为大根堆，时间复杂度O(N*logN)
/*		for (int i = 0; i < arr.length; i++) { // O(N)
			heapInsert(arr, i); // O(logN)
		}*/

        /*
         *优化：-->自底向顶heapify转化为大根堆
         * 可将时间复杂度优化为O（n）
         * 区别就是需要 批量处理 而不是一个个数处理
         * */
        for (int i= arr.length-1;i>=0;i--){
            heapify(arr,i,arr.length);
        }
        heapSize = arr.length ;


        /*最终堆结构完全转化成有序数组
        * 头尾交换，尾巴不考虑进大根堆
        * 这一步时间复杂度为O（NlogN）
        * */
        while (heapSize>0){
            swap(arr,0,--heapSize);
            heapify(arr,0,heapSize);
        }
    }

    public static void main(String[] args) {
        int[] arr=  {3,1,2,5,4,7,6} ;
        MaxRootHeap heap =new MaxRootHeap(10) ;
/*        heap.push(4);
        heap.push(3);
        heap.push(33);
        heap.push(5);
        System.out.println(heap.pop());
        System.out.println(heap.pop());
        System.out.println(heap.pop());
        System.out.println(heap.pop());*/
        heap.heapSort(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }
}