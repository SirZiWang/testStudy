package com.wangzi.test.algorithm;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;

public class ReferenceDemo {

	private static final int _1MB = 1024*1024;//设置大小为1MB

	public static void main(String[] args) throws InterruptedException{
		/*ReferenceQueue<Object> referenceQueue = new ReferenceQueue<>(); //用引用队列进行监控引用的回收情况
		Object value = new Object();
		Map<Object, Object> map = new HashMap<>();
		for (int i = 0; i < 100; i++) {//循环100次把数据插入到弱应用中（WeakReference）， 同时把弱引用作为key存入HashMap
			byte[] bytes = new byte[_1MB];
			//每个引用中都有关联引用队列（referenceQueue）的构造器，用引用队列监听回收情况
			//如此，那么每次WeakReference中的bytes被回收之后，那么这个weakReference对象就会放入引用队列
			WeakReference<byte[]> weakReference = new WeakReference<byte[]>(bytes, referenceQueue);
			map.put(weakReference, value);
		}
		
	    Thread thread = new Thread(new Runnable() {//线程通过调用引用队列的情况查看那些对象被回收
            @SuppressWarnings("unchecked")
            public void run() {
                try {
                    int cnt = 0;
                    WeakReference<byte[]> k;
                    while ((k = (WeakReference<byte[]>) referenceQueue.remove()) != null) {//返回被回收对象的引用（注意本例中被回收的是bytes）
                        System.out.println((cnt++)+"回收了"+k);
                        System.out.println("map的size = " + map.size());//用于监控map的存储数量有没有发生变化
                    }
                } catch (Exception e) {
                    // TODO: handle exception
                }
            }
        });

        thread.start();
    
	}*/
		
		Object value = new Object();
		Map<Object, Object> map = new WeakHashMap<Object, Object>();
		for (int i = 0; i < 100; i++) {//循环100次把数据插入WeakHashMap中
			byte[] bytes = new byte[_1MB];
			map.put(bytes, value);
		}
		while (true) {//死循环监控map大小变化
			Thread.sleep(500);//稍稍停顿，效果更直观
			System.out.println(map.size());//打印WeakHashMap的大小
			System.gc();//建议系统进行GC
		}
	}
}
