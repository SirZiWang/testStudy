package com.wangzi.test.algorithm;

public class MoveZeroes {

	public void moveZeroes(int[] nums) {
		int i =0, j=0;
		while(j < nums.length) {
			if(nums[j] != 0) {
				swap(nums, i,j);
				i++;
			}
			j++;
		}
	}
	
	public void swap(int[] nums, int left, int right) {
        int temp = nums[left];
        nums[left] = nums[right];
        nums[right] = temp;
    }

	
	public static void main(String[] args) {
		MoveZeroes mz = new MoveZeroes();
		int[] nums = new int[]{1};
		for(int num : nums) {
			System.out.print(num + ",");
		}
		mz.moveZeroes(nums);
		System.out.println();
		for(int num : nums) {
			System.out.print(num + ",");
		}
	}
}
