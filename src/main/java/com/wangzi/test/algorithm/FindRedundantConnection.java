package com.wangzi.test.algorithm;

public class FindRedundantConnection {
	
	int[] parents;
	public int[] findRedundantConnection(int[][] edges) {
		if(edges == null || edges.length == 0) return new int[]{0, 0};
		int n = edges.length + 1;
		init(n);
		for(int[] edge : edges) {
			int x = edge[0], y = edge[1];
			if(!union(x, y)) { //第二次出现了联通的边时，表示已经找到了
				return edge;
			}
		}
		return new int[]{0, 0};
	}

	//递归版路径压缩，找到x的根节点
    private int find(int x) {
        if (x != parents[x]) {
            parents[x] = find(parents[x]);
        }
        return parents[x];
    }

	//改写union方法，第一次当x与y没有联通时，将其设置联通关系，返回ture
    //第二次x和y的跟节点发现一致时，他们已经联通了，返回false
    private boolean union(int x, int y) {
        int rootX = find(x), rootY = find(y);
        if (rootX == rootY) return false;
        parents[rootX] = rootY;
        return true;
    }


	private void init(int n) {
		parents = new int[n];
		for(int i=0; i<n; i++){
			parents[i] = i;
		}
	}
	
	public static void main(String[] args) {
		FindRedundantConnection f = new FindRedundantConnection();
		int[][] edges = new int[][]{{1,2}, {1,3}, {2,3}};
		f.findRedundantConnection(edges);
	}
}
