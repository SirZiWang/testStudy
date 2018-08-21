package com.wangzi.test.algorithm;

import com.google.common.util.concurrent.RateLimiter;

public class RateLimiterTest {
	public static void main(String[] args) throws InterruptedException {
		RateLimiter r = RateLimiter.create(2);
		while(true){
//			System.out.println(r.acquire(2));
//			Thread.sleep(2000);
			System.out.println(r.acquire(5));
			System.out.println(r.acquire(1));
			System.out.println(r.acquire(1));
		}
	}
}
