package com.wangzi.test.algorithm;

public class PredictTheWinner {

	public boolean predictTheWinner(int[] nums) {
		return total(nums, 0, nums.length - 1, 1) >= 0;
	}

	private int total(int[] nums, int start, int end, int turn) {
		if(start == end) return nums[start] * turn;
		int scoreStart = nums[start] * turn + total(nums, start + 1, end, -turn);
		int scoreEnd = nums[end] * turn + total(nums, start, end - 1, -turn);
		return Math.max(scoreStart * turn, scoreEnd * turn) * turn; 
	}

	//动态规划
	public boolean predictTheWinner1(int[] nums) {
		int n = nums.length;
		int[][] dp = new int[n][n];
		for(int i=0; i<n; ++i) 
			dp[i][i] = nums[i];
		for(int i = n-2; i>=0; i--) {
			for(int j=i+1; j<n; j++) {
				dp[i][j] = Math.max(nums[i] - dp[i + 1][j], nums[j] - dp[i][j-1]);
			}
		}
		return dp[0][n-1] >= 0;
	}

	public boolean predictTheWinner2(int[] nums) {
		int n = nums.length;
		int[] dp = new int[n];
		for (int i = 0; i < n; i++) {
			dp[i] = nums[i];
		}
		for (int i = n - 2; i >= 0; i--) {
			for (int j = i + 1; j < n; j++) {
				dp[j] = Math.max(nums[i] - dp[j], nums[j] - dp[j - 1]);
			}
		}
		return dp[n - 1] >= 0;
	}


	public static void main(String[] args) {
		PredictTheWinner p = new PredictTheWinner();
		int[] nums = {1,5,8,4};
		boolean flag = p.predictTheWinner(nums);
		System.out.println(flag);
	}
}
