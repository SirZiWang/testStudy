package com.wangzi.test.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadAtomicTest2 {
	public static void main(String[] args) {
		ExecutorService service = Executors.newFixedThreadPool(2);
	     Lock lock = new ReentrantLock(false);
	     Runnable t1 = new AtomicRunnable2("张三", 2000, lock);
	     Runnable t2 = new AtomicRunnable2("李四", 3600, lock);
	     Runnable t3 = new AtomicRunnable2("王五", 2700, lock);
	     Runnable t4 = new AtomicRunnable2("老张", 600, lock);
	     Runnable t5 = new AtomicRunnable2("老牛", 1300, lock);
	     Runnable t6 = new AtomicRunnable2("胖子", 800, lock);
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
