package com.wangzi.test.subpub;
/**
 * 发布者接口
 * @author Administrator
 *
 * @param <M>
 */
public interface IPublisher<M> {
	/**
	 * @Description 向订阅器发布消息 
	 * @param subscribePublish 订阅器
	 * @param message 消息
	 * @param isInstantMsg 是否立即发送
	 */
	public void publish(SubscribePublish subscribePublish,M message,boolean isInstantMsg);
}
