package com.Thread;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

interface DecimalAccount{
    //获取余额
    BigDecimal getBalance() ;
    //取款
    void withdraw(BigDecimal account) ;

    static void demo(DecimalAccount account){
        List<Thread> ts = new ArrayList<>() ;
        /**
         * 方法内会启动 1000 个线程，每个线程做 -10 元 的操作
         * 如果初始余额为 10000 那么正确的结果应当是 0
         */

        for (int i = 0; i < 1000; i++) {
            ts.add(new Thread(()->{
                account.withdraw(BigDecimal.TEN);
            }));
        }
        ts.forEach(Thread::start);
        ts.forEach(t->{
            try {
                t.join(); //同步下面的打印语句
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println(account.getBalance());
    }
}

 public  class TestAtomicRef implements DecimalAccount {

    //CAS
     AtomicReference<BigDecimal> ref ;
    //重量锁
    private final Object lock = new Object();
//    BigDecimal balance ;

    public TestAtomicRef(BigDecimal balance) {
//        this.balance = balance;
        ref = new AtomicReference<>(balance)  ;
    }

    @Override
    public BigDecimal getBalance() {
//        return balance ;
        return  ref.get() ;
    }
     /*安全实现 - CAS*/
     @Override
     public void withdraw(BigDecimal amount) {
         while (true){
             BigDecimal prev = ref.get() ;
             BigDecimal next = prev.subtract(amount) ;
             if (ref.compareAndSet(prev,next)) break;
         }
         }


    /*安全实现 - 重量级锁*/
/*     @Override
     public void withdraw(BigDecimal amount) {

        synchronized (lock){
            BigDecimal balance = this.getBalance() ;
            this.balance = balance.subtract(amount) ;
        }
     }*/
     /*不安全实现*/
     /*
        @Override
        public void withdraw(BigDecimal amount) {
        BigDecimal balance = this.getBalance() ;
        this.balance = balance.subtract(amount) ;
    }*/
}

 class Test{
     public static void main(String[] args) {
         TestAtomicRef ref = new TestAtomicRef(new BigDecimal(10000))  ;

         DecimalAccount.demo(ref);
     }

}