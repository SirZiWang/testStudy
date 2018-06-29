package com.wangzi.test.thread;

import sun.misc.Lock;

public class Count {
	private int count = 0;
	
	public int inc(){
		synchronized(this){
			return ++count;
		}
	}
	
	private Lock lock = new Lock();
	public int inc2() throws InterruptedException{
		lock.lock();
		int newCount = ++ count;
		lock.unlock();
		return newCount;
	}
}
