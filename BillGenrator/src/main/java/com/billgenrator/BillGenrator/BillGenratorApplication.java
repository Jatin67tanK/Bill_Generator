package com.billgenrator.BillGenrator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BillGenratorApplication {

	public static void main(String[] args) {
		SpringApplication.run(BillGenratorApplication.class, args);
		System.out.println("Started");
	}

}
