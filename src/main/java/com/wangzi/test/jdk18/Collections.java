package com.wangzi.test.jdk18;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
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
		
		Map<String, String> map = new HashMap<>();
		map.put("1", "A");
		map.put("2", "B");
		map.put("3", "C");
		map.put("4", "D");
		map.put("5", "E");
		map.forEach((k, v) ->{
			System.out.println(k +" : " + v);
		});
		
		Set<Entry<String, String>> entrySet = map.entrySet();
		for(Entry<String, String> entry : entrySet){
			 System.out.println("key = " + entry.getKey() + ", value = " + entry.getValue());
		}
		
		Iterator<Entry<String, String>> it = entrySet.iterator();
		while(it.hasNext()){
			Map.Entry<String, String> entry = it.next();
			System.out.println("key = " + entry.getKey() + ", value = " + entry.getValue());
		}
	}
}
