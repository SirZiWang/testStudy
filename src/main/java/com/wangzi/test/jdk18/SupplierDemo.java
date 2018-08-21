package com.wangzi.test.jdk18;

import java.util.function.Supplier;

public class SupplierDemo {
	
	public static void main(String[] args) {
		String str = "Hello World";
		String sup = supplierTest(() -> str);
		System.out.println(sup);
		getString(String :: new );
	}
	
	public static String supplierTest(Supplier<String> sup){
		String str = sup.get();
		return str;
	}
	
	public static void getString(Supplier<String> su) {
		String s = su.get();
		System.out.println(s == null);
	}

}
