package com.Thread;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

public class TestFieldUpdater {

    private volatile int field ;

    public static void main(String[] args) {
    AtomicIntegerFieldUpdater fieldUpdater
    = AtomicIntegerFieldUpdater.newUpdater(TestFieldUpdater.class,"field") ;

    TestFieldUpdater updater = new TestFieldUpdater();
    fieldUpdater.compareAndSet(updater,0,10);

        // 修改成功 field = 10
        System.out.println(updater.field);
        // 修改成功 field = 20
        fieldUpdater.compareAndSet(updater,10,20);
        System.out.println(updater.field);
        // 修改成功 field = 20
        fieldUpdater.compareAndSet(updater,10,30);
        System.out.println(updater.field);

    }
}
