package com.wangzi.test.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class ReconstructQueue {

	public int[][] reconstructQueue(int[][] people) {

		Arrays.sort(people, new Comparator<int[]>(){
			@Override
			public int compare(int[] person1, int[] person2) {
				if(person1[0] != person2[0])
					return person1[0] - person2[0];
				else
					return person2[1] - person1[1];
			}
		});
		int n = people.length;
		int[][] res = new int[n][];
		for(int[] person : people) {
			int spaces = person[1] + 1;
			for (int i = 0; i < n; ++i) {
				if (res[i] == null) {
					--spaces;
					if (spaces == 0) {
						res[i] = person;
						break;
					}
				}
			}
		}
		return res;
	}
	
	public int[][] reconstructQueue1(int[][] people){
//		Arrays.sort(people, new Comparator<int[]>(){
//			@Override
//			public int compare(int[] person1, int[] person2) {
//				if(person1[0] != person2[0])
//					return person2[0] - person1[0];
//				else
//					return person2[1] - person1[1];
//			}
//		});
		
		Arrays.sort(people, (o1,o2) -> 
			o1[0] == o2[0] ? o1[1] - o2[1] : o2[0] - o1[0]);
		List<int[]> res = new ArrayList<>();
		for(int[] person : people) {
			res.add(person[1], person);
		}
		return res.toArray(new int[res.size()][]);
	}
}
