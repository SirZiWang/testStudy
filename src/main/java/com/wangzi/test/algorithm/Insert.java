package com.wangzi.test.algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * 给出一个无重叠的 ，按照区间起始端点排序的区间列表。
 * 在列表中插入一个新的区间，你需要确保列表中的区间仍然有序且不重叠（如果有必要的话，可以合并区间）。
 * 输入：intervals = [[1,3],[6,9]], newInterval = [2,5]
 * 输出：[[1,5],[6,9]]
 * @author Administrator
 *
 */
public class Insert {

	public int[][] insert(int[][] intervals, int[] newInterval) {
		int left = newInterval[0];
		int right = newInterval[1];
		boolean placed = false;
		List<int[]> list = new ArrayList<>();
		for(int[] interval : intervals) {
			if(interval[0] > right) { // 插入区间的右侧且无交集
				if(!placed) {
					list.add(new int[]{left, right});
					placed = true;
				}
				list.add(interval);
			} else if(interval[1] < left) // 在插入区间的左侧且无交集
				list.add(interval);
			else { // 与插入区间有交集，计算它们的并集
                left = Math.min(left, interval[0]);
                right = Math.max(right, interval[1]);
			}
		}
		if (!placed) {
            list.add(new int[]{left, right});
        }
        int[][] ans = new int[list.size()][2];
        for (int i = 0; i < list.size(); ++i) {
            ans[i] = list.get(i);
        }
        return ans;
	}
}
