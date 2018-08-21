package com.wangzi.test.collections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class SynchronizedListTest {
	public static void main(String[] args) {
		ArrayList<Integer> arrayList = new ArrayList<>();
		int len = 9000000;
		for (int i = 0; i < len; i++) {
			arrayList.add(i);
		}
		System.out.println(len + "个数据: ");
		List<Integer> list = Collections.synchronizedList(arrayList);

		long start = System.currentTimeMillis();
		for (int size = list.size(), i = 0; i < size; i++) {
			int sta = list.get(i);
		}
		System.out.println("for循环遍历耗时： " + (System.currentTimeMillis()-start) + "毫秒！");

		long second = System.currentTimeMillis();
		Iterator<Integer> iterator = list.iterator();
		while (iterator.hasNext()) {
			int sec = iterator.next();
		}
		System.out.println("迭代器Iterator遍历耗时： " + (System.currentTimeMillis()-second) + "毫秒！");

		long third = System.currentTimeMillis();
		for (int i : list) {// 也是Iterator实现的
			int thi = i;
		}
		System.out.println("foreach循环遍历耗时： " + (System.currentTimeMillis()-third) + "毫秒！");

		long fourth = System.currentTimeMillis();
		ListIterator<Integer> listIterator = list.listIterator();
		while (listIterator.hasNext()) {
			int fou = listIterator.next();
		}
		System.out.println("迭代器ListIterator循环遍历耗时： " + (System.currentTimeMillis()-fourth) + "毫秒！");

		long firth = System.currentTimeMillis();
		list.forEach(integer -> {
			int fir = integer;
		});
		System.out.println("forEach循环遍历耗时： "+ (System.currentTimeMillis()-firth) + "毫秒！");
	}
}
