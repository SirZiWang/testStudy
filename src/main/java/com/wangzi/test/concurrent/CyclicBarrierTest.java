package com.wangzi.test.concurrent;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * CyclicBarrier是一个同步辅助类，它允许一组线程互相等待，直到到达某个公共屏障点(common barrier point)。在涉及一组固定大小的线程的程序中，
 * 这些线程必须不时地互相等待，此时CyclicBarrier很有用。因为该barrier 在释放等待线程后可以重用，所以称它为循环的barrier。
 * CyclicBarrier支持一个可选的Runnable命令，在一组线程中的最后一个线程到达之后（但在释放所有线程之前），该命令只在每个屏障点运行一次。若在继续所有参与线程之前更新共享状态，此屏障操作很有用。
 * @author Administrator
 *
 */
public class CyclicBarrierTest {
	 public static void main(String[] args) {
	        ExecutorService service = Executors.newCachedThreadPool();
	        final  CyclicBarrier cb = new CyclicBarrier(3); //约定3个人
	        for(int i=0;i<3;i++){ //产生3个人
	            Runnable runnable = new Runnable(){
	                    public void run(){
	                    try {
	                        Thread.sleep((long)(Math.random()*10000)); 
	                        System.out.println("线程" + Thread.currentThread().getName() +
	                                "即将到达集合地点1，当前已有" + (cb.getNumberWaiting()+1) + "个已经到达，" + 
	                        		(cb.getNumberWaiting()==2?"都到齐了，继续走啊":"正在等候"));                      
	                        cb.await();
	                         
	                        Thread.sleep((long)(Math.random()*10000)); 
	                        System.out.println("线程" + Thread.currentThread().getName() +
	                                "即将到达集合地点2，当前已有" + (cb.getNumberWaiting()+1) + "个已经到达，" + 
	                        		(cb.getNumberWaiting()==2?"都到齐了，继续走啊":"正在等候"));
	                        cb.await();
	                         
	                        Thread.sleep((long)(Math.random()*10000)); 
	                        System.out.println("线程" + Thread.currentThread().getName() +
	                                "即将到达集合地点3，当前已有" + (cb.getNumberWaiting() + 1) + "个已经到达，" + 
	                        		(cb.getNumberWaiting()==2?"都到齐了，继续走啊":"正在等候"));                    
	                        cb.await();                    
	                    } catch (Exception e) {
	                        e.printStackTrace();
	                    }              
	                }
	            };
	            service.execute(runnable);
	        }
	        service.shutdown();
	    }
}
