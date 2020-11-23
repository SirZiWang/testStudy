package com.wangzi.test.algorithm;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class CanVisitAllRooms {

	boolean[] vis;
	int flag;
	public boolean canVisitAllRooms(List<List<Integer>> rooms) {
		int n = rooms.size();
		flag = 0;
		vis = new boolean[n];
		dfs(rooms, 0);
		return flag == n;
	}

	// 深度优先
	private void dfs(List<List<Integer>> rooms, int i) {
		vis[i] = true;
		flag++;
		for(int t : rooms.get(i)) {
			if(!vis[t])
				dfs(rooms, t);
		}
	}
	
	// 广度优先
	public boolean canVisitAllRooms1(List<List<Integer>> rooms) {
		int n = rooms.size(), num = 0;
		boolean[] vis = new boolean[n];
		Queue<Integer> queue = new LinkedList<>();
		vis[0] = true;
		queue.offer(0);
		while(!queue.isEmpty()) {
			int m = queue.remove();
			num++;
			for(int it : rooms.get(m)) {
				if(!vis[it]){
					vis[it] = true;
					queue.offer(it);
				}
			}
		}
		return num == n;
	}
}
