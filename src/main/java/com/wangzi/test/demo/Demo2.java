package com.wangzi.test.demo;

public interface Demo2 {
	default String test(){
		return "hello world";
	}
}
