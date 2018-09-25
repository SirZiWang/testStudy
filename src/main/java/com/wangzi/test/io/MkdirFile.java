package com.wangzi.test.io;

import java.io.File;
import java.io.IOException;

public class MkdirFile {

	public static void main(String[] args) throws IOException {

		File file1 = new File("D:/java/io/admin.java");
		if(!file1.exists()){
			file1.createNewFile();
		}

		File file2 = new File("D:/java/io/mkdir");
		if(!file2.exists()){
			file2.mkdir();
		}

		File file3 = new File("D:/python/io/mkdirs");
		if(!file3.exists()){
			file3.mkdirs();
		}
		file1.delete();
		file2.deleteOnExit();
		System.out.println(file1.exists());
		System.out.println(file2.exists());
	}

}
