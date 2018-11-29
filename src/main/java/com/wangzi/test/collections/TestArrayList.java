package com.wangzi.test.collections;

import java.util.ArrayList;
import java.util.List;

public class TestArrayList {
	private static List<Integer> list = new ArrayList<>();
	public static void main(String[] args) throws InterruptedException{
		for (int i = 0; i < 10; i++) {
			testList();
			list.clear();
		}
		
	}
	private static void testList() throws InterruptedException{
		Runnable runnable = () -> {
			for(int i = 0; i < 10000; i++){
				list.add(i);
			}
		};
		Thread thread1 = new Thread(runnable);
		Thread thread2 = new Thread(runnable);
		Thread thread3 = new Thread(runnable);
		thread1.start();
		thread2.start();
		thread3.start();
		
		thread1.join();
		thread2.join();
		thread3.join();
		
		System.out.println(list.size());
	}
}
