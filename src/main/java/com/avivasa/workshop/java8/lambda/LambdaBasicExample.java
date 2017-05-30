package com.avivasa.workshop.java8.lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

public class LambdaBasicExample {

	/**
	 * Anonymous inner class version*/
	public void sort(List<String> list) {
		list.sort(new Comparator<String>(){
			@Override
			public int compare(String s1, String s2) {
				return s1.compareTo(s2);
			}
		});
	}
	
	/**
	 * Lambda version
	 * 
	 * concise, more readable
	 * */
	public void sortLambda(List<String> list) {
		list.sort((s1, s2) -> s1.compareTo(s2));
	}
	
	
	/**
	 * Lambda no parameter no return value
	 * */
	public Runnable _0parameterNoReturn() {
		return () -> System.out.println(""); // we have to use brackets for no parameter
				// OR
		// return () -> { System.out.println(""); };
	}
	
	/**
	 * Lambda 1 parameter no return value
	 * */
	public Consumer<String> _1parameterNoReturn() {
		return s -> { System.out.println(s); };
				// OR
		// return s -> System.out.println(s);  
	}
	
	/**
	 * Lambda 1 parameter return value without return keyword
	 * */
	public Function<String, String> _1parameterReturnValueNoReturnKeyword() {
		return s -> s; // identity function same as Function.identity();  
	}
	
	/**
	 * Lambda 1 parameters return value without return keyword
	 * */
	public Function<String, String> _1parameterReturnValue() {
		return s -> {return s;}; // identity function same as Function.identity();  
	}
	
	/**
	 * Lambda 2 parameters no return value
	 * */
	public BiConsumer<String, String> _2parameterNoReturn() {
		return (s, t) ->  System.out.println(s + t); // we have to use brackets for more than 1 parameters
	}
	
	
	public static void main(String[] args) {
		List<String> list1 = Arrays.asList("Bahadir", "Sefa", "Volkan", "Serkan");
		List<String> list2 = new ArrayList<>(list1);
		
		LambdaBasicExample example = new LambdaBasicExample();
		example.sort(list1);
		System.out.println("Old way:");
		list1.stream().forEach((s) -> System.out.println("\t" + s));
		
		System.out.println("Lambda:");
		example.sortLambda(list2);
		list2.stream().forEach((s) -> System.out.println("\t" + s));		
		
	}
}
