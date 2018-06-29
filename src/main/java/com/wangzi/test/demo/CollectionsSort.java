package com.wangzi.test.demo;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CollectionsSort {
	public static void main(String[] args) {
		List<String> list = Arrays.asList("1","3","8","4","7","12","2");
		Collections.sort(list);
		for(String ss : list){
			System.out.println(ss);
		}
	}
}
