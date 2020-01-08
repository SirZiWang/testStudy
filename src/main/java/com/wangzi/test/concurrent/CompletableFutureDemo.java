package com.wangzi.test.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.apache.commons.lang.StringUtils;

public class CompletableFutureDemo {

	public static void main(String[] args) throws InterruptedException, ExecutionException {

		for(int i=0;i<=10;i++){
			List<String> subDetails = new ArrayList<>();
			for(int j=10;i>=0;j--){
				if(!StringUtils.equals("sss", "ss")){
					break;
				}
				subDetails.add("33");
			}
		}
		
		
//		CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
//			ThreadUtil.sleep(200);
//			return 10/0;});
//		//		System.out.println(future.join());
//		//		System.out.println(future.get());
//		System.out.println(future.getNow(10));
//		future.complete(30);

		CompletableFuture<Void> future1 = CompletableFuture.runAsync(() -> System.out.println("runAsync"));
		CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> "supplyAsync");

		System.out.println(future1.get());
		System.out.println(future2.get());

		CompletableFuture<Integer> future3 = CompletableFuture.supplyAsync(() -> {
			ThreadUtil.sleep(100);
			return 20;
		}).whenCompleteAsync((v, e) -> {
			System.out.println(v);
			System.out.println(e);
		});
		System.out.println(future3.get());
	}

	static class ThreadUtil {
		public static void sleep(long ms) {
			try {
				Thread.sleep(ms);
			} catch (InterruptedException e) {
				e.printStackTrace();
				throw new RuntimeException(e.getMessage());
			}
		}
	}
}

