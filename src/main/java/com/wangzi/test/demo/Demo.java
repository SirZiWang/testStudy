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
	}
}

class A{
	String str;
}