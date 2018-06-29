package com.wangzi.test.thread;



public class MyClass {

	int count = 0;
	public synchronized void add(int value){
		count += value;
	}
	
	public void add2(int value){
		synchronized(this){
			this.count += value;
		}
	}
}
