package com.wangzi.test.thread;

class VolatileTest1 extends Thread {

	private volatile boolean flag = true;
	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}


	@Override
	public void run() {
		System.out.println("进入run方法");
		while(flag == true){
			
		}
		System.out.println("线程执行完成了");
	}
}
public class VolatileDemo {
	public static void main(String[] args) {
		try {
			VolatileTest1 thread = new VolatileTest1();
			thread.start();
			Thread.sleep(1000);
			thread.setFlag(false);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
