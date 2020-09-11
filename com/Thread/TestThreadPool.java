package com.Thread;


import java.util.Date;
import java.util.concurrent.*;

import static java.lang.Thread.sleep;


public class TestThreadPool {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ExecutorService pool = Executors.newFixedThreadPool(1);
        Future<Boolean> f = pool.submit(() -> {
            System.out.println("task1");
            int i = 1 / 0;
            return true;
        });
        System.out.println("result:"+f.get());

    }

}
