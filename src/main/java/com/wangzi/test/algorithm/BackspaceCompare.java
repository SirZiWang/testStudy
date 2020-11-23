package com.wangzi.test.algorithm;

public class BackspaceCompare {

	public boolean backspaceCompare(String S, String T) {
		return build(S).equals(build(T));
	}

	private String build(String s) {
		StringBuffer sb = new StringBuffer();
		int len = s.length();
		for(int i=0; i<len; i++) {
			char ch = s.charAt(i);
			if(ch != '#')
				sb.append(ch);
			else {
				if(sb.length() > 0)
					sb.deleteCharAt(sb.length() -1);

			}
		}
		return sb.toString();
	}

	public boolean backspaceCompare1(String S, String T) {
		int i = S.length() - 1, j = T.length() - 1;
		int skipS = 0, skipT = 0;

		while (i >= 0 || j >= 0) {
			while (i >= 0) {
				if (S.charAt(i) == '#') {
					skipS++;
					i--;
				} else if (skipS > 0) {
					skipS--;
					i--;
				} else 
					break;
			}
			while (j >= 0) {
				if (T.charAt(j) == '#') {
					skipT++;
					j--;
				} else if (skipT > 0) {
					skipT--;
					j--;
				} else 
					break;
			}
			if (i >= 0 && j >= 0) {
				if (S.charAt(i) != T.charAt(j)) 
					return false;
			} else {
				if (i >= 0 || j >= 0) 
					return false;
			}
			i--;
			j--;
		}
		return true;
	}

	public static void main(String[] args) {
		BackspaceCompare bc = new BackspaceCompare();
		String S = "ab##ccc";
		String T = "abc###cc#cc";
		System.out.println(bc.backspaceCompare1(S, T));
	}
}
