package com.wangzi.test.guava;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.google.common.base.CharMatcher;
import com.google.common.base.Joiner;
import com.google.common.base.Optional;
import com.google.common.base.Predicate;
import com.google.common.base.Splitter;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import com.google.common.primitives.Ints;

public class GuavaTest {
	
	public static void main(String[] args) {
		GuavaTest test = new GuavaTest();
		Multimap<String, String> multimap =  ArrayListMultimap.create();
		multimap.put("name", "Tom");
		multimap.put("name", "Bob");
		multimap.put("name", "Lily");
		multimap.put("address", "shanghai");
		Collection<String> col = multimap.get("name");
		System.out.println("打印姓名列表：");
		for(String name : col){
			System.out.println(name);
		}
		multimap.remove("name", "Tom");
		
		 // Maps 支持过滤数据  
        Map<String, Integer> user = new HashMap<String, Integer>();  
        user.put("小徐", 22);  
        user.put("xuyw", 23);  
        user.put("10000", 15);
        Map<String, Integer> filtedMap = Maps.filterValues(user,  
                new Predicate<Integer>() {  
                    public boolean apply(Integer value) {  
                        return value > 20;  
                    }  
                });  
        System.out.println(filtedMap);
        Optional<Integer> optional = Optional.of(test.getA());
        int a = optional.or(5);
        System.out.println(a);
        boolean flag = optional.isPresent();
        int num = optional.get();
        System.out.println("flag:" + flag + ", num:" + num);
        
        String str = CharMatcher.DIGIT.retainFrom("test 132342 test");
        String string = CharMatcher.DIGIT.removeFrom("some text 89983 and more");
        System.out.println(str + ","+ string);
        String[] subdirs = { "usr", "local", "lib" };
        String directory = Joiner.on("/").join(subdirs);
        System.out.println(directory);
        
        int[] numbers = { 1, 2, 3, 4, 5 };
        String numbersAsString = Joiner.on(";").join(Ints.asList(numbers));
        String numbersAsStringDirectly = Ints.join(";", numbers);
        System.out.println(numbersAsString + "," + numbersAsStringDirectly);
        
        Iterable<String> split = Splitter.on(",").split("numbersAsString,hhh");
        System.out.println(split);
        
        String splits = "foo,what,,,more,";
        Iterable<String> splitsTest = Splitter.on(",").omitEmptyStrings().trimResults().split(splits);
        System.out.println(splitsTest);
        
	}

	private int a;
	private String b;
	
	public String getB() {
		return b;
	}
	public void setB(String b) {
		this.b = b;
	}
	public int getA() {
		return a;
	}
	public void setA(int a) {
		this.a = a;
	}
	
}
