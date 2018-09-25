package com.wangzi.test.collections;

public class GcTest {
	
	public static GcTest gcTest = null;
	
	//重写finalize方法
	@Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("执行finalize方法");
        GcTest.gcTest = this;     //把自己关联到引用链上
    }
	
	public static void main(String[] args) throws InterruptedException {
		gcTest = new GcTest();
		gcTest = null;
		System.gc();
		Thread.sleep(500);   //finalize()方法优先级很低，需要等待一下它
        if(gcTest != null){
            System.out.println("第一次被救活");
        }else {
            System.out.println("第一次救活失败");
        }

        //对象救活后又失去了和引用链到关联，也就是进入第二次尝试救活

        gcTest = null;
        System.gc();
        Thread.sleep(500);
        if(gcTest != null){
            System.out.println("第二次被救活");
        }else {
            System.out.println("第二次救活失败");
        }
	}
	
}
