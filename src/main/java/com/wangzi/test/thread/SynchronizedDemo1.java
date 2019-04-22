package com.wangzi.test.thread;

public class SynchronizedDemo1 implements Runnable{

	static int i=0;
	static SynchronizedDemo1 instance = new SynchronizedDemo1();

	@Override
	public void run() {
		synchronized(instance){
			for(int j=0;j<1000000;j++){
				i++;
			}
		}
		
		//this,当前实例对象锁
//		synchronized(this){
//		    for(int j=0;j<1000000;j++){
//		        i++;
//		    }
//		}
//
//		//class对象锁
//		synchronized(SynchronizedDemo1.class){
//		    for(int j=0;j<1000000;j++){
//		        i++;
//		    }
//		}
	}

	public static void main(String[] args) throws InterruptedException {
		Thread t1=new Thread(instance);
		Thread t2=new Thread(instance);
		t1.start();t2.start();
		t1.join();t2.join();
		System.out.println(i);
	}
}
