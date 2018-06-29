package com.wangzi.test.thread;

import java.util.HashMap;
import java.util.Map;
/**
 * 读锁重入
 * @author Administrator
 *
 */
public class ReadWriteLock1 {
	
	private Map<Thread, Integer> readingThreads = new HashMap<>();
	private int wirters = 0;
	private int writeRequests = 0;
	
	public synchronized void lockRead() throws InterruptedException{
		Thread callingThread = Thread.currentThread();
		while(!canGrantReadAccess(callingThread)){
			wait();
		}
		readingThreads.put(callingThread, getAccessCount(callingThread)+1);
	}
	public synchronized void unlockRead()throws InterruptedException{
		Thread callingThread = Thread.currentThread();
		int accessCount = getAccessCount(callingThread);
		if(accessCount == 1){
			readingThreads.remove(callingThread);
		}else{
			readingThreads.put(callingThread, getAccessCount(callingThread)-1);
		}
		notifyAll();
	}
	private boolean canGrantReadAccess(Thread callingThread){
		if(wirters > 0) return false;
		if(isReader(callingThread)) return true;
		if(writeRequests > 0) return false;
		return true;
	}
	
	private int getAccessCount(Thread callingThread){
		Integer accessCount = readingThreads.get(callingThread);
		if(accessCount == null) return 0;
		return accessCount.intValue();

	}
	private boolean isReader(Thread callingThread){
		return readingThreads.get(callingThread) !=null;
	}
}
