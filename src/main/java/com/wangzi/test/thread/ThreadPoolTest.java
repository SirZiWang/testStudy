package com.wangzi.test.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 线程池
 * @author Administrator
 *
 */
public class ThreadPoolTest {
	
	public static void main(String[] args) {
		ThreadPoolTest thread = new ThreadPoolTest();
		 //创建一个可重用固定线程数的线程池 
        ExecutorService pool = Executors.newFixedThreadPool(2); 
        //创建实现了Runnable接口对象，Thread对象当然也实现了Runnable接口 
        Thread t1 = thread.new MyThread(); 
        Thread t2 = thread.new MyThread(); 
        Thread t3 = thread.new MyThread(); 
        Thread t4 = thread.new MyThread(); 
        Thread t5 = thread.new MyThread(); 
        //将线程放入池中进行执行 
        pool.execute(t1); 
        pool.execute(t2); 
        pool.execute(t3); 
        pool.execute(t4); 
        pool.execute(t5); 
        //关闭线程池 
        pool.shutdown(); 
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
