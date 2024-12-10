package com.gtp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;


@SpringBootApplication
@EntityScan(basePackages = "com.gtp.models")

public class GtpApplication {

	public static void main(String[] args) {
		SpringApplication.run(GtpApplication.class, args);
	}

}
