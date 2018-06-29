package com.wangzi.test.thread;

import java.util.ArrayList;
import java.util.List;

//Fair Lock implementation without nested monitor lockout problem,
//but with missed signals problem.

public class FairLock4 {
	
	private boolean isLocked = false;
	private Thread lockingThread = null;
	private List<QueueObject> waitingThreads = new ArrayList<>();

	public void lock() throws InterruptedException{
		QueueObject queueObject = new QueueObject();
		synchronized(this){
			waitingThreads.add(queueObject);
		}
		boolean mustWait = true;
		while(mustWait){
			synchronized (this) {
				mustWait = isLocked || waitingThreads.get(0)!= queueObject;
				if(!mustWait){
					waitingThreads.remove(queueObject);
					isLocked = true;
					lockingThread = Thread.currentThread();
					return;
				}
			}
			synchronized (queueObject) {
				if(mustWait){
					try {
						queueObject.wait();
					} catch (InterruptedException e) {
						waitingThreads.remove(queueObject);
						throw e;
					}
				}
			}
		}
	}
	public synchronized void unLock(){
		if(this.lockingThread != Thread.currentThread()){
			throw new IllegalMonitorStateException(
					"Calling thread has not locked this lock");
		}
		isLocked = false;
		lockingThread = null;
		if(waitingThreads.size() > 0){
			QueueObject queueObject = waitingThreads.get(0);
			synchronized(queueObject){
				queueObject.notify();
			}
		}
	}
}
