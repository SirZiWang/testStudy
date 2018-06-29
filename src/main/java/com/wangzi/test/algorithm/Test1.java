package com.wangzi.test.algorithm;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

public class Test1 {
	
	public static void main(String[] args) {
		
		int[] arr = {1,10,11,12,13,6,9,4,5,6,7,10,9};
//		Set<Integer> set = new LinkedHashSet<>();
//		Set<Integer> set = new HashSet<>();
		Set<Integer> set = new TreeSet<>();
		for(int i=0;i<arr.length-1;i++){
			if(arr[i+1]-arr[i] == 1){
				set.add(arr[i]);
				set.add(arr[i+1]);
			}
		}
		for (Integer integer : set) {
			System.out.print(integer +" ");
		}
	}
}
