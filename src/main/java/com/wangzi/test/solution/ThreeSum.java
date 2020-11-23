package com.wangzi.test.solution;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 首先对数组进行排序，排序后固定一个数 nums[i]，
 * 再使用左右指针指向 nums[i]后面的两端，数字分别为 nums[L]和 nums[R]，计算三个数的和 sumsum 判断是否满足为 0，满足则添加进结果集
 * 如果 nums[i]> 0，则三数之和必然无法等于 0，结束循环
 * 如果 nums[i] = nums[i-1]，则说明该数字重复，会导致结果重复，所以应该跳过
 * 当 sumsum = 0 时，nums[L] = nums[L+1] 则会导致结果重复，应该跳过，L++
 * 当 sumsum = 0 时，nums[R] = nums[R-1] 则会导致结果重复，应该跳过，R--

 * @author Administrator
 *
 */
public class ThreeSum {
	
	public List<List<Integer>> threeSum(int[] nums) {
		//数组进行排序
		Arrays.sort(nums);
		List<List<Integer>> result = new ArrayList<>();
		for(int i=0; i<nums.length; i++) {
			if(nums[i] > 0) //大于0三数之和不可能为0
				break;
			if(i>0 && nums[i] == nums[i-1]) //去掉重复的
				continue;
			int target = 0 - nums[i];
			int left = i + 1;
			int right = nums.length - 1;
			while(left < right) {
				List<Integer> list = new ArrayList<>();
				if(nums[left] + nums[right] == target) {
					list.add(nums[i]);
					list.add(nums[left]);
					list.add(nums[right]);
					result.add(list);
					while(right > left && nums[left+1] == nums[left])
						left++;
					while(right > left && nums[right-1] == nums[right])
						right--;
					left++;
					right--;
				} else if(nums[left]+nums[right]>target)
					right--;
					else 
					left++;
			}
		}
		return result;
    }
}
