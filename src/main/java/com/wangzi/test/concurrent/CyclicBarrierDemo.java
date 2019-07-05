package com.wangzi.test.concurrent;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo {
	
	public static void main(String[] args) {
		CyclicBarrier cyclicBarrier = new CyclicBarrier(5, new Runnable() {
			
			@Override
			public void run() {
				System.out.println("等待所有线程执行完毕！");
			}
		});
		for(int i=1; i<=5; i++){
			new Writer(cyclicBarrier).start();
		}
		System.out.println("主线程继续执行！");
	}
	
	static class Writer extends Thread{
		private CyclicBarrier cyclicBarrier;
		
		public Writer(CyclicBarrier cyclicBarrier){
			this.cyclicBarrier = cyclicBarrier;
		}
		
		@Override
		public void run(){
			System.out.println("线程：" + Thread.currentThread().getName() + "正在执行写入数据....");
			try {
				Thread.sleep(3000);
				System.out.println("线程" + Thread.currentThread().getName() + "写入数据完毕");
				cyclicBarrier.await();
			} catch(InterruptedException  e) {
				e.printStackTrace();
			} catch (BrokenBarrierException e) {
				e.printStackTrace();
			}
		}
	}
}
