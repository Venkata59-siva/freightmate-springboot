package com.example.freightmate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import com.example.freightmate.model.ConnoteNumberPojo;
import com.example.freightmate.service.ConsignmentNoteNumberService;

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
