package com.wangzi.test.algorithm;

public class NextPermutation {
	
	// 从数组倒着查找，找到nums[i] 比nums[i+1]小的时候，就将nums[i]跟nums[i+1]到nums[nums.length - 1]当中找到一个最小的比nums[i]大的元素交换
	public void nextPermutation(int[] nums) {
		int i = nums.length -2;
		while(i>=0 && nums[i] >= nums[i+1])
			i--;
		if(i>=0) {
			int j = nums.length - 1;
			while(j>=0 && nums[i] >= nums[j]){
				j--;
			}
			swap(nums, i, j);
		}
		reverse(nums, i + 1);
	}

	private void reverse(int[] nums, int start) {
		int left = start, right = nums.length -1;
		while(left < right) {
			swap(nums, left, right);
			left++;
			right--;
		}
	}

	private void swap(int[] nums, int i, int j) {
		int temp = nums[i];
		nums[i] = nums[j];
		nums[j] = temp;
	}
	public static void main(String[] args) {
		NextPermutation next = new NextPermutation();
		int[] nums = new int[]{1,2,3,4,2,7};
		next.nextPermutation(nums);
	}
}
