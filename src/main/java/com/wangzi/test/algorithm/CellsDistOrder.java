package com.wangzi.test.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class CellsDistOrder {
	// 直接排序
	public int[][] allCellsDistOrder(int R, int C, int r0, int c0) {
		int[][] res = new int[R*C][];
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				res[i*C+j] = new int[]{i, j};
			}
		}
		Arrays.sort(res, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return (Math.abs(o1[0] - r0) + Math.abs(o1[1] - c0)) - (Math.abs(o2[0] - r0) + Math.abs(o2[1] - c0));
			}
		});
		return res;
    }
	// 桶排序
	public int[][] allCellsDistOrder1(int R, int C, int r0, int c0) {
		int maxDist = Math.max(r0, R-1-r0) + Math.max(c0, C-1-c0);
		List<List<int[]>> list = new ArrayList<>();
		for(int i=0; i<=maxDist; i++)
			list.add(new ArrayList<>());
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				int dist = dist(i, j, r0, c0);
				list.get(dist).add(new int[]{i, j});
			}
		}
		int[][] res = new int[R * C][];
        int index = 0;
        for (int i = 0; i <= maxDist; i++) {
            for (int[] it : list.get(i)) {
                res[index++] = it;
            }
        }
        return res;

	}
	
	public int dist(int r1, int c1, int r2, int c2) {
        return Math.abs(r1 - r2) + Math.abs(c1 - c2);
    }
	
	// 几何法
	int[] dr = {1, 1, -1, -1};
    int[] dc = {1, -1, -1, 1};

    public int[][] allCellsDistOrder2(int R, int C, int r0, int c0) {
        int maxDist = Math.max(r0, R - 1 - r0) + Math.max(c0, C - 1 - c0);
        int[][] ret = new int[R * C][];
        int row = r0, col = c0;
        int index = 0;
        ret[index++] = new int[]{row, col};
        for (int dist = 1; dist <= maxDist; dist++) {
            row--;
            for (int i = 0; i < 4; i++) {
                while ((i % 2 == 0 && row != r0) || (i % 2 != 0 && col != c0)) {
                    if (row >= 0 && row < R && col >= 0 && col < C) {
                        ret[index++] = new int[]{row, col};
                    }
                    row += dr[i];
                    col += dc[i];
                }
            }
        }
        return ret;
    }
}
