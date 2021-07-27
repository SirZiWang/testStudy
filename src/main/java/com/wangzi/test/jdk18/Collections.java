package com.wangzi.test.jdk18;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.MapDifference;
import com.google.common.collect.Maps;
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
		map.putIfAbsent("2", "FFF");
		map.compute("3", (k,v) -> k+v);
		map.computeIfPresent("2",(k,v) -> k+v);
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

		Integer[] i = new Integer[]{1,2,3,4,5};
		List<Integer> l = Arrays.asList(i);
		System.out.println(l);
		i[0] = 10;
		System.out.println(l.get(0));
//		l.add(6);   //  ArrayList 没有实现 add()方法，调用add()方法时，实际是调用AbstractList 的add()方法，该方法直接抛出UnsupportedOperationException 异常
//		System.out.println(l);



		List<Integer> list = new ArrayList<Integer>(){{
			add(1);
			add(2);
			add(3);
			add(4);
		}};
		// 下面这行被注释的代码这么写是无法转化成数组的，无参 toArray 返回的是 Object[],
		// 无法向下转化成 List<Integer>，编译都无法通过
//		 List<Integer> list2 = list.toArray();
		System.out.println(list);
		list = Lists.reverse(list);  // list翻转
		System.out.println(list);
		
		List<List<Integer>> lists = Lists.partition(list, 2);  // list切割
		System.out.println(lists);
		
		// 演示有参 toArray 方法，数组大小不够时，得到数组为 null 情况
		Integer[] array0 = new Integer[2];
		list.toArray(array0);
		System.out.println(array0[0] + "====" + array0[1]);
		
		// 演示数组初始化大小正好，正好转化成数组
		Integer[] array1 = new Integer[list.size()];
		list.toArray(array1);
		System.out.println(array1[3]);
		
		// 演示数组初始化大小大于实际所需大小，也可以转化成数组
		Integer[] array2 = new Integer[list.size()+2];
		list.toArray(array2);
		System.out.println(array2[3] +"======"+ array2[4]);
		
		
		Map<String,String> withExpectedSizeHashMap = Maps.newHashMapWithExpectedSize(20);
		
		// ImmutableMap.of 也是 Guava 提供初始化 Map 的方法，入参格式为 k1,v1,k2,v2,k3,v3……
		Map<String,String> leftMap = ImmutableMap.of("1","1","2","2","3","3");
		Map<String,String> rightMap = ImmutableMap.of("2","2","3","30","4","4");
		MapDifference<String,String> difference = Maps.difference(leftMap, rightMap);
		System.out.println("左边 map 独有 key：" + difference.entriesOnlyOnLeft());
		System.out.println("右边 map 独有 key：" + difference.entriesOnlyOnRight());
		System.out.println("左右边 map 都有 key，并且 value 相等：" + difference.entriesInCommon());
		System.out.println("左右边 map 都有 key，但 value 不等：" + difference.entriesDiffering());
		
		
		List<Integer> lists1 = new CopyOnWriteArrayList<>();
		lists1.add(10);
		lists1.add(1,3);
		System.out.println(lists1);
	}
}
