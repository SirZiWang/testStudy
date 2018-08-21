package com.wangzi.test.jdk18;

import java.util.ArrayList;
import java.util.List;

public class ForEachDemo {
	public static void main(String[] args) {
		List<String> list = new ArrayList<>();
		list.add("e");
		list.add("c");
		list.add("a");
		list.add("d");
		list.add("b");
		list.sort((s1, s2) -> s1.compareTo(s2));
		list.forEach((s) -> System.out.println(s));
		
		list.forEach(System.out::println);

	}
}
