package MyDataTest_02;

import java.util.Stack;

public class TwoStackToQueue {
    public static void main(String[] args) {
        MyQueue_From_TwoStack queue = new MyQueue_From_TwoStack() ;
        queue.add(1);
        queue.add(2);
        queue.add(3);
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.peek());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
    }
}
/*
*如何用栈结构实现队列结构
*原则1）：入口栈数据转移到出口栈，需要保证出口栈为空
*原则2）：数据转移需要一次性转移完毕
 * */
class MyQueue_From_TwoStack{
    private Stack<Integer> popStack ; //出口栈
    private Stack<Integer> pushStack  ; //入口栈

    public MyQueue_From_TwoStack(){
        popStack = new Stack<>() ;
        pushStack = new Stack<>() ;
    }

    //入队
    public void add(int value ){
        //从入口栈栈入栈
        pushStack.push(value) ;
        pushToPop();
    }
    //出队
    public int poll(){
        if (popStack.isEmpty()) throw new RuntimeException("已空，勿取") ;
        Integer popValue = popStack.pop();
        //如果队列被拿空了，则下面函数执行有效
        pushToPop();
        return popValue ;
    }

    //数据转移
    private void pushToPop(){
        //原则一
        if (popStack.isEmpty()){
            //原则二
            while (!pushStack.isEmpty()){
                popStack.push(pushStack.pop());
            }
        }

    }

    //获取首元素但不出队
    public int peek() {
        if (popStack.empty() && pushStack.empty()) {
            throw new RuntimeException("Queue is empty!");
        }
        pushToPop();
        return popStack.peek();
    }
}