package com.wangzi.test.thread;

public class TestSysnc2 implements Runnable{
	
	 volatile int b =100;
	
	synchronized void m1() throws InterruptedException {
		b = 1000;
		Thread.sleep(500);
		System.out.println("b = " + b);
	}
	
	synchronized void m2() throws InterruptedException {
		Thread.sleep(500);
		b = 2000;
	}
	
	@Override
	public void run() {
		try {
			m1();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) throws InterruptedException {
		TestSysnc2 ts = new TestSysnc2();
		Thread t = new Thread(ts);
		t.start();
		ts.m2();
		System.out.println("main thread b = " + ts.b);
	}
}
