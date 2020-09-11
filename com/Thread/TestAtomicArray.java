package com.Thread;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class TestAtomicArray {
    /*测试数组安全性的通用方法，采用函数式编程提供参数即可*/
    /**
     参数1，提供数组、可以是线程不安全数组或线程安全数组
     参数2，获取数组长度的方法
     参数3，自增方法，回传 array, index
     参数4，打印数组的方法
     */
    private static <T> void demo(
        Supplier<T> arraySupplier,
        Function<T, Integer> lengthFun,
        BiConsumer<T, Integer> putConsumer,
        Consumer<T> printConsumer)
    {
        List<Thread> ts = new ArrayList<>();

        T array = arraySupplier.get() ;
        Integer length = lengthFun.apply(array);

        for (int i = 0; i < length ; i++) {
            //每个线程对数组作1000次操作
            ts.add(new Thread(()->{
                for (int j = 0; j < 10000; j++) {
                    putConsumer.accept(array,j%length);//取模是为了均摊在数组的每个元素上
                }
            }));
        }
        ts.forEach(t -> t.start()); // 启动所有线程
        ts.forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }); // 等所有线程结束
        printConsumer.accept(array);
    }

    public static void main(String[] args) {
/*        demo(
                ()->new int[10],
                (array)->array.length,
                (array,index)->array[index]++, //自增
                array-> System.out.println(Arrays.toString(array))
        );*/

        //安全的
        demo(
                ()->new AtomicIntegerArray(10),
                (array)->array.length(),
                (array,index)->array.getAndIncrement(index),
                array-> System.out.println(array)
        );
    }
}
