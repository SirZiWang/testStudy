package com.wangzi.test.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadAtomicTest {
	public static void main(String[] args) {
		ExecutorService service = Executors.newFixedThreadPool(2);
		Runnable t1 = new AtomicRunnable("张三", 2000);
		Runnable t2 = new AtomicRunnable("李四", 3600);
        Runnable t3 = new AtomicRunnable("王五", 2700);
        Runnable t4 = new AtomicRunnable("老张", 600);
        Runnable t5 = new AtomicRunnable("老牛", 1300);
        Runnable t6 = new AtomicRunnable("胖子", 800);
        // 执行各个线程
        service.execute(t1);
        service.execute(t2);
        service.execute(t3);
        service.execute(t4);
        service.execute(t5);
        service.execute(t6);
        // 关闭线程池
        service.shutdown();
	}
}
