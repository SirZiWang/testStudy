package com.wangzi.test.algorithm;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class MyClassLoader extends ClassLoader{
	
	private String loaderName = ""; //类加载名称
	private String path = ""; //加载类的路径
	private final String fileType = ".class";
	
	public MyClassLoader(String name){
		super(); //应用类加载器为该类的父类
		this.loaderName = name;
	}
	public MyClassLoader(ClassLoader parent, String name){
		super(parent);
		this.loaderName = name;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	
	@Override
	public String toString() {
		return this.loaderName;
	}
	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {
		byte[] date = loaderClassData(name);
		return this.defineClass(name, date, 0, date.length);
	}
	//读取.class文件
	private byte[] loaderClassData(String name) {
		InputStream is = null;
		byte[] date = null;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			is = new FileInputStream(new File(path + name + fileType));
			int c= 0;
			while(-1 != (c=is.read())){
				baos.write(c);
			}
			date = baos.toByteArray();
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			try {
				if(is != null)
					is.close();
				if(baos != null)
					baos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return date;
	}
	
}
