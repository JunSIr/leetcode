package MyDataTest_02;
import java.util.Stack;

/*
实现一个特殊的栈，在基本功能的基础上，再实现返回栈中最小元素的功能
1）pop、push、getMin操作的时间复杂度都是 O(1)。
2）设计的栈类型可以使用现成的栈结构。
*/
public class GetMinStack {
    public static void main(String[] args) {
        StackGetMinEnable_02 stack = new StackGetMinEnable_02() ;
        System.out.println("空否："+stack.isEmpty());
        stack.push(1);
        stack.push(2);
        stack.push(3);
        System.out.println("最小值为"+stack.getMin());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());

        System.out.println(stack.getMin());
    }
}
/*非同步*/
class StackGetMinEnable_01{

    Stack<Integer> dataStack = new Stack() ; //数据栈
    Stack<Integer> minStack = new Stack() ; //最小栈

    //入栈
    public void push(int value){
        //第一个元素入栈
        if (minStack.isEmpty()) {
            minStack.push(value) ;
            //否则，判断是否最小值
        } else if(value<=minStack.peek()){
            minStack.push(value) ;  //最小值同步进栈
        }
        dataStack.push(value) ;
    }

    //出栈
    public int pop(){
        //判空
        if (this.isEmpty()) throw new RuntimeException("已空") ;

        Integer i = dataStack.pop();
        //如果是最小值，同步出栈
        if (i==this.getMin()) minStack.pop() ;
        return i ;
    }

    //获取最小值
    public int getMin(){
        if (minStack.isEmpty()) throw new RuntimeException("栈是空的") ;
        return minStack.peek() ;
    }

    //内部方法，判空
    public boolean isEmpty(){
        return dataStack.isEmpty() ;
    }


}

/*同步*/
class StackGetMinEnable_02{

    Stack<Integer> dataStack = new Stack() ; //数据栈
    Stack<Integer> minStack = new Stack() ; //最小栈

    //入栈
    public void push(int value){
        //第一个元素入栈
        if (minStack.isEmpty()) {
            minStack.push(value) ;
            //否则，判断是否最小值
        } else if(value<=minStack.peek()){
            minStack.push(value) ;  //最小值同步进栈
        }else {
            //如果不是最小值，最小栈复制一份栈顶元素，重复入最小栈
            int minValue = minStack.peek() ;
            minStack.push(minValue) ;
        }
        dataStack.push(value) ;
    }

    //出栈
    public int pop(){
        //判空
        if (this.isEmpty()) throw new RuntimeException("已空") ;
        //同步出栈
        minStack.pop();
        return dataStack.pop() ;
    }

    //获取最小值
    public int getMin(){
        if (minStack.isEmpty()) throw new RuntimeException("栈是空的") ;
        return minStack.peek() ;
    }

    //内部方法，判空
    public boolean isEmpty(){
        return dataStack.isEmpty() ;
    }


}