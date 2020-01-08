package com.wangzi.test.demo;

import java.util.ArrayList;
import java.util.List;

/**
 * -Xmx16m -XX:PermSize=8m -XX:MaxPermSize=8m
 * -Xmx16m -XX:MetaspaceSize=8m -XX:MaxMetaspaceSize=8m
 * @author Administrator
 *
 */
public class StringOomMock {
	static String  base = "string";
    public static void main(String[] args) {
        List<String> list = new ArrayList<String>();
        for (int i=0;i< Integer.MAX_VALUE;i++){
            String str = base + base;
            base = str;
            list.add(str.intern());
        }
    }
}
