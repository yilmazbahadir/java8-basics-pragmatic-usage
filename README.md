# Java8 Workshop 
Java8 Basics and Pragmatic Usage with Examples



## Focusing on
	
* [Interface : default and static methods](#default-static-methods-interface)
* [Functional programming paradigm](#functional-programming-paradigm)
* [Lambdas](#lamdas)
* [Streams](#streams)
* [Collectors](#collectors)
* [Optionals](#optionals)
   
-----------

### Interface: default and static methods

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

Every Lambda expression can be implemented as Anonymous inner class. Basicly we call an interface Functional if it has only one abstract method.

> When writing lambda expressions we should think about parameters and the returning value of the interface(functional) that it corresponds. 


A lambda expression looks like
```
** ( method's parameter ) -> ( method's body ) **


When **no** parameters
> () -> {}  

When **only one** parameter
>t -> {}

When **multiple** paramters
>(t, k) -> {}


```

And a Functional Interface looks like
```
@FunctionalInterface
public interface Runnable {
     public abstract void run();
}
```
