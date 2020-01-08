package com.wangzi.test.dataStructure;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import com.wangzi.test.guava.Student;

public class Test {
	public static int longestConsecutive(int[] num) {
		// write your code here
		HashSet<Integer> set = new HashSet<>();
		for(int number : num) {
			set.add(number);
		}
		int ans = 0;
		for(int item : num){
			if(set.contains(item)){
				set.remove(item);

				int left = item -1;
				int right = item + 1;
				while(set.contains(left)){
					set.remove(left);
					left--;
				}
				while(set.contains(right)){
					set.remove(right);
					right++;
				}
				ans = Math.max(ans, right - left - 1);
			}
		}
		return ans;
	}


	public static List<List<Integer>> result = new ArrayList<>();
	public static List<List<Integer>> fourSum(int[] numbers, int target) {
		// write your code here
		Arrays.sort(numbers);
		for(int i=0; i<numbers.length; i++) {
			if(i>0 && numbers[i] == numbers[i-1])
				continue;
			for(int j=i+1; j<numbers.length; j++) {
				if(j>i+1 && numbers[j] == numbers[j-1])
					continue;
				for(int k=j+1; k<numbers.length; k++){
					if(k>j+1 && numbers[k] == numbers[k-1])
						continue;
					for(int l=k+1; l<numbers.length; l++){
						if(l>k+1 && numbers[l] == numbers[l-1])
							continue;
						if(numbers[i] + numbers[j] + numbers[k] + numbers[l] == target){
							List<Integer> list = new ArrayList<>();
							list.add(numbers[i]);
							list.add(numbers[j]);
							list.add(numbers[k]);
							list.add(numbers[l]);
							result.add(list);
						}
					}
				}
			}
		}
		return result;
	}

	public boolean compareStrings(String A, String B) {
		//counts首先记录A中所有的字符以及它们的个数，然后遍历B,如果出现counts[i]小于0的情况，说明B中该字符出现的次数
		//大于在A中出现的次数
		int[] counts = new int[26];
		for (int i = 0; i < 26; i++) {
			counts[i] = 0;
		}
		for (int i = 0; i < A.length(); i++) {
			counts[A.charAt(i) - 'A'] ++;
		}
		for (int i = 0; i < B.length(); i++) {
			if (--counts[B.charAt(i) - 'A'] < 0)
				return false;
		}
		return true;
	}

	public static void main(String[] args) {
		Test test = new Test();
		System.out.println(test.compareStrings("AADAABBBCC", "AABCCD"));

	}

	public int majorityNumber(List<Integer> nums) {
		// write your code here
		int candidate1=0, candidate2=0;
		int count1=0, count2=0;
		for(int i=0;i<nums.size(); i++){
			if(count1==0){
				candidate1=nums.get(i);
				count1++;
			}else if(candidate1 == nums.get(i)) {
				count1++;
			}else if(count2 ==0){
				candidate2=nums.get(i);
				count2++;
			}  else if(candidate2 == nums.get(i)) {
				count2++;
			}else {
				count1--;
				count2--;
			}
		}

		count1 = 0;
		count2 = 0;
		for(int i=0;i<nums.size();i++) {
			if(nums.get(i) == candidate1) {
				count1++;
			}
			if(nums.get(i) == candidate2) {
				count2++;
			}
		}

		return count1>count2?candidate1 :candidate2;
	}
}
