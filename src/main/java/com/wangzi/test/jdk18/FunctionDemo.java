package com.wangzi.test.jdk18;

import java.util.function.Function;

public class FunctionDemo {
	
	public static void main(String[] args) {
		String str = "Hello world!";
		int len = testFun(str, (s) -> s.length());
		System.out.println(len);
		
		long l1 = testAbs(-10L, (s) -> Math.abs(s));
		long l2 = testAbs(-20L, Math::abs);
		System.out.println("l1=" + l1 +",l2="+ l2);
		
	}
	
	public static int testFun(String str, Function<String, Integer> fun){
		Integer length = fun.apply(str);
		return length;
	}
	
	public static long testAbs(long s, Function<Long, Long> fun) {
		Long l = fun.apply(s);
		return l;
	}

}
