package com.wangzi.test.algorithm;

public class StringBufferTest {
	
	public static void main(String[] args) {
		StringBuffer str = new StringBuffer();
		str.append("12345");
		System.out.println(str.capacity());		//16
		System.out.println(str.length());		//5
		str.append("67890123456"); 	
		System.out.println(str.capacity());		//16
		System.out.println(str.length());		//16
		str.append("1");
		System.out.println(str.capacity());		//34
		System.out.println(str.length());		//17
		str = new StringBuffer("123");
		System.out.println(str.capacity());		//19
		System.out.println(str.length());		//3
	}

}
