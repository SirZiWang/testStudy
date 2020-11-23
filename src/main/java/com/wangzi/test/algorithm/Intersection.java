package com.wangzi.test.algorithm;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Intersection {

	public int[] intersection(int[] nums1, int[] nums2) {
		Set<Integer> set1 = new HashSet<>();
		Set<Integer> set2 = new HashSet<>();
		for(int i=0; i<nums1.length; i++)
			set1.add(nums1[i]);
		for(int i=0; i<nums2.length; i++)
			set2.add(nums2[i]);
		set1.retainAll(set2);
		int[] res = new int[set1.size()];
		int k = 0;
		for(Iterator<Integer> it = set1.iterator(); it.hasNext(); ){
			res[k] = it.next();
			k++;
		}
		return res;
	}
}
