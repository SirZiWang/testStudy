package com.wangzi.test.aqs;

import java.util.concurrent.locks.StampedLock;

public class Point {
	
	private double x, y;
	private final StampedLock sLock = new StampedLock();
	
	//写锁的使用
	void move(double deltaX, double deltaY){
		long stamp = sLock.writeLock(); // 涉及对共享资源的修改，使用写锁-独占操作
		try {
			x += deltaX;
			y += deltaY;
		} finally {
			sLock.unlockWrite(stamp);
		}
	}
	
	/**
     * 使用乐观读锁访问共享资源
     * 注意：乐观读锁在保证数据一致性上需要拷贝一份要操作的变量到方法栈，并且在操作数据时候可能其他写线程已经修改了数据，
     * 而我们操作的是方法栈里面的数据，也就是一个快照，所以最多返回的不是最新的数据，但是一致性还是得到保障的。
     *
     * @return
     */
	
	double distanceFromOrigin(){
		long stamp = sLock.tryOptimisticRead(); // 使用乐观读锁
		double currentX = x, currentY = y; // 拷贝共享资源到本地方法栈中
		if(!sLock.validate(stamp)){ // 如果有写锁被占用，可能造成数据不一致，所以要切换到普通读锁模式
			stamp = sLock.readLock();
			try {
				currentX = x;
				currentY = y;
			} finally {
				sLock.unlockRead(stamp);
			}
		}
		return Math.sqrt(currentX * currentX + currentY * currentY);
	}
	
	/**
	 * 悲观读锁以及读锁升级写锁的使用
	 * @param newX
	 * @param newY
	 */
	void moveIfAtOrigin(double newX, double newY) {
		long stamp = sLock.readLock();
		try {
			while(newX == 0 && newY == 0){
				long ws = sLock.tryConvertToWriteLock(stamp);  // 读锁转换为写锁
				if(ws!=0L){
					stamp =ws;
					x = newX;
					y = newY;
					break;
				} else {
					sLock.unlockRead(stamp); // 转换失败释放读锁
					sLock.writeLock(); // 强制获取写锁
				}
			}
		} finally {
			sLock.unlock(stamp); 
		}
	}
}
