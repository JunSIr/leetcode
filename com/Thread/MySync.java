package com.Thread;

import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/*自定义同步器，只需要继承AQS，并实现基本功能即可*/
final class MySync extends AbstractQueuedSynchronizer {

    /*实现获取锁*/
    @Override
    protected boolean tryAcquire(int acquires) {
        if (acquires==1){
            //0代表无线程持有锁，1代表有线程持有锁，此处通过CAS将锁状态边为1
            if (compareAndSetState(0,1)){
                setExclusiveOwnerThread(Thread.currentThread());
                return true ;
            }
        }
        return false ;
    }

    /*实现释放锁*/
    @Override
    protected boolean tryRelease(int acquires) {
        if (acquires == 1){
            //如果锁状态为0，即无线程持有,抛出对象头状态异常
            if (getState()==0) throw new IllegalMonitorStateException();
            //将同步器的持有线程置空
            setExclusiveOwnerThread(null);
            setState(0);
            return true ;
        }
        return false ;
    }


    /*锁是否被占用*/
    @Override
    protected boolean isHeldExclusively() {
        return getState()==1  ;
    }

    /*自己添加一个返回条件变量的方法*/
    protected Condition newCondition(){
        return new ConditionObject();
    }
}

class MyLock implements Lock{
    //一个myLock对象共享一个同步器，故用static
    static MySync sync = new MySync() ;

    @Override
    public void lock() {
        sync.acquire(1);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        //使用同步器的默认实现
        sync.acquireInterruptibly(1);
    }

    @Override
    public boolean tryLock() {
        return sync.tryAcquire(1);
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return sync.tryAcquireNanos(1,unit.toNanos(time));
    }

    @Override
    public void unlock() {
        sync.release(1) ;
    }

    @Override
    public Condition newCondition() {
        //返回AQS的条件变量
        return sync.newCondition();
    }

    /*test*/
    public static void main(String[] args) {
        MyLock lock = new MyLock() ;
        new Thread(()->{
            lock.lock();
            try {
                System.out.println("t1 locking ..."+new Date());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }finally {
                System.out.println("t1 unlocking ..."+new Date());
                lock.unlock();
            }
        }).start();

        new Thread(()->{
            lock.lock();
            try {
                System.out.println("t2 locking ..."+new Date());
            }finally {
                System.out.println("t2 unlocking ..."+new Date());
                lock.unlock();
            }
        }).start();
    }
}


