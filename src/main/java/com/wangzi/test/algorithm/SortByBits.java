package com.wangzi.test.algorithm;

public class SortByBits {

	public int[] sortByBits(int[] arr) {
		int[][] temp = new int[arr.length][2];
		for(int i=0; i<arr.length; i++) {
			temp[i][0] = arr[i];
			temp[i][1] = hammingWeight(arr[i]);
		}
		// 排序
		sorted(temp);

		for (int i = 0; i < arr.length; i++) {
			arr[i] = temp[i][0];
		}
		return arr;

	}
	private void sorted(int[][] array) {
		for(int i=0; i<array.length; i++) {
			int j;
			int[] temp = array[i];
			for(j=i-1; j>=0; j--) {
				if (array[j][1] > temp[1] || (array[j][1] == temp[1] && array[j][0] > temp[0])) {
					array[j + 1] = array[j];
				} else {
					break;
				}
			}
			array[j + 1] = temp;
		}
		
	}


	/**
	 * 统计一个数字二进制中1的个数
	 * @param n
	 * @return
	 */
	private int hammingWeight(int n) {
		int count = 0;
		while(n!=0) {
			count += n&1;
			n = n>>>1;
		}
		return count;
	}
}
