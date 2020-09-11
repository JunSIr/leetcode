package MyDataTest_02;

import java.util.LinkedList;
import java.util.Queue;

public class TwoQueueToStack {
    public static void main(String[] args) {
        MyStack_From_TwoQueue stack = new MyStack_From_TwoQueue() ;
        stack.push(1);
        stack.push(2);
        stack.push(3);
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.peek());
        System.out.println(stack.pop());

    }


}

class MyStack_From_TwoQueue<T>{

    //一个数据队列，一个辅助队列
    private Queue<T> queue ;
    private Queue<T> help ;

    public MyStack_From_TwoQueue() {
        //使用链表实现
        this.queue = new LinkedList<>();
        this.help = new LinkedList<>();
    }
    //入栈
    public void push(T value){
        //相比add，offer是fast-fail原则
        queue.offer(value);
    }
    //出栈
    public T pop(){
        if (queue.isEmpty()) throw new RuntimeException("已空，勿取") ;

        //数据队列 按序出队 按序入队 数据队列保留一个
        while (queue.size()>1){
            help.offer(queue.poll()) ;
        }
        T ans = queue.poll();
        //辅助队列与数据队列交换
        Queue<T> temp = help ;
        help = queue ;
        queue = temp ;
        return ans ;
    }
    //获取堆顶元素
    public T peek(){

        if (queue.isEmpty()) throw new RuntimeException("已空") ;

        //数据队列 按序出队 按序入队 数据队列保留一个
        while (queue.size()>1){
            help.offer(queue.poll()) ;
        }
        T ans = queue.peek();
        //ans是下一个出栈的元素，追在队尾没问题
        help.offer(queue.poll()) ;
        //辅助队列与数据队列交换
        Queue<T> temp = help ;
        help = queue ;
        queue = temp ;


        return ans ;
    }
}
