package com.wangzi.test.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FullPermutation {
	
	public static void finishFullPermutation(char[] array) {
		permutation(array, 0, array.length);
	}

	private static void permutation(char[] array, int start, int end) {
		
		if(end<=0){  //字符串中没有元素直接返回
			return;
		}
		if(start == end){
			System.out.println(array);
		}
		
		for(int i=start; i<end; i++){
			swap(array, i, start);  // 更换前缀
			permutation(array, start + 1, end); // 递归将剩余元素全排列
			swap(array, start, i); //将前缀换回，以便进行上一个前缀的全排列
		}
	}
	private static void swap(char[] array, int i, int j) {
		char temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}
	public static void main(String[] args) {
		char[] array = {'a','b','b','c'};
		finishFullPermutation2(array);
	}
	
	public static List<String> list = new ArrayList<>();
	public static void finishFullPermutation2(char[] array) {
		if(list != null) {   //调用该方法前判断存放组合的list是否为空，也顺便解决了多次调用要清空list的问题
			list.removeAll(list);
		}
		permutation2(array, 0, array.length);
	}
	public static void permutation2(char[] array, int start, int end) {   
		if(end < 0) {
			return;
		}
		if(start == end) {
			String node = Arrays.toString(array); //将组合转换为字符串方便判断
			if(!list.contains(node)) { //判断该组合在list中是否已经存在
				list.add(node);
				System.out.println(node); //打印list中的组合
			} 
		}else {
			for(int i = start; i < end; i++) {
				swap(array, i, start);
				permutation2(array, start + 1, end);
				swap(array, start, i);
			}
		}
	}
}
