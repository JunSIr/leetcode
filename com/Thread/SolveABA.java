package com.Thread;

import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

public class SolveABA {
    static AtomicStampedReference<String> ref = new AtomicStampedReference<>("A",0);

    public static void main(String[] args) throws InterruptedException {
        System.out.println("主线程开始");
        String prev = ref.getReference() ;
        //获取版本号
        int stamp = ref.getStamp();
        System.out.println("主版本号为"+stamp);
        AtoBtoA();
        Thread.sleep(1000);

        boolean c = ref.compareAndSet(prev, "C", stamp, stamp + 1);
        //change ->C
        System.out.println("change A->C"+c);
    }

    private static void AtoBtoA() throws InterruptedException {

        new Thread(()->{
            String prev = ref.getReference();
            boolean b = ref.compareAndSet(prev, "B", ref.getStamp(), ref.getStamp() + 1);
            System.out.println("change A->B"+b);
        }).start();
        Thread.sleep(500);
        new Thread(()->{
            String prev = ref.getReference();
            boolean a = ref.compareAndSet(prev, "A", ref.getStamp(), ref.getStamp() + 1);
            System.out.println("change B->A"+a);
        }).start();
    }
}
