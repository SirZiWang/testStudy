package com.wangzi.test.jdk18;

public interface PersonFactory<P extends Person> {
	P create(String firstName, String lastName);
	
	PersonFactory<Person> personFactory = Person::new;
	Person person = personFactory.create("Peter", "Parker");
}
