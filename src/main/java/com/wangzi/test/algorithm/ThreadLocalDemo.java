package com.wangzi.test.algorithm;

public class ThreadLocalDemo {
	
	private static ThreadLocal<Integer> local = new ThreadLocal<Integer>(){
		// 通过匿名内部类覆盖ThreadLocal的initialValue()方法，指定初始值 
		public Integer initialValue() {  
            return 0;
		}
	};
	//获取下一个序列值  
	public int getNextNum(){
		local.set(local.get()+1);
		return local.get();
	}
	public static void main(String[] args) {
		ThreadLocalDemo t =new ThreadLocalDemo();
		TestThread t1 = new TestThread(t);
		TestThread t2 = new TestThread(t);
		TestThread t3 = new TestThread(t);
		t1.start();
		t2.start();
		t3.start();
	}
	
	private static class TestThread extends Thread{
		private ThreadLocalDemo sn;  
		  
        public TestThread(ThreadLocalDemo sn) {  
            this.sn = sn;  
        }  
  
        public void run() {  
            for (int i = 0; i < 3; i++) {  
                // 每个线程打出3个序列值  
                System.out.println("thread[" + Thread.currentThread().getName() + "] --> sn["  
                         + sn.getNextNum() + "]");  
            }  
        }  
	}
}
