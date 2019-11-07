package com.kfir.reputation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ReputationApplication {
	

	public static void main(String[] args) {
		SpringApplication.run(ReputationApplication.class, args);
		System.out.println("Server is up");
		
	}

}
