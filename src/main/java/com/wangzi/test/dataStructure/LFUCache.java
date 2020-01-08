package com.wangzi.test.dataStructure;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.TreeMap;

public class LFUCache {
	
	// 主要容器，保存k-v
	private Map<Integer, Integer> keyToValue = new HashMap<>();
	// 记录每个key的访问次数
	private Map<Integer, Integer> keyToCounts = new HashMap<>();
	//访问相同次数的key列表，按照访问次数排序，value为相同访问次数到key列表。  
    private TreeMap<Integer, LinkedHashSet<Integer>> countToLRUKeys = new TreeMap<>(); 
	//初始化容量的大小
	private int capacity;
	
	public LFUCache(int capacity){
		this.capacity = capacity;
	}
	
	public Object get(int key){
		if(!keyToValue.containsKey(key))
			return -1;
		touch(key);
		return keyToValue.get(key);
	}
	
	/**
	 * 如果一个key被访问，应该将其访问次数调整。 
	 * @param key
	 */
	private void touch(int key) {
		int count = keyToCounts.get(key);
		keyToCounts.put(key, count+1);  //访问次数+1
		//从原有访问次数统计列表中移除  
		countToLRUKeys.get(count).remove(key); 
		//如果符合最少调用次数到key统计列表为空，则移除此调用次数到统计 
		if(countToLRUKeys.get(count).size() == 0){
			countToLRUKeys.remove(count);
		}
		
		//然后将此key的统计信息加入到管理列表中
		LinkedHashSet<Integer> countKeys = countToLRUKeys.get(count+1);
		if (countKeys == null) {  
            countKeys = new LinkedHashSet<>();  
            countToLRUKeys.put(count + 1,countKeys);  
        }  
        countKeys.add(key);  
	}
	
	public void put(int key, int value){
		if(capacity <=0){
			return;
		}
		if(keyToValue.containsKey(key)) {
			keyToValue.put(key, value);
			touch(key);
			return;
		}
		//容量超额之后，移除访问次数最少的元素 
		if(keyToValue.size() >= capacity) {
			Map.Entry<Integer, LinkedHashSet<Integer>> entry = countToLRUKeys.firstEntry();
			Iterator<Integer> it = entry.getValue().iterator();
			Integer evictKey = it.next();
			it.remove();
			if(!it.hasNext()) {
				countToLRUKeys.remove(entry.getKey());
			}
			keyToCounts.remove(evictKey);  
            keyToValue.remove(evictKey);  
		}
		keyToValue.put(key, value);  
        keyToCounts.put(key, 1);  
        LinkedHashSet<Integer> keys = countToLRUKeys.get(1);  
        if (keys == null) {  
            keys = new LinkedHashSet<>();  
            countToLRUKeys.put(1, keys);  
        }  
        keys.add(key); 
	}
	public static void main(String[] args) {
		LFUCache LFU = new LFUCache(3);
		LFU.put(1, 1);
		LFU.put(2, 2);
		System.out.println(LFU.get(2));
		System.out.println(LFU.get(1));
		System.out.println(LFU.get(2));
		LFU.put(3, 3);
		LFU.put(4, 4);
		System.out.println(LFU.get(3));
		System.out.println(LFU.get(2));
		System.out.println(LFU.get(1));
		System.out.println(LFU.get(4));

	}
}
