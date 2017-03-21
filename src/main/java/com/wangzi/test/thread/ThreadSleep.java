package com.wangzi.test.thread;

public class ThreadSleep {
	
	public static void main(String[] args) {
		ThreadSleep t = new ThreadSleep();
		Thread thread = t.new MyThread();
		Thread thread2 = new Thread(t.new MyThread2());
		thread.start();
		thread2.start();
	}
	
	class MyThread extends Thread{
		public void run() {
			for(int i=0; i<3; i++){
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
			for(int i=0; i<3; i++){
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
