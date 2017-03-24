package com.wangzi.test.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 自定义线程池
 * @author Administrator
 *
 */
public class ThreadPoolExecutorTest {
	
	public static void main(String[] args) {
		
		ThreadPoolExecutorTest test = new ThreadPoolExecutorTest();
		// 创建等待队列
		BlockingQueue<Runnable> bQueue = new ArrayBlockingQueue<Runnable>(20);
		// 创建一个单线程执行程序，它可安排在给定延迟后运行命令或者定期地执行。
		ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 3, 2, TimeUnit.MILLISECONDS, bQueue);
		
		// 创建实现了Runnable接口对象，Thread对象当然也实现了Runnable接口
        Thread t1 = test.new MyThread();
        Thread t2 = test.new MyThread();
        Thread t3 = test.new MyThread();
        Thread t4 = test.new MyThread();
        Thread t5 = test.new MyThread();
        Thread t6 = test.new MyThread();
        Thread t7 = test.new MyThread();
        // 将线程放入池中进行执行
        executor.execute(t1);
        executor.execute(t2);
        executor.execute(t3);
        executor.execute(t4);
        executor.execute(t5);
        executor.execute(t6);
        executor.execute(t7);
        // 关闭线程池
        executor.shutdown();
	}
	class MyThread extends Thread{

		@Override
		public void run() {
			System.out.println(Thread.currentThread().getName() + "正在执行.");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
}
