package MyDataTest_02;

//双向链表数据结构
 class dNode<T> {
    public T value;
    public dNode<T> last;
    public dNode<T> next;

    public dNode(T data) {
        value = data;
    }
}
/*自定义双向队列，底层双向链表*/
public class DoubleEndQueue<T> {


     //双向队列底层是链表，维护两端节点，头节点与尾结点
     dNode<T> head = null ;
     dNode<T> tail = null ;

     //从双向队列头部进队
    public void  addFromHead(T value){
        //用户传进来的值转化为链表节点
        dNode<T> cur = new dNode<>(value) ;
        //如果是空队列
        if (this.head==null){
            this.head = cur ;
            this.tail = cur ;
        }else {
            //如果非空，入队并更新头尾指针
            this.head.last = cur ;
            cur.next = this.head ;
            head = cur ;
        }
    }

    //从双向队列尾部进队
    public void  addFromTail(T value){
        //用户传进来的值转化为链表节点
        dNode<T> cur = new dNode<>(value) ;
        //如果是空队列
        if (this.head==null){
            this.head = cur ;
            this.tail = cur ;
        }else {
            //如果非空，入队并更新头尾指针
            this.tail.next = cur ;
            cur.last = this.tail ;
            this.tail = cur ;
        }

    }
    //从双向队列头部出队
    public  T  popFromHead(){
        //判空
        if (this.head==null) return null ;
        dNode cur =  this.head ;
        //如果只有一个节点
        if (head==tail){
            head = null ;
            tail = null ;
        }
        this.head.next.last = null ;
        this.head = this.head.next  ;

        return (T) cur.value;
    }
    //从双向队列尾部出队
    public  T  popFromTail(){
        //判空
        if (this.head==null) return null ;
        dNode cur =  this.tail ;
        //如果只有一个节点
        if (head==tail){
            head = null ;
            tail = null ;
        }
        //重连
        tail = tail.last ;
        tail.next = null ;

        return (T) cur.value;
    }

    public boolean isEmpty(){
        if (head!=null)  return false ;
        return true ;
    }

    //test
    public static void main(String[] args) {
        DoubleEndQueue<Integer> queue = new DoubleEndQueue<>();
        //头入队
        queue.addFromHead(1);
        queue.addFromHead(2);
        queue.addFromTail(3);


        Integer x = queue.popFromTail();
        System.out.println(x);
        System.out.println(queue.tail.value);
        System.out.println(queue.head.value);

    }
}

/*自定义单向队列，头进尾出*/
class OneEndQueue<T>{
    //对双向队列限制即可
    private DoubleEndQueue<T> queue ;
    //默认构造器
    public OneEndQueue(){
        queue  =new DoubleEndQueue<T>();
    }

    //从双向队列头部进队
    public void  push(T value){
        queue.addFromHead(value); ;
    }

    //从双向队列尾部出队
    public  T  poll(){
        return (T)queue.popFromTail() ;
    }

    public boolean isEmpty(){
        return queue.isEmpty() ;
    }

    //test
    public static void main(String[] args) {
        OneEndQueue<Integer> queue = new OneEndQueue<>();
        //头入队
        queue.push(1);
        queue.push(2);
        queue.push(3);


        Integer x = queue.poll();
        System.out.println(x);
        Integer y = queue.poll();
        System.out.println(y);
        System.out.println(queue.isEmpty());

    }
}

/*自定义栈,还是以双向链表为基础*/
class MyStack<T>{
    private DoubleEndQueue<T> queue;
    public MyStack(){
        queue = new DoubleEndQueue<>();
    }
    //弹出
    public T pop(){

        return queue.popFromHead() ;
    }
    //入栈
    public void push(T t){
        queue.addFromHead(t);
    }
    //判空
    public boolean isEmpty(){
        return queue.isEmpty() ;
    }
    //test
    public static void main(String[] args) {
        MyStack<Integer> stack = new MyStack<>() ;
        stack.push(1);
        stack.push(2);
        stack.push(3);
        System.out.println(stack.pop());
        System.out.println(stack.pop());
    }
}