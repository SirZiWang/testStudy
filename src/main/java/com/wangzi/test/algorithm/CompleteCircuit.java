package com.wangzi.test.algorithm;

public class CompleteCircuit {

	public int canCompleteCircuit(int[] gas, int[] cost) {
		int n = gas.length;
		for(int i = 0; i<n; i++) {
			int j = i;
			int remain = gas[j];  // 当前可以获得燃料
			while(remain - cost[j] >= 0) {
				remain = remain  - cost[j] + gas[(j + 1) % n]; // 消耗燃料+可以获得燃料
				j = (j+1) % n;
				if(j == i)
					return i;
			}
		}
		return -1;
	}
}
