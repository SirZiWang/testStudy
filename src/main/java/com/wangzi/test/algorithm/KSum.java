package com.wangzi.test.algorithm;

import java.util.ArrayList;
import java.util.List;

public class KSum {

	public static int kSum(int[] nums, int k, int target) {
		int sum = 0;
		int n = nums.length;
		if(n == 0 || n<k) 
			return sum;
		int[][] f = new int[k+1][target+1];
		f[0][0] = 1;
		//使用i-1的原因，是因为这里把三维数组合并成了二维数组
		//f[i][k][target] = f[i-1][k][target] + f[i-1][k-1][target-arr[i]]
		for(int i=0; i<n; i++) {
			for(int j=k; j>=1; j--) {
				for (int s=target; s>=nums[i]; s--) {
					f[j][s] = f[j][s]+f[j-1][s-nums[i]];
				}
			}
		}
		return f[k][target];
	}

	public static int kSum1(int[] A, int k, int target) {
		int result[][][] = new int[A.length + 1][k + 1][target + 1];
		for (int i=0; i<A.length+1; i++) {
			result[i][0][0] = 1;
		}
		for (int h=1; h<A.length+1; h++) {
			for (int i=k; i>=1; i--) {
				for (int j=target; j>=1; j--) {
					if(j >= A[h-1]) {
						result[h][i][j] = result[h-1][i][j] + result[h-1][i-1][j-A[h-1]];
					}else {
						result[h][i][j] = result[h-1][i][j];
					}
				}
			}
		}
		return result[A.length ][k][target];
	}

	public static int kSum2(int[] A, int K, int T) {
		int n = A.length;
		int[][][] f = new int[n + 1][K + 1][T + 1];
		int i, j, k;
		for (j = 0; j <= K; ++j) {
			for (k = 0; k <= T; ++k) {
				f[0][j][k] = 0;
			}
		}

		f[0][0][0] = 1;
		for (i = 1; i <= n; ++i) {
			for (j = 0; j <= K; ++j) {
				for (k = 0; k <= T; ++k) {
					// not using A[i - 1]
					f[i][j][k] = f[i - 1][j][k];

					// using A[i - 1]
					if (j > 0 && k >= A[i - 1]) {
						f[i][j][k] += f[i - 1][j - 1][k - A[i - 1]];
					}
				}
			}
		}

		return f[n][K][T];
	}

	public static void main(String[] args) {
//		int[] nums = new int[]{1,2,3,4,5};
//		int k = 2;
//		int target = 5;
//		int kSum = kSum2(nums, k, target);
//		System.out.println(kSum);
		KSum sum = new KSum();
		int[] nums = new int[]{1,3,4,6};
		int k = 2;
		int target = 7;
		List<List<Integer>> kSum = sum.KSumII(nums, k, target);
		System.out.println(kSum.toString());
		
		
		int[] array = new int[]{0,0,1,1,1,2,2,3,3,4};
		int removeDuplicates = sum.removeDuplicates(array);
		System.out.println(removeDuplicates);
		
		int ss = sum.removeDuplicationII(new int[]{1,1,1,2,2,3});
		System.out.println(ss);
	}

	public List<List<Integer>> KSumII(int[] nums, int k, int target) {
		List<List<Integer>> result = new ArrayList<>();
		List<Integer> path = new ArrayList<>();
		helperKSum(result, path, nums, k, target, 0);
		return result;
	}

	private void helperKSum(List<List<Integer>> result, List<Integer> path, int[] nums, int k, int target, int index) {
		if(path.size() == k) {
			if(target == 0)
				result.add(new ArrayList<Integer>(path));
			else
				return;
		}
		for(int i=index; i<nums.length; i++) {
			path.add(nums[i]);
			helperKSum(result, path, nums, k, target - nums[i], i+1);
			path.remove(path.size() - 1);
			
		}
	}
	
	public int removeDuplicates(int[] nums) {
		if(nums == null || nums.length == 0) return 0;
		int num = 1;
		for(int i=0; i<nums.length-1; i++) {
			if(nums[i]!=nums[i+1]) {
				nums[num] = nums[i+1];
				num++;
			}
		}
		return num;
	}
	
	public int removeDuplicationII(int[] nums) {
		
		if(nums == null || nums.length ==0) return 0;
		int index = 0;
		for(int i=2; i<nums.length; i++){
			if(nums[i] != nums[index]) {
				nums[index+2] = nums[i];
                index++;
                continue;
            }
        }
        return index+2;
	}
}
