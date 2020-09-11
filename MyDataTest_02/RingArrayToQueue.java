package MyDataTest_02;
/*环形数组成队列*/
public class RingArrayToQueue {

    //test
    public static void main(String[] args) throws Exception {
        MyQueue_From_Array queue = new MyQueue_From_Array(3) ;
        queue.push(1);
        queue.push(2);
        queue.push(3);
        queue.push(3);
    }

}

class MyQueue_From_Array{


    private  int[] arr ; //底层数据结构
    private final int limit ; //指定大小，固定的
    private int size ; //实际大小
    private int pushi ; //入队下标
    private int polli ; //出队下标

    public MyQueue_From_Array( int limit) {
        this.arr = new int[limit];
        this.limit = limit;
        this.size = 0;
        this.pushi = 0;
        this.polli = 0;
    }
    //入队
    public void push(int value) throws Exception {
        //判断是否越界
        if (size>=limit) throw new Exception("已满，勿放") ;

        size++ ;
        arr[pushi] = value ;

        //pushi更新到下一位
        if (pushi<limit-1){
            pushi++ ;
        } else {
            pushi = 0 ;  //环形数组的体现
        }
    }
    //出队
    public int poll() throws Exception {

        if (size==0) throw new Exception("已空，勿取") ;

        size-- ;
        int ans = arr[polli];

        if (polli<limit-1){
            polli++ ;
        } else {
            polli = 0 ;  //环形数组的体现
        }

        return ans ;
    }


}