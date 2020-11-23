package com.wangzi.test.algorithm;

public class LandPerimeter {

	public int islandPerimeter(int[][] grid) {
		if(grid == null || grid.length == 0) return 0;
		int res = 0;
		for(int i=0; i<grid.length; i++) {
			for(int j=0; j<grid[i].length; j++) {
				if(grid[i][j] == 1) {
					res += 4;
					if (i > 0 && grid[i - 1][j] == 1)
						res -= 2;
					if (j > 0 && grid[i][j - 1] == 1)
						res -= 2;
				}
			}
		}
		return res;
	}
	public static void main(String[] args) {
		LandPerimeter l = new LandPerimeter();
		int[][] grid = new int[][]{{0,1,0,0},{1,1,1,0},{0,1,0,0},{1,1,0,0}};
		int islandPerimeter = l.islandPerimeter(grid);
		System.out.println(islandPerimeter);
	}
}
