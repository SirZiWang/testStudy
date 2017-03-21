package com.wangzi.test.thread;

/**
 * 线程调度（优先级）
 * @author Administrator
 *
 */
public class ThreadPriority {
	
	public static void main(String[] args) {
		ThreadPriority thread = new ThreadPriority();
		Thread t1 = thread.new MyThread();
		Thread t2 = new Thread(thread.new MyThread2());
		t1.setPriority(10);
		t2.setPriority(1);
		t1.start();
		t2.start();
	}
	
	class MyThread extends Thread{
		public void run(){
			for(int i=0; i<10; i++){
				System.out.println("线程1第" + i + "次执行！");
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	class MyThread2 implements Runnable{
		public void run(){
			for(int i=0; i<10; i++){
				System.out.println("线程2第" + i + "次执行！");
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
