package com.wangzi.test.jdk18;

@FunctionalInterface
public interface Converter<F, T> {
	T convert(F from);
	Converter<String, Integer> converter =  (from) -> Integer.valueOf(from);
	Integer converted = converter.convert("123");
	
	Converter<String, Integer> converter2 = Integer::valueOf;
	Integer converted2 = converter2.convert("123");
	
}