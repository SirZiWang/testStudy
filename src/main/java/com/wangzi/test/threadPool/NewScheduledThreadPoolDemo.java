package com.wangzi.test.threadPool;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class NewScheduledThreadPoolDemo {

	public static void main(String[] args) {
		ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);
		scheduledThreadPool.schedule(new Runnable(){
			
			@Override
			public void run() {
				System.out.println("delay 3 seconds");
			}
			
		}, 3, TimeUnit.SECONDS);
	}

}
