package com.wangzi.test.concurrent;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * 并发队列ConcurrentLinkedQueue和阻塞队列LinkedBlockingQueue用法
 * 在Java多线程应用中，队列的使用率很高，多数生产消费模型的首选数据结构就是队列(先进先出)。Java提供的线程安全的Queue可以分为阻塞队列和非阻塞队列，其中阻塞队列的典型例子是BlockingQueue，
 * 非阻塞队列的典型例子是ConcurrentLinkedQueue，在实际应用中要根据实际需要选用阻塞队列或者非阻塞队列。
 * 注：什么叫线程安全？这个首先要明确。线程安全就是说多线程访问同一代码，不会产生不确定的结果。
 * 并行和并发区别
 * 1、并行是指两者同时执行一件事，比如赛跑，两个人都在不停的往前跑；
 * 2、并发是指资源有限的情况下，两者交替轮流使用资源，比如一段路(单核CPU资源)同时只能过一个人，A走一段后，让给B，B用完继续给A ，交替使用，目的是提高效率
 * 由于LinkedBlockingQueue实现是线程安全的，实现了先进先出等特性，是作为生产者消费者的首选，LinkedBlockingQueue 可以指定容量，也可以不指定，不指定的话，
 * 默认最大是Integer.MAX_VALUE，其中主要用到put和take方法，put方法在队列满的时候会阻塞直到有队列成员被消费，take方法在队列空的时候会阻塞，直到有队列成员被放进来。
 * @author Administrator
 *
 */
public class LinkedBlockingQueueTest {
	
	public class Basket{
		BlockingQueue<String> basket = new LinkedBlockingDeque<String>(3);
		public void produce() throws InterruptedException {
			basket.put("An apple");
		}
		public String consumer() throws InterruptedException{
			return basket.take();
		}
	}
	
	class Producer implements Runnable {
        private String instance;
        private Basket basket;

        public Producer(String instance, Basket basket) {
            this.instance = instance;
            this.basket = basket;
        }

        public void run() {
            try {
                while (true) {
                    // 生产苹果
                    System.out.println("生产者准备生产苹果：" + instance);
                    basket.produce();
                    System.out.println("!生产者生产苹果完毕：" + instance);
                    // 休眠300ms
                    Thread.sleep(300);
                }
            } catch (InterruptedException ex) {
                System.out.println("Producer Interrupted");
            }
        }
    }
	class Consumer implements Runnable{
		private String instance;
		private Basket basket;
		public Consumer(String instance, Basket basket){
			this.instance = instance;
			this.basket = basket;
		}
	
		public void run() {
			try {
				while(true){
					//消费苹果
					System.out.println("消费者准备消费苹果：" + instance);
					basket.consumer();
					System.out.println("!消费者消费苹果完毕：" + instance);
					Thread.sleep(3000);
				}
			} catch (InterruptedException e) {
				System.out.println("Consumer Interrupted");
			}
		}
	}
	public static void main(String[] args) {
		LinkedBlockingQueueTest test = new LinkedBlockingQueueTest();
		Basket basket = test.new Basket();
		ExecutorService service = Executors.newCachedThreadPool();
		Producer producer = test.new Producer("生产者001", basket);
		Producer producer2 = test.new Producer("生产者002", basket);
        Consumer consumer = test.new Consumer("消费者001", basket);
        service.submit(producer);
        service.submit(producer2);
        service.submit(consumer);
//         程序运行5s后，所有任务停止
        try {
            Thread.sleep(1000 * 5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        service.shutdownNow();
    }
}
