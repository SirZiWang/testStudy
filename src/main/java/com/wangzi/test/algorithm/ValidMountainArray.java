package com.wangzi.test.algorithm;

public class ValidMountainArray {
	
	public boolean validMountainArray(int[] A) {
		if(A.length < 3) return false;
		int left = 0, right = A.length - 1;
		// 上坡
		while(left < right && A[left] < A[left+1]) 
			left++;
		// 下坡
		while(left < right && A[right] < A[right - 1])
			right --;
		return left == right && left != 0 && right != A.length;
	}
}
