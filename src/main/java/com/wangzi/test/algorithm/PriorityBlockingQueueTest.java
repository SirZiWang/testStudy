package com.wangzi.test.algorithm;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

public class PriorityBlockingQueueTest {

	public static void main(String[] args) throws InterruptedException {
		BlockingQueue<Integer> queue = new PriorityBlockingQueue<>(11);
		for(int i=0; i<20; i++){
			queue.offer(i);
			queue.offer(i);
			queue.take();
		}
	}
}
