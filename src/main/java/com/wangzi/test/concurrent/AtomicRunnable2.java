package com.wangzi.test.concurrent;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.Lock;

public class AtomicRunnable2 implements Runnable{
	private static AtomicLong aLong = new AtomicLong(1000);
	private String name;
	private int x;
	private Lock lock;
	
	public AtomicRunnable2(String name, int x, Lock lock) {
		super();
		this.name = name;
		this.x = x;
		this.lock = lock;
	}

	public void run() {
		lock.lock();
		System.out.println(name + "执行了" + x + "，当前余额：" + aLong.addAndGet(x));
		lock.unlock();
	}
	
}
