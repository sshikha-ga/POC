package com.ga;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class HelloJpaApplication {
	private static final Logger logger = LoggerFactory
			.getLogger(HelloJpaApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(HelloJpaApplication.class, args);
	}

}
