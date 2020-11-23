package com.wangzi.test.algorithm;

import java.util.Arrays;
import java.util.Comparator;

public class SmallerNumbersThanCurrent {

	// O(N^2) O(1)
	public int[] smallerNumbersThanCurrent(int[] nums) {
		int[] res = new int[nums.length];
		for(int i=0; i<nums.length; i++) {
			int count = 0;
			for(int j=0; j<nums.length; j++) {
				if(nums[i] > nums[j])
					count++;
			}
			res[i] = count;
		}
		return res;
	}

	//快速排序
	public int[] smallerNumbersThanCurrent1(int[] nums) {
		int n = nums.length;
		int[][] data = new int[nums.length][2];
		for(int i=0; i<n; i++) {
			data[i][0] = nums[i];
			data[i][1] = i;
		}

		Arrays.sort(data, new Comparator<int[]>() {
			@Override
			public int compare(int[] data1, int[] data2) {
				return data1[0] - data2[0];
			}
		});

		int[] res = new int[n];
		int prev = -1;
		for(int i=0; i<n; i++) {
			if(prev == -1 || data[i][0] != data[i-1][0]) 
				prev = i;
			res[data[i][1]] = prev;
		}
		return res;
	}
	
	// 计数排序
	public int[] smallerNumbersThanCurrent2(int[] nums) {
		int n = nums.length;
		int[] cnt = new int[101];
		for(int i=0; i<n; i++) // 统计值为i在数组中出现的次数
			cnt[nums[i]]++;
		for(int i=1; i<=100; i++) // 统计值<=i在数组中出现的个数
			cnt[i] +=cnt[i-1];
		int[] ret = new int[n];
        for (int i = 0; i < n; i++) // 将值<nums[i]的数字个数及cnt[i]-1的值放入最后的结果数组
            ret[i] = nums[i] == 0 ? 0 : cnt[nums[i] - 1];
        return ret;
	}
	
	public static void main(String[] args) {
		SmallerNumbersThanCurrent s = new SmallerNumbersThanCurrent();
		int[] nums = new int[]{8,1,2,2,3};
		s.smallerNumbersThanCurrent2(nums);
	}
}
