package com.Thread;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicMarkableReference;

public class TestAutomic {

    public static void main(String[] args) {

        AtomicInteger i = new AtomicInteger(0) ;

        // 获取并自增（i = 0, 结果 i = 1, 返回 0），类似于 i++
        System.out.println(i.getAndIncrement());
        // 自增并获取（i = 1, 结果 i = 2, 返回 2），类似于 ++i
        System.out.println(i.incrementAndGet());

        /* 获取并更新（i = 0, p 为 i 的当前值, 结果 i = -2, 返回 0）
         其中函数中的操作能保证原子，但函数需要无副作用*/
        System.out.println(i.getAndUpdate(p->p+2));

        /* 更新并获取（i = -2, p 为 i 的当前值, 结果 i = 0, 返回 0）
         其中函数中的操作能保证原子，但函数需要无副作用*/
        System.out.println(i.updateAndGet(p -> p + 2));

        /*获取并计算/计算并获取*/
        //p = i ; x = 10 ;
        System.out.println(i.getAndAccumulate(10, (p, x) -> p + x));
        //p = i ; x = 10 ;
        System.out.println(i.accumulateAndGet(10, (p, x) -> p + x));


    }



}
