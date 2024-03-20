package com.rodsussumu.publisher;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PublisherApplication {

	public static void main(String[] args) {
		System.out.printf("teste");
		SpringApplication.run(PublisherApplication.class, args);
	}

}
