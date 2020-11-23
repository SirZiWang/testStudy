package com.wangzi.test.algorithm;

public class SortArrayByParityII {
	
	public int[] sortArrayByParityII(int[] A) {
		int n = A.length;
		int j = 1;
		for(int i=0; i<n; i +=2) {
			while(A[i]%2 == 1) {
				if(A[j]%2 == 1) {
					j +=2;
				}
				swap(A, i,j);
			}
		}
		return A;
	}

	private void swap(int[] A, int i, int j) {
		int temp = 	A[i];
		A[i] = A[j];
		A[j] = temp;
	}
}
