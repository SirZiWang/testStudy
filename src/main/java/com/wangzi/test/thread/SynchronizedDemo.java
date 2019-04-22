package com.wangzi.test.thread;

public class SynchronizedDemo implements Runnable{

	static int i=0;
	
	/**
	 * 作用于静态方法,锁是当前class对象,也就是
	 * SynchronizedDemo类对应的class对象
	 */
	private static synchronized void increase(){
		i++;
	}
	
	/**
     * 非静态,访问时锁不一样不会发生互斥
     */
	private synchronized void increase1(){
		i++;
	}
	@Override
	public void run() {
		for(int j=0;j<1000000;j++){
//			increase();
			increase1();
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		Thread t0 = new Thread(new SynchronizedDemo());
		Thread t1 = new Thread(new SynchronizedDemo());
		t0.start();t1.start();
		t0.join();t1.join();
		System.out.println(i);
	}

}
