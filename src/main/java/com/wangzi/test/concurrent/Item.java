package com.wangzi.test.concurrent;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class Item implements Delayed {

	private long time; //触发时间
	public String name;
	
	public Item() {}
	public Item( String name, long time, TimeUnit unit) {
		this.name = name;
		this.time = System.currentTimeMillis() + (time > 0? unit.toMillis(time): 0);
	}
	@Override
	public int compareTo(Delayed o) {
		Item item = (Item) o;
        long diff = this.time - item.time;
        if (diff <= 0) {// 改成>=会造成问题
            return -1;
        }else {
            return 1;
        }
	}

	@Override
	public long getDelay(TimeUnit unit) {
		return time - System.currentTimeMillis();
	}
	
	@Override
	public String toString() {
		return "Item [time=" + time + ", name=" + name + "]";
	}

}
