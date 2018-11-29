package com.wangzi.test.incident;

public class EventTest {
	public static void main(String[] args) {
		EventSoureceDemo eventSource = new EventSoureceDemo();
		eventSource.addEventListenere(new EventListenerDemo(){
			
			@Override
			 public void fireEvent(EventDemo e) {  
                super.fireEvent(e);  
            }  
		});
		eventSource.setName("hello world");
	}
}
