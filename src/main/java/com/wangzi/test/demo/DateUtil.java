package com.wangzi.test.demo;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * SimpleDateFormat中的日期格式不是同步的。推荐（建议）为每个线程创建独立的格式实例。如果多个线程同时访问一个格式，则它必须保持外部同步。
 * JDK原始文档如下：
 * Synchronization：
 * Date formats are not synchronized. 
 * It is recommended to create separate format instances for each thread. 
 * If multiple threads access a format concurrently, it must be synchronized externally.
 * 
 * calendar.setTime(date)这条语句改变了calendar，稍后，calendar还会用到（在subFormat方法里），
 * 而这就是引发问题的根源。想象一下，在一个多线程环境下，有两个线程持有了同一个SimpleDateFormat的实例，分别调用format方法：
 * 线程1调用format方法，改变了calendar这个字段。
 * 中断来了。
 * 线程2开始执行，它也改变了calendar。
 * 又中断了。
 * 线程1回来了，此时，calendar已然不是它所设的值，而是走上了线程2设计的道路。如果多个线程同时争抢calendar对象，则会出现各种问题，时间不对，线程挂死等等。
 * 分析一下format的实现，我们不难发现，用到成员变量calendar，唯一的好处，就是在调用subFormat时，少了一个参数，
 * 却带来了这许多的问题。其实，只要在这里用一个局部变量，一路传递下去，所有问题都将迎刃而解。
 * 这个问题背后隐藏着一个更为重要的问题--无状态：无状态方法的好处之一，就是它在各种环境下，都可以安全的调用。衡量一个方法是否是有状态的，
 * 就看它是否改动了其它的东西，比如全局变量，比如实例的字段。format方法在运行过程中改动了SimpleDateFormat的calendar字段，所以，它是有状态的。
 * 这也同时提醒我们在开发和设计系统的时候注意下一下三点:
 * 1.自己写公用类的时候，要对多线程调用情况下的后果在注释里进行明确说明
 * 2.对线程环境下，对每一个共享的可变变量都要注意其线程安全性
 * 3.我们的类和方法在做设计的时候，要尽量设计成无状态的
 * 
 * @author Administrator
 *
 */

public class DateUtil {

	//	private final static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	//	
	//	public static String formatDate(Date  date) throws ParseException{
	//		return sdf.format(date);
	//	}
	//	
	//	public static Date parse(String strDate) throws ParseException{
	//		return sdf.parse(strDate);
	//	}

	/**
	 * 1.需要的时候创建新实例
	 */
	//	public static String formatDate(Date  date) throws ParseException{
	//		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	//		 return sdf.format(date);
	//	}
	//	
	//	public static Date parse(String strDate) throws ParseException{
	//		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	//		 return sdf.parse(strDate);
	//	}

	/**
	 * 使用同步：同步SimpleDateFormat对象
	 */
	//	public static String formatDate(Date  date) throws ParseException{
	//		synchronized(sdf){
	//			return sdf.format(date);
	//		}
	//	}
	//	
	//	public static Date parse(String strDate) throws ParseException{
	//		synchronized(sdf){
	//			return sdf.parse(strDate);
	//		}
	//	}
	

	/**
	 * 使用ThreadLocal
	 */
	//	private static ThreadLocal<DateFormat> threadLocal = new ThreadLocal<DateFormat>(){
	//		
	//		@Override
	//		protected DateFormat initialValue() {
	//            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	//        }
	//		
	//	};
	
	
//		public static Date parse(String dateStr) throws ParseException {
//	        return threadLocal.get().parse(dateStr);
//	    }
//	
//	    public static String formatDate(Date date) {
//	        return threadLocal.get().format(date);
//	    }

	private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
	private static ThreadLocal<DateFormat> threadLocal = new ThreadLocal<DateFormat>(); 

	public static DateFormat getDateFormat() {  
		DateFormat df = threadLocal.get();  
		if(df==null){  
			df = new SimpleDateFormat(DATE_FORMAT);  
			threadLocal.set(df);  
		}  
		return df;  
	}  

	public static String formatDate(Date date) throws ParseException {
		return getDateFormat().format(date);
	}

	public static Date parse(String strDate) throws ParseException {
		return getDateFormat().parse(strDate);
	}   

}
