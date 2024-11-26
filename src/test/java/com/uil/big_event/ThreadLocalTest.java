package com.uil.big_event;


import org.junit.jupiter.api.Test;

public class ThreadLocalTest {

    @Test
    public void TestThreadLocalSetAndGet() {
        ThreadLocal tl = new ThreadLocal();
        Thread t1 = new Thread(()->{
            tl.set("消息1");
            System.out.println(Thread.currentThread().getName() + ":" + tl.get());
            System.out.println(Thread.currentThread().getName() + ":" + tl.get());
            System.out.println(Thread.currentThread().getName() + ":" + tl.get());
        },"线程1");
        t1.start();

        Thread t2 = new Thread(()->{
            tl.set("消息2");
            System.out.println(Thread.currentThread().getName() + ":" + tl.get());
            System.out.println(Thread.currentThread().getName() + ":" + tl.get());
            System.out.println(Thread.currentThread().getName() + ":" + tl.get());
        },"线程2");
        t2.start();
    }
}
