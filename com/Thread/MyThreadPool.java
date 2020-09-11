package com.Thread;



import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

//步骤一：自定义拒绝策略接口
@FunctionalInterface  //函数式接口
interface RejectPolicy<T>{
    void reject(BlockingQueue<T> queue, T task) ;
}

//自定义阻塞队列
class BlockingQueue<T>{
    //任务队列
    private Deque<T> queue = new ArrayDeque<>() ; //双向队列：对头、队尾都可操作
    //锁，队列的头部元素获取与尾部元素添加都需要在锁中完成
    private ReentrantLock lock = new ReentrantLock();
    //生产者条件变量（生产者的等待队列），在任务队列满的时候，添加任务的线程进入等待
    private Condition fullWaitSet = lock.newCondition() ;
    //消费者条件变量，在任务任务队列空的时候，线程不再集合拉取任务
    private Condition emptyWaitSet = lock.newCondition();
    // 队列容量
    private int capcity;
    //构造器，只需要调用者给出容量即可
    public BlockingQueue(int capcity) {
        this.capcity = capcity;
    }

    //poll->返回任务队列头部的元素给消费者（线程集合）
    // 超时机制版的阻塞获取，一定时间内无任务则不再进行获取
    public T poll(long timeout, TimeUnit unit) {

        lock.lock(); //获取操作需要同步

        try {
        //将 timeout 统一转换为 纳秒
        long nanos = unit.toNanos(timeout);

        //任务队列中已经空了（无任务）
        while (queue.isEmpty()) {
            try {
                //等待时间超时，返回空值
                if (nanos <= 0) {
                    return null;
                }
                // 队列空的等待操作
                nanos = emptyWaitSet.awaitNanos(nanos);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //任务队列不空，返回队列头部元素
        T t = queue.removeFirst();
        //通知向队列添加任务的线程，可以继续添加任务
        fullWaitSet.signal();
        return t;
        }
        finally {
            lock.unlock(); //解除锁
        }
    }

    //普通版阻塞队列获取任务
    public T take(){
        lock.unlock();
        try {
            while (queue.isEmpty()){
                try {
                    emptyWaitSet.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            T t = queue.removeFirst();
            fullWaitSet.signal();
            return t ;
        }finally {
            lock.unlock();
        }
    }

    //阻塞队列，任务的添加
    public void put(T task){
        lock.lock();  //添加需要上锁
        try {
            //队列满
            while (queue.size()==capcity) {
                try {
                    //进入等待，等待可以放入任务的允许通知
                    System.out.println("等待加入任务队列"+task);
                    fullWaitSet.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("加入任务队列"+task);
            queue.addLast(task); //向队列尾部添加任务
            emptyWaitSet.signal();//告诉消费任务的线程，可以消费了
        }finally {
            lock.unlock(); //解锁
        }
    }

    //超时机制版阻塞队列，任务的添加
    public boolean offer(T task,long timeout,TimeUnit unit){
        lock.lock();
        try {
            long nanos = unit.toNanos(timeout);
            //队列满
            while (queue.size()==capcity) {
                try {
                    if(nanos <= 0) {
                        return false;
                    }
                    //等待上面的通知就完事
                    System.out.println("等待加入任务队列"+task);
                     nanos = fullWaitSet.awaitNanos(nanos);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("加入任务队列"+task);
            queue.addLast(task);
            emptyWaitSet.signal();//广播 队列不为空了，唤醒等待队列不为空的线程（在上面）
            return true;
        }finally {
            lock.unlock();
        }
    }
    //获取任务队列大小
    public int size() {
        lock.lock();
        try {
            return queue.size();
        } finally {
            lock.unlock();
        }
    }

    //带拒绝策略的put，即如果任务队列无法添加元素，使用何种策略进行拒绝添加
    public void tryPut(RejectPolicy<T> rejectPolicy,T task){
        lock.lock();
        try {
            //判断队列是否满，如满，执行拒绝策略 --> 策略模式
            if (queue.size() == capcity) rejectPolicy.reject(this,task);
        else {
                System.out.println("加入任务队列"+task);
                queue.addLast(task);
                emptyWaitSet.signal();//广播 队列不为空了，唤醒等待队列不为空的线程（在上面）
            }
        }finally {
            lock.unlock();
        }
    }

}

//自定义线程池
class ThreadPool{
    public ThreadPool(int coreSize, long timeout, TimeUnit timeUnit, int queueCapcity,RejectPolicy<Runnable> rejectPolicy) {
        this.coreSize = coreSize;
        this.timeout = timeout;
        this.timeUnit = timeUnit;
        this.rejectPolicy = rejectPolicy;
        this.taskQueue = new BlockingQueue<>(queueCapcity) ;
    }

    //单个线程的封装，使用Worker对线程进行了扩展
    class Worker extends Thread{
        private Runnable task ;

        public Worker(Runnable task) {
            this.task = task;
        }
        public void run(){
            // 执行任务
            // 1) 当 task 不为空，执行任务
            // 2) 当 task 执行完毕，再接着从任务队列获取任务并执行
            while (task!=null||(task=taskQueue.poll(timeout,timeUnit))!=null){
                try {
                    System.out.println("正在执行"+task);
                    task.run();
                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    task = null ;
                }
            }
            synchronized (workers){
                System.out.println("worker被移除"+this);
                workers.remove(this);
            }
        }
    }
    //任务队列
    private BlockingQueue<Runnable> taskQueue ;
    //线程集合，集合中使用Worker对线程进行了扩展
    private HashSet<Worker> workers = new HashSet<>();
    //核心线程数
    private int coreSize ;
    //获取任务时的超时时间
    private long timeout ;
    //超时时间的单位规范
    private TimeUnit timeUnit ;
    //拒绝策略，由主线程自己决定，主线程取实现这个RejectPolicy，并传入本线程池即可
    private RejectPolicy<Runnable> rejectPolicy ;

    //执行任务
    public void execute(Runnable task){
        // 当任务数没有超过 coreSize 时，直接交给 worker 对象执行，注意，Work执行完毕后不能立即消失，要被循环利用
        // 如果任务数超过 coreSize 时，加入任务队列暂存
        synchronized (workers){

            //如果线程集合中的线程数量少于核心线程数，创建Work对象来执行任务
            if (workers.size()<coreSize){
                Worker worker = new Worker(task);
                System.out.println("新增worker"+worker+"task"+task);
                workers.add(worker) ;
                worker.start();
            }else {
                // 各种拒绝策略
                // 1) 死等
                // 2) 带超时等待
                // 3) 让调用者放弃任务执行
                // 4) 让调用者抛出异常
                // 5) 让调用者自己执行任务

                //加入任务队列，等待被执行
                taskQueue.tryPut(rejectPolicy,task);
            }
        }
    }
}


public class MyThreadPool {
    public static void main(String[] args) {
        ThreadPool threadPool = new ThreadPool(1,
                1000, TimeUnit.MILLISECONDS, 1, (queue, task)->{
            // 1. 死等
// queue.put(task);
            // 2) 带超时等待
// queue.offer(task, 1500, TimeUnit.MILLISECONDS);
            // 3) 让调用者放弃任务执行
// log.debug("放弃{}", task);
            // 4) 让调用者抛出异常
// throw new RuntimeException("任务执行失败 " + task);
            // 5) 让调用者自己执行任务
            task.run();
        });
        for (int i = 0; i < 4; i++) {
            int j = i;
            threadPool.execute(() -> {
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(j); //执行打印j
            });
        }
    }
}
