package com.kunming.study.threadstudy;

import java.util.concurrent.atomic.AtomicInteger;

public class ThreadSafeCounter {
    private AtomicInteger count = new AtomicInteger(0);

    public void increment() {
        count.incrementAndGet(); // 线程安全操作
    }

    // 故意制造线程不安全版本作为对比
    public void unsafeIncrement() {
        int temp = count.get();
        count.set(temp + 1); // 存在竞态条件
    }


    public AtomicInteger getCount() {
        return count;
    }

    public static void main(String[] args) {
        try {
            ThreadSafeCounter counter = new ThreadSafeCounter();

            Runnable task = () -> {
                for (int i = 0; i < 1000; i++) {
                    counter.increment();
                }
            };

            Thread t1 = new Thread(task);
            Thread t2 = new Thread(task);

            t1.start();
            t2.start();
            t1.join();
            t2.join();

            System.out.println("Final count: " + counter.getCount()); // 应该为2000
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public static void main1(String[] args) {
        try {
            ThreadSafeCounter counter = new ThreadSafeCounter();

            Runnable task = () -> {
                for (int i = 0; i < 1000; i++) {
                    counter.unsafeIncrement();
                }
            };

            Thread t1 = new Thread(task);
            Thread t2 = new Thread(task);

            t1.start();
            t2.start();
            t1.join();
            t2.join();

            System.out.println("Final count: " + counter.getCount()); // 应该为2000
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
