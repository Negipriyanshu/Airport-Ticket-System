package com.negi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing  //@CreatedDate and @LastModifiedDate in airline entity
public class AirlineCoreServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AirlineCoreServiceApplication.class, args);
	}

}
