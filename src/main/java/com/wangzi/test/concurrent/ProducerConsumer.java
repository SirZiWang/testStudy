package com.wangzi.test.concurrent;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * 1、生产者仅仅在仓储未满时候生产，仓满则停止生产。
 * 2、消费者仅仅在仓储有产品时候才能消费，仓空则等待。
 * 3、当消费者发现仓储没产品可消费时候会通知生产者生产。
 * 4、生产者在生产出可消费产品时候，应该通知等待的消费者去消费。
 * 
 * @author Administrator
 *
 */
public class ProducerConsumer {
	
	class Consumer implements Runnable{
		private String name;
		private Storage storage = null;
		
		public Consumer(String name, Storage storage) {
			super();
			this.name = name;
			this.storage = storage;
		}

		public void run() {
			try {
				while(true){
					System.out.println(name + "消费者开始消费产品.");
					Product product = storage.pop();
					System.out.println(name + "已消费(" + product.toString() + ").");
                    System.out.println("===============");
                    Thread.sleep(500);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	class Producer implements Runnable{
		private String name; 
		private Storage storage = null;
		
		public Producer(String name, Storage storage) {
			super();
			this.name = name;
			this.storage = storage;
		}

		public void run() {
			try {
				while(true){
					Product product = new Product((int)(Math.random() * 1000));
					System.out.println(name + "准备生产(" + product.toString() + ").");
					storage.pop(product);
					System.out.println(name + "已生产(" + product.toString() + ").");
					System.out.println("===============");
					Thread.sleep(500);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	/**
	 * 仓库
	 * @author Administrator
	 *
	 */
	public class Storage{
		BlockingQueue<Product> bQueue = new LinkedBlockingDeque<Product>(10);
		//生产产品
		public Product pop() throws InterruptedException {
			return bQueue.take();
		}
		//消费产品
		public void pop(Product product) throws InterruptedException {
			bQueue.put(product);
		}
	}
	
	public class Product{
		private int id;

		public Product(int id) {
			super();
			this.id = id;
		}

		@Override
		public String toString() {
			return "Product [id=" + id + "]";
		}
	}
	public static void main(String[] args) {
		ProducerConsumer pc = new ProducerConsumer();
		Storage storage = pc.new Storage();
		ExecutorService eService = Executors.newCachedThreadPool();
		Producer producer = pc.new Producer("张三", storage);
		Producer producer2 = pc.new Producer("李四", storage);
		Consumer consumer = pc.new Consumer("王五", storage);
		Consumer consumer2 = pc.new Consumer("赵六", storage);
		Consumer consumer3 = pc.new Consumer("老王", storage);
		eService.submit(producer);
		//eService.submit(producer2);
		eService.submit(consumer);
		eService.submit(consumer2);
		eService.submit(consumer3);
//		eService.shutdown();
	}
}
