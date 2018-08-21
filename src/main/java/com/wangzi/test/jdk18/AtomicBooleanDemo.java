package com.wangzi.test.jdk18;

import java.util.concurrent.atomic.AtomicBoolean;

public class AtomicBooleanDemo {
	
	public static void main(String[] args) {
		
		AtomicBoolean atBoolean = new AtomicBoolean(true);
		boolean expectedValue = true;  
		boolean newValue = false;  
		boolean wasNewValueSet = atBoolean.compareAndSet(expectedValue, newValue);
		System.out.println(wasNewValueSet);
		boolean flag = atBoolean.get();
		System.out.println(flag);
	}
}
