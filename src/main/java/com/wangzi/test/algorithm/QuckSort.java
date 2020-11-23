package com.wangzi.test.algorithm;

/**
 * 快速排序
 * @author Administrator
 *
 */
public class QuckSort {
	
	public void quickSort(int[] arr){
		quick(arr);
		for(int i=0;i<arr.length;i++){
			System.out.print(arr[i] + " ");
		}
	}
	private void quick(int[] arr) {
		
		 if (arr.length > 0) {    //查看数组是否为空     
             _quickSort(arr, 0, arr.length - 1);     
		 }     
	}
	private void _quickSort(int[] arr, int low, int high) {
		if(low<high){
			int middle = getMiddle(arr, low, high);  //将list数组进行一分为二     
			_quickSort(arr, low, middle - 1);        //对低字表进行递归排序     
			_quickSort(arr, middle + 1, high);       //对高字表进行递归排序  
		}
	}
	
	private int getMiddle(int[] arr, int low, int high) {
		
		int temp = arr[low]; //数组的第一个元素为中轴
		while(low<high){
			while(low<high && arr[high]>=temp){
				high--;
			}
			arr[low] = arr[high]; //比中轴小的记录移到低端
			while(low<high && arr[low]<=temp){
				low++;
			}
			arr[high] = arr[low];
		}
		arr[low] = temp;  //中轴记录到尾 
		return low;  //返回中轴的位置  
	}
	public static void main(String[] args) {
		QuckSort quickSort = new QuckSort();
		int arr[] = {22,4,56,93,21,98,31,64,17,6,28};
		quickSort.quickSort(arr);
		System.out.println();
		System.out.println(System.getenv());
		System.out.println(System.getProperties());
	}

}
