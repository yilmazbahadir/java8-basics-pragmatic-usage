package com.avivasa.workshop.java8.lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

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
