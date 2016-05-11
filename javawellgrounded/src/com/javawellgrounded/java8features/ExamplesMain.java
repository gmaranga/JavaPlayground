package com.javawellgrounded.java8features;

import java.time.Clock;
import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Formatter;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
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
		
		//-- Parallel Streams vs sequential Streams
		//Operations on sequential streams are performed on a single thread while operations on parallel streams
		//are performed concurrent on multiple threads.
		int max = 1000000;
		List<String> values = new ArrayList<>();
		for(int i = 0; i < max; i++){
			UUID uuid = UUID.randomUUID();
			values.add(uuid.toString());
		}
		long t0 = System.nanoTime();
		long count = values.stream().sorted().count();
		long t1 = System.nanoTime();
		long millis = TimeUnit.NANOSECONDS.toMillis(t1 - t0);
		System.out.println(String.format("Sequential sort took: %d ms. Total count: %d", millis, count));
		
		t0 = System.nanoTime();
		count = values.parallelStream().sorted().count();
		t1 = System.nanoTime();
		millis = TimeUnit.NANOSECONDS.toMillis(t1 - t0);
		System.out.println(String.format("Parallel sort took: %d ms. Total count: %d", millis, count));
		
		//--Map new methods/features
		Map<Integer, String> map = new HashMap<>();
		for (int i = 0; i < 10; i++) {
			map.putIfAbsent(i, "val" + i);
		}
		System.out.println("Map forEach:");
		map.forEach((id, val) -> System.out.println(val));
		
		map.computeIfPresent(3,(key, val) -> val + key);
		System.out.println("computeIfPresent: "+ map.get(3));
		map.computeIfPresent(9, (key, value)-> null);
		System.out.println("containsKey: "+ map.containsKey(9));
		map.computeIfAbsent(23, (key)-> "val" + key);
		System.out.println("containsKey: "+ map.containsKey(23));
		//Remove entries for a a given key, only if it's currently mapped to a given value
		map.remove(3, "val33");
		System.out.println("Get after conditional removing: "+ map.get(3));
		System.out.println("Get with default value: "+ map.getOrDefault(42, "not found"));
		//Merging entries
		//Merge either put the key/value into the map if no entry for the key exists, or the merging function will be called to change the existing value.
		map.merge(9, "val9", (value, newValue)-> value.concat(newValue));
		System.out.println("Get with merge: " + map.get(9));
		map.merge(9, "concat", (value, newValue)-> value.concat(newValue));
		System.out.println("Get with merge 2: " + map.get(9));
		map.merge(9, "concat2", (value, newValue)-> value.concat(newValue));
		
		//--Date API
		//The new Date API is comparable with the Joda-Time library, however it's not the same.
		 //Clocks are aware of a timezone and may be used instead of System.currentTimeMillis() to retrieve the current milliseconds. 
		//Such an instantaneous point on the time-line is also represented by the class Instant. Instants can be used to create legacy java.util.Date objects.
		Clock clock = Clock.systemDefaultZone();
		millis = clock.millis();
		System.out.println("Clock current millis: " + millis);
		Instant instant = clock.instant();
		Date legacyNow = Date.from(instant);
		System.out.println("Legacy date from Instant: " + legacyNow);
		//Timezones.
		//Timezones are represented by a ZoneId. Timezones are represented by a ZoneId
		System.out.println("Timezones:");
		System.out.println(ZoneId.getAvailableZoneIds());
		ZoneId zone1 = ZoneId.of("US/Central");
		ZoneId zone2 = ZoneId.of("America/Argentina/Buenos_Aires");
		System.out.println(zone1.getRules());
		System.out.println(zone2.getRules());
		//LocalTime represents a time without a timezone
		LocalTime now1 = LocalTime.now(zone1);
		LocalTime now2 = LocalTime.now(zone2);
		System.out.println("now1 is before now2: " + now1.isBefore(now2));
		long hoursBetween = ChronoUnit.HOURS.between(now1, now2);
		long minutesBetween = ChronoUnit.MINUTES.between(now1, now2);
		System.out.println("Time diference in hours: " + hoursBetween);
		System.out.println("Time diference in minutes: " + minutesBetween);
		//LocalTime comes with various factory method to simplify the creation of new instances, including parsing of time strings.
		LocalTime late = LocalTime.of(23, 59, 59);
		DateTimeFormatter germanFormatter = DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT).withLocale(Locale.GERMAN);
		LocalTime leetTime = LocalTime.parse("13:37", germanFormatter);
		System.out.println("Formatted time: " + leetTime);
		//LocalDate
		//LocalDate represents a distinct date, e.g. 2014-03-11. It's immutable and works exactly analog to LocalTime.
		//Keep in mind that each manipulation returns a new instance.
		LocalDate today = LocalDate.now();
		LocalDate tomorrow = today.plus(1, ChronoUnit.DAYS);
		LocalDate yesterday = tomorrow.minusDays(2);
		LocalDate independenceDay = LocalDate.of(1816, Month.JULY, 9); 
		System.out.println("Today: " + today);
		System.out.println("Tomorrow: " + tomorrow);
		System.out.println("Yesterday: " + yesterday);
		System.out.println("Argentina independence day: " + independenceDay);
		DateTimeFormatter usFormatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM).withLocale(Locale.GERMAN);
		LocalDate xmas = LocalDate.parse("24.12.2016", usFormatter);
		System.out.println("Xmas: "  + xmas);
		//LocalDateTime. Represent a date-time. Works similar to LocalTime and LocalDate.
		LocalDateTime eoy = LocalDateTime.of(2016, Month.DECEMBER, 31, 23, 59, 59);
		DayOfWeek dayOfWeek = eoy.getDayOfWeek();
		System.out.println("eoy dow: " + dayOfWeek);
		Month month = eoy.getMonth();
		System.out.println("eoy month: " + dayOfWeek);
		long minuteOfDay = eoy.getLong(ChronoField.MINUTE_OF_DAY);
		System.out.println("eoy min of the day: " + minuteOfDay);
		//With the additional information of a timezone it can be converted to an instant.
		//Instants can easily be converted to legacy dates of type java.util.Date.
		instant = eoy.atZone(ZoneId.systemDefault()).toInstant();
		Date legacyDate = Date.from(instant);
		System.out.println("eoy to date: " + legacyDate);
		//Formatting date-times works just like formatting dates or times. Instead of using pre-defined formats we can create formatters from custom patterns.
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy - HH:mm");//Unlike java.text.NumberFormat the new DateTimeFormatter is immutable and thread-safe.
		LocalDateTime parsed = LocalDateTime.parse("Nov 03, 2016 - 15:29", formatter);
		String parsedDate = formatter.format(parsed);
		System.out.println("Custom parsed date: "+ parsedDate);
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
