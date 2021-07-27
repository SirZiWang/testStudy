package com.wangzi.test.collections;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

public class LinkedHashMapDemo {

	public static void main(String[] args) {
		LinkedHashMap<Integer, Integer> map = new LinkedHashMap<Integer, Integer>(20, 0.75f, true) {
			{
				for (int i = 0; i < 10; i++) {//按顺序放入1~9
					put(i, i);
				}
			}
			@Override
			protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
				return size() > 3;
			}
		};

		System.out.println("原数据："+map.toString());

		for (Entry<Integer, Integer> entry:map.entrySet()) {
			System.out.println("key="+entry.getKey()+" "+"value="+entry.getValue());
		}
		map.get(7);
		System.out.println("查询存在的某一个："+map.toString());
		for (Entry<Integer, Integer> entry:map.entrySet()) {
			System.out.println("key="+entry.getKey()+" "+"value="+entry.getValue());
		}
		map.put(4, 4);
		System.out.println("插入已存在的某一个："+map.toString());
		for (Entry<Integer, Integer> entry:map.entrySet()) {
			System.out.println("key="+entry.getKey()+" "+"value="+entry.getValue());
		}
		map.put(10, 10);
		System.out.println("插入一个原本没存在的："+map.toString());
		for (Entry<Integer, Integer> entry:map.entrySet()) {
			System.out.println("key="+entry.getKey()+" "+"value="+entry.getValue());
		}
	}

}
