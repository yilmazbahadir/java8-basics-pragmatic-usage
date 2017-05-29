package com.avivasa.workshop.java8.defaultmethod;


/**
 * default, static methods in Interface example
 * 
 * @author BAHADIR
 * */
public interface Calculator {

	/**
	 * abstract method definition
	 * */
	/* public */ int sum(int a, int b); // we can omit the public modifier - all methods and constants are public by nature
	
	
	/**
	 * default method implementation
	 * */
	default int subtract(int a, int b) {
		return a + b;
	}
	
	/**
	 * static method example 
	 */
	static SqrtCalculator getInstance() {
		return new SqrtCalculator();
	}
	
}

/**
 * Calculator implementation
 * */
class SqrtCalculator implements Calculator {

	/**
	 * @return square root of the sum
	 * */
	@Override
	public int sum(int a, int b) {
		
		return (int) Math.sqrt(a + b);
	}
	
	// no need to implement default method
}