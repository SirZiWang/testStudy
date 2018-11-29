package com.wangzi.test.algorithm;

import java.util.concurrent.TimeUnit;

public class StringStringBufferDemo {
	
	public static void main(String[] args) {
		/**
		 * for (int i = 0; i < times; i++) { 
		 * 		StringBuilder sb = new StringBuilder(str);
		 *		sb.append(tempstr); 
		 *		str = sb.toString(); 
		 * }
		 */
		
		long t0 = System.nanoTime();
		String str = "str";
		for (int i = 0; i < 100000; i++) {
			str = str + i;
		}
		long t1 = System.nanoTime();
		long millis = TimeUnit.NANOSECONDS.toMillis(t1 - t0);
		System.out.println(String.format("String : %d ms", millis));
		
		StringBuffer sb = new StringBuffer(str);
		long t2 = System.nanoTime();
		for (int j = 0; j < 100000; j++) {
			sb.append(j);
		}
		long t3 = System.nanoTime();
		long millis1 = TimeUnit.NANOSECONDS.toMillis(t3 - t2);
		System.out.println(String.format("String : %d ms", millis1));
	}
}
