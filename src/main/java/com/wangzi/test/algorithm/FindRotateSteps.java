package com.wangzi.test.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 解题思路 - 动态规划
 * 定义dp[i][j] 表示从前往后拼写出 key 的第 i 个字符，ring 的第 j 个字符与 12:00 方向对齐的最少步数（下标均从 0 开始）。
 * 显然，只有当字符串 ring 的第 j 个字符需要和 key 的第 i 个字符相同时才能拼写出 key 的第 i 个字符，
 * 因此对于 key 的第 i 个字符，需要考虑计算的 ring 的第 j 个字符只有 key[i] 在ring 中出现的下标集合。
 * 我们对每个字符维护一个位置数组 pos[i]，表示字符 i 在 ring 中出现的位置集合，用来加速计算转移的过程。
 *
 * 对于状态 dp[i][j]，需要枚举上一次与 12:00 方向对齐的位置 k，因此可以列出如下的转移方程：
 */


public class FindRotateSteps {

	public int findRotateSteps(String ring, String key) {
		int n = ring.length(), m = key.length();
		List<Integer>[] pos = new List[26];
		for (int i = 0; i < 26; ++i) {
			pos[i] = new ArrayList<Integer>();
		}
		for (int i = 0; i < n; ++i) {
			pos[ring.charAt(i) - 'a'].add(i);
		}
		int[][] dp = new int[m][n];
		for (int i = 0; i < m; ++i) {
			Arrays.fill(dp[i], 0x3f3f3f);
		}
		for (int i : pos[key.charAt(0) - 'a']) {
			dp[0][i] = Math.min(i, n - i) + 1;
		}
		for (int i = 1; i < m; ++i) {
			for (int j : pos[key.charAt(i) - 'a']) {
				for (int k : pos[key.charAt(i - 1) - 'a']) {
					dp[i][j] = Math.min(dp[i][j], dp[i - 1][k] + Math.min(Math.abs(j - k), n - Math.abs(j - k)) + 1);
				}
			}
		}
		return Arrays.stream(dp[m - 1]).min().getAsInt();

	}

	public int findRotateSteps1(String ring, String key) {
		final int n = ring.length();
		final int[] memo = new int[n];
		final int[] count = new int[26];
		final char[] cs = ring.toCharArray();
		for (char c : cs) {
			count[c-'a']++; 
		}
		final int[][] positions = new int[26][];
		for (int i = 0; i < 26; i++) { 
			positions[i] = new int[count[i]];
		}
		Arrays.fill(count, 0);
		for (int i = 0; i < n; i++) {
			positions[cs[i]-'a'][count[cs[i]-'a']++] = i; 
		}
		for (int idx : positions[key.charAt(0)-'a']) { 
			memo[idx] = 1+distance(0, idx, n);
		}
		for (int idx = 1; idx != key.length(); idx++) {
			int il = key.charAt(idx-1)-'a', ic = key.charAt(idx)-'a';
			int[] cur = positions[ic];
			if (il == ic) {
				for (int i : cur) { memo[i]++; }
				continue; // special ops. should be right
			}
			int[] last = positions[il];
			for (int i : cur) {
				int midDistance = Integer.MAX_VALUE; // note. cannot n
				for (int j : last) {
					midDistance = Math.min(midDistance, memo[j] + distance(i, j, n));
				}
				memo[i] = midDistance+1;
			}
		}
		int res = Integer.MAX_VALUE; // note cannot n
		for (int idx : positions[key.charAt(key.length()-1)-'a']) { 
			res = Math.min(res, memo[idx]); 
		}
		return res;
	}

	private int distance(int src, int dest, final int n) {
		if (src < dest) { 
			return Math.min(dest-src, n-dest+src); 
		}
		else { 
			return Math.min(src-dest, n-src+dest); 
		}
	}

	public static void main(String[] args) {
		FindRotateSteps f = new FindRotateSteps();
		String ring = "godding";
		String key = "gd";
		int findRotateSteps = f.findRotateSteps1(ring, key);
		System.out.println(findRotateSteps);
	}
}
