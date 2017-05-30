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
