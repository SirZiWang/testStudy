package com.wangzi.test.aqs;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * 自定义独占锁
 * @author Administrator
 *
 */
public class MutexLock implements Lock{

	private static class Sync extends AbstractQueuedSynchronizer{

		private static final long serialVersionUID = 6413627926823590862L;

		/**
		 * 尝试获得锁
		 */
		@Override
		protected boolean tryAcquire(int arg) {
			//只有当 state 的值为 0，并且线程成功将 state 值修改为 1 之后，线程才可以获得独占锁
			if(compareAndSetState(0, 1)){
				setExclusiveOwnerThread(Thread.currentThread());
				return true;
			}
			return false;
		}

		/**
		 * 释放锁
		 */
		@Override
		protected boolean tryRelease(int arg) {
			// state 为 0 说明当前同步块中没有锁了，无需释放
			if(getState() == 0)
				throw new IllegalMonitorStateException();
			//将独占锁设置为null
			setExclusiveOwnerThread(null);
			// 将状态变量的值设为 0，以便其他线程可以成功修改状态变量从而获得锁
			setState(0);
			return true;
		}

		/**
		 * 判断当前线程是否被独占
		 */
		@Override
		protected boolean isHeldExclusively() {
			return getState() == 1;
		}

		Condition newCondition() {
			return new ConditionObject();
		}

	}

	private final Sync sync = new Sync();

	@Override
	public void lock() {
		sync.acquire(1);

	}

	@Override
	public void lockInterruptibly() throws InterruptedException {
		sync.acquireInterruptibly(1);

	}

	@Override
	public boolean tryLock() {
		sync.tryAcquire(1);
		return false;
	}

	@Override
	public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
		return sync.tryAcquireNanos(1, unit.toNanos(time));
	}

	@Override
	public void unlock() {
		sync.release(1);
	}

	@Override
	public Condition newCondition() {
		return sync.newCondition();
	}

	public boolean hasQueuedThreads(){
		return sync.hasQueuedThreads();
	}

	public boolean isLocked() {
		return sync.isHeldExclusively();
	}

	public static void withoutMutex() throws InterruptedException {
		System.out.println("without mutex");
		int threadCounts = 2;
		final Thread[] threads = new Thread[threadCounts];
		for(int i=0; i<threads.length; i++){
			final int index = i;
			threads[i] = new Thread(new Runnable() {

				@Override
				public void run() {
					for (int j = 0; j < 100000; j++) {
						if (j % 20000 == 0) {
							System.out.println("Thread-" + index + ": j =" + j);
						}
					}
				}
			});
		}

		for (int i = 0; i < threads.length; i++) {
			threads[i].start();
		}
		for (int i = 0; i < threads.length; i++) {
			threads[i].join();
		}
	}

	public static void withMutex() {
		System.out.println("With mutex: ");
		final MutexLock mutex = new MutexLock();
		int threadCount = 2;
		final Thread threads[] = new Thread[threadCount];
		for (int i = 0; i < threads.length; i++) {
			final int index = i;
			threads[i] = new Thread(new Runnable() {
				@Override
				public void run() {
					mutex.lock();
					try {
						for (int j = 0; j < 100000; j++) {
							if (j % 20000 == 0) {
								System.out.println("Thread-" + index + ": j =" + j);
							}
						}
					} finally {
						mutex.unlock();
					}
				}
			});
		}
		for (int i = 0; i < threads.length; i++) {
			threads[i].start();
		}
	}
	public static void main(String[] args) throws InterruptedException {
		withoutMutex();
		System.out.println("mutex Lock");
		withMutex();
	}
}
