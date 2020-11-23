package com.wangzi.test.algorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * 给定一个非空的整数数组，返回其中出现频率前 k 高的元素。
 * @author Administrator
 *
 */

public class TopKFrequent {

	public int[] topKFrequent(int[] nums, int k) {

		Map<Integer, Integer> map = new HashMap<>();
		for(int num : nums) {
			map.put(num, map.getOrDefault(num, 0) + 1);
		}

		PriorityQueue<int[]> queue = new PriorityQueue<>(new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[1] - o2[1];
			}
		});

		for(Map.Entry<Integer, Integer> entry : map.entrySet()) {
			int num = entry.getKey(), count = entry.getValue();
			if(queue.size() == k) {
				if(queue.peek()[1] < count) {
					queue.poll();
					queue.offer(new int[] {num, count});
				}
			} else {
				queue.offer(new int[] {num, count});
			}
		}
		int res[] = new int[k];
		for (int i = 0; i < k; ++i) {
			res[i] = queue.poll()[0];
		}
		return res;
	}


	public int[] topKFrequent1(int[] nums, int k) {
		Map<Integer, Integer> map = new HashMap<>();
		for(int num : nums) {
			map.put(num, map.getOrDefault(num, 0) + 1);
		}
		List<int[]> list = new ArrayList<>();
		for(Map.Entry<Integer, Integer> entry : map.entrySet()) {
			int num = entry.getKey(), count = entry.getValue();
			list.add(new int[] {num, count});
		}
		int res[] = new int[k];
		qsort(list, 0, list.size() - 1, res, 0, k);
		return res;
	}

	private void qsort(List<int[]> list, int start, int end, int[] res, int resIndex, int k) {
		int picked = (int) (Math.random() * (end - start + 1)) + start;
		Collections.swap(list, picked, start);

		int pivot = list.get(start)[1];
		int index = start;
		for (int i = start + 1; i <= end; i++) {
			if (list.get(i)[1] >= pivot) {
				Collections.swap(list, index + 1, i);
				index++;
			}
		}
		Collections.swap(list, start, index);

		if (k <= index - start) {
			qsort(list, start, index - 1, res, resIndex, k);
		} else {
			for (int i = start; i <= index; i++) {
				res[resIndex++] = list.get(i)[0];
			}
			if (k > index - start + 1) {
				qsort(list, index + 1, end, res, resIndex, k - (index - start + 1));
			}
		}
	}


	public static void main(String[] args) {
		TopKFrequent t = new TopKFrequent();
		int[] nums = new int[]{1,1,1,2,2,3,3,6,6,8};
		t.topKFrequent1(nums, 3);
	}
}
