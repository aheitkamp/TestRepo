package com.kccs.lambda1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.log4j.Logger;
import org.junit.Test;

public class Test1 {
	
	private static Logger LOGGER = Logger.getLogger(Test1.class);
	
	private List<Holder> holders = Arrays.asList(
			new Holder(5, "Al", "Heitkamp", 56),
			new Holder(5, "Al", "Heitkamp", 56),
			new Holder(9, "Audrey", "Lamb Heitkamp", 55),
			new Holder(6, "Shannon", "Marie Heitkamp", 25),
			new Holder(66, "Meghan", "D. Heitkamp", 23)
			);
	
	
	@Test 
	public void testStream() {
		LOGGER.info("First Logged Message");
		List<String> strings = Arrays.asList("A", "B");
		strings.stream().forEach(e -> LOGGER.info(e));
		List<String> l =  strings.stream().map(s -> s + "_X").collect(Collectors.toList());
		LOGGER.info(l);
		List<String> x =  strings.stream().map(s -> s + "_X").filter(s -> s.startsWith("B")).collect(Collectors.toList());
		LOGGER.info(x);
		
		List<Integer> ints = Arrays.asList(2, 4, 8, 1, 99, 7);
		LOGGER.info(ints);
		ints.sort(Comparator.naturalOrder());
		LOGGER.info(ints);
		ints.sort(Comparator.reverseOrder());
		LOGGER.info(ints);
		LOGGER.info(ints.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList()));
		


		LOGGER.info("avg = " + holders.stream().collect(Collectors.averagingInt(Holder::getIntValue)));
		Map<Integer, Holder> map = holders.stream().distinct().collect(Collectors.toMap(Holder::getIntValue, Function.identity()));
		LOGGER.info(map.get(5).getFirstName());
		LOGGER.info(map.get(66).getLastName());
		LOGGER.info(holders.stream().filter(h -> h.getFirstName().startsWith("M")).collect(Collectors.toList()));
		LOGGER.info(holders.stream().filter(h -> h.getAge() > 23 && h.getAge() < 26).collect(Collectors.toList()));

		LOGGER.info(holders.stream().filter(h -> h.getAge() >= 23 && h.getAge() <= 26).count());
		
		LOGGER.info(holders.stream().distinct().count());
		
		Comparator<Holder> byAge = new Comparator<Test1.Holder>() {
			public int compare(Holder o1, Holder o2) {
				return o1.getAge().compareTo(o2.getAge());
			};
		};
		LOGGER.info("dropWhile");
//		holders.stream().sorted(byAge).dropWhile(h -> h.getAge() >= 23 && h.getAge() <= 26).forEach(h -> LOGGER.info(h));
		LOGGER.info("takeWhile");
//		holders.stream().sorted(byAge).takeWhile(h -> h.getAge() >= 23 && h.getAge() <= 26).forEach(h -> LOGGER.info(h));
		
	}
	
	@Test 
	public void testStream1() {
		LOGGER.info(holders.get(0));
		UnaryOperator<Holder> u = (h)-> {h.setAge(h.getAge() + 5); return h;};
		LOGGER.info(u.apply(holders.get(0)));
	}
	
	class Holder {
		Integer intValue;
		String firstName;
		String lastName;
		private Integer age;

		public Holder(int intValue, String firstName, String lastName, Integer age) {
			super();
			this.intValue = intValue;
			this.firstName = firstName;
			this.lastName = lastName;
			this.setAge(age);
		}

		public Integer getIntValue() {
			return intValue;
		}

		public String getFirstName() {
			return firstName;
		}

		public String getLastName() {
			return lastName;
		}

		@Override
		public String toString() {
			return "Holder [intValue=" + intValue + ", firstName=" + firstName + ", lastName=" + lastName + ", age=" + age +"]";
		}

		@Override
		public int hashCode() {
			return new HashCodeBuilder().append(this.getIntValue()).hashCode();
		}

		@Override
		public boolean equals(Object obj) {
			if (obj == this) { return true; }
			if (obj == null) { return false; }
			if (!(obj instanceof Holder)) { return false; }
			Holder that = (Holder) obj;
			return new EqualsBuilder().append(this.intValue, that.intValue).isEquals();
		}

		public Integer getAge() {
			return age;
		}

		public void setAge(Integer age) {
			this.age = age;
		}
	}
	
}
