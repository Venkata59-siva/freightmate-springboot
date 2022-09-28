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
