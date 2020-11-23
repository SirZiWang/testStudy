package com.wangzi.test.algorithm;

public class Divide {

	public int divide(int dividend, int divisor) {
		if(dividend == 0) return 0;
		if(dividend == Integer.MIN_VALUE && divisor == -1) return Integer.MAX_VALUE;
		boolean flag = (dividend ^ divisor) < 0; // 异或运算确认正负号
		long t = Math.abs((long) dividend);
		long d = Math.abs((long) divisor);
		int result = 0;
		for(int i=31; i>=0; i--) {
			if((t>>i) >= d) {  // 找出足够大的数2^n*divisor
				result += 1<<i;  //将结果加上 2^n 
				t -= d<<i; //将被除数减去2^n*divisor
			}
		}
		return flag ? -result : result;
	}
	
	public static void main(String[] args) {
		Divide d = new Divide();
		int dividend = -2147483648;
		int divisor = 1;
		int divide = d.divide(dividend, divisor);
		System.out.println(divide);
	}
}
