package com.wangzi.test.jdk18;

import java.util.function.Consumer;

public class ConsumerDemo {
	
	public static void main(String[] args) {
		String str = "Hello World";
		conTest(str, (s) -> System.out.println(s));
	}
	
	public static void conTest(String str, Consumer<String> con){
		con.accept(str);
	}
}
