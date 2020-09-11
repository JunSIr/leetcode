package com.Thread;

import java.util.concurrent.atomic.AtomicReference;

public class TestABA {
    static AtomicReference<String> ref = new AtomicReference<>("A");

    public static void main(String[] args) throws InterruptedException {
        System.out.println("主线程开始");
        String prev = ref.get() ;
        AtoBtoA();
        Thread.sleep(1000);
        //change ->C

        boolean c = ref.compareAndSet(prev, "C");
        System.out.println("change A->C"+c);
    }

    private static void AtoBtoA() throws InterruptedException {

        new Thread(()->{
            String prev = ref.get();
            boolean b = ref.compareAndSet(prev, "B");
            System.out.println("change A->B"+b);
        }).start();
        Thread.sleep(500);

        new Thread(()->{
            String prev = ref.get();
            boolean a = ref.compareAndSet(prev, "A");
            System.out.println("change B->A"+a);
        }).start();
    }
}
