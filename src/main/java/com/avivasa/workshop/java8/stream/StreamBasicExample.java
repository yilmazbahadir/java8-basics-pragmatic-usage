package com.avivasa.workshop.java8.stream;

import static com.avivasa.workshop.java8.common.Car.cars;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.avivasa.workshop.java8.common.Car;
import com.avivasa.workshop.java8.common.Oil;

public class StreamBasicExample {

	public void testCollectorsToList() {
		List<Car> carList = Stream.of(cars).collect(Collectors.toList());
		System.out.println("***List of Cars:");
		carList.stream().forEach(System.out::println);
	}
	
	public void testCollectorsToSet() {
		Set<Car> carSet = Stream.of(cars).collect(Collectors.toSet());
		System.out.println("***Set of Cars:");
		carSet.stream().forEach(System.out::println);
	}
	
	public void testCollectorsToMap() {
		Map<Integer, Car> carMap = Stream.of(cars).collect(Collectors.toMap(Car::getModelYear, t -> t));
		System.out.println("***Map of Cars:");
		carMap.entrySet().forEach((e) -> System.out.println(e.getValue()));
		
		// adding a car which will be duplicate according to modelYear (2014)
		List<Car> carList = new ArrayList<Car>(Arrays.asList(cars));
		carList.add(new Car("Mercedes", "c180", 2014, Oil.NORMAL, 7.00, BigDecimal.valueOf(124000.00), Stream.of("2014-2017@Mahmut Tuncer").collect(Collectors.toMap(k -> k.substring(0, k.indexOf('@')), v -> v.substring(v.indexOf('@') + 1, v.length())))));
		carMap = Stream.of(cars).collect(Collectors.toMap(Car::getModelYear, t -> t, (i1, i2) -> i2));
		System.out.println("Distinct Map of Cars(According to year):");
		carMap.entrySet().forEach((e) -> System.out.println(e.getValue()));
	}
	
	public void testCollectorsToConcurrentMap() {
		Map<Integer, Car> carMap = Stream.of(cars).collect(Collectors.toConcurrentMap(Car::getModelYear, t -> t));
		System.out.println("***ConcurrentMap of Cars:");
		carMap.entrySet().forEach((e) -> System.out.println(e.getValue()));
	}
	
	public void testCollectorsJoining() {
		String modelsJoined = Stream.of(cars).map(Car::getModelName).collect(Collectors.joining(","));
		System.out.println("***Joining modelNames:" + modelsJoined);
	}
	
	public void testCollectorsGroupingBy() {
		Map<String, List<Car>> groupingCarsByBrandName = Stream.of(cars).collect(Collectors.groupingBy(Car::getBrandName));
		System.out.println("***Grouping Cars By Brand Name:");
		groupingCarsByBrandName.entrySet().forEach(System.out::println);
	}
	
	public void testCollectorsMapping() {
		List<String> brandModelNameList = Stream.of(cars).collect(Collectors.mapping(car -> car.getBrandName() + "-" + car.getModelName(), Collectors.toList()));
		System.out.println("***Mapping Brand-Model Name:");
		brandModelNameList.forEach(System.out::println);
	}
	
	public void testCollectorsCollectingAndThen() {
		List<String> unmodifiableBrandModelNameList = Stream.of(cars).collect(Collectors.collectingAndThen(Collectors.mapping(car -> car.getBrandName() + "-" + car.getModelName(), Collectors.toList()), (List<String> t) -> Collections.unmodifiableList(t)));
		System.out.println("***CollectingAndThen Brand-Model Name:");
		unmodifiableBrandModelNameList.forEach(System.out::println);
	}
	
	public void testCollectorsCounting() {
		long count = Stream.of(cars).collect(Collectors.counting());
		System.out.println("***Counting Cars:" + count);
	}
	
	public void testCollectorsMinBy() {
		Optional<Car> minByModelYear = Arrays.asList(cars).stream().collect(Collectors.minBy(Comparator.comparing(Car::getModelYear)));
		//Arrays.asList(cars).stream().collect(Collectors.minBy((t,k) -> Integer.compare(t.getModelYear(), k.getModelYear()) ));
		if(minByModelYear.isPresent()) {
			Car car = minByModelYear.get();
			System.out.println("***Min By:" + car);
		}
	}
	
	public void testCollectorsMaxBy() {
		Optional<Car> maxByModelYear = Arrays.asList(cars).stream().collect(Collectors.maxBy(Comparator.comparing(Car::getModelYear)));
		if(maxByModelYear.isPresent()) {
			Car car = maxByModelYear.get();
			System.out.println("***Max By:" + car);
		}
	}
	
	public void testcollectorsSummingDouble() {
		System.out.println("***SummingInt Group BrandName Sum Prices:");
		Stream.of(cars).collect(Collectors.groupingBy(Car::getBrandName, Collectors.summingDouble(t -> t.getPrice().doubleValue()))).entrySet().forEach(System.out::println);;
	}
	
	public void testCollectorsReducing() {
		Optional<Car> maxByModelYear = Arrays.asList(cars).stream().collect(Collectors.reducing((t, k) -> Integer.compare(t.getModelYear(), k.getModelYear()) > 0 ? t : k ));
		//Optional<Car> maxByModelYear2 = Arrays.asList(cars).stream().reduce((t, k) -> Integer.compare(t.getModelYear(), k.getModelYear()) > 0 ? t : k);
		
		if(maxByModelYear.isPresent()) {
			Car car = maxByModelYear.get();
			System.out.println("***Max By Model Year reducing:" + car);
		}
	}
	
	
	public void testCollectorsPartitioningBy() {
		Map<Boolean, List<Car>> partitionedCarMap = Arrays.asList(cars).stream().collect(Collectors.partitioningBy(c -> c.getOilType() == Oil.DIESEL));
		System.out.println("***Partitioning By Oil Type:");
		partitionedCarMap.entrySet().forEach(System.out::println);
	}
	
	
	
	public static void main(String[] args) {
		StreamBasicExample example = new StreamBasicExample();
		example.testCollectorsToList();
		example.testCollectorsToSet();
		example.testCollectorsToMap();
		example.testCollectorsToConcurrentMap();
		example.testCollectorsJoining();
		example.testCollectorsGroupingBy();
		example.testCollectorsMapping();
		example.testCollectorsCollectingAndThen();
		example.testCollectorsCounting();
		example.testCollectorsMinBy();
		example.testCollectorsMaxBy();
		example.testcollectorsSummingDouble();
		example.testCollectorsReducing();
		example.testCollectorsPartitioningBy();
		
		
		
		//Stream.of(new Integer[]{1,2}, new Integer[]{3, 4}, new Integer[]{5, 6}).flatMap(arr -> Arrays.stream(arr)).forEach(System.out::print);
		// !!! Stream.of(new int[]{1,2}, new int[]{3, 4}, new int[]{5, 6}).flatMap(arr -> Arrays.stream(arr)); gives Type mismatch: cannot convert from IntStream to Stream<? extends Object>
		// We can use Stream.of() with an array of Objects to return the stream. Note that it doesn’t support autoboxing, so we can’t pass primitive type array.
	}
}
