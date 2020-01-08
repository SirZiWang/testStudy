package com.wangzi.test.incident;

public class EventTest {
	public static void main(String[] args) {
		EventSoureceDemo eventSource = new EventSoureceDemo();
		eventSource.addEventListener(new EventListenerDemo(){
			
			@Override
			 public void fireEvent(EventDemo e) {  
                super.fireEvent(e);  
            }  
		});
		eventSource.setName("hello world");
		System.out.println((4&(16-1)) + " " + 4%16);
		System.out.println(hash(4));
	}
	 static final int hash(Object key) {
	        int h;
	        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
	    }
}
