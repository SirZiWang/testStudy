package com.wangzi.test.jdk18;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Stream {

	public static void main(String[] args) {
		List<String> stringCollection = new ArrayList<>();
		stringCollection.add("ddd2");
		stringCollection.add("aaa2");
		stringCollection.add("bbb1");
		stringCollection.add("aaa1");
		stringCollection.add("bbb3");
		stringCollection.add("ccc");
		stringCollection.add("bbb2");
		stringCollection.add("ddd1");

		stringCollection
		.stream()
		.filter((s) -> s.startsWith("a"))
		.forEach(System.out::println);

		stringCollection
		.stream()
		.sorted()
		.filter((s) -> s.startsWith("b"))
		.forEach(System.out::println);

		stringCollection
		.stream()
		.map(String::toUpperCase)
		.sorted((a, b) -> b.compareTo(a))
		.forEach(System.out::println);

		boolean anyStartsWithA = 
				stringCollection
				.stream()
				.anyMatch((s) -> s.startsWith("a"));
		System.out.println(anyStartsWithA);      // true

		boolean allStartsWithA = 
				stringCollection
				.stream()
				.allMatch((s) -> s.startsWith("a"));

		System.out.println(allStartsWithA);      // false

		boolean noneStartsWithZ = 
				stringCollection
				.stream()
				.noneMatch((s) -> s.startsWith("z"));

		System.out.println(noneStartsWithZ);      // true

		long startsWithB = 
				stringCollection
				.stream()
				.filter((s) -> s.startsWith("b"))
				.count();
		System.out.println(startsWithB);  // 3

		Optional<String> reduced =
				stringCollection
				.stream()
				.sorted()
				.reduce((s1, s2) -> s1 + "#" + s2);
		reduced.ifPresent(System.out::println);
		// "aaa1#aaa2#bbb1#bbb2#bbb3#ccc#ddd1#ddd2"

		long count = stringCollection.
				stream()
				.filter((s) -> s.length() > 2)
				.count();
		System.out.println(count);
		List<String> list2 = 
				stringCollection
				.stream()
				.filter((s) -> s.length() > 2)
				.collect(Collectors.toList());
		 
		list2.forEach(System.out :: println);

	}

}
