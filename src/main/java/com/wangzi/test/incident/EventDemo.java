package com.wangzi.test.incident;

import java.util.EventObject;

/**
 * 事件。一般继承自java.util.EventObject类，封装了事件源对象及跟事件相关的信息。
 * @author JunWang
 *
 */
public class EventDemo extends EventObject{

	private static final long serialVersionUID = 1L;
	private Object sourece;

	public EventDemo(Object source) {
		super(source);
		this.source = source;
	}
	
	public Object getSourece() {
		return sourece;
	}

	public void setSourece(Object sourece) {
		this.sourece = sourece;
	}
}
