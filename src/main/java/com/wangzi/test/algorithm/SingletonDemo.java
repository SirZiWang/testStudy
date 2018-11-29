package com.wangzi.test.algorithm;

public class SingletonDemo {
	
	// 缺点：无论这个类是否被使用，都会创建一个instance对象
	//	private static final SingletonDemo instance = new SingletonDemo(); 
	//	private SingletonDemo(){}
	//	
	//	public static SingletonDemo getSingleton(){
	//		return instance;
	//	}

	// 缺点：多线程下面有可能会创建多个对象
	//	private static SingletonDemo instance = null;
	//	private SingletonDemo(){}
	//	public static SingletonDemo getSingleton(){
	//		if(instance == null){
	//			instance = new SingletonDemo();
	//		}
	//		return instance;
	//	}

	// 缺点：synchronized修饰的同步块可是要比一般的代码段慢上几倍的！如果存在很多次getInstance()的调用	
	//	private static SingletonDemo instance = null;
	//	private SingletonDemo(){}
	//	public synchronized static SingletonDemo getSingleton(){
	//		if(instance == null){
	//			instance = new SingletonDemo();
	//		}
	//		return instance;
	//	}

	private volatile static SingletonDemo instance = null;
	private SingletonDemo(){}
	public static SingletonDemo getSingleton(){
		if(instance == null){
			synchronized (SingletonDemo.class) {
				if (instance == null) { 
					instance = new SingletonDemo(); 
				} 
			}
		}
		return instance;
	}
}
