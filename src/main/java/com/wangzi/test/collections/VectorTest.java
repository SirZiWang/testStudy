package com.wangzi.test.collections;

import java.util.Enumeration;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Vector;

public class VectorTest {
	public static void main(String[] args) {
        Vector<Integer> vector = new Vector<>();
        int len = 9000000;
        for (int i = 0; i < len; i++) {
            vector.add(i);
        }
        System.out.println(len + "个数据: Vector方法加了synchronized关键字！");


        long start = System.currentTimeMillis();
        for (int size = vector.size(), i = 0; i < size; i++) {
            int sta = vector.get(i);
        }
        System.out.println("for循环遍历耗时： " + (System.currentTimeMillis()-start) + "毫秒！");

        long second = System.currentTimeMillis();
        Iterator<Integer> iterator = vector.iterator();
        while (iterator.hasNext()) {
            int sec = iterator.next();
        }
        System.out.println("迭代器Iterator遍历耗时： " + (System.currentTimeMillis()-second) + "毫秒！");

        long third = System.currentTimeMillis();
        for (int i : vector) {// 也是Iterator实现的
            int thi = i;
        }
        System.out.println("foreach循环遍历耗时： " + (System.currentTimeMillis()-third) + "毫秒！");

        long fourth = System.currentTimeMillis();
        ListIterator<Integer> listIterator = vector.listIterator();
        while (listIterator.hasNext()) {
            int fou = listIterator.next();
        }
        System.out.println("迭代器ListIterator循环遍历耗时： " + (System.currentTimeMillis()-fourth) + "毫秒！");

        long firth = System.currentTimeMillis();
        vector.forEach(integer -> {
            int fir = integer;
        });
        System.out.println("forEach循环遍历耗时： "+ (System.currentTimeMillis()-firth) + "毫秒！");

        long sixth = System.currentTimeMillis();
        Enumeration<Integer> elements = vector.elements();
        while (elements.hasMoreElements()) {
            int six = elements.nextElement();
        }
        System.out.println("Enumeration循环耗时： " + (System.currentTimeMillis()-sixth) + "毫秒");
    }
}
