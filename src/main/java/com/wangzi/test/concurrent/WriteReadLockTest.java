package com.wangzi.test.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁
 * @author Administrator
 *
 */
public class WriteReadLockTest {
	
	public static void main(String[] args) {
		WriteReadLockTest test = new WriteReadLockTest();
        // 创建并发访问的账户
        Count count = test.new Count("95599200901215522", 10000);
        // 创建一个锁对象
        ReadWriteLock lock = new ReentrantReadWriteLock(false);
        // 创建一个线程池
        ExecutorService pool = Executors.newFixedThreadPool(2);
        // 创建一些并发访问用户，一个信用卡，存的存，取的取，好热闹啊
        User u1 = test.new User("张三", count, -4000, lock, false);
        User u2 = test.new User("张三他爹", count, 6000, lock, false);
        User u3 = test.new User("张三他弟", count, -8000, lock, false);
        User u4 = test.new User("张三", count, 800, lock, false);
        User u5 = test.new User("张三他爹", count, 0, lock, true);
        // 在线程池中执行各个用户的操作
        pool.execute(u1);
        pool.execute(u2);
        pool.execute(u3);
        pool.execute(u4);
        pool.execute(u5);
        // 关闭线程池
        pool.shutdown();
	}
	
	class User implements Runnable{
		private String name; // 用户名
        private Count count; // 所要操作的账户
        private int iocash; // 操作的金额，当然有正负之分了
        private ReadWriteLock myLock; // 执行操作所需的锁对象
        private boolean ischeck; // 是否查询
        
		public User(String name, Count count, int iocash, ReadWriteLock myLock, boolean ischeck) {
			super();
			this.name = name;
			this.count = count;
			this.iocash = iocash;
			this.myLock = myLock;
			this.ischeck = ischeck;
		}

		public void run() {
			if(ischeck){
				 // 获取读锁
                myLock.readLock().lock();
                System.out.println("读：" + name + "正在查询" + count + "账户，当前金额为" + count.getCash());
                // 释放读锁
                myLock.readLock().unlock();
            } else {
                // 获取写锁
                myLock.writeLock().lock();
                // 执行现金业务
                System.out.println("写：" + name + "正在操作" + count + "账户，金额为" + iocash + "，当前金额为"
                        + count.getCash());
                count.setCash(count.getCash() + iocash);
                System.out.println("写：" + name + "操作" + count + "账户成功，金额为" + iocash + "，当前金额为"
                        + count.getCash());
                // 释放写锁
                myLock.writeLock().unlock();
            }
		}
		
	}
	class Count{
		private String oid; //账户
		private int cash;	//余额
		
		public Count(String oid, int cash) {
			super();
			this.oid = oid;
			this.cash = cash;
		}
		public String getOid() {
			return oid;
		}
		public void setOid(String oid) {
			this.oid = oid;
		}
		public int getCash() {
			return cash;
		}
		public void setCash(int cash) {
			this.cash = cash;
		}
		@Override
		public String toString() {
			return "Count [oid=" + oid + ", cash=" + cash + "]";
		}
		
	}
}
