package com.wangzi.test.demo;

import java.io.FileOutputStream;

import jdk.internal.org.objectweb.asm.ClassWriter;
import jdk.internal.org.objectweb.asm.MethodVisitor;
import jdk.internal.org.objectweb.asm.Opcodes;

public class ASMByteCodeManipulation extends ClassLoader implements Opcodes {
	
	public static void main(String args[]) throws Exception {
		ClassWriter cw = new ClassWriter(0);
		cw.visit(V1_1, ACC_PUBLIC, "Example", null, "Son", null);

		// creates a MethodWriter for the (implicit) constructor
		MethodVisitor mw = cw.visitMethod(ACC_PUBLIC, "<init>", "()V", null,null);
		mw.visitVarInsn(ALOAD, 0);
		mw.visitMethodInsn(INVOKESPECIAL, "Son", "<init>", "()V");
		mw.visitInsn(RETURN);
		mw.visitMaxs(1, 1);
		mw.visitEnd();

		// creates a MethodWriter for the 'test' method
		mw = cw.visitMethod(ACC_PUBLIC, "test", "()V", null, null);
		mw.visitFieldInsn(GETSTATIC, "java/lang/System", "out","Ljava/io/PrintStream;");
		mw.visitLdcInsn("test of AI3");
		mw.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println",
				"(Ljava/lang/String;)V");
		//Call test() of GrandParent
		mw.visitVarInsn(ALOAD, 0);
		mw.visitMethodInsn(INVOKESPECIAL, "GrandParent", "test", "()V");
		//Call test() of GrandParent
		mw.visitVarInsn(ALOAD, 0);
		mw.visitMethodInsn(INVOKESPECIAL, "Parent", "test", "()V");
		//Call test() of GrandParent
		mw.visitVarInsn(ALOAD, 0);
		mw.visitMethodInsn(INVOKESPECIAL, "Son", "test", "()V");
		mw.visitInsn(RETURN);
		mw.visitMaxs(2, 1);
		mw.visitEnd();

		byte[] code = cw.toByteArray();
		FileOutputStream fos = new FileOutputStream("Example.class");
		fos.write(code);
		fos.close();
		ASMByteCodeManipulation loader = new ASMByteCodeManipulation();
		Class<?> exampleClass = loader.defineClass("Example", code, 0,
				code.length);
		Object obj = exampleClass.newInstance();
		exampleClass.getMethod("test", null).invoke(obj, null);

	}
}
