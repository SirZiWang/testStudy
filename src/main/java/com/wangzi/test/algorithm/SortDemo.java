package com.wangzi.test.algorithm;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * 插入排序
 * @author Administrator
 *
 */
public class SortDemo {

	public static void sort(int[] arr){
		int j;
		for (int p = 1; p < arr.length; p++) {// 遍历数组，从1下标开始，0位置一个元素不用排序
			// 用temp暂存要排序的值
			int temp = arr[p];
			// 代处理的数据，在已排好序的数组中从后往前进行比较遍历
			// 条件是：代处理的数据是否比有序的数据中某一个值小，如果小那么就需要对数组进行移位
			for (j = p; j > 0 && temp < arr[j - 1]; j--) {
				// 数组移位，为数据的插入做准备
				arr[j] = arr[j - 1];
			}
			// 把数据插入到合适的j位置
			arr[j] = temp;
		}
	}

	public static void main(String[] args) throws Exception {
		int[] ints = { 1, 5, 4, 2, 6, 4, 6, 47, 5, 4, 12, 41, 66, 4, 2, 10, 42 };
		sort(ints);
		for (int i : ints) {
			System.out.print(i + " ");
		}
		
		System.out.println(-15|3);
		List<Integer> list = new ArrayList<>();
		Method method = list.getClass().getMethod("add", Object.class);
		method.invoke(list, "我是String");
		System.out.println(list.toString());
		
	}
}
