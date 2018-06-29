package com.wangzi.test.concurrent;

import java.util.LinkedList;
import java.util.List;

public class BlockingQueue {
	
	private List<Object> queue = new LinkedList<>();
	private int limit = 10;
	
	public BlockingQueue(int limit){
		this.limit = limit;
	}
	
	public synchronized void enQueue(Object object) throws InterruptedException{
		while(this.queue.size() == limit){
			wait();
		}
		if(this.queue.size() == 0){
			notifyAll();
		}
		queue.add(object);
	}
	
	public synchronized boolean delQueue() throws InterruptedException{
		while(this.queue.size() == 0){
			wait();
		}
		if(this.queue.size() == limit){
			notifyAll();
		}
		return queue.remove(queue);
	}
}
