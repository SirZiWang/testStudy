package com.wangzi.test.solution;

public class LongestCommonPrefix {

	//从前往后遍历所有字符串的每一列，比较相同列上的字符是否相同，如果相同则继续对下一列进行比较，如果不相同则当前列不再属于公共前缀，当前列之前的部分为最长公共前缀。
	public String longestCommonPrefix(String[] strs) {
		if(strs.length <=0 || strs == null) return "";
		int length = strs[0].length();
		int count = strs.length;
		for(int i=0; i<length; i++) {
			char c = strs[0].charAt(i);
			for (int j = 1; j < count; j++) {
				if (i == strs[j].length() || strs[j].charAt(i) != c) {
					return strs[0].substring(0, i);
				}
			}
		}
		return strs[0];
	}

	//横向
	public String longestCommonPrefix1(String[] strs) {
		if(strs.length <=0 || strs == null) return "";
		String prefix = strs[0];
		int count = strs.length;
		for(int i=1; i<count; i++) {
			prefix = longestCommonPrefix1(prefix, strs[i]);
			if(prefix == null)
				break;
		}
		return prefix;
	}

	private String longestCommonPrefix1(String str1, String str2) {
		int min = Math.min(str1.length(), str2.length());
		int index = 0;
		while(index < min && str1.charAt(index) == str2.charAt(index)){
			index++;
		}
		return str1.substring(0, index);
	}

	//分治
	public String longestCommonPrefix2(String[] strs) {
		if(strs.length <=0 || strs == null) return "";
		return longestCommonPrefix2(strs, 0, strs.length - 1);
	}

	private String longestCommonPrefix2(String[] strs, int left, int right) {
		if(left == right) return strs[left];
		int mid = (right - left)>>1 + left;
		String lcpLeft = longestCommonPrefix2(strs, left, mid);
		String lcpRight = longestCommonPrefix2(strs, mid + 1, right);
		return commonPrefix(lcpLeft, lcpRight);

	}

	private String commonPrefix(String str1, String str2) {
		int minLength = Math.min(str1.length(), str2.length());       
		for (int i = 0; i < minLength; i++) {
			if (str1.charAt(i) != str2.charAt(i)) {
				return str1.substring(0, i);
			}
		}
		return str1.substring(0, minLength);
	}
	
	public String longestCommonPrefix3(String[] strs) {
        if (strs == null || strs.length == 0)
            return "";
        int minLength = Integer.MAX_VALUE;
        for (String str : strs) {
            minLength = Math.min(minLength, str.length());
        }
        int low = 0, high = minLength;
        while (low < high) {
            int mid = low + ((high - low + 1) >> 1);
            if (isCommonPrefix(strs, mid)) {
                low = mid;
            } else {
                high = mid - 1;
            }
        }
        return strs[0].substring(0, low);
    }

    public boolean isCommonPrefix(String[] strs, int length) {
        String str0 = strs[0].substring(0, length);
        int count = strs.length;
        for (int i = 1; i < count; i++) {
            String str = strs[i];
            for (int j = 0; j < length; j++) {
                if (str0.charAt(j) != str.charAt(j)) {
                    return false;
                }
            }
        }
        return true;
    }

	
	public static void main(String[] args) {
		LongestCommonPrefix l = new LongestCommonPrefix();
		String[] strs = new String[]{"flower","flow","flight"};
		System.out.println(l.longestCommonPrefix(strs));
		System.out.println(l.longestCommonPrefix1(strs));
		System.out.println(l.longestCommonPrefix2(strs));
		System.out.println(l.longestCommonPrefix3(strs));
	}
}
