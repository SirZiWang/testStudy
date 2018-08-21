package com.wangzi.test.jdk18;

public interface Formula {
	
	public double calculate(int num);
	default double sqlit(int num){
		return Math.sqrt(num);
	}
}
