package com.javawellgrounded.java8features;

/*
 * We can use arbitrary interfaces as lambda expressions as long as the interface only contains one abstract method.
 *  To ensure that your interface meet the requirements, you should add the @FunctionalInterface annotation. 
 *  The compiler is aware of this annotation and throws a compiler error as soon as you try to add a second 
 *  abstract method declaration to the interface.
 */
@FunctionalInterface
public interface Converter<F, T> {

	T convert(F from);
}
