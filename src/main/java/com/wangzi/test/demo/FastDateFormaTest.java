package com.wangzi.test.demo;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.time.FastDateFormat;

public class FastDateFormaTest {
	/*patten*/  
	public final static String PATTEN_YEAR = "yyyy";
	public final static String PATTEN_YEAR_MM = "yyyy-MM"; 
	public final static String PATTEN_YEAR_MM_DD = "yyyy-MM-dd";
	public final static String PATTEN_YEAR_MM_DD_HH = "yyyy-MM-dd HH";
	public final static String PATTEN_YEAR_MM_DD_HH_MM = "yyyy-MM-dd HH:mm";
	public final static String PATTEN_YEAR_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
	public final static String PATTEN_YEAR_MM_DD_HH_MM_SS_MS = "yyyy-MM-dd HH:mm:ss:ms";

	public static  void main(String[] args)  {  
		/** 
		 * 测试10次 
		 */  
		for(int i=1;i<=10;i++){ 
			testDateFormat(i); 
		}  
//		Date date = new Date();  
//		System.out.println(dateToString(date,PATTEN_YEAR));  
//		System.out.println(dateToString(date,PATTEN_YEAR_MM));  
//		System.out.println(dateToString(date,PATTEN_YEAR_MM_DD));  
//		System.out.println(dateToString(date,PATTEN_YEAR_MM_DD_HH));  
//		System.out.println(dateToString(date,PATTEN_YEAR_MM_DD_HH_MM));  
//		System.out.println(dateToString(date,PATTEN_YEAR_MM_DD_HH_MM_SS));  
//		System.out.println(dateToString(date,PATTEN_YEAR_MM_DD_HH_MM_SS_MS));  
	}  

	/** 
	 * 指定日期及需输出格式，返回格式化后的字符串 
	 * @param date 
	 * @param patten 
	 * @return 
	 */  
	public final static String dateToString(Date date,String patten) {  
		FastDateFormat fastDateFormat = FastDateFormat.getInstance(patten);  
		return fastDateFormat.format(date);  
	}  



	/** 
	 *  testDateFormat 
	 */  
	public static void testDateFormat(int num){  
		System.out.println("执行第"+num+"次");  
		int cont = 200000;  

		long l1 = System.currentTimeMillis();  
		for(int i=0;i<=cont;i++){  
			SimpleDateFormat s = new SimpleDateFormat(PATTEN_YEAR_MM_DD_HH_MM_SS);  
			Date date = new Date();  
			String string = s.format(date); 
//			System.out.println(string);

		}  
		long l2 =  System.currentTimeMillis();  
		System.out.println("SimpleDateFormat covert time:"+(l2-l1));  

		for(int i=0;i<=cont;i++){  
			FastDateFormat s = FastDateFormat.getInstance(PATTEN_YEAR_MM_DD_HH_MM_SS);  
			Date date = new Date();  
			String string = s.format(date);  
//			System.out.println(string);  
		}  
		long l3 =  System.currentTimeMillis();  
		System.out.println("FastDateFormat covert time:"+(l3-l2));  

	}  
}
