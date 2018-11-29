package com.wangzi.test.demo;

public class Test { 
	public static void main(String[] args) { 
		System.out.println("return value of test(): " + test3()); 
		
//		try {  
//			System.out.println("try block");  
//			return ;  
//		} finally {  
//			System.out.println("finally block");  
//		}  
	} 
	public static int test() { 
		int i = 1; 
		if(i == 1) 
			return 0; 
		System.out.println("the previous statement of try block "); 
		i = i / 0; 
		try { 
			System.out.println("try block"); 
			return i; 
		}finally { 
			System.out.println("finally block"); 
		} 
	} 
	public static int test2(){
		int i=1;
		try {
			System.out.println("try block");
//			System.exit(0);
			return i;
		} finally {
			System.out.println("finally block");
		}
	}
	
	public static int test3(){ 
		int i = 1;        
		try {  
			System.out.println("try block");  
			i = 1 / 0; 
			return 1;  
		}catch (Exception e){ 
			System.out.println("exception block"); 
			return 2; 
		}finally {  
			System.out.println("finally block");  
		} 
	} 
}
