package com.wangzi.test.algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PartitionLabels {
	
	public List<Integer> partitionLabels(String S) {
		
		Map<Character, Integer> map = new HashMap<>();  //记录char c 和其最后出现位置的 map
		List<Integer> res = new ArrayList<>();
		int start = 0, end = 0;
		for(int i=0; i<S.length(); i++) {
			map.put(S.charAt(i), i);
		}
		for(int i=0; i<S.length(); i++) {
			end = Math.max(end, map.get(S.charAt(i)));
			if(i == end) {
				res.add(end - start + 1);
				start = i + 1;
			}
		}
		return res;
    }
	public static void main(String[] args) {
		PartitionLabels p = new PartitionLabels();
		String S = "ababcbacadefegdehijhklij";
		p.partitionLabels(S);
	}
}
