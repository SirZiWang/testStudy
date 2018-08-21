package com.wangzi.test.algorithm;

public class ThreadByOrder {
	static Thread t1 = new Thread(new Runnable() {
		
		@Override
		public void run() {
			System.out.println("t1");			
		}
	});
	
	static Thread t2 = new Thread(new Runnable() {
		
		@Override
		public void run() {
			try {
				t1.join();
//				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("t2");
		}
	});
	
	static Thread t3 = new Thread(new Runnable() {

		@Override
		public void run() {
			try {
				t2.join();
//				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("t3");
		}
	});
	public static void main(String[] args) {
		 t1.start();
		 t3.start();
		 t2.start();
	}
}
