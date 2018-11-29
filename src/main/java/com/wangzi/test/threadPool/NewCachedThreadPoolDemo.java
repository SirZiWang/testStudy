package com.wangzi.test.threadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NewCachedThreadPoolDemo {
	
	public static void main(String[] args) {
		ExecutorService service = Executors.newCachedThreadPool();
		for(int i=0; i<10; i++){
			final int index = i;
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			service.execute(new Runnable(){

				@Override
				public void run() {
					System.out.println(index);
					
				}
				
			});
		}
	}
}
