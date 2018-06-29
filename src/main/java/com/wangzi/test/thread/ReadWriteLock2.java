package com.wangzi.test.thread;

import java.util.HashMap;
import java.util.Map;
/**
 * 写锁重入
 * @author Administrator
 *
 */
public class ReadWriteLock2 {
	
	private Map<Thread, Integer> readingThreads = new HashMap<>();
	private int wirters = 0;
	private int writeRequests = 0;
	private Thread writingThread = null;
	
	 public synchronized void lockWrite() throws InterruptedException{
		 writeRequests++;
		 Thread callingThread = Thread.currentThread();
		 while(!canGrantWriteAccess(callingThread)){
			 wait();
		}
		wirters++;
		writeRequests++;
		writingThread = Thread.currentThread();
	 }
	 public synchronized void unlockWrite() throws InterruptedException{
		 wirters--;
		 if(wirters == 0){
			 writingThread = null;
		 }
		 notifyAll();
	 }
	 private boolean canGrantWriteAccess(Thread callingThread){
		 if(hasReaders()) return false;
		 if(writeRequests == 0) return true;
		 if(!isWriter(callingThread)) return false;
		 return true;
		 
	 }
	 private boolean isWriter(Thread callingThread){
		 return writingThread == callingThread;
	 }
	 private boolean hasReaders(){
		 return readingThreads.size() > 0;
	 }
}
