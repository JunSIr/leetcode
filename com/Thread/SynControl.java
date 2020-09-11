package com.Thread;

import sun.security.ssl.SSLContextImpl;

import java.util.concurrent.locks.LockSupport;

public class SynControl {
    //锁对象
    public static Object obj = new Object();

    //表示t2是否被运行过，为true才允许t1执行
    public static boolean t2round = false ;

    public static void main(String[] args) {
        //waitandnotify();
        //parkandunpark();
    }

    public static void waitandnotify() {

        new Thread(()->{
            synchronized (obj){
               while (!t2round){
                   //如果t2round还未批准t1，则等待
                   try {
                       obj.wait();
                   } catch (InterruptedException e) {
                       e.printStackTrace();
                   }
               }
                System.out.println("t1执行over");
            }

        },"t1").start();

        new Thread(()->{
            synchronized (obj){
                t2round = true ;
                obj.notifyAll(); //唤醒t1
            }

            System.out.println("t2执行over");
        },"t2").start();

    }

    public static void parkandunpark(){
        //注意:private LockSupport() {} // Cannot be instantiated.
        // LockSupport lock = new LockSupport() ;

        Thread t1 = new Thread(()->{

            //当没有『许可』时，当前线程暂停运行；有『许可』时，用掉这个『许可』，当前线程恢复运行
            LockSupport.park();
            System.out.println("t1执行-over");

        });

        Thread t2 =new Thread(()->{
            t2round = true ;
            // 给线程 t1 发放『许可』（多次连续调用 unpark 只会发放一个『许可』）
            LockSupport.unpark(t1);
            System.out.println("t2执行-over");
        },"t2");

        t2.start();
        t1.start();
    }

}

