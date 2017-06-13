package com.wangzi.test.subpub;
/**
 * 订阅者接口
 * @author Administrator
 *
 * @param <M>
 */
public interface ISubcriber<M> {
	/**      
     * @Description: 订阅  
     * @param: subscribePublish 订阅器 
     */  
    public void subcribe(SubscribePublish subscribePublish);  
    /**      
     * @Description: 退订 
     * @param: subscribePublish 订阅器 
     */  
    public void unSubcribe(SubscribePublish subscribePublish);  
    /**      
     * @Description: 接收消息    
     * @param: publisher 发布者 
     * @param: message 消息 
     */  
    public void update(String publisher, M message);  
      
}
