package com.wangzi.test.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 3个线程ABC。按照ABC来运行（A线程输出A，B线程输出B，C线程输出C，以此类推，循环输出）。
 * @author Administrator
 *
 */
public class SequenceThread1 {
	
	public static void main(String[] args) throws InterruptedException {
		Lock lock = new ReentrantLock();
		Condition condition1 = lock.newCondition();
		Condition condition2 = lock.newCondition();
		
		Thread s1 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				while(true){
					this.print();
				}
			}
			
			public void print() {
                lock.lock();
                System.out.println("here is thread 1 ");
                try {
                	condition1.signal();
                	condition2.await();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                lock.unlock();
            }
		});
		Thread s2 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    this.print();
                }
            }

            public void print() {
                lock.lock();
                System.out.println("here is thread 2 ");
                try {
                	condition1.signal();
                	condition2.await();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                lock.unlock();
            }
        });

        s1.start();
        s2.start();
        Thread.sleep(1000);
    }
}
