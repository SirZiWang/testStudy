package com.wangzi.test.subpub;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * 订阅器
 * @author Administrator
 *
 * @param <M>
 */
public class SubscribePublish<M> {
	//订阅器名称
	private String name;
	//订阅器队列容量
	final int QUEUE_CAPACITY = 20;
	//订阅器存储队列
	private BlockingQueue<Msg> queue = new ArrayBlockingQueue<Msg>(QUEUE_CAPACITY);
	//订阅者  
	private List<ISubcriber> subcribers  = new ArrayList<ISubcriber>(); 
	
	public SubscribePublish(String name){
		this.name = name;
	}
	
	/**  
     * @Description: 接收发布者的消息 
     * @param publisher 
     * @param Msg 
     * @param isInstantMsg   
     */  
    public void publish(String publisher,M message,boolean isInstantMsg) {  
        if(isInstantMsg){  
            update(publisher,message);  
            return;  
        }  
          
        Msg<M> m = new Msg<M>(publisher,message);  
        if(!queue.offer(m)){  
            update();  
        }         
    }  
  
    /**   
     * @Description: 订阅  
     * @param subcriber  
     * @return: void     
     */  
    public void subcribe(ISubcriber subcriber) {  
        subcribers.add(subcriber);  
    }  
    /**   
     * @Description: 退订  
     * @param subcriber  
     * @return: void     
     */  
    public void unSubcribe(ISubcriber subcriber) {  
        subcribers.remove(subcriber);  
    }  
      
    /**  
     * @Description: 发送存储队列所有消息  
     * @return: void     
     */  
    public void update(){  
        Msg m = null;  
        while((m = queue.peek())!= null){  
            this.update(m.getPublisher(),(M)m.getMsg());  
        }  
    }  
  
    /**  
     * @Description: 发送消息    
     * @param publisher 
     * @param Msg    
     * @return: void     
     */  
    public void update(String publisher,M Msg) {  
        for(ISubcriber subcriber:subcribers){  
            subcriber.update(publisher,Msg);  
        }         
    }  
}
