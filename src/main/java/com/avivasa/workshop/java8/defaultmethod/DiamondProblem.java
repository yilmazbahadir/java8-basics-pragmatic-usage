package com.avivasa.workshop.java8.defaultmethod;

/**
 * Example of diamond problem
 * 
 * Java8 supports multi-type inheritance by interface default method implementation
 * When multiple interfaces abstracts/implements same method signature
 * compiler doesn't know which super method to use, this is called diamond problem.
 * */

public class DiamondProblem implements X, Y {
	// TODO 1. fix the problem
}

interface X {
	
	default void aMethod(int a) {
		
	}
}

interface Y {
	
	default void aMethod(int a) {
		
	}
}