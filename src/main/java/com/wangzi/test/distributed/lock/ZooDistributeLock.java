package com.wangzi.test.distributed.lock;


import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ZooDistributeLock implements Watcher{
	
	private static final Logger logger = LoggerFactory.getLogger(ZooDistributeLock.class);

	@Override
	public void process(WatchedEvent event) {
		
		
	}

}
