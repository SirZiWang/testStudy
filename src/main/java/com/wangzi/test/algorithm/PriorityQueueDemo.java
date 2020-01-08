
package com.wangzi.test.algorithm;

import java.util.Comparator;
import java.util.PriorityQueue;

public class PriorityQueueDemo {

	public int[] medianII(int[] nums) {

		PriorityQueue<Integer> maxHeap = new PriorityQueue<>(nums.length);
//		PriorityQueue<Integer> minHeap = new PriorityQueue<>((a,b) -> b-a);
		PriorityQueue<Integer> minHeap = new PriorityQueue<>(nums.length, new Comparator<Integer>() {

			@Override
			public int compare(Integer a, Integer b) {
				return b-a;
			}
		});

		int[] results = new int[nums.length];
		int index = 0;
		for(int num : nums) {
			maxHeap.offer(num);
			minHeap.offer(maxHeap.poll());
			if (maxHeap.size() < minHeap.size()) {
				maxHeap.offer(minHeap.poll());
			}
			results[index++] = maxHeap.size() == minHeap.size() ?
					Math.min(maxHeap.peek(), minHeap.peek()) :
						maxHeap.peek();
		}

		return results;
	}
	public static void main(String[] args) {
		PriorityQueueDemo priorityQueueDemo = new PriorityQueueDemo();
		int[] nums = {4,5,1,3,2,6,0};
		int[] medianII = priorityQueueDemo.medianII(nums);
		for(int median : medianII)
			System.out.print(median + " ");
	}
}

