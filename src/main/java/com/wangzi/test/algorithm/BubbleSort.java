package com.wangzi.test.algorithm;

public class BubbleSort {
	public static void bubbleSort(){
		int arr[] = {12,4,56,93,21,98,31,64,17,6,28};
		int temp = 0;
		for(int i=0;i<arr.length-1;i++){
			for(int j=0;j<arr.length-1-i;j++){
				if(arr[j]>arr[j+1]){
					temp = arr[j];
					arr[j] = arr[j+1];
					arr[j+1] = temp;
				}
			}
		}
		for(int i=0;i<arr.length;i++){
			System.out.print(arr[i] + " ");
		}
	}
	public static void main(String[] args) {
		bubbleSort();
	}
}
