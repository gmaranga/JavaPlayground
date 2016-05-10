package com.javawellgrounded.java8features;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class ExamplesMain {

	static int outerStaticNum;
	int outerNum;
	
	public static void main(String[] args) {

		//-- Default methods.
		Formula formula = new Formula() {
			
			@Override
			public double calculate(int a) {
				return sqrt(a * 100);// The default method sqrt can be used out of the box.
			}
		};
		System.out.println("Default sqrt method: " + formula.sqrt(9));
		
		//-- Lambda expressions
		List<String> names = Arrays.asList("peter", "anna", "mike", "xenia");
		Collections.sort(names, (String a, String b) -> { return b.compareTo(a);});
		//For one line method bodies you can skip both the braces {} and the return keyword. But it gets even more shorter
		Collections.sort(names, (String a, String b)-> b.compareTo(a));
		//The java compiler is aware of the parameter types so you can skip them as well.
		Collections.sort(names, (a,b)-> b.compareTo(a));
		
		//-- Functional Interfaces
		//Each lambda corresponds to a given type, specified by an interface. 
		//A so called functional interface must contain exactly one abstract method declaration.
		Converter<String, Integer> converter = (from) -> Integer.valueOf(from);
		Integer converted = converter.convert("123");
		System.out.println(converted);
		
		//--Lambda scopes
		//Accessing outer scope variables from lambda expressions is very similar to anonymous objects.
		//You can access final variables from the local outer scope as well as instance fields and static variables.
		final int num = 1;//This can be marked as non final but it has to be implicitly final in order to compile.
		Converter<Integer, String> stringConverter = (from)-> String.valueOf(from + num);
		System.out.println("stringConverter: " + stringConverter.convert(2));
		//Accessing fields and static variables
		//We have read and write access to instance fields and static variables from within lambda expressions.
		new ExamplesMain().testScopes();
		
		//-- Method and Constructor References
		//The above example code can be further simplified by utilizing static method references:
		Converter<String, Integer> simpleConverter = Integer::valueOf;
		Integer simpleConverted = simpleConverter.convert("123");
		//Java 8 enables you to pass references of methods or constructors via the :: keyword. 
		//The above example shows how to reference a static method. But we can also reference object methods:
		StartWith sw = new StartWith();
		Converter<String, String> swconverter = sw::startWith;
		System.out.println("Starts with: " + swconverter.convert("Java"));
		//:: with constructors
		//We create a reference to the Person constructor via Person::new. 
		//The Java compiler automatically chooses the right constructor by matching
		//the signature of PersonFactory.create.
		PersonFactory<Person> personFactory = Person::new;
		Person person = personFactory.create("Peter", "Parker");
		System.out.println("New person: " + person.firstName + " " + person.lastName);
		//Default method cannot be accessed from within lambda expressions.
		//Ex: Formula formula = (a) -> sqrt(a * 100); Does not compile
		
		//-- Predicates
		//Predicates are boolean-valued functions of one argument. 
		//The interface contains various default methods for composing predicates to complex logical terms (and, or, negate)
		Predicate<String> predicate = (s)-> s.length() > 0;
		System.out.println("Predicate test: " + predicate.test("foo"));
		System.out.println("Negated predicate: " + predicate.negate().test("foo"));
		
		Predicate<Person> isNull = Objects::isNull;
		System.out.println("IsNull: " + isNull.test(person));
		
		//--Functions
		//Functions accept one argument and produce a result. 
		//Default methods can be used to chain multiple functions together (compose, andThen).
		Function<String, Integer> toInteger = Integer::valueOf;
		Function<String, String> backToString = toInteger.andThen(String::valueOf);
		System.out.println(backToString.apply("123").getClass());
		
		//--Suppliers
		//Suppliers produce a result of a given generic type. Unlike Functions, Suppliers don't accept arguments.
		Supplier<Person> personSupplier = Person::new;
		System.out.println(personSupplier.get());
		
		//--Consumers
		//Consumers represents operations to be performed on a single input argument.
		Consumer<Person> gretter = (p)-> System.out.println("Hello, " + p.firstName);
		gretter.accept(new Person("Luke", "Skywalker"));
		
		//--Comparators
		//Comparators are well known from older versions of Java. Java 8 adds various default methods to the interface.
		Comparator<Person> comparator = (p1, p2) -> p1.firstName.compareTo(p2.firstName);
		Person p1 = personFactory.create("John", "Doe");
		Person p2 = personFactory.create("Alice", "Wonderland");
		System.out.println(comparator.compare(p1, p2));
		System.out.println(comparator.reversed().compare(p1, p2));
		
		//-- Optionals
		//Not functional interfaces, instead it's a nifty utility to prevent NullPointerException.
		//A simple container for a value which may be null or non-null. 
		//Think of a method which may return a non-null result but sometimes return nothing. 
		//Instead of returning null you return an Optional in Java 8.
		Optional<String> optional = Optional.ofNullable("bam");
		System.out.println("Is present: " + optional.isPresent());
		System.out.println(optional.get());
		
		//-- Streams
		//A java.util.Stream represents a sequence of elements on which one or more operations can be performed. 
		//Stream operations are either intermediate or terminal. 
		//While terminal operations return a result of a certain type, intermediate operations return the stream itself
		//so you can chain multiple method calls in a row. 
		//Streams are created on a source, e.g. a java.util.Collection like lists or sets (maps are not supported). 
		//Stream operations can either be executed sequential or parallel.
		List<String> stringCollection = new ArrayList<>();
		stringCollection.add("ddd2");
		stringCollection.add("aaa2");
		stringCollection.add("bbb1");
		stringCollection.add("aaa1");
		stringCollection.add("bbb3");
		stringCollection.add("ccc");
		stringCollection.add("bbb2");
		stringCollection.add("ddd1");
		// Filter
		// Filter accepts a predicate to filter all elements of the stream.
		// This operation is intermediate which enables us to call another
		// stream operation (forEach) on the result.
		// ForEach accepts a consumer to be executed for each element in the
		// filtered stream.
		// ForEach is a terminal operation. It's void, so we cannot call another
		// stream operation.
		System.out.println("Filter:");
		stringCollection.stream().filter((s) -> s.startsWith("a")).forEach(System.out::println);
		// Sorted
		// Sorted is an intermediate operation which returns a sorted view of
		// the stream.
		// The elements are sorted in natural order unless you pass a custom
		// Comparator.
		System.out.println("Sorted filter:");
		// The ordering of stringCollection is untouched
		stringCollection.stream().sorted().filter((s) -> s.startsWith("a")).forEach(System.out::println);
		// Map
		//The intermediate operation map converts each element into another object via the given function. 
		//The following example converts each string into an upper-cased string. 
		//But you can also use map to transform each object into another type. 
		//The generic type of the resulting stream depends on the generic type of the function you pass to map
		System.out.println("Map:");
		stringCollection.stream().map(String::toUpperCase).sorted((a, b) -> b.compareTo(a))
				.forEach(System.out::println);
		//Match
		//Various matching operations can be used to check whether a certain predicate matches the stream. 
		//All of those operations are terminal and return a boolean result.
		System.out.println("Match:");
		System.out.println("Any starts with 'a': " + stringCollection.stream().anyMatch((s)-> s.startsWith("a")));
		System.out.println("All starts with 'a': " + stringCollection.stream().allMatch((s)-> s.startsWith("a")));
		System.out.println("None starts with 'z': " + stringCollection.stream().noneMatch((s)-> s.startsWith("z")));
		//Count
		//Count is a terminal operation returning the number of elements in the stream as a long.
		System.out.println("Count:");
		System.out.println(
				stringCollection.stream().filter((s) -> s.startsWith("b")).count() + " strings start with 'b'");
		//Reduce
		//This terminal operation performs a reduction on the elements of the stream with the given function.
		//The result is an Optional holding the reduced value.
		Optional<String> reduced = stringCollection.stream().sorted().reduce((s1, s2) -> s1 + "#" + s2);
		reduced.ifPresent(System.out::println);
	}
	
	void testScopes(){
		Converter<Integer, String> stringConverter1 = (from) -> {
			outerNum = 23;
			return String.valueOf(from + outerNum);
		};
		
		Converter<Integer, String> stringConverter2 = (from) -> {
			outerStaticNum = 72;
			return String.valueOf(from + outerStaticNum);
		};
		
		System.out.println(stringConverter1.convert(7));
		System.out.println(stringConverter2.convert(8));
	}
}
