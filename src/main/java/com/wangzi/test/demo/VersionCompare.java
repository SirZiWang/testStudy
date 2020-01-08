package com.wangzi.test.demo;

public class VersionCompare {
	/**
	 * 两个版本比较前者大返回1，相等返回0，后者大返回-1
	 * @param version1
	 * @param version2
	 * @return
	 */
	public static int compareVersion(String version1, String version2)  {  

		if (version1 == null || version2 == null) {
			throw new NullPointerException();
		}
		String[] versionArray1 = version1.split("\\.");
//		for(int i = 0 ; i<versionArray1.length ; i++){
//			if(versionArray1[i].length() == 1){
//				versionArray1[i] = "0" + versionArray1[i];
//			}
//		}
		String[] versionArray2 = version2.split("\\.");
//		for(int i = 0 ; i<versionArray2.length ; i++){
//			if(versionArray2[i].length() == 1){
//				versionArray2[i] = "0" + versionArray2[i];
//			}
//		}
		int idx = 0;
		int minLength = Math.min(versionArray1.length, versionArray2.length);//取最小长度值
		int diff = 0;
		while (idx < minLength
				&& (diff = versionArray1[idx].length() - versionArray2[idx].length()) == 0//先比较长度
				&& (diff = versionArray1[idx].compareTo(versionArray2[idx])) == 0) {//再比较字符
			++idx;
		}
		//如果已经分出大小，则直接返回，如果未分出大小，则再比较位数，有子版本的为大；
		diff = (diff != 0) ? diff : versionArray1.length - versionArray2.length;
		return diff;
	}
}
