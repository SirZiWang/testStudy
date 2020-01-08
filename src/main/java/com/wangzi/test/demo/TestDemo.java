package com.wangzi.test.demo;

public class TestDemo {
	public static String a = "a";
	
	public static void main(String[] args) {
		String b = "a";
		String c = new String("a");
		String d = c.intern();
		Integer in = -128;
		Integer ins = -128;
		System.out.println("Integer == :" + (in == ins) + ", equals : " + (in.equals(ins)));
		System.out.println(a==c);
		System.out.println(test2());
	}
	
	private static int test0(){
		int num = 10;
		try {
			System.out.println("try");
			return num += 20;
		} catch (Exception e) {
			System.out.println("error");
		} finally {
			if(num > 20)
				System.out.println("num > 20 :" + num);
			System.out.println("finally");
		}
		return num;
	}
	private static int test1(){
		int num = 10;
		try {
			System.out.println("try");
			return num += 20;
		} catch (Exception e) {
			System.out.println("error");
		} finally {
			if(num > 20)
				System.out.println("num > 20 :" + num);
			System.out.println("finally");
			num = 100;
			return num;
		}
	}
	
	private static int test2() {
		int num = 10;
		try {
			System.out.println("try");
			return num;
		} catch (Exception e) {
			System.out.println("error");
		} finally {
			if (num > 20)
				System.out.println("num>20 : " + num);
			System.out.println("finally");
			num = 100;
		}
		return num;
	}
	
	private static Num test3(){
		Num number = new Num();
		try{
			System.out.println("try");
			return number;
		}catch(Exception e){
			System.out.println("error");
		}finally{
			if (number.num > 20){
				System.out.println("number.num>20 : " + number.num);
			}
			System.out.println("finally");
			number.num = 100;
		}
		return number;
	}
}

class Num {
	public int num = 10;
}
