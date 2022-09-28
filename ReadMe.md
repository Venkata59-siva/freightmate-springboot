Please follow below steps to run the application
1. import as existing maven project into the eclipse/springtoolsuite
2. set the jre as 1.8 in eclipse/springtoolsuite
3. maven clean install the project
mvn clean install
4. FreightmateApplication - open the spring boot main class  from the below path and right click -> run as java application
freightmate\src\main\java\com\example\freightmate\FreightmateApplication.java

@SpringBootApplication
public class FreightmateApplication {

	@Autowired
	private ConsignmentNoteNumberService consignmentNoteNumberService;

	public static void main(String[] args) {
		SpringApplication.run(FreightmateApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {
			ConnoteNumberPojo connoteInput = new ConnoteNumberPojo("FreightmateCourierCo", "123ABC", 10, 19604, 19000,
					20000);
			System.out.println(connoteInput);

			String uniqueConnoteNumber = consignmentNoteNumberService.generateConnoteNumber(connoteInput); // call
																											// Number
			System.out.println("The new Consignment Note Number: " + uniqueConnoteNumber);
		};
	}

}


  output : 
  ConnoteNumberDto {carrierName:'FreightmateCourierCo', accountNumber:'123ABC', digits:10, lastUsedIndex:19604, rangeStart:19000, rangeEnd:20000}
The new Consignment Note Number: FCC123ABC00000196051

junit test cases run:
-------------------
1. right click on the project - Junit test
2. all test cases available in the below path
freightmate\src\test\java\com\example\freightmate\FreightmateApplicationTests.java

package com.example.freightmate;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.freightmate.model.ConnoteNumberPojo;
import com.example.freightmate.service.ConsignmentNoteNumberService;

@SpringBootTest
class FreightmateApplicationTests {
	@Autowired
	private ConsignmentNoteNumberService consignmentNoteNumberService;

	@Test
	void contextLoads() {
	}

	@Test
	void zeroRangeValueFrieghtmateID() {
		ConnoteNumberPojo connoteNumberPojo = new ConnoteNumberPojo("FreightmateCourierCo", "123ABC", 10, 18604, 19000,
				20000);
		String id = consignmentNoteNumberService.generateConnoteNumber(connoteNumberPojo);
		assertEquals("The given last Connote Number was not in Range 19000-20000", id);
	}

	@Test
	void validRangeValueFrieghtmateID() {
		ConnoteNumberPojo connoteInput = new ConnoteNumberPojo("FreightmateCourierCo", "123ABC", 10, 19604, 19000,
				20000);
		String id = consignmentNoteNumberService.generateConnoteNumber(connoteInput);
		assertEquals("FCC123ABC00000196051", id);
	}

	@Test
	void nullRangeValueFrieghtmateID() {
		ConnoteNumberPojo connoteNumberPojo = new ConnoteNumberPojo("FreightmateCourierCo","123ABC",0,0,0,0);
		String id = consignmentNoteNumberService.generateConnoteNumber(connoteNumberPojo);
		assertEquals("The given last Connote Number was not in Range 0-0", id);
	}

}


