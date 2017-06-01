package com.avivasa.workshop.java8.optional;

import java.util.Optional;

public class OptionalBasicExample {

	
	public void testOptionalCreate() {
		String s = "1";
		Optional<String> opt1 = Optional.of(s); // must be non-null otherwise throws exception
		Optional<String> opt2 = Optional.ofNullable(s); // if null then Optional.EMPTY
		
		System.out.println("Optionals are created.");
	}
	
	public void testOptionalMap() {
		String s = "123";
		// Optional<String> optional = Optional.of(null);
		Optional<String> optional = Optional.ofNullable(s); 
		Optional<Integer> intOptional = optional.map(Integer::parseInt);
		
		System.out.println("Optional Map:" + intOptional.get());
		
		System.out.println("Optional Map Or Else:" + intOptional.orElseGet(() -> 0));
	}
	
	
	public static void main(String[] args) {
		OptionalBasicExample example = new OptionalBasicExample();
		example.testOptionalCreate();
		example.testOptionalMap();
	}
}
