package com.wangzi.test.algorithm;

public class StrStr {

	/**
	 * 子串逐一比较
	 * @param haystack
	 * @param needle
	 * @return
	 */
	public int strStr(String haystack, String needle) {
		int n = haystack.length(), L = needle.length();
		for(int start=0; start<n-L+1; start++) {
			if(haystack.substring(start, start + L).equals(needle))
				return start;
		}
		return -1;
	}
	/**
	 * 双指针
	 * 移动 pn 指针，直到 pn 所指向位置的字符与 needle 字符串第一个字符相等。
	 * 通过 pn，pL，curr_len 计算匹配长度。
	 * 如果完全匹配（即 curr_len == L），返回匹配子串的起始坐标（即 pn - L）。
	 * 如果不完全匹配，回溯。使 pn = pn - curr_len + 1， pL = 0， curr_len = 0。
	 * @param haystack
	 * @param needle
	 * @return
	 */
	public int strStr1(String haystack, String needle) {
		int L = needle.length(), n = haystack.length();
		if(L==0) return 0;
		int pn = 0;
		while(pn < n - L + 1) {
			// 找到needle中首字母在haystack字符串首次出现的位置
			while(pn < n -L + 1 && haystack.charAt(pn) != needle.charAt(pn))
				pn++;
			int currLen = 0, pL = 0;
			while (pL < L && pn < n && haystack.charAt(pn) == needle.charAt(pL)) {
				++pn;
				++pL;
				++currLen;
			}
			if (currLen == L) 
				return pn - L;
			pn = pn - currLen + 1;
		}
		return -1;

	}
}
