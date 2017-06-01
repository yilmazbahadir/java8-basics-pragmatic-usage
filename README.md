# Java8 Workshop 
Java8 Basics and Pragmatic Usage with Examples



## Focusing on
	
* [default and static methods in Interface](#default-and-static-methods-in-interface)
* [Functional programming paradigm](#functional-programming-paradigm)
* [Lambdas](#lambdas)
* [Streams](#streams)
* [Collectors](#collectors)
* [Optionals](#optionals)
   
-----------

### default and static methods in Interface

- default methods
	- extends the interface without the fear of breaking inheritance chain
	- multi-type behavioral inheritance is now possible
	- base class implementation is now not a necessity
	- the difference between abstract class and interface is lessened
	- to support lambdas in Collection classes actually
	- pay attention to diamond problem : extending multiple classes and superclass doesn't know which method to use (same method signature). When a class implements 2 interfaces having same default method signature compiler forces to implements the default method
	
- static methods 
	- only be called through the interface or class type reference, not an instance variable
	- can not be overridden as it is for normal static methods	 

### Functional Programming Paradigm

The functional programming paradigm was explicitly created to support functional approach to problem solving.
Functional programming is a type of declarative programming. Prior to Java 8, Java was more like a imperative(procedural) programming language.

| Imperative        | Functional |
| ----------------- |------------|
| A developer writes code in exact order the steps that the computer must take to accomplish a goal.   | Developer must functionalize the code and compose them to be executed by computer. |
| int[] array = new int[] {  1, 2, 3, 4, 5, 6, 7, 8, 8, 8, 9, 10  }; <br/>int sum = 0;<br/> for (int j = 0; j < array.length; j++) {<br/> &nbsp;for (int k = j + 1; k < array.length; k++) { <br/>&nbsp;&nbsp;if (k != j && array[k] == array[j])       { <br/>&nbsp;&nbsp;&nbsp; sum = sum + array[k]; <br/>          &nbsp;&nbsp;&nbsp; System.out.println(                "Duplicate found: "              + array[k]              + " "              + &nbsp;&nbsp;&nbsp;"Sum of the duplicate value is " + sum);       <br/> &nbsp;&nbsp;}   <br/> &nbsp;}<br/>}| int[] array = new int[] {  1, 2, 3, 4, 5, 6, 7, 8, 8, 8, 9, 10  }; <br/>IntStream.of(array)<br/>.boxed()<br/>.collect(groupingBy(i -> i))<br/>.entrySet()<br/>.stream()<br/>.filter(e -> e.getValue().size() > 1)<br/>.forEach(e -> {System.out.println("Duplicates found for : "+ e.getKey()+ " their sum being : "+ e.getValue().stream().collect(summingInt(i -> i)));}); |


Before Java 8 all we could do was to mimic functional style programming in Java. You would use anonymous inner classes to pass behavior to methods, classes. Or you would use libraries such as Guava in order to support functional programming. Higher order functions were not supported. With the help of Lambdas, java.util.Function package in Java 8, things changed.

|Characteristic |Imperative  |Functional |
|-----|-----|-----|
| Programmer focus	| How to perform tasks (algorithms) and how to track changes in state.	|What information is desired and what transformations are required.|
|State changes	 |Important.	 |Non-existent. |
| Order of execution	|Important.	 |Low importance. |
|Primary flow control	 | Loops, conditionals, and function (method) calls.	| Function calls, including recursion.|
| Primary manipulation unit	|Instances of structures or classes.	 | Functions as first-class objects and data collections.|


### Lambdas

- functional style programming (imperative(procedural) -> declarative(functional))
- make your code concise (short but easily understandable ?) 
- allow to pass behavior
- higher order programming
	- functions take functions as parameter or returns function
- before Java 8 anonymous classes were used for behavior passing

Every Lambda expression can be implemented as Anonymous inner class. Basically, we call an interface **Functional** if it has **only one abstract method**.

> When writing lambda expressions we should think about parameters and the returning value of the interface(functional) that it corresponds. 


A lambda expression looks like
```
( method's parameter ) -> ( method's body ) 


When no parameters:  () -> {}  

When only one parameter: t -> {}

When multiple parameters: (t, k) -> {}


```

And a Functional Interface looks like
```
@FunctionalInterface
public interface Runnable {
     public abstract void run();
}
```
>@FunctionalInterface is not mandatory. It is a marker and it helps compiler to check fitness of the code in to functional interface pattern. 

Lambdas are not compiled as Anonymous Inner Classes. When we examine byte code compiled, we see invokedynamic instruction is used.

```java
  public sortLambda(java.util.List arg0) { //(Ljava/util/List;)V
         <sig:(Ljava/util/List<Ljava/lang/String;>;)V>         <localVar:index=0 , name=this , desc=Lcom/avivasa/workshop/java8/lambda/LambdaBasicExample;, sig=null, start=L1, end=L2>
         <localVar:index=1 , name=list , desc=Ljava/util/List;, sig=Ljava/util/List<Ljava/lang/String;>;, start=L1, end=L2>

         L1 {
             aload1
             invokedynamic compare(java.util.Comparator);
             invokeinterface java/util/List sort((Ljava/util/Comparator;)V);
         }
         L3 {
             return
         }
         L2 {
         }
     }
```

| Anonymous Inner Class | Lambda |
|-----------------------|--------|
|this refers to inner class | this refers to the enclosing class|
|shadowing variables is possible|shadowing variables is impossible|
|explicitly specify the class | no need to specify, it is inferred from the context|


#### Functional Interfaces

Although it is possible to write your own functional interfaces, in most cases you don't need. Because there are
predefined Functional interfaces and they are placed in **java.util.function** package.

##### java.util.function.Function<T, R>

Represents a function that accepts one argument and produces a result.

Function is
```java
R apply(T t);
```

Example usage:
```java
Function<String, String> trimmer = str -> str.trim(); 
```

##### java.util.function.Predicate<T>

Represents a predicate (boolean-valued function) of one argument.

Function is
```java
boolean test(T t); 
```

##### java.util.function.Consumer<T>

Accepts a single input argument and returns no result.

Function is
```java
void accept(T t); 
```
##### java.util.function.Supplier<T>

Takes no parameter, returns result.

Function is
```java
T get(); 
```
##### java.util.function.BiConsumer<T,U>

Accepts 2 parameters, returns no result.

Function is
```java
void accept(T t, U u); 
```

##### Double Colon(::) Operator
:: Operator is shorthand for lambdas and is introduced in Java 8.

Instead of
`Function<String, Integer> strToLength = str -> str.length();`
We can use
`Function<String, Integer> strToLength = String::length;`

As you notice in first statement function takes str as parameter and returns result of the length method call. In second statement it takes a string object and returns length method call result as well with :: operator. 

```java 
Consumer<String> strToPrint = (s) -> System.out.println(s);

// becomes

Consumer<String> strToPrint = System.out::println;
```
In the code block above, lambda takes an input and returns no result, that corresponds a java.util.function.Consumer functional interface, not java.util.function.Function.

>Double colon(::) operator does not mean it always references a java.util.function.Function, it must be represented as matching functional interface according to inputs and the result. 

### Streams

Stream API  which is introduced with Java 8, is a abstraction for operations over Collections in a functional manner where Collection API is a abstraction for data structure.

> A stream is a pipeline of functions that can be evaluated.

* A stream is not a data structure.
* Streams cannot mutate data.

This how we convert any collection to stream:
```java
List<String> numberList = Arrays.asList("1", "2", "3", "4");
numberList.stream(); // or numberList.parallelStream();
```

If we don't have any collection then:
```java
Stream.of("1", "2", "3", "4"); // Stream.of("1", "2", "3", "4").parallel();

```
  
Stream manipulation can be done in a single thread using `stream()` method or in concurrently using `parallelStream()` method.

>There are predefined stream classes for primitive data types such as IntStream, DoubleStrem, LongStream.  
 
 There are 2 types of operations available on streams. `intermediate` and `terminal` operations.
 We are examining the essential methods of Stream interface below.
 
#### Intermediate Operations

* Always return streams
* Lazily executed

##### map
Maps one type of stream to another. Executes a function for all of the elements in the stream.

A code snippet transforms int stream to String stream
```java
IntStream.range(1,6).boxed().map(Object::toString).forEach(System.out::print);
// prints 12345
```
##### flatMap

Stream operations (filter, sum, distinct…) and collectors do not support operation on Stream<String[]>, Stream<Set<String>>, Stream<List<String>>. 
We use `flatMap` to do these conversions :
```
Stream<String[]>		-> flatMap ->	Stream<String>
Stream<Set<String>>	-> flatMap ->	Stream<String>
Stream<List<Object>>	-> flatMap ->	Stream<Object>
```
A code snippet transforms int[] stream to string stream
```java
Stream.of(new Integer[]{1,2}, new Integer[]{3, 4}, new Integer[]{5, 6}).flatMap(arr -> Arrays.stream(arr)).map(Object::toString).forEach(System.out::print);
```
##### filter
Filters the element of the stream that match the given predicate.
```java
IntStream.range(1,6).filter(n -> n > 3).boxed().map(Object::toString).forEach(System.out::print);
//prints 45 
```

##### distinct
Returns a stream consisting of the distinct elements (according to Object.equals(Object)) of this stream.

##### limit
Returns a stream consisting of the elements of this stream, truncated to be no longer than maxSize in length.

##### sorted
Sorts a stream based on natural order or Comparator given.

```java
Stream.of(1,3,5,4,2).sorted().map(Object::toString).forEach(System.out::print);
// prints 12345
```
##### peek
Allows you to run a consumer(takes 1 parameter, no result) operation on each element while returning exact same stream.

```java
Stream.of(1,3,5,4,2).sorted().map(Object::toString).peek(t -> System.out.print(".")).forEach(System.out::print);
// prints .1.2.3.4.5
```

> Order of composing intermediate operations is essential //TODO give the orders, examples

#### Intermediate Operations Cheat Sheet

|Function|Preserves count|Preserves type|Preserves order|
|-------|-------|-------|-------|
|map|+|-|+|
|filter|-|+|+|
|distinct|-|+|+|
|sorted|+|+|+|
|peek|+|+|+|


#### Terminal Operations

* Return concrete types or produce a side effect
* Eagerly executed

##### reduce
Performs a reduction on the elements of the stream. Result is single.

```java
  IntStream.range(1, 4).reduce((a, b) -> a + b).orElse(0); // 1 + 2 + 3 = 6
```
##### collect
Accumulates input elements into a mutable result container, such as Collection or StringBuilder through Collector implementation.

```java
 List<Integer> list = IntStream.range(1, 4).boxed().collect(Collectors.toList());
```

##### forEach
Performs an action for each element of the stream.

```java
 IntStream.range(1, 4).boxed().forEach(System.out::println);
 //prints 123
```
#### sum
Sums up all the primitive items in the stream(IntStream, DoubleStream).

```java
 int sum = Stream.of("1", "2", "3", "4").mapToInt(Integer::parseInt).sum();
```

#### count
Returns the count of the elements in the stream.
 
```java
 int count = Stream.of("1", "2", "3", "4").mapToInt(Integer::parseInt).count();
```

>Intermediate operations won't run unless there is a Terminal Operation on that stream.


#### Terminal Operations Cheat Sheet
|Function|Output|When to use|
|-------|-------|-------|
|reduce|concrete type|to cummulate elements|
|collect|list, map or set|to group elements|
|forEach|side effect(method invoke,sysout etc.)| to perform a side effect on elements|

 
 
### Collectors
 
 In Java 8 `java.util.stream.Collector` interface is used in Stream.collect method in order to do perform mutable fold operations (repackaging elements to some data structures and applying some additional logic, concatenating them, etc.)

>The purpose of the collector is to compose the data from the stream into one another type.

 `java.util.stream.Collectors` is a utility class having various Collector interface implementations which enable us to do useful reduction operations, such as accumulating elements into collections, summarizing elements according to various criteria, etc.
 
 A collector looks like: 
 ```java
 interface Collector<T,A,R> {
    Supplier<A>          supplier()
    BiConsumer<A,T>      accumulator()
    BinaryOperator<A>    combiner()
    Function<A,R>        finisher()
    Set<Characteristics> characteristics()
}
```

Let's examine all methods through an example:
```java
List<Integer> list = IntStream.range(1,6).boxed().collect(Collectors.toList());
```

What does `Collectors.toList()` do here and how ? 

```java
public final class java.util.stream.Collectors {
...
public static <T>
    Collector<T, ?, List<T>> toList() {
        return new CollectorImpl<>((Supplier<List<T>>) ArrayList::new, List::add,
                                   (left, right) -> { left.addAll(right); return left; },
                                   CH_ID);
    }
...
}    
```

In our example as you notice stream is an Integer stream. So T, type of stream element, is Integer. R, return type, is List\<Integer\>. A, intermediate operator(accumulator) type, is also List\<Integer\>. 

As further explanation, `supplier` supplies a new ArrayList, `accumulator` takes the ArrayList and add a item from the stream. `Combiner` takes two ArrayLists and returns the merged list. This goes on till the last item on the stream.

### Common methods of java.util.stream.Collectors

Let's examine methods through the sample Class of Stream
```java
public class Car {
	private String brandName;
	private String modelName;
	private String modelYear;
	private Oil oilType;
	private double oilConsumptionPer100Km;
	private BigDecimal price;
	private Map<String, String> owners;
}

public enum Oil {
	NORMAL, DIESEL
}

// in main method
Car[] cars = new Cars[] { ...}; // Array of Cars
```

#### toList()
Converts stream to same type of List.

```java
List<Car> carList = Stream.of(cars).collect(Collectors.toList());
``` 

#### toSet()
Converts stream to same type of Set (distinct objects according to Object.equals)
```java
Set<Car> carList = Stream.of(cars).collect(Collectors.toSet());
``` 

#### toMap(Function<? super T, ? extends K>, Function<? super T, ? extends U>)
Converts stream to Map with the help of keyMapper, valueMapper functions.
```java
Map<Integer> map = Stream.of(1,2,3,4,5).boxed().collect(Collectors.toMap());
``` 
#### toConcurrentMap(Function<? super T, ? extends K>, Function<? super T, ? extends U>)
Converts stream to ConcurrentMap with the help of keyMapper, valueMapper functions.
```java
Map<Integer, Car> carMap = Stream.of(cars).collect(Collectors.toConcurrentMap(Car::getModelYear, t -> t));
``` 
#### joining(CharSequence)
```java
String modelsJoined = Stream.of(cars).map(Car::getModelName).collect(Collectors.joining(","));
```
#### groupingBy(Function<? super T, ? extends K>)
```java
Map<String, List<Car>> groupingCarsByBrandName = Stream.of(cars).collect(Collectors.groupingBy(Car::getBrandName));
```

#### mapping(Function<? super T, ? extends U>, Collector<? super U, A, R>)
```java
List<String> brandModelNameList = Stream.of(cars).collect(Collectors.mapping(car -> car.getBrandName() + "-" + car.getModelName(), Collectors.toList()));
```

#### collectingAndThen(Collector<T, A, R>, Function<R, RR>)
```java
List<String> unmodifiableBrandModelNameList = Stream.of(cars).collect(Collectors.collectingAndThen(Collectors.mapping(car -> car.getBrandName() + "-" + car.getModelName(), Collectors.toList()), (List<String> t) -> Collections.unmodifiableList(t)));
```

#### counting()
```java
long count = Stream.of(cars).collect(Collectors.counting());
```

#### minBy(Comparator<? super T>)
```java
Optional<Car> minByModelYear = Arrays.asList(cars).stream().collect(Collectors.minBy(Comparator.comparing(Car::getModelYear)));
```

#### maxBy(Comparator<? super T>)
```java
Optional<Car> maxByModelYear = Arrays.asList(cars).stream().collect(Collectors.maxBy(Comparator.comparing(Car::getModelYear)));
```

#### summingDouble(ToDoubleFunction<? super T> mapper)
```java
Stream.of(cars).collect(Collectors.groupingBy(Car::getBrandName, Collectors.summingDouble(t -> t.getPrice().doubleValue()))).entrySet().forEach(System.out::println);;
```

#### reducing(BinaryOperator<T>)
```java
Optional<Car> maxByModelYear = Arrays.asList(cars).stream().collect(Collectors.reducing((t, k) -> Integer.compare(t.getModelYear(), k.getModelYear()) > 0 ? t : k ));
```

#### partitioningBy(Predicate<? super T>)
```java
Map<Boolean, List<Car>> partitionedCarMap = Arrays.asList(cars).stream().collect(Collectors.partitioningBy(c -> c.getOilType() == Oil.DIESEL));
```


### Optionals

Optional is an attempt to reduce the number of null pointer exceptions in Java systems.

> If a function returns Optional object, it explicitly force you to think about null value and handle it.


**of/ofNullable**: Create an Optional :
```java
Optional<String> optional = Optional.of(a); // must be non-null otherwise throws NullPointerException
Optional<String> optionalNullable = Optional.ofNullable(a); // if null value then Optional.EMPTY is returned
```

**present**: Check value is present:

```java
Optional<String> optional = Optional.ofNullable(a);
if(optional.isPresent()) {
	// then
}
```
**get**: If value is present returns the value 
else throws NoSuchElementException
```java
Optional<String> optional = Optional.ofNullable(a);
if(optional.isPresent()) {
	String value = optional.get();
}
```


**map**: If value is present, apply the mapping function and return optional
else return empty optional 
```java
String s = "123";
Optional<String> optional = Optional.ofNullable(a); 
Optional<Integer> intOptional = optional.map(Integer::parseInt);
```

