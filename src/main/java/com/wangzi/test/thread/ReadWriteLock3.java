package com.wangzi.test.thread;

import java.util.HashMap;
import java.util.Map;

/**
 * 读锁升级到写锁
 * @author Administrator
 *
 */
public class ReadWriteLock3 {
	private Map<Thread, Integer> readingThreads =new HashMap<Thread, Integer>();
	private int writeAccesses = 0;
	private int writeRequests = 0;
	private Thread writingThread = null;
	private int readers = 0;

	public synchronized void lockWrite() throws InterruptedException{
		 writeRequests++;
		 Thread callingThread = Thread.currentThread();
		 while(!canGrantWriteAccess(callingThread)){
			 wait();
		}
		 writeAccesses++;
		writeRequests++;
		writingThread = Thread.currentThread();
	 }
	 public synchronized void unlockWrite() throws InterruptedException{
		 writeAccesses--;
		 if(writeAccesses == 0){
			 writingThread = null;
		 }
		 notifyAll();
	 }
	 private boolean canGrantWriteAccess(Thread callingThread){
		 if(isOnlyReader(callingThread)) return true;
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
	 private boolean isOnlyReader(Thread callingThread){
		 return readers == 1 && readingThreads.get(callingThread) != null;
	 }
}
