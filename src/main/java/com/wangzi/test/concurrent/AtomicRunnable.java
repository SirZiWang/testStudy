package com.wangzi.test.concurrent;

import java.util.concurrent.atomic.AtomicLong;

/**
 * 单变量多线程并发安全访问的工具包java.util.concurrent.atomic
 * @author Administrator
 *
 */
public class AtomicRunnable implements Runnable{
	
	private static AtomicLong aLong = new AtomicLong(1000); // 原子量，每个线程都可以自由操作
	private String name; //操作人
	private int x; //操作数额
	
	public AtomicRunnable(String name, int x) {
		super();
		this.name = name;
		this.x = x;
	}

	public void run() {
		System.out.println(name + "执行了" + x + "，当前余额：" + aLong.addAndGet(x));
	}

}
