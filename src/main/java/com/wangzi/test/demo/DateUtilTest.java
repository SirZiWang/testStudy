package com.wangzi.test.demo;

import java.text.ParseException;

public class DateUtilTest {
	public static class TestSimpleDateFormatThreadSafe extends Thread {
		@Override
		public void run() {
			while(true) {
				try {
					this.join(2000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				try {
					System.out.println(this.getName()+":"+DateUtil.parse("2013-12-11 14:50:20"));
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
		}    
	}

	public static void main(String[] args) {
		for(int i = 0; i < 3; i++){
			new TestSimpleDateFormatThreadSafe().start();
		}
	}
}
