package com.wangzi.test.demo;

public class Demo {
	static void change(int x, A a){
		x = 20;
		a.str = "hehe";
	}
	public static void main(String[] args) {
		A n = new A();  
		n.str = "haha";  
		int x = 10;  
		System.out.println("对象的引用改变前：" + n.str + " ,int型引用改变前" + x);  
		change(x,n);  
		System.out.println("对象的引用改变后：" + n.str + " ,int型引用改变后" + x);

		StringBuffer sb = new StringBuffer("Hello ");
		System.out.println("Before change, sb = " + sb);
		changeData(sb);
		System.out.println("After changeData(n), sb = " + sb);

	}
	public static void changeData(StringBuffer strBuf) {
		StringBuffer sb2 = new StringBuffer("Hi ");
		strBuf = sb2;
		sb2.append("World!");

	}
}

class A{
	String str;
}