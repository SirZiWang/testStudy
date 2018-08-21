package com.wangzi.test.jdk18;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
public class Collections {

	public static void main(String[] args) {
		List<String> names = Arrays.asList("Tom", "Peter", "Bob", "Lily");
		java.util.Collections.sort(names, new Comparator<String>() {

			@Override
			public int compare(String a, String b) {
				return b.compareTo(a);
			}
		});
		
		java.util.Collections.sort(names, (String a, String b) -> b.compareTo(a));
	}
}
