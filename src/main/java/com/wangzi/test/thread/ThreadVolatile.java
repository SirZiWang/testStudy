package com.wangzi.test.thread;

public class ThreadVolatile extends Thread{
	
	public static volatile int n = 0;
	public void run(){
		for(int i=0; i<10; i++){
			try {
				n = n+1;
				sleep(3);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		
		Thread[] threads = new Thread[100];
		for(int i=0; i<threads.length; i++)
			threads[i] = new ThreadVolatile();
		for (int i = 0; i < threads.length; i++)
            // 运行刚才建立的100个线程
            threads[i].start();
        for (int i = 0; i < threads.length; i++)
            // 100个线程都执行完后继续
            threads[i].join();
        System.out.println("n=" + ThreadVolatile.n);
	}
}
