package com.wangzi.test.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CallableFutureTest {
	
	@SuppressWarnings("unchecked" )
	public static void main(String[] args) throws ExecutionException, InterruptedException{
		CallableFutureTest test = new CallableFutureTest();
		//创建线程池
		ExecutorService service = Executors.newFixedThreadPool(2);
		Callable c1 = test.new MyThread("A");
		Callable c2 = test.new MyThread("B");
		// 执行任务并获取Future对象
        Future f1 = service.submit(c1);
        Future f2 = service.submit(c2);
 
        // 从Future对象上获取任务的返回值，并输出到控制台
        System.out.println(">>>" + f1.get().toString());
        System.out.println(">>>" + f2.get().toString());
 
		service.shutdown();
	}
	@SuppressWarnings("rawtypes")
	class MyThread implements Callable{
		private String name;
		
		public MyThread(String name) {
			super();
			this.name = name;
		}

		public Object call() throws Exception {
			return name + "任务返回的内容。";
		}
	}
}
