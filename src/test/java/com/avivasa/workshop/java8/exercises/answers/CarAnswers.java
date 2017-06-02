package com.avivasa.workshop.java8.exercises.answers;

import static com.avivasa.workshop.java8.common.Car.cars;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.Test;

import com.avivasa.workshop.java8.common.Car;

public class CarAnswers {


	/**
	 * Find the most oil consuming car
	 * */
	@Test
	public void mostOilConsumingCar() {
		Optional<Car> result = Arrays.stream(cars).reduce((c1, c2) -> Double.compare(c1.getOilConsumptionPer100Km(), c2.getOilConsumptionPer100Km()) > 0 ? c1 : c2);
		if(result.isPresent()) {
			System.out.println(result.get());
		}
	}
	
	/**
	 * Sort the cars according to the oilConsumption in desc order
	 * */
	@Test
	public void sortCarsConsumptionDesc() {
		Arrays.stream(cars).sorted((c1, c2) -> Double.compare(c2.getOilConsumptionPer100Km(), c1.getOilConsumptionPer100Km())).forEachOrdered(System.out::println);
	}
	
	/**
	 * List cars which brandName contains letter a (case-insensitively)
	 * */
	@Test
	public void listCarsBrandNameA() {
		Arrays.stream(cars).filter(c -> c.getBrandName().toLowerCase().contains("a")).forEach(System.out::println);
	}
	
	/**
	 * Total price of cars which brandName contains letter a (case-insensitively)
	 * */
	@Test
	public void sumTotalPriceOfCarsBrandNameA() {
		double sum = Arrays.stream(cars).filter(c -> c.getBrandName().toLowerCase().contains("a")).mapToDouble(c -> c.getPrice().doubleValue()).sum();
		System.out.println("Sum:" + sum);
	}
	
	/**
	 * List the car owner names
	 * */
	@Test
	public void listTheCarOwnerNames() {
		//TODO implement this
	}
	
	/**
	 * Find date ranges Cem Yilmaz owned a car
	 * */
	@Test
	public void findDateRangesCemYilmazOwned() {
		Arrays.stream(cars).flatMap(c -> c.getOwners().entrySet().stream()).filter(e -> e.getValue().contains("Cem Yilmaz")).map(e -> e.getKey()).forEach(System.out::println);
	}
	
	/**
	 * print the owners between 2011-2015
	 * */
	@Test
	public void printTheOwners2011_2015() {
		
	}
}
