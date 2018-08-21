package com.wangzi.test.jdk18;

import java.util.function.Predicate;

public class PredicateDemo {

	public static void main(String[] args) {
		String str = "Hello World";
		boolean flag = testPred(str, (s) -> s.length() > 0);
		System.out.println(flag);
	}

	private static boolean testPred(String str, Predicate<String> pred) {
		boolean flag = pred.test(str);
		return flag;
	}

}
