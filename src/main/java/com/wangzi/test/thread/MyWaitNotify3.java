package com.wangzi.test.thread;

/**
 * 假唤醒
 * @author Administrator
 *
 */
public class MyWaitNotify3 {
	MonitorObject myMonitorObject = new MonitorObject();
	boolean wasSignalled = false;
	public void doWait(){
		synchronized (myMonitorObject) {
			while(!wasSignalled){
				try {
					myMonitorObject.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			//clear signal and continue running.
			wasSignalled = false;

		}
	}
	
	public void doNotify(){
		synchronized(myMonitorObject){
			wasSignalled = true;
			myMonitorObject.notify();
		}
	}
	public class MonitorObject{

	}
}
