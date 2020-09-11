package com.Thread;

public class SynWaitNotify {

    /*线程 1 输出 a 5 次，线程 2 输出 b 5 次，线程 3 输出 c 5 次。现在要求输出 abcabcabcabcabc 怎么实现*/

    //决定下一个执行的线程
    private int flag ;
    private int loopNumber ;

    public SynWaitNotify(int flag, int loopNumber) {
        this.flag = flag;
        this.loopNumber = loopNumber;
    }

    public void print(int waitFlag, int nextFlag, String str) throws InterruptedException {

        for (int i = 0; i < loopNumber ; i++) {
            synchronized (this){
                while (this.flag!=waitFlag){
                    this.wait();
                }
                System.out.println(str);
                flag = nextFlag ;
                this.notifyAll();
            }
        }
    }


    public static void main(String[] args) {
        SynWaitNotify syn = new SynWaitNotify(1,5) ;
        new Thread(()->{
            try {
                syn.print(1,2,"a");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(()->{
            try {
                syn.print(2,3,"b");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(()->{
            try {
                syn.print(3,1,"c");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

}
