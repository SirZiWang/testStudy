package com.wangzi.test.dataStructure;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;

public class FIFOCache {
	
	//按照访问时间排序,保存所有key-value 
	private final Map<String, Value> CACHE = new LinkedHashMap<>();
	// 过期数据，只保存有过期时间的key 
	private final Map<Long, String> EXPIRED = new TreeMap<>();
	//队列的初始化大小
	private final int capacity;
	
	public FIFOCache(int capacity) {
		this.capacity = capacity;
	}
	
	public Object get(String key){
		Value value = CACHE.get(key);
		if(value == null)
			return null;
		//如果不包含过期时间
		long expired = value.expired;
		long now = System.nanoTime();
		//已过期
		if(expired > 0 && expired <= now){
			CACHE.remove(key);
			EXPIRED.remove(expired);
			return null;
		}
		return value.value;
	}
	
	public void put(String key,Object value) {
		put(key, value, -1);
	}
	
	public void put(String key, Object value, int seconds) {
		//如果容量不足，移除过期的
		if(capacity < CACHE.size()) {
			long now = System.nanoTime();
			Iterator<Long> iterator = EXPIRED.keySet().iterator();
			while(iterator.hasNext()) {
				long _key = iterator.next();
				//如果已过期，或者容量仍然溢出，则删除  
				if(_key > now)
					break;
				//一次性移除所有过期的key
				String _value = EXPIRED.get(_key);  
				CACHE.remove(_value);  
				iterator.remove();  
			}
		}
		//如果仍然容量不足，则移除最早访问的数据  
		if(capacity < CACHE.size()) {
			Iterator<String> iterator = CACHE.keySet().iterator();
			while(iterator.hasNext() && capacity < CACHE.size()) {
				String _key = iterator.next();
				Value _value = CACHE.get(_key);
				long expired = _value.expired;
				if(expired > 0) {
					EXPIRED.remove(expired);
				}
				iterator.remove();
			}
		}
		//如果key已经存在，移除旧的
		Value current = CACHE.remove(key);
		if(current != null && current.expired > 0) {
			EXPIRED.remove(current.expired);
		}
		
		// 如果指定了过期时间
		if(seconds > 0) {
			long expireTime = expireTime(seconds);
			EXPIRED.put(expireTime, key);  
            CACHE.put(key, new Value(expireTime, value));  
		} else {
			 CACHE.put(key, new Value(-1, value)); 
		}
	}
	
	private long expireTime(int expired) {
		return System.nanoTime() + TimeUnit.SECONDS.toNanos(expired);
	}

	public void remove(String key) {
		Value value = CACHE.remove(key);  
        if(value == null) {  
            return;  
        }  
        long expired = value.expired;  
        if (expired > 0) {  
            EXPIRED.remove(expired);  
        }  
	}
	
	class Value {
		
		long expired;
		Object value;
		public Value(long expried, Object value){
			this.expired = expried;
			this.value = value;
		}
	}
}
